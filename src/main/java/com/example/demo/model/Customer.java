package com.example.demo.model;

import com.querydsl.core.annotations.QueryEntity;
import org.slf4j.LoggerFactory;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Document(collection = "customers")
//@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"firstName" , "lastName"}) , @UniqueConstraint(columnNames = {"eMail"})})
//@CompoundIndexes({
//        @CompoundIndex(unique = true , name = "first_last", def = "{ 'customerFirstName' : 1 , 'customerLastName' : 1}"),
//
//})
//@CompoundIndex(unique = true , name = "email" , def = "{'customerEmail' : 1}")
public class Customer {

//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private int customerId;
    @NotBlank(message = "First Name is mandatory")
    @Size(min = 3, max = 200, message = "First Name should be in between 10 and 200 characters")
    @Pattern(regexp = "^([a-zA-Z]{3})((\\s?)[a-zA-z])*$")
//    @Column(name = "firstName")
    private String customerFirstName;

    @NotBlank(message = "Last Name is mandatory")
    @Size(min = 3, max = 200, message = "Last Name should be in between 10 and 200 characters")
    @Pattern(regexp = "^([a-zA-Z]{3})((\\s?)[a-zA-z])*$")
//    @Column(name = "lastName")
    private String customerLastName;

    @NotBlank
    @Email
    @Pattern(regexp = "^[a-zA-Z]+\\.[a-zA-z0-9]+@([a-zA-Z])+\\.([a-zA-Z0-9])*([a-zA-Z]){2,}")
//    @Column(name = "eMail" , unique = true)
    @Field(name = "email")
    @Indexed(unique = true)
    private String customerEmail;


    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerFirstName() {
        return customerFirstName;
    }

    public void setCustomerFirstName(String customerFirstName) {
        this.customerFirstName = customerFirstName;
    }

    public String getCustomerLastName() {
        return customerLastName;
    }

    public void setCustomerLastName(String customerLastName) {
        this.customerLastName = customerLastName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }
}
