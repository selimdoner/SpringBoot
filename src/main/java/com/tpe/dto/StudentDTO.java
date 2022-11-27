package com.tpe.dto;



import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.tpe.domain.Student;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

	@Getter
	@Setter
	@AllArgsConstructor
	@NoArgsConstructor


	public class StudentDTO {
		
		@NotBlank(message="Please provide First Name")
		@Size(min=4,max=100,message="First Name '${validatedValue}' must be between {min} and {max} chars long")

		private String firstName;
		
		@NotBlank(message="Please provide Last Name")
		@Size(min=1,max=100,message="Last Name '${validatedValue}' must be between {min} and {max} chars long")

		private String lastName;
		
		@NotNull(message="Please provide Grade")
		private Integer grade;
		
		//555-555-5555
		//(555).555.5555
		//555 555 5555
		@Column(length = 14)
		@Pattern(regexp = "^((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$", message = "Please provide valid phone number")
		private String phoneNumber;

		@Email(message="Provide valid email")
		private String email;
		
		
		
		public StudentDTO(Student student) {
			this.firstName=student.getFirstName();
			this.lastName=student.getLastName();
			this.grade=student.getGrade();
			this.phoneNumber=student.getPhoneNumber();
			this.email=student.getEmail();
		}
		
		
		
		
	
}
