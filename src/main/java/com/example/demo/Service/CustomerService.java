package com.example.demo.Service;

import com.example.demo.dao.CustomerDAO;
import com.example.demo.exception.CustomerNotFoundException;
import com.example.demo.model.Customer;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component

public class CustomerService {

    @Autowired
    private CustomerDAO customerDAO;
    
    public Customer addCustomer(Customer customer){

        customer.setCreatedDate(new DateTime().toString("MM/dd/yyyy HH:mm"));
        return customerDAO.save(customer);
    }

    public List<Customer> getCustomers(){

        return customerDAO.findAll();
    }

    public Customer getCustomer(int customerId){

        Optional<Customer> optionalCustomer = customerDAO.findById(customerId);
        if(!optionalCustomer.isPresent()){
            throw new CustomerNotFoundException("Customer Record is not available");
        }
        return optionalCustomer.get();
    }

    public Customer updateCustomer(int customerId, Customer customer){

        String str = ((customerDAO.findById(customerId)).get()).getCreatedDate();
        customer.setCreatedDate(str);
        Customer customer1 = customerDAO.save(customer);
        return customer1;
    }


    public void deleteCustomer(int customerId){

        customerDAO.deleteById(customerId);
    }

}
