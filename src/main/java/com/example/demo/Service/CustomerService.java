package com.example.demo.Service;

import com.example.demo.dao.CustomerDAO;
import com.example.demo.exception.CustomerNotFoundException;
import com.example.demo.model.Customer;
import com.mongodb.internal.operation.OrderBy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.Index;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class CustomerService {

    @Autowired
    private CustomerDAO customerDAO;
    private final MongoTemplate mongoTemplate;

    public CustomerService(MongoTemplate mongoTemplate){
        this.mongoTemplate = mongoTemplate;
    }
    public Customer addCustomer(Customer customer){
        mongoTemplate.indexOps(Customer.class).ensureIndex(new Index().unique().named("mail").on("customerEmail" , Sort.Direction.ASC)) ;
        mongoTemplate.indexOps(Customer.class).ensureIndex(new Index().unique().named("name").on("customerFirstName" , Sort.Direction.ASC).on("customerLastName" , Sort.Direction.ASC)) ;
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

        customer.setCustomerId(customerId);
        return customerDAO.save(customer);
    }


    public void deleteCustomer(int customerId){

        customerDAO.deleteById(customerId);
    }

    public Customer patchCustomer(int customerId, Customer customer){
        Optional<Customer> optionalCustomer = customerDAO.findById(customerId);
        if(!optionalCustomer.isPresent()){
            throw new CustomerNotFoundException("Customer Record is not available");
        }
        Customer previous = optionalCustomer.get();
        customer.setCustomerId(customerId);
        customer.setCustomerFirstName(customer.getCustomerFirstName() == null ? previous.getCustomerFirstName() : customer.getCustomerFirstName().toLowerCase());
        customer.setCustomerLastName(customer.getCustomerLastName() == null ? previous.getCustomerLastName() : customer.getCustomerLastName().toLowerCase());
        customer.setCustomerEmail(customer.getCustomerEmail() == null ? previous.getCustomerEmail() : customer.getCustomerEmail().toLowerCase());


        return customerDAO.save(customer);
    }

    public List<Customer> getAllByValue(String by , String value){
//        System.out.println(by + " " + value);
        Query query = new Query().addCriteria(Criteria.where(""+by).is(value));
        return mongoTemplate.find(query, Customer.class);
    }

    public List<Customer> getAllCustomerPaginated(int pageNumber, int pageSize) {
        Query query = new Query();
        query.skip(pageNumber * pageSize);
        query.limit(pageSize);
        return mongoTemplate.find(query, Customer.class);
    }

}
