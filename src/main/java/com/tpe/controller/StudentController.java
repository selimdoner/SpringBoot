package com.tpe.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tpe.domain.Student;
import com.tpe.dto.StudentBookDTO;
import com.tpe.dto.StudentDTO;
import com.tpe.service.StudentService;

@RestController
@RequestMapping("/student")
public class StudentController {

	@Autowired
	private StudentService studentService;

	@GetMapping("/welcome")
	public String welcome(HttpServletRequest httpServletRequest) {
		return "Welcome to Student Controller" + httpServletRequest.getServletPath();
	}

	//http://localhost:8080/student
	/*
	 * {
    "firstName": "Bruce",
    "lastName": "Wayne",
    "email":"bruce@email.com",
    "phoneNumber":"888 555 5555",
    "grade":10
  }
	 */
	
	@PreAuthorize("hasRole('INSTRUCTOR')")
	@PostMapping
	public ResponseEntity<Map<String, String>> createStudent(@Valid @RequestBody StudentDTO studentDTO) {
		studentService.saveStudent(studentDTO);

		Map<String, String> map = new HashMap<>();
		map.put("message", "Student created successfully");
		map.put("success", "true");

		return new ResponseEntity<>(map, HttpStatus.CREATED);
	}

	//http://localhost:8080/student
	@GetMapping
	public ResponseEntity<List<Student>> getAllStudent() {
		List<Student> allStudent = studentService.getAllStudent();
		return ResponseEntity.ok(allStudent);
	}

	//http://localhost:8080/student/query?id=4
	// /query?id=1
	@GetMapping("/query")
	public ResponseEntity<Student> getStudent(@RequestParam("id") Long id) {
		Student student = studentService.findStudentById(id);
		return ResponseEntity.ok(student);
	}
	
	//http://localhost:8080/student/4
	@GetMapping("/{id}")
	public ResponseEntity<Student> getStudentByPath(@PathVariable("id") Long id) {
		Student student = studentService.findStudentById(id);
		return ResponseEntity.ok(student);
	}
	
	//localhost:8080/student/5
	/*
	 *    {
        "firstName": "John1",
        "lastName": "Coffee",
        "grade": 11,
        "phoneNumber": "444 555 5555",
        "email": "john1@email.com"
  }
	 */
	@PutMapping("/{id}")
	public ResponseEntity<Map<String, String>> updateStudent(@PathVariable Long id,@Valid @RequestBody StudentDTO studentDTO){
		
		studentService.updateStudent(id,studentDTO);
		Map<String, String> map = new HashMap<>();
		map.put("message", "Student updated successfully");
		map.put("success", "true");
		return ResponseEntity.ok(map);
	}
	
	//http://localhost:8080/student/4
	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String, String>> deleteStudent(@PathVariable Long id){
	 	studentService.deleteStudent(id);
	 	Map<String, String> map = new HashMap<>();
		map.put("message", "Student deleted successfully");
		map.put("success", "true");
		return ResponseEntity.ok(map);
	}
	
	//http://localhost:8080/student/email?email=john1@email.com
	@GetMapping("/email")
	public ResponseEntity<Map<String, String>> checkEmail(@RequestParam("email") String email){
	 	 Boolean isExist = studentService.existsByEmail(email);
	 	Map<String, String> map = new HashMap<>();
		map.put("isEmailExist", isExist.toString());

		return ResponseEntity.ok(map);
	}
	
	//http://localhost:8080/student/pages?page=1&size=3&sort=lastName&direction=DESC
	@GetMapping("/pages")
	public ResponseEntity<Page<Student>> getStudentPage(@RequestParam("page") int page, @RequestParam("size") int size,
														@RequestParam("sort") String prop,
														@RequestParam(value="direction",required=false,defaultValue="DESC") Direction direction){
		Pageable pageable=PageRequest.of(page, size,Sort.by(direction, prop));
		Page<Student> studentPage = studentService.getStudentPage(pageable);
		return ResponseEntity.ok(studentPage);
	}
	
	//http://localhost:8080/student/grade/11
	@GetMapping("/grade/{grade}")
	public ResponseEntity<List<Student>> getStudentsEqualsGrade(@PathVariable("grade") Integer grade ){
		List<Student> students = studentService.findAllEqualsGrade(grade);
		return ResponseEntity.ok(students);
	}
	
	//http://localhost:8080/student/grade/query?grade=11
	@GetMapping("/grade/query")
	public ResponseEntity<List<Student>> getStudentsEqualsGradeWithQuery(@RequestParam("grade") Integer grade ){
		List<Student> students = studentService.findAllEqualsGrade(grade);
		return ResponseEntity.ok(students);
	}
	
	//http://localhost:8080/student/query/dto?id=7
	@GetMapping("/query/dto")
	public ResponseEntity<StudentDTO> getStudentDTO(@RequestParam("id") Long id) {
		StudentDTO studentDTO = studentService.findStudentDTOById(id);
		return ResponseEntity.ok(studentDTO);
	}
	
	//http://localhost:8080/student/query/list
	@GetMapping("/query/list")	
	public ResponseEntity<List<Student>> getStudents() {
		List<Student> allStudent = studentService.getStudents();
		return ResponseEntity.ok(allStudent);
	}
	
	//http://localhost:8080/student/query/list/dto
	@GetMapping("/query/list/dto")	
	public ResponseEntity<List<StudentDTO>> getStudentsDTO() {
		List<StudentDTO> allStudentDTO = studentService.getStudentsDTO();
		return ResponseEntity.ok(allStudentDTO);
	}
	
	//http://localhost:8080/student/query/studentbook
	@GetMapping("/query/studentbook")	
	public ResponseEntity<List<StudentBookDTO>> getStudentBookDTOs() {
		List<StudentBookDTO> allStudentBookDTO = studentService.getStudentBookDTO();
		return ResponseEntity.ok(allStudentBookDTO);
	}
	
	//http://localhost:8082/student/query/studentbook/pages?page=1&size=2&sort=lastName&direction=DESC
	@GetMapping("/query/studentbook/pages")	
	public ResponseEntity<Page<StudentBookDTO>> getStudentBookDTOPage(@RequestParam("page") int page, @RequestParam("size") int size,
			@RequestParam("sort") String prop,
			@RequestParam(value="direction",required=false,defaultValue="DESC") Direction direction){
		
        Pageable pageable=PageRequest.of(page, size,Sort.by(direction, prop));
        
		Page<StudentBookDTO> studentBookDTOPage = studentService.getStudentBookDTOPage(pageable);
		return ResponseEntity.ok(studentBookDTOPage);
	}
	
}
