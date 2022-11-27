package com.tpe.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.tpe.domain.Student;
import com.tpe.dto.StudentBookDTO;
import com.tpe.dto.StudentDTO;
import com.tpe.exception.ConflictException;
import com.tpe.exception.ResourceNotFoundException;
import com.tpe.mapper.StudentMapper;
import com.tpe.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private StudentMapper studentMapper;
	
	@Override
	public List<Student> getAllStudent() {
		return studentRepository.findAll();
	}

	@Override
	public Student findStudentById(Long id) {
		return studentRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Student not found with id:"+id));
	}

	@Override
	public void saveStudent(StudentDTO studentDTO) {
		 boolean isExist = existsByEmail(studentDTO.getEmail());
		
		 if(isExist) {
			 throw new ConflictException("Email already exist:"+studentDTO.getEmail());
		 }
		 
		 Student student=new Student();
		 
		 student.setFirstName(studentDTO.getFirstName());
		 student.setLastName(studentDTO.getLastName());
		 student.setGrade(studentDTO.getGrade());
		 student.setPhoneNumber(studentDTO.getPhoneNumber());
		 student.setEmail(studentDTO.getEmail());
		 
		
		studentRepository.save(student);
		
	}

	@Override
	public void updateStudent(Long id,StudentDTO studentDTO) {
         Student foundStudent= findStudentById(id);
		 
		 boolean isExist = existsByEmail(studentDTO.getEmail());
		 
		 if(isExist&&!studentDTO.getEmail().equals(foundStudent.getEmail())) {
			 throw new ConflictException("Email already exist:"+studentDTO.getEmail());
		 }

		 
		 foundStudent.setFirstName(studentDTO.getFirstName());
		 foundStudent.setLastName(studentDTO.getLastName());
		 foundStudent.setGrade(studentDTO.getGrade());
		 foundStudent.setPhoneNumber(studentDTO.getPhoneNumber());
		 foundStudent.setEmail(studentDTO.getEmail());
		
		 studentRepository.save(foundStudent);
	}

	@Override
	public void deleteStudent(Long id) {
		Student foundStudent= findStudentById(id);
		studentRepository.delete(foundStudent);
	}

	@Override
	public boolean existsByEmail(String email) {
		return studentRepository.existsByEmail(email);
	}

	@Override
	public Page<Student> getStudentPage(Pageable pageable) {
		return studentRepository.findAll(pageable);
	}

	@Override
	public List<Student> findAllEqualsGrade(Integer grade) {
		//return studentRepository.findAllEqualsGrade(grade);
		return studentRepository.findAllEqualsGradeWithSQL(grade);
	}

	@Override
	public StudentDTO findStudentDTOById(Long id) {
		return studentRepository.findStudentDTOById(id).orElseThrow(()->new ResourceNotFoundException("Student not found with id:"+id));
	}

	@Override
	public List<Student> getStudents() {
		return studentRepository.findAllStudent();
	}

	@Override
	public List<StudentDTO> getStudentsDTO() {
		List<Student> studentList= studentRepository.findAllStudent();
		return studentMapper.mapToStudentDTO(studentList);
	}

	@Override
	public List<StudentBookDTO> getStudentBookDTO() {
		List<Student> studentList= studentRepository.findAll();
		return studentMapper.mapToStudentBookDTOs(studentList);
	}

	@Override
	public Page<StudentBookDTO> getStudentBookDTOPage(Pageable pageable) {
		Page<Student> studentPage = studentRepository.findAll(pageable);
		Page<StudentBookDTO> dtoPage= studentPage.map(new java.util.function.Function<Student, StudentBookDTO>() {
            
			@Override       
			public StudentBookDTO apply(Student student) {
			      return studentMapper.studentToStudentBookDTO(student);	
			};
		
		});
		
		return dtoPage;
	}

}
