package com.shiming.secondskill.service;

import com.shiming.secondskill.exception.GlobalException;
import com.shiming.secondskill.result.CodeMsg;
import com.shiming.secondskill.dao.UserMapper;
import com.shiming.secondskill.pojo.User;
import com.shiming.secondskill.util.MD5Util;
import com.shiming.secondskill.vo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SecondsKillService {

    @Autowired
    private UserMapper userMapper;

    public User getUserById(long id) {
        return userMapper.selectByPrimaryKey(id);
    }

    public boolean login(LoginVo loginVo) {
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
        return true;
    }
}
