package com.shiming.secondskill.controller;

import com.shiming.secondskill.pojo.User;
import com.shiming.secondskill.redis.RedisService;
import com.shiming.secondskill.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    UserService userService;

    @Autowired
    RedisService redisService;

    public String list(Model model, User user) {
        model.addAttribute("user", user);
        return "goods_list";
    }




}
