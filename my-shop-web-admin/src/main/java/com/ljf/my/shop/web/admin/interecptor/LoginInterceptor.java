package com.ljf.my.shop.web.admin.interecptor;

/**
 * ClassNmae:LoginInterceptor
 * Package:com.ljf.my.shop.web.interceptor
 * Description:
 *
 * @DATE:2019/10/23 13:46
 * @Author:
 */


import com.ljf.my.shop.commons.constant.ConstanUtils;
import com.ljf.my.shop.domain.TbUser;


import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录拦截器
 */
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        TbUser tbUser = (TbUser) httpServletRequest.getSession().getAttribute(ConstanUtils.SESSION_USER);

        if(tbUser==null){
            //未登录
            httpServletResponse.sendRedirect("/login");
        }


        //放行
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
