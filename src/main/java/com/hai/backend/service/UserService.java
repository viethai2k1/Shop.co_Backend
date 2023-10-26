package com.hai.backend.service;

import com.hai.backend.entity.UserEntity;
import com.hai.backend.modal.UserUpdate;

import java.util.List;
import java.util.Optional;

public interface UserService  {
    List<UserEntity> getAllUser();

    UserEntity getUserById(int id);

    UserEntity saveUser(UserEntity user);

    UserEntity updateUser(UserEntity userEntity);


    UserEntity userUpdate(UserUpdate user);

    Boolean check(int id);

    Boolean checkByEmail(String email);


    Optional<UserEntity> findByEmailAndPassword(String email, String password);

}
