package com.tpe.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import com.tpe.domain.Book;
import com.tpe.domain.Student;
import com.tpe.dto.StudentBookDTO;
import com.tpe.dto.StudentDTO;

@Mapper(componentModel="spring")
public interface StudentMapper {
    
	List<StudentDTO> mapToStudentDTO(List<Student> studentList);
	
	StudentDTO studentToStudentDTO(Student student);
	
	
	List<StudentBookDTO> mapToStudentBookDTOs(List<Student> studentLis);
	
	@Mapping(source="books",target="books",qualifiedByName = "getBookNames")
	StudentBookDTO studentToStudentBookDTO(Student student);
	
	
	@Named("getBookNames")
	public static List<String> getNames(List<Book> books){
		List<String> bookNameList=new ArrayList<>();
		bookNameList=books.stream().map(b->b.getName()).collect(Collectors.toList());
		return bookNameList;
	}
	
}
