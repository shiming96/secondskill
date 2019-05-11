package com.shiming.secondskill.service;

import com.shiming.secondskill.dao.UserMapper;
import com.shiming.secondskill.exception.GlobalException;
import com.shiming.secondskill.pojo.User;
import com.shiming.secondskill.redis.MiaoshaUserKey;
import com.shiming.secondskill.redis.RedisService;
import com.shiming.secondskill.result.CodeMsg;
import com.shiming.secondskill.util.MD5Util;
import com.shiming.secondskill.util.UUIDUtil;
import com.shiming.secondskill.vo.LoginVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    RedisService redisService;

    public static final String COOKIE_NAME_TOKEN = "token";

    public User getUserById(long id) {
        return userMapper.selectByPrimaryKey(id);
    }

    public User getUserByToken(HttpServletResponse response, String token) {
        if (StringUtils.isEmpty(token)) {
            return null;
        }
        User user = redisService.get(MiaoshaUserKey.token, token, User.class);

        //每次根据token获取用户，都重新往redis中设置，延长过期时间
        if (user != null) {
            addCookie(response, token, user);
        }
        return user;

    }

    public String login(HttpServletResponse response, LoginVo loginVo) {
        if (loginVo == null)
            throw new GlobalException(CodeMsg.SERVER_ERROR);
        String mobile = loginVo.getMobile();
        String formPass = loginVo.getPassword();

        //判断手机号是否存在
        User user = getUserById(Long.parseLong(mobile));
        if (user == null)
            throw new GlobalException(CodeMsg.MOBILE_NOT_EXIST);

        //验证密码
        String dbPass = user.getPassword();
        String slatDB = user.getSalt();
        String calcPass = MD5Util.formPassToDBPass(formPass, slatDB);
        if (!calcPass.equals(dbPass)) {
            throw new GlobalException(CodeMsg.PASSWORD_ERROR);
        }
        //生成cookie
        String token = UUIDUtil.uuid();
        addCookie(response, token, user);
        return token;
    }

    private void addCookie(HttpServletResponse response, String token, User user) {
        redisService.set(MiaoshaUserKey.token, token, user);
        Cookie cookie = new Cookie(COOKIE_NAME_TOKEN, token);
        cookie.setMaxAge(MiaoshaUserKey.token.expireSeconds());
        cookie.setPath("/");
        response.addCookie(cookie);
    }

}
