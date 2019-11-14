package com.ljf.my.shop.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ljf.my.shop.commons.persistence.BaseEntity;
import com.ljf.my.shop.commons.utils.RegexpUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
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

    @Length(min = 6,max = 20,message = "姓名的长度必须介于6-20位之间")
    private String username;
    @JsonIgnore
    @Length(min = 6,max = 30,message = "密码长度必须设置为6-20位")
    private String password;
    @Pattern(regexp = RegexpUtils.PHONE,message = "手机号格式不正确")
    private String phone;
    @Pattern(regexp = RegexpUtils.EMAIL,message = "邮箱格式不正确")
    private String email;





}
