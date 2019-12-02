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

    public RoleName findRoleName(String name) {
        RoleName roleName = null;
        switch (name) {
            case "ROLE_USER":
                roleName = RoleName.ROLE_USER;
                break;
            case "ROLE_ADMIN":
                roleName = RoleName.ROLE_ADMIN;
                break;
            case "ROLE_INSTRUCTOR":
                roleName = RoleName.ROLE_INSTRUCTOR;
                break;
        }
        return roleName;
    }

}
