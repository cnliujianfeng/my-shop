package com.ljf.my.shop.web.admin.service;

import com.ljf.my.shop.commons.dto.BaseResult;
import com.ljf.my.shop.commons.dto.Pageinfo;
import com.ljf.my.shop.domain.TbUser;


import java.util.List;

/**
 * ClassNmae:TbUserService
 * Package:com.ljf.my.shop.web.admin.service
 * Description:
 *
 * @DATE:2019/10/24 12:14
 * @Author:
 */
public interface TbUserService {
    /**
     * 查询全部
     * @return
     */
    List<TbUser> selectAll();

    /**
     * 保存用户信息
     * @param tbUser
     * @return
     */
    BaseResult save(TbUser tbUser);

    /**
     * 删除用户信息
     * @param id
     */
    void delete(Long id);

    /**
     * 根据id获取用户信息
     * @param id
     * @return
     */
    TbUser getById(Long id);

    /**
     * 更新用户信息
     * @param tbUser
     */
    void update(TbUser tbUser);




    /**
     * 用户登录
     * @param email
     * @param password
     * @return
     */
    TbUser login(String email, String password);


    /**
     * 批量删除
     * @param ids
     */
    void deleteMulti(String[] ids);

    /**
     * 分页查询
     * @param start
     * @param length
     * @return
     */
    Pageinfo<TbUser> page(int start, int length,int draw,TbUser tbUser);

    /**
     * 查询总笔数
     * @return
     */
    int count(TbUser tbUser);
}