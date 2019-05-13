package com.shiming.secondskill.config;

import com.shiming.secondskill.pojo.User;
import com.shiming.secondskill.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class UserArgumentResolver implements HandlerMethodArgumentResolver {

    @Autowired
    UserService userService;

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        Class<?> clazz = methodParameter.getParameterType();
        return clazz == User.class;
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        HttpServletRequest request = nativeWebRequest.getNativeRequest(HttpServletRequest.class);
        HttpServletResponse response = nativeWebRequest.getNativeResponse(HttpServletResponse.class);

        //用户可能从cookie传回token或从参数中传回token
        //获取参数token或cookie中的token
        String paramToken = request.getParameter(UserService.COOKIE_NAME_TOKEN);
        String cookieToken = getCookieValue(request, UserService.COOKIE_NAME_TOKEN);

        if (StringUtils.isEmpty(paramToken) && StringUtils.isEmpty(cookieToken)) {
            return null;
        }

        String token = StringUtils.isEmpty(paramToken) ? cookieToken : paramToken;
        return userService.getUserByToken(response, token);
    }

    private String getCookieValue(HttpServletRequest request, String cookieName) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(cookieName))
                return cookie.getValue();
        }
        return null;
    }
}
