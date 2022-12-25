package com.example.demo.student;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="api/v1/student")
public class StudentController {

    private final StudentService studentService;

    @Autowired //Saying this studentService above should be automatically wired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    //Get
    @GetMapping() //Root page
    public List<Student> getStudents() {
        return studentService.getStudents();
    }

    //Post
    @PostMapping
    // URL is http://localhost:8080/api/v1/student?
    public void registerNewStudent(@RequestBody Student student){
        //@RequestBody takes the json body and parses it into student?
        studentService.addNewStudent(student);
    }
    
    @DeleteMapping(path = "{studentID}")
    // /http://localhost:8080/api/v1/student/1, where 1 is the id
    public void deleteStudent(@PathVariable("studentID") Long studentID){
        //Above takes in the URL from the path, assigns it to variable id
        studentService.deleteStudent(studentID);
    }

    //My failed attempt
    // @PutMapping
    // public void updateStudent(@PathVariable("studentID") Long studentID) {
    //     //Takes in the student ID and updates a prefixed variable
    //     studentService.updateStudent(studentID);
    // }

    @PutMapping(path = "{studentID}")
    public void updateStudent(
        @PathVariable("studentID") Long studentID,
        @RequestParam(required = false) String name,
        @RequestParam(required = false) String email ) {
            studentService.updateStudent(studentID, name, email);
        }
    
}
