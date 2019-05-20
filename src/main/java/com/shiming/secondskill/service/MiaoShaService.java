package com.shiming.secondskill.service;

import com.shiming.secondskill.pojo.Goods;
import com.shiming.secondskill.pojo.OrderInfo;
import com.shiming.secondskill.pojo.User;
import com.shiming.secondskill.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MiaoShaService {

    @Autowired
    GoodsService goodsService;

    @Autowired
    OrderService orderService;

    @Transactional
    public OrderInfo miaosha(User user, GoodsVo goods) {
        //减少库存
        goodsService.reduceStock(goods);

        //下订单 order_info miaosha_info
        return orderService.createOrder(user, goods);
    }
}
