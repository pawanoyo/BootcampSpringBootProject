//package com.example.demo;
//
//import com.example.demo.Service.CustomerService;
//import com.example.demo.dao.CustomerDAO;
//import com.example.demo.model.Customer;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.runners.MockitoJUnitRunner;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.core.annotation.SynthesizedAnnotation;
//import org.springframework.test.context.junit4.SpringRunner;
//import sun.tools.jconsole.JConsole;
//
//import javax.annotation.meta.When;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Stream;
//
//@RunWith(MockitoJUnitRunner.class)
////@SpringBootTest
//public class CustomerTest {
//    @Mock
//    CustomerDAO repository;
//    @InjectMocks
//    CustomerService service;
//
//
//    public void add(){
//        Customer customer = new Customer();
//        customer.setCustomerId(1);
//        customer.setCustomerFirstName("Ankit");
//        customer.setCustomerLastName("Jangir");
//        customer.setCustomerEmail("ankit.oyo@oyorooms.com");
//        repository.save(customer);
//    }
//
//    @Test
//    public void addCustomer(){
////        customerDAO.deleteAll();
////        customerService.addCustomer({
////                "customerId": 5,
////                "customerFirstName": "Vikasho",
////                "customerLastName": "sharma",
////                "customerEmail": "ankit.oyo4@gmail.com"
////});
//////        customerDAO.save(new Customer(2,"anit","jangir","abc.ef@oyo.com"));//same Email
//////        customerDAO.save(new Customer(3,"anit","jan","abc.efgh@oyo.com"));//same Name(first + last)
////        customerService.addCustomer(new Customer(4,"a","jan","abc.ef1@oyo.com"));//less then 3 characters
////        customerService.addCustomer(new Customer(5,"an it","jan","abc.ef2@oyo.com"));
////        customerService.addCustomer(new Customer(6,"ankit kumar","jangir","abc.ef3@oyo.com"));
////        customerService.addCustomer(new Customer(7,"ankit","jan","abc.ef4@oyo.com"));
//
//        Customer customer = new Customer();
//        customer.setCustomerId(1);
//        customer.setCustomerFirstName("Ankit");
//        customer.setCustomerLastName("Jangir");
//        customer.setCustomerEmail("ankit.oyo@oyorooms.com");
//        List<Customer> list = new ArrayList<>();
//        list.add(customer);
//        Mockito.when(repository.findAll()).thenReturn(list);
//        Mockito.when(repository.save(customer)).thenReturn(customer);
//        System.out.println(repository.save(customer));
////        repository.findAll().forEach(System.out::println);
////        System.out.println(service.getCustomers());
//        Assert.assertEquals(customer, service.addCustomer(customer));
//    }
//}
