package com.example.demo.student;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class StudentService {

	private final StudentRepository studentRepository;

	@Autowired
	public StudentService(StudentRepository studentRepository) {
		//Constructor
		this.studentRepository = studentRepository;
	}

    public List<Student> getStudents() {
		//This returns a JSON page instead
		return studentRepository.findAll();
		// return List.of(
		// 	new Student(
		// 		1L,
		// 		"Gengar",
		// 		"gengar@gmail.com",
		// 		LocalDate.of(2000, Month.JANUARY, 5),
		// 		21
		// 	)
		// );
	}

	public void addNewStudent(Student student) {
		//Prints out the POST data
		// System.out.println(student);
		//Check if email exists
		// studentRepository.findStudentByEmail(student.getEmail());
		Optional<Student>studentOptional = studentRepository
					.findStudentByEmail(student.getEmail());
		if (studentOptional.isPresent()){
			throw new IllegalStateException("Email Taken");
		}

		studentRepository.save(student);
	}

	public void deleteStudent(Long studentID) {
		boolean exists = studentRepository.existsById(studentID);
		if (!exists) {
			throw new IllegalStateException("Student with id: " +studentID + " does not exist!" );
		}
		studentRepository.deleteById(studentID);
	}
	
	//My attempt
	// @Transactional
	// public void updateStudent(Long studentID){
	// 	boolean exists = studentRepository.existsById(studentID);
	// 	if (!exists) {
	// 		throw new IllegalStateException("Student with id: " +studentID + " does not exist!" );
	// 	}
	// 	//Access the student
	// 	System.out.println(studentRepository.findStudentById(studentID));
	// }
	@Transactional
	public void updateStudent(Long studentID, String name, String email) {
		boolean exists = studentRepository.existsById(studentID);
		if (!exists) {
			throw new IllegalStateException("Student with id: " +studentID + " does not exist!" );
		} else{
			Student student = studentRepository.findById(studentID).get();
			//Check that name is not empty
			if (name != null && 
				name.length() > 0 && 
				!Objects.equals(student.getName(), name)){
					student.setName(name);
				}
			//Check thta email is not in used
			if (email != null &&
				email.length() > 0 &&
				!Objects.equals(student.getEmail(), email)) {
					Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
					if (studentOptional.isPresent()) {
						throw new IllegalStateException("Email is taken!");
					}
					student.setEmail(email);
				}
		}

	}
	// 	// Student student = studentRepository.findById(studentID);
	// 	Student student = studentRepository.findById(studentID)
	// 		.orElseThrow( () -> new IllegalStateException("Fucker does not exist"));
	// }

}
