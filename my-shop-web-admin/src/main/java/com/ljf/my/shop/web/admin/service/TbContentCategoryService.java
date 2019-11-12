package com.ljf.my.shop.web.admin.service;

import com.ljf.my.shop.domain.TbContentCategory;

import java.util.List;

/**
 * ClassNmae:TbContentCategoryService
 * Package:com.ljf.my.shop.web.admin.service
 * Description:
 *
 * @DATE:2019/11/7 18:33
 * @Author:
 */
public interface TbContentCategoryService  {

    List<TbContentCategory> selectAll();

    /**
     * 根据父级节点id查询所有子节点
     * @param pid
     * @return
     */
    List<TbContentCategory> selectByPid(Long pid);
}
