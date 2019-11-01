package com.ljf.my.shop.web.admin.dao;


import com.ljf.my.shop.domain.TbUser;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

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
     * 根据邮箱查询用户信息
     */
    TbUser getByEmail(String email);

    /**
     * 批量删除
     * @param ids
     */
    void deleteMulti(String[] ids);

    /**
     * 分页查询
     * @param params 需要两个参数一个是start 记录数据开始位置 一个是length 每页记录数
     * @return
     */
    List<TbUser> page(Map<String,Object> params);

    /**
     * 查询总笔数
     * @return
     */
    int count(TbUser tbUser);
}
