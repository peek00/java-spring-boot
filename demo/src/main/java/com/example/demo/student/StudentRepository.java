package com.example.demo.student;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

//Acts as data access layer
//Long because that is the type of ID
//We want to use this inside our service
@Repository
public interface StudentRepository 
    extends JpaRepository<Student, Long>{

    //Transforms this into an SQL segement
    @Query("SELECT s FROM Student s WHERE s.email =?1") //This is JPQL?! Student here refers to class Student
    Optional<Student> findStudentByEmail(String email);

    // //My attempt
    // @Query("SELECT s FROM STUDENT s where s.id =?1")
    // Optional<Student> findStudentById(Long id);

}
