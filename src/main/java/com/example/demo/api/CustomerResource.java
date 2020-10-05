package com.example.demo.api;

import com.example.demo.Service.CustomerService;
import com.example.demo.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@RestController
@Validated
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

    @GetMapping(value = "/search/{by}/{value}")
    public List<Customer> getCustomersByEmail(@PathVariable("by") String by , @PathVariable("value") String value){
//        System.out.println(by + " " + value);
        return customerService.getAllByValue(by , value);
    }

    @GetMapping(value="/pagination/{pageNumber}/{pageSize}")
    public  List<Customer> getCustomersForPage(@PathVariable("pageNumber") int pageNumber , @PathVariable("pageSize") int pageSize){
        return customerService.getAllCustomerPaginated(pageNumber, pageSize);
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
