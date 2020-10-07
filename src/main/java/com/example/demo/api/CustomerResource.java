package com.example.demo.api;

import com.example.demo.Service.CustomerService;
import com.example.demo.model.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    Logger logger = LoggerFactory.getLogger(CustomerResource.class);

    @PostMapping
    public Customer addCustomer(@Valid  @RequestBody Customer customer){
        customer.setCustomerFirstName(customer.getCustomerFirstName().toLowerCase());
        customer.setCustomerLastName(customer.getCustomerLastName().toLowerCase());
        customer.setCustomerEmail(customer.getCustomerEmail().toLowerCase());

        logger.info("Client has a request for adding new customer: Name - " + customer.getCustomerFirstName() + " " + customer.getCustomerLastName()
                + ", Email: "+customer.getCustomerEmail());
        return customerService.addCustomer(customer);
    }

    @GetMapping
    public List<Customer> getCustomerId(){

        logger.info("Client has requested for list of customer exist in database");
        return customerService.getCustomers();
    }

    @GetMapping(value="/{customerId}")
    public Customer getCustomer(@PathVariable("customerId") int customerId){

        logger.info("Client has requested a customer of id " + customerId);
        return customerService.getCustomer(customerId);
    }

    @GetMapping(value = "/search/{by}/{value}")
    public List<Customer> getCustomersByEmail(@PathVariable("by") String by , @PathVariable("value") String value){

        logger.info("Client has requested of customer that has " + by + " = " + value);
        return customerService.getAllByValue(by , value);
    }

    @GetMapping(value="/pagination/{pageNumber}/{pageSize}")
    public  List<Customer> getCustomersForPage(@PathVariable("pageNumber") int pageNumber , @PathVariable("pageSize") int pageSize){

        logger.info("Client has requested of customer pagination with window size " + pageSize + " and page number " + pageNumber);
        return customerService.getAllCustomerPaginated(pageNumber, pageSize);
    }

    @PutMapping(value = "/{customerId}")
    public Customer updateCustomer(@PathVariable("customerId") int customerId,@Valid @RequestBody Customer customer){

        logger.info("Client has a request for updating a customer(Id: " + customerId +") with Name - "+ customer.getCustomerFirstName() + " " + customer.getCustomerLastName()
                + ", Email: "+customer.getCustomerEmail());
        return customerService.updateCustomer(customerId, customer);
    }

    @DeleteMapping(value="/{customerId}")
    public void deleteCustomer(@PathVariable("customerId") int customerId){
        logger.info("Client has a request for deleting a customer(CustomerId - "+ customerId + ")");
        customerService.deleteCustomer(customerId);
    }

    @PatchMapping(value ="/{customerId}")
    public Customer patchCustomer(@PathVariable("customerId") int customerId, @RequestBody Customer customer){

        customer.setCustomerId(customerId);
        Customer previous = getCustomer(customerId);

        String str = "Client has a request for updating a customer(Id: " + customerId +") with ";
        str += (customer.getCustomerFirstName().toLowerCase().equals(previous.getCustomerFirstName())) ? "": ", First Name - " + customer.getCustomerFirstName() ;
        str += (customer.getCustomerLastName().toLowerCase().equals(previous.getCustomerLastName())) ? "": ", Last Name - " +customer.getCustomerLastName();
        str += (customer.getCustomerEmail().toLowerCase().equals(previous.getCustomerEmail()))? "": ", Email - " + customer.getCustomerEmail() ;
        logger.info(str);
        return customerService.patchCustomer(customerId, customer);
    }
}
