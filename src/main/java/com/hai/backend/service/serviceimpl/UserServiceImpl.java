package com.hai.backend.service.serviceimpl;

import com.hai.backend.entity.UserEntity;
import com.hai.backend.modal.UserUpdate;
import com.hai.backend.repository.UserRepository;
import com.hai.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserEntity> getAllUser() {
        return userRepository.findAllByIsDeleteFalse();
    }

    @Override
    public UserEntity getUserById(int id) {
        return userRepository.findById(id).get();
    }

    @Override
    public UserEntity saveUser(UserEntity user) {

        return userRepository.save(user);
    }

    @Override
    public UserEntity updateUser(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }

    @Override
    public UserEntity userUpdate(UserUpdate userModal) {
        UserEntity userEntity = userRepository.findById(userModal.getId()).get();

        userEntity.setFullname(userModal.getFullName());
        userEntity.setEmail(userModal.getEmail());
        userEntity.setPhonenumber(userModal.getPhone());

        userRepository.save(userEntity);

        return userEntity;
    }

    @Override
    public Boolean check(int id) {
        return userRepository.existsById(id);
    }

    @Override
    public Boolean checkByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public Optional<UserEntity> findByEmailAndPassword(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }

}
