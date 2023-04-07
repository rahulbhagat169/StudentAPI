package com.studentapi.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.studentapi.Entities.Student;
import com.studentapi.Services.StudentService;

//Rest controller is the combination of Controller and ResponseBody annotation
//Here i have used ResponseEntity to send the status code , header and body if any
@RestController()
public class StudentCotnroller {
	
	@Autowired
	StudentService studentService;
	
	@GetMapping("/getstudents")
	public ResponseEntity<List<Student>> getStudent(){
		try {
			List<Student> students = studentService.getAllStudents();
			return new ResponseEntity<List<Student>>(students,HttpStatus.FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/getstudents/{id}")
	public ResponseEntity<Student> getStudentById(@PathVariable("id") int id) {
		try {
			Student student=studentService.getStudentbyId(id);
			return new ResponseEntity<Student>(student,HttpStatus.FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/getstudents/studentname")
	public ResponseEntity<Student> getStudentByName(@RequestParam("name") String name) {
		try {
			Student studentbyName = studentService.getStudentbyName(name);
			return new ResponseEntity<Student>(studentbyName,HttpStatus.FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/addstudent")
	public Student addStudent(@RequestBody Student student) {
		studentService.addUpdateStudent(student);
		return student;
	}
	
	@PutMapping("/updatestudent/{id}")
	public ResponseEntity<Student> updateStudent(@PathVariable("id") int id,@RequestBody Student student) {
		
		try {
			Student existStudent = studentService.getStudentbyId(id);
			
			
			existStudent.setFirstName(student.getFirstName());
			existStudent.setLastName(student.getLastName());
			existStudent.setRollNo(student.getRollNo());
			

			Student updated_student = studentService.addUpdateStudent(existStudent);
		
			return new ResponseEntity<Student>(updated_student,HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
	}
	
	@DeleteMapping("/deletestudent/{id}")
	public ResponseEntity<Student> deleteStudent(@PathVariable("id") int id) {
		Student student=null;
		try {
			student=studentService.getStudentbyId(id);
			studentService.deleteStudent(student);
			return new ResponseEntity<Student>(student,HttpStatus.FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
