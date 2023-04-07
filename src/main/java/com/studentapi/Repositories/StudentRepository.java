package com.studentapi.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.studentapi.Entities.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {

}
