package com.ljf.my.shop.web.admin.dao;

import com.ljf.my.shop.domain.TbUser;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ClassNmae:TbUserDao
 * Package:com.ljf.my.shop.web.admin.dao
 * Description:
 *
 * @DATE:2019/10/24 11:57
 * @Author:
 */

@Repository
public interface TbUserDao {
    /**
     * 查询表全部用户信息
     * @return
     */
     List<TbUser> selectAll();

    /**
     * 新增
     * @param tbUser
     */
     void insert(TbUser tbUser);

    /**
     * 删除
     * @param id
     */
   void  delete(Long id);

    /**
     * 根据id查询信 息
     * @param id
     * @return
     */
   TbUser getById(Long id);

    /**
     * 更改
     * @param tbUser
     */
   void update(TbUser tbUser);

    /**
     * 根据用户名进行模糊查询
     * @param username
     * @return
     */
   List<TbUser> selectByUserName(String username);

    /**
     * 根据邮箱查询用户信息
     */
    TbUser getByEmail(String email);

    /**
     * 搜索
     * @param tbUser
     * @return
     */
     List<TbUser> search(TbUser tbUser);

}
