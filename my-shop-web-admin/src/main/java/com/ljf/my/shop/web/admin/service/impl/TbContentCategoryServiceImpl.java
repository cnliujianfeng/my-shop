package com.ljf.my.shop.web.admin.service.impl;

import com.ljf.my.shop.domain.TbContentCategory;
import com.ljf.my.shop.web.admin.dao.TbContentCategoryDao;
import com.ljf.my.shop.web.admin.service.TbContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassNmae:TbContentCategoryServiceImpl
 * Package:com.ljf.my.shop.web.admin.service.impl
 * Description:
 *
 * @DATE:2019/11/7 18:33
 * @Author:
 */
@Service
public class TbContentCategoryServiceImpl implements TbContentCategoryService {

    @Autowired
    private TbContentCategoryDao tbContentCategoryDao;

    @Override
    public List<TbContentCategory> selectAll() {

        return tbContentCategoryDao.selectAll();
    }
}
