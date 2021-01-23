package com.hp.service;

import com.hp.pojo.Order;
import com.hp.utils.JsonData;

import java.util.List;

public interface OrderService {
    JsonData saveOrder(Order order);
    JsonData selectListByPhone(String phone);

    JsonData addOrder(Order order,String name,String car);
    JsonData selectOrdersList(Integer page,Integer limit,String status,String phone);
    JsonData upd(Integer id, String status);
    JsonData update(Order order, String name, String car);

    List<Order> allOrders();

    JsonData liftOrder(Order order);

    JsonData selectByPrimaryKey(Integer id);

    JsonData updateByOrderStatus(Order order);
}
