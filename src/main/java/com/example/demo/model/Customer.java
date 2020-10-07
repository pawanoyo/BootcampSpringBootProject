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

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.validation.executable.ValidateOnExecution;
import java.util.ArrayList;
import java.util.List;


@Document(collection = "customers")
@ValidateOnExecution

public class Customer {

    @Id
    private int customerId;
    @NotBlank(message = "First Name is mandatory")
    @Size(min = 3, max = 200, message = "First Name should be in between 3 and 200 characters")
    @Pattern(regexp = "^([a-zA-Z]{3})((\\s?)[a-zA-z])*$")
    private String customerFirstName;

    @NotBlank(message = "Last Name is mandatory")
    @Size(min = 3, max = 200, message = "Last Name should be in between 3 and 200 characters")
    @Pattern(regexp = "^([a-zA-Z]{3})((\\s?)[a-zA-z])*$")
    private String customerLastName;

    @NotBlank
    @Email
    @Pattern(regexp = "^[a-zA-Z]+\\.[a-zA-z0-9]+@([a-zA-Z])+\\.([a-zA-Z0-9])*([a-zA-Z]){2,}")
    @Field(name = "email")
    private String customerEmail;


    ArrayList<String> customerLog = new ArrayList<String>();

    public ArrayList<String> getCustomerLog() {
        return customerLog;
    }

    public void setCustomerLog(ArrayList<String> customerLog) {
        this.customerLog = customerLog;
    }

    @Override
    public String toString() {
        return String.format("customerId - " + customerId + ", customerFirstName - " + customerFirstName + ", customerLastName - " + customerLastName + ", customerEmail: " + customerEmail);
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerFirstName() {
        return customerFirstName;
    }

    public void setCustomerFirstName(@Valid String customerFirstName) {
        this.customerFirstName = customerFirstName;
    }

    public String getCustomerLastName() {
        return customerLastName;
    }

    public void setCustomerLastName(@Valid String customerLastName) {
        this.customerLastName = customerLastName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(@Valid String customerEmail) {
        this.customerEmail = customerEmail;
    }
}
