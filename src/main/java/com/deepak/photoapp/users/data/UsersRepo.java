package com.deepak.photoapp.users.data;

import org.springframework.data.repository.CrudRepository;

import com.deepak.photoapp.users.data.entity.UserEntity;

public interface UsersRepo extends CrudRepository<UserEntity, Long> {

}
