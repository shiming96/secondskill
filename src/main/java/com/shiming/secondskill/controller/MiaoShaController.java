package com.shiming.secondskill.controller;

import com.shiming.secondskill.pojo.MiaoShaOrder;
import com.shiming.secondskill.pojo.OrderInfo;
import com.shiming.secondskill.pojo.User;
import com.shiming.secondskill.result.CodeMsg;
import com.shiming.secondskill.service.GoodsService;
import com.shiming.secondskill.service.MiaoShaService;
import com.shiming.secondskill.service.OrderService;
import com.shiming.secondskill.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/miaosha")
public class MiaoShaController {

    @Autowired
    GoodsService goodsService;

    @Autowired
    OrderService orderSerivce;

    @Autowired
    MiaoShaService miaoShaService;

    @RequestMapping("/do_miaosha")
    public String list(Model model, User user, @RequestParam("goodsId") long goodsId) {
        model.addAttribute("user", user);
        if (user == null)
            return "login";

        //判断库存
        GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId);
        int stock = goods.getStockCount();
        if (stock <= 0) {
            model.addAttribute("errmsg", CodeMsg.MIAO_SHA_OVER.getMsg());
            return "miaosha_fail";
        }

        //判断该用户是否已经秒杀过
        MiaoShaOrder order = orderSerivce.getMiaoShaOrderByUserIdGoodsId(user.getId(), goodsId);
        if (order != null) {
            model.addAttribute("errmsg", CodeMsg.REPEATE_MIAOSHA.getMsg());
            return "miaosha_fail";
        }

        //减库存，下订单
        OrderInfo orderInfo = miaoShaService.miaosha(user, goods);
        model.addAttribute("orderInfo", orderInfo);
        model.addAttribute("goods", goods);

        return "order_detail";
    }
}
