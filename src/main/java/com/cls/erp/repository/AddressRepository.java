package com.cls.erp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cls.erp.model.Address;

public interface AddressRepository extends JpaRepository<Address, String> {
    Address findByOwnerId(String ownerId);
}
