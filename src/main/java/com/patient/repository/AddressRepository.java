package com.patient.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.patient.entity.Address;
/**
 * The repository interface for the Address domain object
 */
@Repository
public interface AddressRepository extends JpaRepository<Address, String> {
}