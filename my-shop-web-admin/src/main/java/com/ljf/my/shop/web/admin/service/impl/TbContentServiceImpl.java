package com.ljf.my.shop.web.admin.service.impl;

import com.ljf.my.shop.commons.dto.BaseResult;
import com.ljf.my.shop.commons.dto.Pageinfo;
import com.ljf.my.shop.commons.utils.RegexpUtils;
import com.ljf.my.shop.commons.validator.BeanValidator;
import com.ljf.my.shop.domain.TbContent;
import com.ljf.my.shop.domain.TbUser;
import com.ljf.my.shop.web.admin.dao.TbContentDao;
import com.ljf.my.shop.web.admin.service.TbContentService;
import lombok.val;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ClassNmae:TbContentServiceImpl
 * Package:com.ljf.my.shop.web.admin.service.impl
 * Description:
 *
 * @DATE:2019/11/12 11:33
 * @Author:
 */
@Service
public class TbContentServiceImpl implements TbContentService {
    @Autowired
    private TbContentDao tbContentDao;

    @Override
    public List<TbContent> selectAll() {
        return tbContentDao.selectAll();
    }

    /**
     * 保存用户信息
     *
     * @param tbContent
     * @return
     */
    @Override
    public BaseResult save(TbContent tbContent) {
        String validator = BeanValidator.validator(tbContent);

        //验证不通过
        if (validator != null) {
            return BaseResult.fail(validator);

            //验证通过
        } else {
            tbContent.setUpdated(new Date());
            //新增
            if (tbContent.getId() == null) {
                //密码加密处理
                tbContent.setCreated(new Date());
                tbContentDao.insert(tbContent);


                //编辑用户
            } else {
                System.out.println("进入保存了");
                tbContentDao.update(tbContent);
            }
            return BaseResult.success(("保存内容信息成功"));

        }

    }

    /**
     * 删除用户信息
     *
     * @param id
     */
    @Override
    public void delete(Long id) {

        tbContentDao.delete(id);
    }


    /**
     * 根据id查询信息
     *
     * @param id
     * @return
     */
    @Override
    public TbContent getById(Long id) {
        return tbContentDao.getById(id);
    }

    /**
     * 更新信息
     *
     * @param tbContent
     */
    @Override
    public void update(TbContent tbContent) {
        tbContentDao.update(tbContent);
    }

    /**
     * 批量删除
     *
     * @param ids
     */
    @Override
    public void deleteMulti(String[] ids) {
        tbContentDao.deleteMulti(ids);
    }


    /**
     * 分页查询
     *
     * @param start
     * @param length
     * @return
     */
    @Override
    public Pageinfo<TbContent> page(int start, int length, int draw, TbContent tbContent) {
        int count = tbContentDao.count(tbContent);

        Map<String, Object> params = new HashMap<>();
        params.put("start", start);
        params.put("length", length);
        params.put("tbContent", tbContent);

        Pageinfo<TbContent> pageinfo = new Pageinfo<>();
        pageinfo.setDraw(draw);
        pageinfo.setRecordsTotal(count);
        pageinfo.setRecordsFiltered(count);
        pageinfo.setData(tbContentDao.page(params));


        return pageinfo;
    }

    /**
     * 查询总笔数
     *
     * @return
     */
    @Override
    public int count(TbContent tbContent) {
        return tbContentDao.count(tbContent);
    }


}
