package com.hai.backend.controller;

import com.hai.backend.entity.RoleEntity;
import com.hai.backend.entity.UserEntity;
import com.hai.backend.modal.ApiReponse;
import com.hai.backend.modal.UserLogin;
import com.hai.backend.modal.UserRegister;
import com.hai.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("http://localhost:3000")
public class AuthController {
    @Autowired
    UserService userService;

    @PostMapping("/register")
    public ApiReponse<UserEntity> register(@RequestBody UserRegister userRegister) {
        if (userService.checkByEmail(userRegister.getEmail())) {
            return new ApiReponse<>(false, null);
        }

        RoleEntity user = new RoleEntity(8, "", "");

        List<RoleEntity> roles = new ArrayList<>();
        roles.add(user);

        UserEntity nguoidung = new UserEntity();
        nguoidung.setRoles(roles);
        nguoidung.setFullname(userRegister.getFullName());
        nguoidung.setEmail(userRegister.getEmail());
        nguoidung.setPassword(userRegister.getPassword());
        nguoidung.setUsername(userRegister.getEmail());
        nguoidung.setStatus(true);
        nguoidung.setCreateDate(new Date());

        return new ApiReponse<>(true, userService.saveUser(nguoidung));
    }

    @PostMapping("/login")
    public ApiReponse<UserEntity> login(@RequestBody UserLogin userLogin) {
        Optional<UserEntity> user = userService.findByEmailAndPassword(userLogin.getEmail(), userLogin.getPassword());

        if(user.isEmpty()){
            return new ApiReponse<>(false, null);
        }else{
            return new ApiReponse<>(true, user.get());
        }
    }
}
