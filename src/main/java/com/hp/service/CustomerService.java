package com.hp.service;

import com.hp.pojo.Customer;
import com.hp.utils.JsonData;

public interface CustomerService {
    JsonData selectAll(int page, int limit, Customer customer);

    JsonData updateByPrimaryKey(Customer customer);
}
