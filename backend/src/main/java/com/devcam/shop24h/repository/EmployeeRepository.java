package com.devcam.shop24h.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devcam.shop24h.entity.Employees;


public interface EmployeeRepository extends JpaRepository<Employees, Long>{
    Employees findByUserName(String userName);
    @Query(value = "select * from employees where username = ?1", nativeQuery = true)
    Employees getEmployeesByUserName(String username);
}
