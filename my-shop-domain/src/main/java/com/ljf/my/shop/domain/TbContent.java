package com.ljf.my.shop.domain;

import com.ljf.my.shop.commons.persistence.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 内容管理
 * ClassNmaee:TbContnt
 * Package:com.ljf.my.shop.domain
 * Description:
 *
 * @DATE:2019/11/12 11:25
 * @Author:
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TbContent extends BaseEntity {

    private Long categoryId;
    private String title;
    private String subTitle;
    private String titleDesc;
    private String url;
    private String pic;
    private String pic2;
    private String content;


}
