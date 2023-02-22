package com.devcam.shop24h.service;

import com.devcam.shop24h.entity.Employees;
import com.devcam.shop24h.security.UserPrincipal;

public interface EmployeeService {
    Employees createUser(Employees employees);

    UserPrincipal findByUserName(String username);
}
