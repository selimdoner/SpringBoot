package com.tpe.domain;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatTypes;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message="Please provide First Name")
	@Size(min=4,max=100,message="First Name '${validatedValue}' must be between {min} and {max} chars long")
	//@NotEmpty//prevent empty but not space
	@Column(length = 100, nullable = false)
	private String firstName;
	
	@NotBlank(message="Please provide Last Name")
	@Size(min=1,max=100,message="Last Name '${validatedValue}' must be between {min} and {max} chars long")
	@Column(length = 100, nullable = false)
	private String lastName;
	
	@NotNull(message="Please provide Grade")
	private Integer grade;
	
	//555-555-5555
	//(555).555.5555
	//555 555 5555
	@Column(length = 14)
	@Pattern(regexp = "^((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$", message = "Please provide valid phone number")
	private String phoneNumber;
	
	@Column(length = 100, nullable = false, unique = true)
	@Email(message="Provide valid email")
	private String email;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING,pattern="MM/dd/yyyy HH:mm:ss")
	@Setter(AccessLevel.NONE)
	private LocalDateTime createdAt=LocalDateTime.now();
	
	@OneToMany(mappedBy ="student")
	private List<Book> books;

}
