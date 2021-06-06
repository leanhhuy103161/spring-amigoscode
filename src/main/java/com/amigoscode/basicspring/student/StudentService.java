package com.amigoscode.basicspring.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    public List<Student> getAllStudent() {
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentOptional = studentRepository
                .findStudentByEmail(student.getEmail());
        if (studentOptional.isPresent()) {
            throw new IllegalStateException("Email taken");
        }
        System.out.println(student);
        studentRepository.save(student);
    }

    public void deleteStudent(Student student) {
        boolean exists = studentRepository.existsById(student.getId());
        if (!exists) {
            throw new IllegalStateException("student with id:" + student.getId() + " does not exists");
        }
//        System.out.println("student want to delete had id: "+ student.getId());
        studentRepository.deleteById(student.getId());
    }

    @Transactional
    public void updateStudent(Student student, String name, String email) {
        Long id = student.getId();
        Student student1 = studentRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException(
                        "student with id:" + student.getId() + " does not exists"
                ));
        if (name != null && name.length() > 0 && !Objects.equals(student1.getName(), name)) {
            // just set
            student1.setName(name);
        }

        if (email != null && email.length() > 0 && !Objects.equals(student1.getEmail(), email)) {
            Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
            if(studentOptional.isPresent()) {
                throw new IllegalStateException("email taken");
            }
            // just set
            student1.setEmail(email);
        }

//        studentRepository.save(student1);
    }
}
