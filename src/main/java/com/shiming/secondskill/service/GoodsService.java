package com.shiming.secondskill.service;

import com.shiming.secondskill.dao.GoodsMapper;
import com.shiming.secondskill.pojo.MiaoShaGoods;
import com.shiming.secondskill.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsService {

    @Autowired
    GoodsMapper goodsMapper;

    public List<GoodsVo> listGoodsVo() {
        return goodsMapper.listGoodsVo();
    }

    public GoodsVo getGoodsVoByGoodsId(long goodsId) {
        return goodsMapper.getGoodsVoByGoodsId(goodsId);
    }

    public void reduceStock(GoodsVo goods) {
        MiaoShaGoods g = new MiaoShaGoods();
        g.setId(goods.getId());
        goodsMapper.reduceStock(g);
    }
}
