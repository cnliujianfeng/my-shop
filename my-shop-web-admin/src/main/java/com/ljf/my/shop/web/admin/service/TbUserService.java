package com.ljf.my.shop.web.admin.service;

import com.ljf.my.shop.commons.dto.BaseResult;
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

    List<TbUser> selectAll();

    BaseResult save(TbUser tbUser);

    void delete(Long id);

    TbUser getById(Long id);

    void update(TbUser tbUser);


    List<TbUser> selectByUserName(String username);

    /**
     * 用户登录
     * @param email
     * @param password
     * @return
     */
    TbUser login(String email, String password);

    /**
     * 搜索
     * @param tbUser
     * @return
     */
    List<TbUser> search(TbUser tbUser);

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
    List<TbUser> page(int start,int length);

    /**
     * 查询总笔数
     * @return
     */
    int count();
}