package com.tpe.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tpe.domain.Student;
import com.tpe.dto.StudentDTO;

//Repository
//CrudRepository
//PagingAndSortingRepository,QueryByExampleExecutor
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
	
	
	@Query("SELECT s from Student s WHERE s.grade=:pGrade")
	List<Student> findAllEqualsGrade(@Param("pGrade") Integer grade);
	
	@Query(value="SELECT * from student s WHERE s.grade=:pGrade",nativeQuery=true)
	List<Student> findAllEqualsGradeWithSQL(@Param("pGrade") Integer grade);
	
	@Query("SELECT new com.tpe.dto.StudentDTO(s) FROM Student s WHERE s.id=:id")
	Optional<StudentDTO> findStudentDTOById(@Param("id") Long id);
	
	@Query("FROM Student")
	List<Student> findAllStudent();
	
	//Entity Graph ise, bir Entity nesnesinin sahip olduğu veri alanlarının veritabanından çekeceği verileri ne zaman çekeceği ile ilgili özel bir konudur. Entity Graph yapısı, JPA çekim stratejisini (fetch strategy) 
	//yapılandırmaya olanak sunan bir özelliktir
	@EntityGraph(attributePaths = {"books"})
	List<Student> findAll();
	
	
	Boolean existsByEmail(String email);
	
	List<Student> findByFirstName(String name);
	
	List<Student> queryByFirstName(String name);
	
	List<Student> getByFirstName(String name);
	
	List<Student> findByFirstNameLike(String firstName);
	
	List<Student> findByFirstNameOrderByFirstName(String name);
	
	List<Student> findByFirstNameOrderByFirstNameAsc(String name);
	
	//%param
	List<Student> findByFirstNameStartingWith(String name);
	
	
//	findByFirstNameAndLastName
//	findByFirstNameContainingIgnoreCase
//	findByLastNameContainsOrderByLastNameAsc
//	findFirst5ByTitleOrderByTitleAsc
	
	

}
