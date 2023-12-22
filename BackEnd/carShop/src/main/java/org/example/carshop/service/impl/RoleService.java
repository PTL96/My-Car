package org.example.carshop.service.impl;

import org.example.carshop.entity.accout.Role;
import org.example.carshop.entity.accout.RoleName;
import org.example.carshop.service.IRoleService;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class RoleService implements IRoleService {
    @Override
    public Optional<Role> findByName(RoleName name) {
        return Optional.empty();
    }
}
