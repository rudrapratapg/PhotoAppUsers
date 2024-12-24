package com.deepak.photoapp.users.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deepak.photoapp.users.controller.model.CreateUserRequestModel;
import com.deepak.photoapp.users.controller.model.CreateUserResponseModel;
import com.deepak.photoapp.users.service.UsersService;
import com.deepak.photoapp.users.service.dto.UserDTO;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UsersController {

	@Autowired
	private Environment env;
	
	@Autowired
	UsersService usersService;
	
	@GetMapping("/status/check")
	public String status() {
		return "Working on "+env.getProperty("local.server.port");
	}
	
	@PostMapping
	public ResponseEntity<CreateUserResponseModel> createUser(@RequestBody @Valid CreateUserRequestModel user) {
		try {
			ModelMapper mapper = new ModelMapper();
			UserDTO userDTO = mapper.map(user, UserDTO.class);
			UserDTO createdUser = usersService.createUser(userDTO);
			CreateUserResponseModel response = mapper.map(createdUser, CreateUserResponseModel.class);
	        return new ResponseEntity<>(response, HttpStatus.CREATED);
		} catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}