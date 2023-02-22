package com.devcam.shop24h.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devcam.shop24h.entity.Customers;


public interface CustomersRepository extends JpaRepository<Customers, Integer>{
    
}
