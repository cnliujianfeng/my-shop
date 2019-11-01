package com.ljf.my.shop.web.admin.service.impl;

import com.ljf.my.shop.commons.dto.BaseResult;
import com.ljf.my.shop.commons.dto.Pageinfo;
import com.ljf.my.shop.commons.utils.RegexpUtils;
import com.ljf.my.shop.domain.TbUser;
import com.ljf.my.shop.domain.User;
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
        BaseResult baseResult = checkTbUser(tbUser);
        //通过验证
        if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS) {
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
            baseResult.setMessage("保存用户信息成功");
        }
        return baseResult;

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
     * 用户信息的有效验证
     *
     * @param tbUser
     */
    public BaseResult checkTbUser(TbUser tbUser) {
        BaseResult baseResult = BaseResult.success();

        //非空验证
        if (StringUtils.isBlank(tbUser.getEmail())) {
            baseResult = BaseResult.fail("邮箱不能为空请重新输入");
        } else if (!RegexpUtils.checkEmail(tbUser.getEmail())) {
            baseResult = BaseResult.fail("邮箱格式不正确请重新输入");
        } else if (StringUtils.isBlank(tbUser.getPassword())) {
            baseResult = BaseResult.fail("密码不能为空请重新输入");
        } else if (StringUtils.isBlank(tbUser.getUsername())) {
            baseResult = BaseResult.fail("姓名不能为空请重新输入");
        } else if (StringUtils.isBlank(tbUser.getPhone())) {
            baseResult = BaseResult.fail("手机不能为空请重新输入");
        } else if (!RegexpUtils.checkPhone(tbUser.getPhone())) {
            baseResult = BaseResult.fail("手机格式不正确请重新输入");
        }


        return baseResult;
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
