package com.konradzadroga.drivingschool.rest_api.role;

import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class RoleService {

    private RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role findRoleByName(RoleName name) {
        Role role = roleRepository.findByName(name).orElseThrow(
                () -> new NoSuchElementException("Role not found")
        );

        return role;
    }

}
