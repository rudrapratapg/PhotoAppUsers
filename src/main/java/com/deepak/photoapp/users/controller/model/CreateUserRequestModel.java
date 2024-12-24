package com.deepak.photoapp.users.controller.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class CreateUserRequestModel {

	@NotNull(message = "First name can not be empty")
    @Size(min = 2, message = "First Name must not be less than 2 characters")
	private String firstName;
	
	@NotNull(message = "Last name can not be empty")
    @Size(min = 2, message = "Last Name must not be less than 2 characters")
	private String lastName;
	
    @NotNull(message = "Email can not be empty")
    @Email
    private String email;
    
    @NotNull(message = "Password can not be empty")
    @Size(min = 8, max = 16, message = "Password should be greater than 8 and less than 16 characters")
    private String password;
}
