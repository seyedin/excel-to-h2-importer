package com.seyedin.excelimport.service;

import com.seyedin.excelimport.model.Customer;
import com.seyedin.excelimport.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    /**
     * Save a list of customers in the database
     *
     * @param customers List<Customer> parsed from Excel
     * @return number of saved records
     */
    @Override
    public int saveAll(List<Customer> customers) {
        // Customers that are valid for saving
        List<Customer> validCustomers = customers.stream()
                .filter(customer ->
                        !customerRepository.existsByIdForm(customer.getIdForm()) &&
                                customerRepository.findByEmail(customer.getEmail()).isEmpty()
                )
                .peek(customer -> log.info("Saving customer: {}", customer.getEmail()))
                .toList();

        // Save valid customers in bulk
        customerRepository.saveAll(validCustomers);

        // Log customers that were skipped due to duplication
        customers.stream()
                .filter(customer ->
                        customerRepository.existsByIdForm(customer.getIdForm()) ||
                                customerRepository.findByEmail(customer.getEmail()).isPresent()
                )
                .forEach(customer -> log.warn("Skipped duplicate customer: {}", customer.getEmail()));

        return validCustomers.size();
    }

    /**
     * Retrieve all customers from the database
     */
    @Override
    public List<Customer> getAll() {
        return customerRepository.findAll();
    }
}


/*
summery:
List<Customer> all --> Stream
                ├── .filter(valid)
                ├── .peek(log valid)
                ├── .toList() → saveAll

                └── .filter(duplicate)   ️
                └── .forEach(log warn)   ️
*/