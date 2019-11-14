package com.ljf.my.shop.web.admin.service.impl;

import com.ljf.my.shop.commons.dto.BaseResult;
import com.ljf.my.shop.commons.dto.Pageinfo;
import com.ljf.my.shop.commons.utils.RegexpUtils;
import com.ljf.my.shop.commons.validator.BeanValidator;
import com.ljf.my.shop.domain.TbUser;
import com.ljf.my.shop.web.admin.dao.TbUserDao;
import com.ljf.my.shop.web.admin.service.TbUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ClassNmae:TbUserServiceImpl
 * Package:com.ljf.my.shop.web.admin.dao.impl
 * Description:
 *
 * @DATE:2019/10/24 12:14
 * @Author:
 */
@Service
public class TbUserServiceImpl implements TbUserService {
    @Autowired
    private TbUserDao tbUserDao;

    @Override
    public List<TbUser> selectAll() {

        return tbUserDao.selectAll();
    }

    /**
     * 保存用户信息
     * @param tbUser
     * @return
     */
    @Override
    public BaseResult save(TbUser tbUser) {
        String validator = BeanValidator.validator(tbUser);
        //验证不通过
        if (validator!=null){

            return BaseResult.fail(validator);


            //通过验证
        }else {
            tbUser.setUpdated(new Date());
            //新增用户
            if (tbUser.getId() == null) {
                //密码加密处理
                tbUser.setPassword(DigestUtils.md5DigestAsHex(tbUser.getPassword().getBytes()));
                tbUser.setCreated(new Date());
                tbUserDao.insert(tbUser);


                //编辑用户
            } else {
                System.out.println("进入保存了");
                tbUserDao.update(tbUser);
            }
            return BaseResult.success("保存用户信息成功");
        }



    }

    /**
     * 删除用户信息
     * @param id
     */
    @Override
    public void delete(Long id) {
        tbUserDao.delete(id);
    }

    /**
     * 根据id查询用户信息
     * @param id
     * @return
     */
    @Override
    public TbUser getById(Long id) {
        return tbUserDao.getById(id);
    }

    /**
     * 更新用户信息
     * @param tbUser
     */
    @Override
    public void update(TbUser tbUser) {
        tbUserDao.update(tbUser);
    }

    /**
     * 用户登录
     * @param email
     * @param password
     * @return
     */
    @Override
    public TbUser login(String email, String password) {
        TbUser tbUser = tbUserDao.getByEmail(email);
        if (tbUser != null) {
            //明文密码加密
            String md5Password = DigestUtils.md5DigestAsHex(password.getBytes());
            //判断加密密码和数据库中密码是否匹配
            if (md5Password.equals(tbUser.getPassword())) {
                return tbUser;
            }
        }
        return null;
    }






    /**
     * 批量删除
     *
     * @param ids
     */
    @Override
    public void deleteMulti(String[] ids) {
        tbUserDao.deleteMulti(ids);
    }

    /**
     * 分页查询
     *
     * @param start
     * @param length
     * @return
     */
    @Override
    public Pageinfo<TbUser> page(int start, int length, int draw,TbUser tbUser) {
        int count = tbUserDao.count(tbUser);

        Map<String, Object> params = new HashMap<>();
        params.put("start", start);
        params.put("length", length);
        params.put("tbUser",tbUser);

        Pageinfo<TbUser> pageinfo = new Pageinfo<>();
        pageinfo.setDraw(draw);
        pageinfo.setRecordsTotal(count);
        pageinfo.setRecordsFiltered(count);
        pageinfo.setData(tbUserDao.page(params));


        return pageinfo;
    }

    /**
     * 查询总笔数
     *
     * @return
     */
    @Override
    public int count(TbUser tbUser) {
        return tbUserDao.count(tbUser);
    }

}
