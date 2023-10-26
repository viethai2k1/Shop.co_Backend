package com.hai.backend.controller;

import com.hai.backend.entity.RoleEntity;
import com.hai.backend.entity.UserEntity;
import com.hai.backend.modal.ApiReponse;
import com.hai.backend.modal.UserRegister;
import com.hai.backend.modal.UserUpdate;
import com.hai.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.Role;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("")
    public ApiReponse<List<UserEntity>> getAllUser() {
        try {
            return new ApiReponse<>(true, userService.getAllUser());
        }catch (Exception e) {
            return new ApiReponse<>(false, null);
        }
    }

    @GetMapping("/{id}")
    public ApiReponse< UserEntity> getUserById(@PathVariable int id) {
            try {
                return new ApiReponse<>(true, userService.getUserById(id));

            }catch (Exception e) {
                return new ApiReponse<>(false, null);
            }
    }

    @PostMapping("/add")
    public ApiReponse<UserEntity> saveUser(@RequestBody UserEntity user) {
        try {
            user.setUsername(user.getEmail());
            return new ApiReponse<>(true, userService.saveUser(user));
        }catch (Exception e) {
            return new ApiReponse<>(false, null);
        }
    }

    @PutMapping("/update")
    public ApiReponse<UserEntity> updateUser(@RequestBody  UserEntity userEntity) {
        if(userService.check(userEntity.getId())) {
            return new ApiReponse<>(true, userService.updateUser(userEntity));

        }else {
            return new ApiReponse<>(false, null);
        }
    }


    @PutMapping("/userupdate")
    public ApiReponse<UserEntity> userUpdate(@RequestBody UserUpdate user) {
        if(userService.check(user.getId())) {
            return new ApiReponse<>(true, userService.userUpdate(user));
        }else {
            return new ApiReponse<>(false, null);
        }
    }

    @DeleteMapping("{id}")
    public ApiReponse<UserEntity> deleteUser(@PathVariable int id) {
        if(userService.check(id)) {
            UserEntity user = userService.getUserById(id);
            user.setDelete(true);
            userService.updateUser(user);

            return new ApiReponse<>(true, user);
        }else {
            return new ApiReponse<>(false, null);
        }
    }

    @PutMapping("/status/{id}")
    public ApiReponse<UserEntity> updateStatusUser(@PathVariable int id) {
        if(userService.check(id)) {
            UserEntity user = userService.getUserById(id);
            user.setStatus(!user.isStatus());
            userService.updateUser(user);

            return new ApiReponse<>(true, user);
        }else {
            return new ApiReponse<>(false, null);
        }
    }

}
