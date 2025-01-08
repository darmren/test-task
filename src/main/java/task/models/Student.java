package task.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "students")
public class Student {
    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "students_id_gen")
//    @SequenceGenerator(name = "students_id_gen", sequenceName = "students_id_seq")
    private Integer id;

    @NotNull
    private String surname;

    @NotNull
    private String name;
    private String patronymic;

    @NotNull
    private Integer groupId;

    @NotNull
    private Date birthday;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Sex sex;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Status status;

    public Student(){}

    public Student(String surname, String name, String patronymic, Integer groupId, Date birthday, Sex sex, Status status) {
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.groupId = groupId;
        this.birthday = birthday;
        this.sex = sex;
        this.status = status;
    }

    public String getSex(){
        return sex.toString();
    }

    public String getStatus(){
        return status.toString();
    }
}