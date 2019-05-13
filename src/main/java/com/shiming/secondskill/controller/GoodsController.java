package com.shiming.secondskill.controller;

import com.shiming.secondskill.pojo.User;
import com.shiming.secondskill.redis.RedisService;
import com.shiming.secondskill.service.GoodsService;
import com.shiming.secondskill.service.UserService;
import com.shiming.secondskill.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    UserService userService;

    @Autowired
    GoodsService goodsService;

    @Autowired
    RedisService redisService;

    @RequestMapping("/to_list")
    public String list(Model model, User user) {
        model.addAttribute("user", user);
        
        //查询商品列表
        List<GoodsVo> goodsList = goodsService.listGoodsVo();
        model.addAttribute("goodsList", goodsList);

        return "goods_list";
    }

}
