package com.shiming.secondskill.dao;

import com.shiming.secondskill.pojo.OrderInfo;
import com.shiming.secondskill.pojo.OrderInfoExample;
import java.util.List;

public interface OrderInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OrderInfo record);

    int insertSelective(OrderInfo record);

    List<OrderInfo> selectByExample(OrderInfoExample example);

    OrderInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OrderInfo record);

    int updateByPrimaryKey(OrderInfo record);
}