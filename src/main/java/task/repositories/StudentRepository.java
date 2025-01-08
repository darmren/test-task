package task.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import task.exceptions.GroupNotFoundException;
import task.exceptions.StudentNotFoundException;
import task.models.BirthdayInterval;
import task.models.Sex;
import task.models.Status;
import task.models.Student;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class StudentRepository{
    private final JdbcTemplate jdbc;
    private List<Student> studentsCache;

    public StudentRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
        this.studentsCache = new ArrayList<>();
        updateCache();
    }

    public void storeStudent(Student student) {
        String query = "INSERT INTO students (surname, name, patronymic, group_id, birthday, sex, status) VALUES(?, ?, ?, ?, ?, ?, ?) ";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(query, new String[]{"id"});
            ps.setString(1, student.getSurname());
            ps.setString(2, student.getName());
            ps.setString(3, student.getPatronymic());
            ps.setInt(4, student.getGroupId());
            ps.setDate(5, new Date(student.getBirthday().getTime()));
            ps.setString(6, student.getSex());
            ps.setString(7, student.getStatus());
            return ps;
        }, keyHolder);
        student.setId((Integer) keyHolder.getKeys().get("id"));
        updateCache();
    }

    public List<Student> getAllStudents() {
        return studentsCache;
    }

    public void updateCache() {
        String query = "SELECT * FROM students";
        studentsCache = jdbc.query(query, new StudentRowMapper());
    }

    public Student getStudent(Integer id) {
        for (Student student : studentsCache) {
            if (student.getId().intValue() == id.intValue())
                return student;
        }
        throw new StudentNotFoundException(String.format("Student with id = %d was not found", id));
    }

    public List<Student> findStudents(String surname, String name, String patronymic, String groupName, BirthdayInterval birthdayInterval, String sex, String status, Integer course){
        StringBuilder stringBuilder;
        if (groupName != null | course != null){
            stringBuilder = new StringBuilder("SELECT * FROM students JOIN groups ON students.group_id = groups.id WHERE ");
            if (groupName != null)
                stringBuilder.append(String.format("groups.name = '%s' AND ", groupName));
            if (course != null)
                stringBuilder.append(String.format("EXTRACT(YEAR FROM CURRENT_DATE) - entering_year = %d AND ", course));
        }
        else
            stringBuilder = new StringBuilder("SELECT * FROM students WHERE ");
        if (birthdayInterval.getFrom() != null & birthdayInterval.getUntil() != null){
            stringBuilder.append(String.format("birthday BETWEEN '%s' AND '%s' AND ", birthdayInterval.getFrom(), birthdayInterval.getUntil()));
        }
        if (surname != null)
            stringBuilder.append(String.format("surname = '%s' AND ", surname));
        if (name != null)
            stringBuilder.append(String.format("name = '%s' AND ", name));
        if (patronymic != null)
            stringBuilder.append(String.format("patronymic = '%s' AND ", patronymic));
        if (sex != null)
            stringBuilder.append(String.format("sex = '%s' AND ", Sex.parseSex(sex)));
        if (status != null)
            stringBuilder.append(String.format("name = '%s' AND ", Status.parseStatus(status)));

        var lastIndex = stringBuilder.lastIndexOf(" AND ");
        if (lastIndex != -1) {
            stringBuilder.delete(lastIndex, stringBuilder.length() - 1);
            stringBuilder.append("ORDER BY students.surname, students.name, students.patronymic");
            var query = stringBuilder.toString();
            var students = jdbc.query(query, new StudentRowMapper());
            if (!students.isEmpty())
                return students;
            else throw new StudentNotFoundException("Students with these parameters were not found");
        }
        throw new StudentNotFoundException("Students with these parameters were not found");
    }

    public List<Student> getOneGroupStudents(String name){
        var query = String.format("SELECT * FROM students JOIN groups ON students.group_id = groups.id WHERE groups.name = '%s' ORDER BY students.surname, students.name, students.patronymic", name);
        var students = jdbc.query(query, new StudentRowMapper());
        if (!students.isEmpty())
            return students;
        else throw new GroupNotFoundException(String.format("Group %s was not found", name));
    }

    static class StudentRowMapper implements RowMapper<Student>{
        @Override
        public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
            Student student = new Student();
            student.setId(rs.getInt("id"));
            student.setName(rs.getString("name"));
            student.setSurname(rs.getString("surname"));
            student.setPatronymic(rs.getString("patronymic"));
            student.setGroupId(rs.getInt("group_id"));
            student.setBirthday(rs.getDate("birthday"));
            student.setSex(Sex.parseSex(rs.getString("sex")));
            student.setStatus(Status.parseStatus(rs.getString("status")));
            return student;
        }
    }
}