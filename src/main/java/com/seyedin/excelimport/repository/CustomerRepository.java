package com.seyedin.excelimport.repository;

import com.seyedin.excelimport.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    // find by email
    Optional<Customer> findByEmail(String email);

    // check if a customer exists by idForm
    boolean existsByIdForm(Integer idForm);
}
