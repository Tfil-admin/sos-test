package com.hp.mapper;

import com.hp.pojo.Waiter;
import com.hp.pojo.WaiterExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WaiterMapper {
    int countByExample(WaiterExample example);

    int deleteByExample(WaiterExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Waiter record);

    int insertSelective(Waiter record);

    List<Waiter> selectByExample(WaiterExample example);

    Waiter selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Waiter record, @Param("example") WaiterExample example);

    int updateByExample(@Param("record") Waiter record, @Param("example") WaiterExample example);

    int updateByPrimaryKeySelective(Waiter record);

    int updateByPrimaryKey(Waiter record);

    List<Waiter> login(@Param("account")  String account, @Param("password") String password);
}