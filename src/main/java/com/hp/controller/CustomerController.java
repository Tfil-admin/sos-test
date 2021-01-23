package com.hp.controller;

import com.hp.pojo.Customer;
import com.hp.service.CustomerService;
import com.hp.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @RequestMapping("/selectList")
    public JsonData selectList(int page, int limit, Customer customer){
        return customerService.selectAll(page,limit,customer);
    }
    @RequestMapping("/customerUpd")
    public JsonData customerUpd(Customer customer){
        return customerService.updateByPrimaryKey(customer);
    }

}
