package com.ljf.my.shop.web.admin.service;

import com.ljf.my.shop.commons.dto.BaseResult;
import com.ljf.my.shop.commons.dto.Pageinfo;
import com.ljf.my.shop.domain.TbContent;

import java.util.List;
import java.util.Map;

/**
 * ClassNmae:TbContentService
 * Package:com.ljf.my.shop.web.admin.service
 * Description:
 *
 * @DATE:2019/11/12 11:33
 * @Author:
 */
public interface TbContentService {

    /**
     * 查询表全部信息
     * @return
     */
    List<TbContent> selectAll();

    /**
     * 新增
     * @param tbContent
     */
    BaseResult save(TbContent tbContent);

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
     * @param tbContent 需要两个参数一个是start 记录数据开始位置 一个是length 每页记录数
     * @return
     */
    Pageinfo<TbContent> page(int start, int length, int draw, TbContent tbContent);

    /**
     * 查询总笔数
     * @return
     */
    int count(TbContent tbContent);
}
