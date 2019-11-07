package com.ljf.my.shop.web.admin.interecptor;


import com.ljf.my.shop.commons.constant.ConstanUtils;
import com.ljf.my.shop.domain.TbUser;


import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 权限拦截器
 * ClassNmae:PermissionInterceptor
 * Package:com.ljf.my.shop.web.interceptor
 * Description:
 *
 * @DATE:2019/10/23 13:58
 * @Author:
 */
public class PermissionInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {


        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        if (modelAndView != null && modelAndView.getViewName().endsWith("login")) {
            TbUser tbUser = (TbUser) httpServletRequest.getSession().getAttribute(ConstanUtils.SESSION_USER);

            if (tbUser != null) {
                //未登录
                httpServletResponse.sendRedirect("/main");
            }


        }
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
