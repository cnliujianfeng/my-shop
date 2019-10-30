package com.ljf.my.shop.web.admin.controller;


import com.ljf.my.shop.commons.constant.ConstanUtils;
import com.ljf.my.shop.domain.TbUser;
import com.ljf.my.shop.web.admin.service.TbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * ClassNmae:LoginController
 * Package:com.ljf.my.shop.web
 * Description:
 *
 * @DATE:2019/10/18 16:00
 * @Author:
 */
@Controller
public class LoginController {

    @Autowired
    private TbUserService tbUserService;

    /**
     * 跳转登录页面
     *
     * @return
     */
    @RequestMapping(value = {"", "login"}, method = RequestMethod.GET)
    public String login() {
    return  "login";
    }

    /**
     * 登录逻辑
     *
     * @param email
     * @param password
     * @return
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(@RequestParam(required = true) String email, @RequestParam(required = true) String password, HttpServletRequest request, Model model) {


        TbUser tbUser = tbUserService.login(email, password);


        //登录失败
        if (tbUser == null) {
            model.addAttribute("message","用户名或密码错误，请重新输入");
            return login();
        } else {
            //登录成功

            request.getSession().setAttribute("tbUser",tbUser);
            return "redirect:/main";
        }


    }

    /**
     * 注销
     *
     * @param session
     * @return
     */
    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        session.removeAttribute(ConstanUtils.SESSION_USER);
        return "redirect:/login";
    }







    /*


    private UserService userService = SpringContext.getBean("userService");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userInfo = CookieUtils.getCookieValue(req, COOKIE_NAME_USER_INFO);
        System.out.println(userInfo);

        if(!StringUtils.isBlank(userInfo)){
            String[] userInfoArray=userInfo.split(":");
            String email=userInfoArray[0];
            String password=userInfoArray[1];
            req.setAttribute("email",email);
            req.setAttribute("password",password);
            req.setAttribute("isRemember",true);

        }

        req.getRequestDispatcher("/login.jsp").forward(req, resp);

    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String email = req.getParameter("email");
        String password = req.getParameter("password");
        Boolean isRemember = req.getParameter("isRemember") == null ? false : true;
        // UserService userService= (UserService) new SpringContext().getBean("userService");
        User admin = userService.login(email, password);
        if(!isRemember){
            CookieUtils.deleteCookie(req,resp,COOKIE_NAME_USER_INFO);
        }

        if (admin != null) {

            if (isRemember) {
                //用户信息存储一周
                CookieUtils.setCookie(req, resp, COOKIE_NAME_USER_INFO, String.format("%s:%s", email, password), 7 * 24 * 60 * 60);
            }
            resp.sendRedirect("/main.jsp");
        } else {
            req.setAttribute("message", "用户名密码错误");
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
        }

    }*/
}
