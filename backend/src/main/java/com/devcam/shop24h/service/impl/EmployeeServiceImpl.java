package com.devcam.shop24h.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devcam.shop24h.entity.Employees;
import com.devcam.shop24h.repository.EmployeeRepository;
import com.devcam.shop24h.security.UserPrincipal;
import com.devcam.shop24h.service.EmployeeService;


@Service
public class EmployeeServiceImpl implements EmployeeService{
    @Autowired
    private EmployeeRepository userRepository;

    @Override
    public UserPrincipal findByUserName(String username) {
        Employees user = userRepository.findByUserName(username);
        UserPrincipal userPrincipal = new UserPrincipal();
        if (null != user) {
            Set<String> authorities = new HashSet<>();
            if (null != user.getRoles()) user.getRoles().forEach(r -> {
                authorities.add(r.getRoleKey());
                r.getPermissions().forEach(p -> authorities.add(p.getPermissionKey()));
            });

            userPrincipal.setUserId(user.getId());
            userPrincipal.setUsername(user.getUserName());
            userPrincipal.setPassword(user.getPassword());
            userPrincipal.setAuthorities(authorities);
        }
        return userPrincipal;
    }

    @Override
    public Employees createUser(Employees employees) {
        // TODO Auto-generated method stub
        return userRepository.saveAndFlush(employees);
    }
}
