package com.example.demo.api;

import com.example.demo.Service.CustomerService;
import com.example.demo.model.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/customers")
public class CustomerResource {

    @Autowired
    private CustomerService customerService;

    Logger logger = LoggerFactory.getLogger(CustomerResource.class);

    @PostMapping
    public Customer addCustomer(@RequestBody Customer customer){

        logger.info("Client has added a new customer: Name - " + customer.getCustomerFirstName() + " " + customer.getCustomerLastName()
                    + ", Email: "+customer.getCustomerEmail());
        return customerService.addCustomer(customer);
    }

    @GetMapping
    public List<Customer> getCustomerId(){

        logger.info("Client has requested for all customers");
        return customerService.getCustomers();
    }

    @GetMapping(value="/{customerId}")
    public Customer getCustomer(@PathVariable("customerId") int customerId){

        logger.info("Client has requested for a customer with id " + customerId);
        return customerService.getCustomer(customerId);
    }

    @PutMapping(value = "/{customerId}")
    public Customer updateCustomer(@PathVariable("customerId") int customerId, @RequestBody Customer customer){
        logger.info("Client has updated a customer(Id: " + customerId +"): Name - "+ customer.getCustomerFirstName() + " " + customer.getCustomerLastName()
                + ", Email: "+customer.getCustomerEmail());
        return customerService.updateCustomer(customerId, customer);
    }

    @DeleteMapping(value="/{customerId}")
    public void deleteCustomer(@PathVariable("customerId") int customerId){
        logger.info("Client has deleted a customer ("+ customerId + ")");
        customerService.deleteCustomer(customerId);
    }

}
