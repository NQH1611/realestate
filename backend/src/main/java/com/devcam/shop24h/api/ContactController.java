package com.devcam.shop24h.api;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.devcam.shop24h.repository.ContactRepository;
import com.devcam.shop24h.entity.*;

@RestController
@CrossOrigin
public class ContactController {
    @Autowired ContactRepository contactRepository;

    @GetMapping("/contact")
    public ResponseEntity<List<Contact>> getAllContact(){
        List<Contact> lstContact = new ArrayList<Contact>();
        contactRepository.findAll().forEach(lstContact::add);
        if (lstContact.size() != 0)
            return new ResponseEntity<>(lstContact, HttpStatus.OK);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
    @GetMapping("/contact/{id}")
    public ResponseEntity<Contact> getProjectsByID(@PathVariable("id") int id){
        Optional<Contact> lstContact = contactRepository.findById(id);
        if(lstContact.isPresent()) return new ResponseEntity<>(lstContact.get(), HttpStatus.OK);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
    @GetMapping("/contactbystatus/{status}")
    public ResponseEntity<List<Contact>> getAllContact(@PathVariable("status") int status){
        List<Contact> lstContact = new ArrayList<Contact>();
        contactRepository.findByStatus(status).forEach(lstContact::add);
        if (lstContact.size() != 0)
            return new ResponseEntity<>(lstContact, HttpStatus.OK);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
    @PostMapping("/contact")
    public ResponseEntity<Object> createContact(@RequestBody Contact Contact) {
        try {
            Contact newContact = new Contact();
            newContact.setFirstName(Contact.getFirstName());
            newContact.setLastName(Contact.getLastName());
            newContact.setPhoneNumber(Contact.getPhoneNumber());
            newContact.setEmail(Contact.getEmail());
            newContact.setMessage(Contact.getMessage());
            newContact.setProject(Contact.getProject());
            newContact.setStatus(Contact.getStatus());
            Contact saveContact = contactRepository.save(newContact);
            return new ResponseEntity<>(saveContact, HttpStatus.CREATED);
        } catch (Exception e) {
            // TODO: handle exception
            return ResponseEntity.unprocessableEntity()
                    .body("Failed to Create specified Customers: " + e.getCause().getCause().getMessage());
        }
    }
    @PutMapping("/contact/{id}")
    public ResponseEntity<Object> updateContact(@PathVariable("id") int id, @RequestBody Contact Contact) {
        Optional<Contact> contactData = contactRepository.findById(id);
        if (contactData.isPresent()) {
			Contact newContact = contactData.get();
            newContact.setFirstName(Contact.getFirstName());
            newContact.setLastName(Contact.getLastName());
            newContact.setPhoneNumber(Contact.getPhoneNumber());
            newContact.setEmail(Contact.getEmail());
            newContact.setMessage(Contact.getMessage());
            newContact.setProject(Contact.getProject());
            newContact.setStatus(Contact.getStatus());
            Contact saveContact = contactRepository.save(newContact);
            return new ResponseEntity<>(saveContact, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
    }
}
