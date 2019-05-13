package com.shiming.secondskill.dao;

import com.shiming.secondskill.pojo.Goods;
import com.shiming.secondskill.pojo.GoodsExample;
import com.shiming.secondskill.vo.GoodsVo;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface GoodsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Goods record);

    int insertSelective(Goods record);

    List<Goods> selectByExampleWithBLOBs(GoodsExample example);

    List<Goods> selectByExample(GoodsExample example);

    Goods selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Goods record);

    int updateByPrimaryKeyWithBLOBs(Goods record);

    int updateByPrimaryKey(Goods record);

    @Select("select g.*,mg.stock_count, mg.start_date, mg.end_date, mg.miaosha_price from miaosha_goods mg left join goods g on mg.goods_id = g.id")
    List<GoodsVo> listGoodsVo();

}