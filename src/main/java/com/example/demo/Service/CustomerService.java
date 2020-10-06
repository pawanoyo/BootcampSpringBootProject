package com.example.demo.Service;

import com.example.demo.dao.CustomerDAO;
import com.example.demo.exception.BadRequest;
import com.example.demo.exception.CustomerNotFoundException;
import com.example.demo.model.Customer;
import com.mongodb.internal.operation.OrderBy;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.Index;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpServerErrorException;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class CustomerService {

    @Autowired
    private CustomerDAO customerDAO;
    private final MongoTemplate mongoTemplate;

    Logger logger = LoggerFactory.getLogger(CustomerService.class);

    public CustomerService(MongoTemplate mongoTemplate){
        this.mongoTemplate = mongoTemplate;
    }
    public Customer addCustomer(@NotNull Customer customer){
        mongoTemplate.indexOps(Customer.class).ensureIndex(new Index().unique().named("mail").on("customerEmail" , Sort.Direction.ASC)) ;
        mongoTemplate.indexOps(Customer.class).ensureIndex(new Index().unique().named("name").on("customerFirstName" , Sort.Direction.ASC).on("customerLastName" , Sort.Direction.ASC)) ;
        ArrayList<String> customerLog = new ArrayList<String>();
        customerLog.add("created " + new Date().toString());
        customer.setCustomerLog(customerLog);

        logger.info("Client has successfully added a customer: " + customer.toString());
        return customerDAO.save(customer);
    }

    public List<Customer> getCustomers(){

        logger.info("Client has successfully received list of customer exist in database");
        return customerDAO.findAll();
    }

    public Customer getCustomer(int customerId){

        Optional<Customer> optionalCustomer = customerDAO.findById(customerId);
        if(!optionalCustomer.isPresent()){
            throw new CustomerNotFoundException("Customer Record is not available");
        }
        logger.info("Client has successfully received a customer: " + optionalCustomer.get().toString());
        return optionalCustomer.get();
    }

    public Customer updateCustomer(int customerId, @NotNull Customer customer){

        customer.setCustomerId(customerId);
        Customer previous = getCustomer(customerId);
        String change = "Updated -> ";

        change+= new Date().toString();
        change += (customer.getCustomerFirstName().toLowerCase().equals(previous.getCustomerFirstName())) ? "": ", First Name" ;
        change += (customer.getCustomerLastName().toLowerCase().equals(previous.getCustomerLastName())) ? "": ", Last Name" ;
        change += (customer.getCustomerEmail().toLowerCase().equals(previous.getCustomerEmail()))? "": ", Email" ;

        customer.setCustomerLog(previous.getCustomerLog());
        customer.getCustomerLog().add(change);
        try {
            logger.info("Client has successfully update customer: " + customer.toString());
            return customerDAO.save(customer);
        }catch (Exception e){
            System.out.println(e.getStackTrace().toString());
            throw new BadRequest("helo elo");

        }
    }


    public void deleteCustomer(int customerId){
        logger.info("Client has successfully deleted a customer: " + customerDAO.findById(customerId).get().toString());
        customerDAO.deleteById(customerId);
    }

    public Customer patchCustomer(int customerId, @NotNull Customer customer){

        Customer previous = getCustomer(customerId);

        customer.setCustomerFirstName(customer.getCustomerFirstName() == null ? previous.getCustomerFirstName() : Validater(customer.getCustomerFirstName().toLowerCase() , "^([a-zA-Z]{3})((\\s?)[a-zA-z])*$"));
        customer.setCustomerLastName(customer.getCustomerLastName() == null ? previous.getCustomerLastName() : Validater(customer.getCustomerLastName().toLowerCase(),"^([a-zA-Z]{3})((\\s?)[a-zA-z])*$"));
        customer.setCustomerEmail(customer.getCustomerEmail() == null ? previous.getCustomerEmail() : Validater(customer.getCustomerEmail().toLowerCase(), "^[a-zA-Z]+\\.[a-zA-z0-9]+@([a-zA-Z])+\\.([a-zA-Z0-9])*([a-zA-Z]){2,}"));
        return updateCustomer(customerId , customer);
    }

    public List<Customer> getAllByValue(String by , String value){
//        System.out.println(by + " " + value);
        Query query = new Query().addCriteria(Criteria.where(""+by).is(value));
        logger.info("Client has successfully received the customers having " + by + " - " + value);
        return mongoTemplate.find(query, Customer.class);
    }

    public List<Customer> getAllCustomerPaginated(int pageNumber, int pageSize) {
        Query query = new Query();
        query.skip(pageNumber * pageSize);
        query.limit(pageSize);
        logger.info("Client has successfully received the customers of that pagination");
        return mongoTemplate.find(query, Customer.class);
    }

    public String Validater(String value , String pattern){
        Pattern firstName = Pattern.compile(pattern);
        Matcher m = firstName.matcher(value);
        if(!m.find()){
            throw new BadRequest(value + " dosen't matches defined pattern.");
        }
        return value;
    }
}