package com.ljf.my.shop.web.admin.dao;

import com.ljf.my.shop.domain.TbContent;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * ClassNmae:TbContentDao
 * Package:com.ljf.my.shop.web.admin.dao
 * Description:
 *
 * @DATE:2019/11/12 11:33
 * @Author:
 */
@Repository
public interface TbContentDao {
    /**
     * 查询表全部信息
     * @return
     */
    List<TbContent> selectAll();

    /**
     * 新增
     * @param tbContent
     */
    void insert(TbContent tbContent);

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
    TbContent getById(Long id);

    /**
     * 更改
     * @param tbContent
     */
    void update(TbContent tbContent);




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
    List<TbContent> page(Map<String,Object> params);

    /**
     * 查询总笔数
     * @return
     */
    int count(TbContent tbContent);
}
