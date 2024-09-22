package com.enigmacamp.loan_app.service.impl;

import com.enigmacamp.loan_app.entity.Role;
import com.enigmacamp.loan_app.repository.RoleRepository;
import com.enigmacamp.loan_app.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    @Override
    public Role getOrSave(Role role) {
        //JIKA SUDAH ADA ROLE MAKA AKAN DI GET
        Optional<Role> optionalRole = roleRepository.findByRole(role.getRole());
        if (!optionalRole.isEmpty()){
            return optionalRole.get();
        }
        //JIKA BELUM ADA ROLE MAKA AKAN DI CREATE
        return roleRepository.save(role);
    }
}
