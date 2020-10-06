package com.example.demo.dao;

import com.example.demo.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerDAO extends MongoRepository<Customer,Integer> {

    @Override
    List<Customer> findAll();

}
