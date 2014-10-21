package com.baldurtech.contact;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.ArrayList;

@Service
public class ContactService {

    ContactRepository contactRepository;
    
    @Autowired
    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }
    
    public List<Contact> getList() {

        return contactRepository.findAll();
    }
    
    public Contact show(Long id) {
        return contactRepository.getById(id);
    }
    
    public void save(Contact contact) {
        contactRepository.save(contact);
    }
    
    public Contact update(Contact contact) {
        return contactRepository.update(contact);
    }
}