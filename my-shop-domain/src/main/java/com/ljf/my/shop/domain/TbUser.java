package com.ljf.my.shop.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ljf.my.shop.commons.persistence.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * ClassNmae:TbUser
 * Package:com.ljf.my.shop.domain
 * Description:
 *
 * @DATE:2019/10/24 11:54
 * @Author:
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TbUser extends BaseEntity {

    private String username;
    private String password;
    private String phone;
    private String email;





}
