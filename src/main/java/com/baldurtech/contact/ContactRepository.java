package com.baldurtech.contact;

import java.util.List;
import java.util.ArrayList;

import javax.persistence.*;
import javax.inject.Inject;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;

@Repository
@Transactional
public class ContactRepository {
    @PersistenceContext
    EntityManager entityManager;
    
    public List<Contact> findAll() {
        try {
            return entityManager.createNamedQuery(Contact.FIND_ALL, Contact.class)
                .getResultList();
        } catch (PersistenceException e) {
			return null;
		}
    }
    
    public Contact getById(Long id) {
		try {
            return (Contact)entityManager.createNamedQuery(Contact.GET_BY_ID, Contact.class)
                .setParameter("id", id)
                .getSingleResult();
        } catch (PersistenceException e) {
			return null;
		}
    }
    
    @Transactional
    public void save(Contact contact) {
		entityManager.persist(contact);
    }
    
    public Contact update(Contact contact) {
        return entityManager.merge(contact);
    }
    
    public void delete(Contact contact) {
        contact = entityManager.find(Contact.class, contact.getId());
        entityManager.remove(contact);
    }
}