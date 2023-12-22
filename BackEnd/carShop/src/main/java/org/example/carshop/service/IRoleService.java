package org.example.carshop.service;

import org.example.carshop.entity.accout.Role;
import org.example.carshop.entity.accout.RoleName;

import java.util.Optional;

public interface IRoleService {
    Optional<Role> findByName(RoleName name);
}
