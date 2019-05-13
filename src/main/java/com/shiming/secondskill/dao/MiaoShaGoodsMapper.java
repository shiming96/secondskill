package com.shiming.secondskill.dao;

import com.shiming.secondskill.pojo.MiaoShaGoods;
import com.shiming.secondskill.pojo.MiaoShaGoodsExample;
import java.util.List;

public interface MiaoShaGoodsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MiaoShaGoods record);

    int insertSelective(MiaoShaGoods record);

    List<MiaoShaGoods> selectByExample(MiaoShaGoodsExample example);

    MiaoShaGoods selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MiaoShaGoods record);

    int updateByPrimaryKey(MiaoShaGoods record);
}