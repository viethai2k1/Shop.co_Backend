package com.hai.backend.service.serviceimpl;

import com.hai.backend.entity.RoleEntity;
import com.hai.backend.repository.RoleRepository;
import com.hai.backend.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<RoleEntity> getAllRole() {
        return roleRepository.findAll();
    }
}
