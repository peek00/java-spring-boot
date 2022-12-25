package com.example.demo.student;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentConfig {
    
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return args -> {
			Student gengar =  new Student(
				"Gengar",
				"gengar@gmail.com",
				LocalDate.of(2000, Month.JANUARY, 5)
				
			);
			Student pikachu =  new Student(
				"Pikachu",
				"pikachu@gmail.com",
				LocalDate.of(2000, Month.JANUARY, 5)
				
			);
            //Saving to db
            repository.saveAll(
                List.of(gengar, pikachu)
                );
	
        };
    }
}
