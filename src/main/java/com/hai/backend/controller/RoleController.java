package com.hai.backend.controller;

import com.hai.backend.entity.RoleEntity;
import com.hai.backend.modal.ApiReponse;
import com.hai.backend.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("api/roles")
public class RoleController {
    @Autowired
    RoleService roleService;

    @GetMapping("")
    public ApiReponse<List<RoleEntity>> getAllRole() {
        try {
            return new ApiReponse<>(true, roleService.getAllRole());

        }catch (Exception e) {
            return new ApiReponse<>(false, null);
        }
    }
}
