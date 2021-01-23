package com.hp.mapper;

import com.hp.pojo.Customer;
import com.hp.pojo.CustomerExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CustomerMapper {
    int countByExample(CustomerExample example);

    int deleteByExample(CustomerExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Customer record);

    int insertSelective(Customer record);

    List<Customer> selectByExample(CustomerExample example);

    Customer selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Customer record, @Param("example") CustomerExample example);

    int updateByExample(@Param("record") Customer record, @Param("example") CustomerExample example);

    int updateByPrimaryKeySelective(Customer record);

    int updateByPrimaryKey(Customer record);

    int updateByPhone(@Param("phone") String  phone,@Param("car") String  car,@Param("name") String  name );

    List<Customer> getByPhone(String phone);

    List<Customer> list(@Param("phone") String  phone,@Param("name") String  name);

    List<Customer> selectAllAndLike(Customer customer);

    List<Customer> selectAll();
}