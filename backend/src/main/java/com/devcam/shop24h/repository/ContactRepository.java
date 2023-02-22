package com.devcam.shop24h.repository;
import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;
import com.devcam.shop24h.entity.Contact;

public interface ContactRepository extends JpaRepository<Contact, Integer>{
    List<Contact> findByStatus(int status);
}
