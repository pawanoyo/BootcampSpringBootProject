package com.example.demo.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"firstName" , "lastName"}) , @UniqueConstraint(columnNames = {"eMail"})})

public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int customerId;
    @NotBlank(message = "First Name is mandatory")
    @Size(min = 3, max = 200, message = "First Name should be in between 10 and 200 characters")
    @Pattern(regexp = "^([a-zA-Z]{3})((\\s?)[a-zA-z])*$")
    @Column(name = "firstName")
    private String customerFirstName;

    @NotBlank(message = "Last Name is mandatory")
    @Size(min = 3, max = 200, message = "Last Name should be in between 10 and 200 characters")
    @Pattern(regexp = "^([a-zA-Z]{3})((\\s?)[a-zA-z])*$")
    @Column(name = "lastName")
    private String customerLastName;

    @NotBlank
    @Email
    @Pattern(regexp = "^[a-zA-Z]+\\.[a-zA-z0-9]+@([a-zA-Z])+\\.([a-zA-Z0-9])*([a-zA-Z]){2,}")
    @Column(name = "eMail" , unique = true)
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
