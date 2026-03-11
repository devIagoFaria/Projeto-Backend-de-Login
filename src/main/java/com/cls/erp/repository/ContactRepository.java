package com.cls.erp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cls.erp.model.Contact;

public interface ContactRepository extends JpaRepository<Contact, String> {
    Contact findByOwnerId(String ownerId);
}