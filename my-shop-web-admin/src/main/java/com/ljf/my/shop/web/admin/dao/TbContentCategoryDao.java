package com.ljf.my.shop.web.admin.dao;

import com.ljf.my.shop.domain.TbContentCategory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ClassNmae:TbContextCategoryDao
 * Package:com.ljf.my.shop.web.admin.dao
 * Description:
 *
 * @DATE:2019/11/7 18:23
 * @Author:
 */
@Repository
public interface TbContentCategoryDao {

    List<TbContentCategory> selectAll();

    /**
     * 根据父级节点id查询所有子节点
     * @param pid
     * @return
     */
    List<TbContentCategory> selectByPid(Long pid);
}
