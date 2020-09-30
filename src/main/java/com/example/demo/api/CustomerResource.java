package com.example.demo.api;

import com.example.demo.Service.CustomerService;
import com.example.demo.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/customers")
public class CustomerResource {

    @Autowired
    private CustomerService customerService;

    @PostMapping
    public Customer addCustomer(@Valid  @RequestBody Customer customer){
        customer.setCustomerFirstName(customer.getCustomerFirstName().toLowerCase());
        customer.setCustomerLastName(customer.getCustomerLastName().toLowerCase());
        customer.setCustomerEmail(customer.getCustomerEmail().toLowerCase());
        return customerService.addCustomer(customer);
    }

    @GetMapping
    public List<Customer> getCustomerId(){
        return customerService.getCustomers();
    }

    @GetMapping(value="/{customerId}")
    public Customer getCustomer(@PathVariable("customerId") int customerId){
        return customerService.getCustomer(customerId);
    }

    @PutMapping(value = "/{customerId}")
    public Customer updateCustomer(@PathVariable("customerId") int customerId,@Valid @RequestBody Customer customer){
        return customerService.updateCustomer(customerId, customer);
    }

    @DeleteMapping(value="/{customerId}")
    public void deleteCustomer(@PathVariable("customerId") int customerId){
        customerService.deleteCustomer(customerId);
    }

    @PatchMapping(value ="/{customerId}")
    public Customer patchCustomer(@PathVariable("customerId") int customerId, @RequestBody Customer customer){
        return customerService.patchCustomer(customerId, customer);
    }
}
