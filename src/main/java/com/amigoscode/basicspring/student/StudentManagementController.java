package com.amigoscode.basicspring.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("management/api/v1/student")
public class StudentManagementController {

    private final StudentService studentService;

    @Autowired
    public StudentManagementController(StudentService studentService) {
        this.studentService = studentService;
    }

//    hasRole('ROLE_') hasAnyRole('ROLE_') hasAuthority('permission') hasAnyAuthority('permission')

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ADMINTRAINEE')")
    public List<Student> getAllStudent() {
        return studentService.getAllStudent();
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ADMINTRAINEE')")
    public Student getStudent(@PathVariable("id") Long id) {
        return studentService.getStudent(id);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('student:write')")
    public void registerNewStudent(@RequestBody Student student) {
        studentService.addNewStudent(student);
    }

    @DeleteMapping
    @PreAuthorize("hasAuthority('student:write')")
    public void deleteStudent(@RequestBody Student student) {
        studentService.deleteStudent(student);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('student:write')")
    public void updateStudent(
            @RequestBody Student student,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email) {
        studentService.updateStudent(student, name, email);
    }
}
