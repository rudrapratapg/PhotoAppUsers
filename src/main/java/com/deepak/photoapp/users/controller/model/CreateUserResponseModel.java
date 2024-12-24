package com.deepak.photoapp.users.controller.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class CreateUserResponseModel {
	private String firstName;
	private String lastName;
    private String email;
    private String userId;
}
