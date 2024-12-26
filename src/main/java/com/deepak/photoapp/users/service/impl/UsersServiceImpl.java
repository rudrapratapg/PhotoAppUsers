package com.deepak.photoapp.users.service.impl;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.deepak.photoapp.users.data.UsersRepo;
import com.deepak.photoapp.users.data.entity.UserEntity;
import com.deepak.photoapp.users.service.UsersService;
import com.deepak.photoapp.users.service.dto.UserDTO;

@Service
public class UsersServiceImpl implements UsersService {
	
	UsersRepo usersRepo;
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	public UsersServiceImpl(UsersRepo usersRepo, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.usersRepo = usersRepo;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@Override
	public UserDTO createUser(UserDTO user) {
		user.setUserId(UUID.randomUUID().toString());
		user.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		System.out.println(user.toString());
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		UserEntity userEntity = modelMapper.map(user, UserEntity.class);
		usersRepo.save(userEntity);
		return user;
	}

}
