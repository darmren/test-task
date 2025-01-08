package task.controllers;

import org.springframework.web.bind.annotation.*;
import task.dto.StoreStudentRequest;
import task.models.BirthdayInterval;
import task.models.Student;
import task.repositories.StudentRepository;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    private final StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @PostMapping
    public void storeStudent(@RequestBody StoreStudentRequest storeStudentRequest) {
        var student = new Student(
                storeStudentRequest.getSurname(),
                storeStudentRequest.getName(),
                storeStudentRequest.getPatronymic(),
                storeStudentRequest.getGroupId(),
                storeStudentRequest.getBirthday(),
                storeStudentRequest.getSex(),
                storeStudentRequest.getStatus());
        studentRepository.storeStudent(student);
    }

    @GetMapping
    public List<Student> getAllStudents() {
        return studentRepository.getAllStudents();
    }

    @GetMapping("/{id}")
    public Student getStudent(@PathVariable Integer id) {
        return studentRepository.getStudent(id);
    }

    @GetMapping("/groups/{name}")
    public List<Student> getOneGroupStudents(@PathVariable String name) {
        return studentRepository.getOneGroupStudents(name);
    }

    @GetMapping("/search")
    public List<Student> findStudents(@RequestParam(required = false) String surname,
                                      @RequestParam(required = false) String name,
                                      @RequestParam(required = false) String patronymic,
                                      @RequestParam(required = false) String groupName,
                                      @RequestParam(required = false) String birthdayFrom,
                                      @RequestParam(required = false) String birthdayUntil,
                                      @RequestParam(required = false) String sex,
                                      @RequestParam(required = false) String status,
                                      @RequestParam(required = false) Integer course) {
        return studentRepository.findStudents(surname, name, patronymic, groupName, new BirthdayInterval(birthdayFrom, birthdayUntil), sex, status, course);
    }
}
