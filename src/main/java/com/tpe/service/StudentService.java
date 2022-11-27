package com.tpe.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.tpe.domain.Student;
import com.tpe.dto.StudentBookDTO;
import com.tpe.dto.StudentDTO;


public interface StudentService {
	List<Student> getAllStudent();
	Student findStudentById(Long id);
	void saveStudent(StudentDTO studentDTO);
	void updateStudent(Long id,StudentDTO studentDTO);
	void deleteStudent(Long id);
	boolean existsByEmail(String email);
	
	Page<Student> getStudentPage(Pageable pageable);
	List<Student> findAllEqualsGrade(Integer grade);
	StudentDTO findStudentDTOById(Long id);
	
	
	 List<Student> getStudents();
	 List<StudentDTO> getStudentsDTO();
	 List<StudentBookDTO> getStudentBookDTO();
	 
	 Page<StudentBookDTO> getStudentBookDTOPage(Pageable pageable);
	 
}
