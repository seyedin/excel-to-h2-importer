package com.seyedin.excelimport.service;

import com.seyedin.excelimport.model.Customer;
import java.util.List;

public interface CustomerService {
    int saveAll(List<Customer> customers);
    List<Customer> getAll();
}