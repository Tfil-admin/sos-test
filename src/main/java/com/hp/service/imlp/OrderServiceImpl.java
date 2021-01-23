package com.hp.service.imlp;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hp.mapper.CustomerMapper;
import com.hp.mapper.MasterAddressMapper;
import com.hp.mapper.MasterMapper;
import com.hp.mapper.OrderMapper;
import com.hp.pojo.Customer;
import com.hp.pojo.Master;
import com.hp.pojo.Order;
import com.hp.pojo.OrderExample;
import com.hp.service.OrderService;
import com.hp.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private MasterMapper masterMapper;
    @Autowired
    private MasterAddressMapper masterAddressMapper;
    @Autowired
    private CustomerMapper customerMapper;
    public JsonData saveOrder(Order order) {
        OrderExample orderExample=new OrderExample();
        OrderExample.Criteria criteria=orderExample.createCriteria();
        criteria.andPhoneEqualTo(order.getPhone());
        criteria.andStatusLessThan("3");
        List<Order> orders = orderMapper.selectByExample(orderExample);

        if(orders.size()==0){
            int insert = orderMapper.insert(order);
            if(insert>0){
                return JsonData.buildSuc("添加订单成功....");
            }
            return JsonData.buildError("添加订单失败....");
        }
        return JsonData.buildError("存在未完成订单，添加订单失败....");
    }

    public JsonData selectListByPhone(String phone) {
        OrderExample orderExample=new OrderExample();
        OrderExample.Criteria criteria=orderExample.createCriteria();
        criteria.andPhoneEqualTo(phone);
        List<Order> orders = orderMapper.selectByExample(orderExample);
        return JsonData.buildSuc(orders);
    }

    public JsonData addOrder(Order order, String name, String car) {
        int i=0;
        order.setStatus("0");
        order.setCreatetime(new Date());
        List<Order> orders = orderMapper.selectListByPhone(order.getPhone());
        if (orders.size()>0){
            String status= null;
            for ( Order order1:orders
                 ) {
                status+=order1.getStatus()+"";
            }
            if (status.contains("0")){
                return JsonData.buildError("订单未完成");
            }
        }else {
            List<Customer> byPhone = customerMapper.getByPhone(order.getPhone());
            if (byPhone.size()>0){
                //存在客户加订单
                i=orderMapper.insert(order);
            }
            Customer customer=new Customer();
            customer.setPhone(order.getPhone());
            customer.setName(name);
            customer.setCar(car);
            customerMapper.insertSelective(customer);
            i=orderMapper.insert(order);

        }
        if (i>0){
            return JsonData.buildSuc("添加成功");
        }
        return JsonData.buildError("失败");
    }

    public JsonData selectOrdersList(Integer page, Integer limit, String status, String phone) {
        PageHelper.startPage(page,limit);
        List<Order> list = orderMapper.list(status,phone);
        PageInfo<Order> pageInfo=new PageInfo<Order>(list);
        return JsonData.buildSuc(pageInfo);
    }

    public JsonData upd(Integer id, String status) {
        int i=orderMapper.upstatus(id, status);
        if (i>0){
            return JsonData.buildSuc("修改成功");
        }
        return JsonData.buildError("修改失败");
    }

//    public JsonData update(Order order, String name, String car) {
//        int i=0;
//        if (order.getId()!=null){
//            i=orderMapper.updateByPrimaryKeySelective(order);
//            customerMapper.updateByPhone(order.getPhone(),car,name);
//        }
//        if (i>0){
//            return JsonData.buildSuc("修改成功");
//        }
//        return JsonData.buildError("修改失败");
//    }

public JsonData update(Order order, String name, String car) {
    int i=0;
    if (order.getId()!=null){
        i= orderMapper.updateByPrimaryKeySelective(order);
        customerMapper.updateByPhone(order.getPhone(),car,name);
    }
    if (i>0){
        return JsonData.buildSuc("修改成功");
    }
    return JsonData.buildError("修改失败");
}

    public List<Order> allOrders() {
        List<Order> orders = orderMapper.selectOrderList();

        return orders;
    }

    public JsonData liftOrder(Order order) {
        if (!order.getStatus().equals("1")){
            return JsonData.buildError("解除失败");
        }
        order.setStatus("0");
        String did = String.valueOf(order.getId());
        Master master = masterMapper.selectByDid(did);
        if (master!=null){
            master.setDid("");
            master.getMasterAddress().setStatus("1");
            int result = masterMapper.updateByPrimaryKey(master);
            int result1 = masterAddressMapper.updateByPrimaryKey(master.getMasterAddress());
            int result2 = orderMapper.updateByOrderStatus(order);
            if (result>0 && result1>0 && result2>0){
                return JsonData.buildSuc("解除成功");
            }
        }
        return JsonData.buildError("解除失败");
    }

    @Override
    public JsonData selectByPrimaryKey(Integer id) {
        Order order = orderMapper.selectByPrimaryKey(id);
        if (order.getStatus().equals("1")){
            return JsonData.buildError("该订单已被技师接单,无法指派");
        }
        if (order.getStatus().equals("-1")){
            return JsonData.buildError("该订单已作废");
        }
        if (order.getStatus().equals("2")){
            return JsonData.buildError("该订单接单技师已到位");
        }
        if (order.getStatus().equals("3")){
            return JsonData.buildError("该订单已完成");
        }
        if (order.getStatus().equals("4")){
            return JsonData.buildError("该订单已完成评价");
        }
        return JsonData.buildSuc("success");
    }

    @Override
    public JsonData updateByOrderStatus(Order order) {
        int result = orderMapper.updateByOrderStatus(order);
        Order order1 = orderMapper.selectByPrimaryKey(order.getId());
        Master master = masterMapper.selectByDid(String.valueOf(order1.getId()));
        if (order1.getStatus().equals("-1")){
            if (master.getMasterAddress().getStatus().equals("0")){
                master.getMasterAddress().setStatus("1");
                int i=masterAddressMapper.updateByPrimaryKey(master.getMasterAddress());
                if (i==0){
                    return JsonData.buildError("修改失败");
                }
            }
        }
        if(order1.getStatus().equals("3")){
            if (master.getMasterAddress().getStatus().equals("0")){
                master.getMasterAddress().setStatus("1");
                int i = masterAddressMapper.updateByPrimaryKey(master.getMasterAddress());
                if (i==0){
                    return JsonData.buildError("修改失败");
                }
            }
        }

        if (result>0){
            return JsonData.buildSuc("修改成功");
        }
        return JsonData.buildError("修改失败");
    }

}
