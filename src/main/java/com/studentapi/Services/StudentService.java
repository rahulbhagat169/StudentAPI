package com.studentapi.Services;

import java.util.List;

import com.studentapi.Entities.Student;

//Here , By creating the interface of StudentService i have used abstraction and 
//polymorphism
public interface StudentService {

	List<Student> getAllStudents();

	public Student getStudentbyId(int id);

	public Student getStudentbyName(String name);

	public Student addUpdateStudent(Student student);

	public void deleteStudent(Student student);

}
