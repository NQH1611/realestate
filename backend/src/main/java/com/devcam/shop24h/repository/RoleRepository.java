package com.devcam.shop24h.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devcam.shop24h.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>{
    Role findByRoleKey(String roleKey);
}
