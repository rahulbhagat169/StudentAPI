package com.studentapi.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studentapi.Entities.Student;
import com.studentapi.Repositories.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	StudentRepository studentRepo;
	
	
	@Override
	public List<Student> getAllStudents() {
		List<Student> findAll = studentRepo.findAll();
		return findAll;
	}


	@Override
	public Student getStudentbyId(int id) {
		//there are two methods that i use to retrieve data from database
		
		//first:-
//		Optional<Student> student = studentRepo.findById(id);
//		return student.get();
		
		//second:-
		List<Student> students = studentRepo.findAll();
		
		Student student = null;
		
		for(Student stud : students) {
			if(stud.getId()==id) {
				student = stud;
			}
		}
		return student;
	}


	@Override
	public Student getStudentbyName(String studName) {
		List<Student> students = studentRepo.findAll();
		
	// the below statement will return one list containing one value only using stream api	
//		List<Student> student = students.stream().
//								filter(name->name.getFirstName().equals(studName)).
//								collect(Collectors.toList());
			
			Student student =null;
			
			
			for(Student stud: students) {
				if(stud.getFirstName().equals(studName)) {
					student = stud;
				}
			}
			
			return student;
	}


	
	@Override
	public Student addUpdateStudent(Student student) {
		
		studentRepo.save(student);
		return student;
		
	}
	
	

	@Override
	public void deleteStudent(Student student) {
		studentRepo.delete(student);
	}


	
}
