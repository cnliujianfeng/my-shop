package com.ljf.my.shop.web.admin.service;

import com.ljf.my.shop.commons.dto.BaseResult;
import com.ljf.my.shop.domain.TbUser;


import java.util.List;

/**
 * ClassNmae:TbUserService
 * Package:com.ljf.my.shop.web.admin.service
 * Description:
 *
 * @DATE:2019/10/24 12:14
 * @Author:
 */
public interface TbUserService {

    List<TbUser> selectAll();

    BaseResult save(TbUser tbUser);

    void delete(Long id);

    TbUser getById(Long id);

    void update(TbUser tbUser);

    List<TbUser> selectByUserName(String username);

    TbUser login(String email, String password);

    List<TbUser> search(TbUser tbUser);

}