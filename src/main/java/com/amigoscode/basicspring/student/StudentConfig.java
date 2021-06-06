package com.amigoscode.basicspring.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository) {
        return args -> {
            Student marian = new Student(
                    "Marian",
                    "mariam.jamal@gmail.com",
                    LocalDate.of(2000, Month.JANUARY, 5)
            );

            Student alex = new Student(
                    "Alex",
                    "alex.jamal@gmail.com",
                    LocalDate.of(2002, Month.JANUARY, 7)
            );

            Student joss = new Student(
                    "Joss",
                    "josstice@gmail.com",
                    LocalDate.of(2012, Month.FEBRUARY, 15)
            );

            repository.saveAll(
                    List.of(marian, alex, joss)
            );
        };
    }
}
