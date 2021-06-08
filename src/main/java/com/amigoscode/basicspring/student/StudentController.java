package com.amigoscode.basicspring.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/student")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getAllStudent() {
        return studentService.getAllStudent();
    }

    @GetMapping("{id}")
    public Student getStudent(@PathVariable("id") Long id) {
        return studentService.getStudent(id);
    }

    @PostMapping
    public void registerNewStudent(@RequestBody Student student) {
        studentService.addNewStudent(student);
    }

    @DeleteMapping
    public void deleteStudent(@RequestBody Student student) {
        studentService.deleteStudent(student);
    }

    @PutMapping
    public void updateStudent(
            @RequestBody Student student,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email) {
        studentService.updateStudent(student, name, email);
    }
}
