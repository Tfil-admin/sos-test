package com.hp.mapper;

import com.hp.pojo.Order;
import com.hp.pojo.OrderExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderMapper {
    int countByExample(OrderExample example);

    int deleteByExample(OrderExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Order record);

    int insertSelective(Order record);

    List<Order> selectByExample(OrderExample example);

    Order selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Order record, @Param("example") OrderExample example);

    int updateByExample(@Param("record") Order record, @Param("example") OrderExample example);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

    List<Order> selectListByPhone(String phone);
    int upstatus(@Param("id") Integer id,@Param("status") String  status);
    List<Order> list(@Param("status")String status,@Param("phone") String phone);

    List<Order>  selectOrderList();

    int  updateByOrderStatus(Order order);
}