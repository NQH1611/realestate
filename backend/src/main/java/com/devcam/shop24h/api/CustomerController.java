package com.devcam.shop24h.api;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.devcam.shop24h.entity.Customers;
import com.devcam.shop24h.repository.CustomersRepository;


@RestController
@CrossOrigin
public class CustomerController {
    @Autowired
    CustomersRepository gCustomersRepository;

    @GetMapping("/customers")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<List<Customers>> getAllCustomers() {
        List<Customers> lstCustomers = new ArrayList<Customers>();
        gCustomersRepository.findAll().forEach(lstCustomers::add);
        if (lstCustomers.size() != 0)
            return new ResponseEntity<>(lstCustomers, HttpStatus.OK);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    @GetMapping("/customers/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<Customers> getCustomerById(@PathVariable("id") int id) {
        Optional<Customers> customers = gCustomersRepository.findById(id);
        if (customers.isPresent())
            return new ResponseEntity<>(customers.get(), HttpStatus.OK);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    @PostMapping("/customers")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_HOMESELLER')")
    public ResponseEntity<Object> createCustomer(@RequestBody Customers customers) {
        try {
            Customers newCustomers = new Customers();
            newCustomers.setContactName(customers.getContactName());
            newCustomers.setContactTitle(customers.getContactTitle());
            newCustomers.setAddress(customers.getAddress());
            newCustomers.setMobile(customers.getMobile());
            newCustomers.setEmail(customers.getEmail());
            newCustomers.setNote(customers.getNote());
            newCustomers.setCreateBy(customers.getCreateBy());
            newCustomers.setCreateDate(new Date());
            Customers saveCustomers = gCustomersRepository.save(newCustomers);
            return new ResponseEntity<>(saveCustomers, HttpStatus.CREATED);
        } catch (Exception e) {
            // TODO: handle exception
            return ResponseEntity.unprocessableEntity()
                    .body("Failed to Create specified Customers: " + e.getCause().getCause().getMessage());
        }
    }

    @PutMapping("/customers/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_HOMESELLER')")
    public ResponseEntity<Object> updateCustomers(@PathVariable("id") int id, @RequestBody Customers customers) {
        try {
            Optional<Customers> resulCustomers = gCustomersRepository.findById(id);
            if (resulCustomers.isPresent()) {
                Customers newCustomers = resulCustomers.get();
                newCustomers.setContactName(customers.getContactName());
                newCustomers.setContactTitle(customers.getContactTitle());
                newCustomers.setAddress(customers.getAddress());
                newCustomers.setMobile(customers.getMobile());
                newCustomers.setEmail(customers.getEmail());
                newCustomers.setNote(customers.getNote());
                newCustomers.setUpdateBy(customers.getUpdateBy());
                newCustomers.setUpdateDate(new Date());
                Customers saveCustomers = gCustomersRepository.save(newCustomers);
                return new ResponseEntity<>(saveCustomers, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            // TODO: handle exception
            return ResponseEntity.unprocessableEntity()
                    .body("Failed to Update specified Customers: " + e.getCause().getCause().getMessage());
        }
    }

    @DeleteMapping("/customers/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    private ResponseEntity<Object> deleteCustomersById(@PathVariable Integer id) {
        Optional<Customers> vCustomersData = gCustomersRepository.findById(id);
        if (vCustomersData.isPresent()) {
            try {
                gCustomersRepository.deleteById(id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } catch (Exception e) {
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            Customers vCustomersNull = new Customers();
            return new ResponseEntity<>(vCustomersNull, HttpStatus.NOT_FOUND);
        }
    }
}
