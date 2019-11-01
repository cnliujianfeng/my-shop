package com.ljf.my.shop.web.admin.service.test;

import com.ljf.my.shop.domain.TbUser;

import com.ljf.my.shop.web.admin.service.TbUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

/**
 * ClassNmae:TbUserServiceTest
 * Package:com.ljf.my.shop.web.admin.service.test
 * Description:
 *
 * @DATE:2019/10/24 12:34
 * @Author:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-context.xml", "classpath:spring-context-druid.xml", "classpath:spring-context-mybatis.xml"})
public class TbUserServiceTest {

    @Autowired
    private TbUserService tbUserService;

    @Test
    public void testSelectAll() {
        List<TbUser> tbUsers = tbUserService.selectAll();
        for (TbUser tbUser : tbUsers) {
            System.out.println(tbUser.getUsername());
        }
    }

    @Test
    public void testInsert() {
        TbUser tbUser = new TbUser();
        tbUser.setUsername("tiansiyu");
        tbUser.setPhone("1315452");
        tbUser.setEmail("admin@ljf.com");
        tbUser.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
        tbUser.setCreated(new Date());
        tbUser.setUpdated(new Date());
        tbUserService.save(tbUser);
    }

    @Test
    public void deleteTest() {
        tbUserService.delete(41L);
    }

    @Test
    public void getByIdTest() {
        TbUser tbUser=tbUserService.getById(36L);
        System.out.println(tbUser.getUsername());
    }




    @Test
    public void toMD5(){
        System.out.println(DigestUtils.md5DigestAsHex("123456".getBytes()));
    }

}