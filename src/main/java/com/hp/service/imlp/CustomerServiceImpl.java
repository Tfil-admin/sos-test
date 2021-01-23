package com.hp.service.imlp;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hp.mapper.CustomerMapper;
import com.hp.pojo.Customer;
import com.hp.service.CustomerService;
import com.hp.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerMapper customerMapper;
    public JsonData selectAll(int page, int limit, Customer customer) {
        List<Customer> customers=null;
        PageHelper.startPage(page,limit);
        if (customer.getName()!=null||customer.getPhone()!=null){
            customers=customerMapper.selectAllAndLike(customer);
        }else {
            customers=customerMapper.selectAll();
        }
        PageInfo<Customer> pageInfo=new PageInfo<Customer>(customers);
        return JsonData.buildSuc(pageInfo);
    }

    public JsonData updateByPrimaryKey(Customer customer) {
        int i = customerMapper.updateByPrimaryKey(customer);
        if (i>0){
            return JsonData.buildSuc("修改成功");
        }
        return JsonData.buildError("修改失败");
    }
}
