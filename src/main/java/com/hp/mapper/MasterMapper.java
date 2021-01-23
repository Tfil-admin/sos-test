package com.hp.mapper;

import com.hp.pojo.Master;
import com.hp.pojo.MasterExample;
import java.util.List;

import com.hp.pojo.Waiter;
import org.apache.ibatis.annotations.Param;

public interface MasterMapper {
    int countByExample(MasterExample example);

    int deleteByExample(MasterExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Master record);

    int insertSelective(Master record);

    List<Master> selectByExample(MasterExample example);

    Master  selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Master record, @Param("example") MasterExample example);

    int updateByExample(@Param("record") Master record, @Param("example") MasterExample example);

    int updateByPrimaryKeySelective(Master record);

    int updateByPrimaryKey(Master record);
    int updateStatus(Master record);

    List<Master> selectList(@Param("phone") String  phone,@Param("name") String  name);

    List<Master> selectAllAndLike(Master m);

    List<Master> selectAll();

    Master selectByDid(String did);
}