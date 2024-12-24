package com.deepak.photoapp.users.service.impl;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deepak.photoapp.users.data.UsersRepo;
import com.deepak.photoapp.users.data.entity.UserEntity;
import com.deepak.photoapp.users.service.UsersService;
import com.deepak.photoapp.users.service.dto.UserDTO;

@Service
public class UsersServiceImpl implements UsersService {
	
	UsersRepo usersRepo;
	
	@Autowired
	public UsersServiceImpl(UsersRepo usersRepo) {
		this.usersRepo = usersRepo;
	}

	@Override
	public UserDTO createUser(UserDTO user) {
		user.setUserId(UUID.randomUUID().toString());
		System.out.println(user.toString());
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		UserEntity userEntity = modelMapper.map(user, UserEntity.class);
		userEntity.setEncryptedPassword("34fg37f6t48f7y459g5h489tu45tg94");
		usersRepo.save(userEntity);
		return user;
	}

}
