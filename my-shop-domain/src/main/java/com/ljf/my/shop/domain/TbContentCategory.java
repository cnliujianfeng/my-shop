package com.ljf.my.shop.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ljf.my.shop.commons.persistence.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 分类管理
 * ClassNmae:TbContentCategory
 * Package:com.ljf.my.shop.domain
 * Description:
 *
 * @DATE:2019/11/7 18:14
 * @Author:
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TbContentCategory extends BaseEntity {
    private Long parentId;
    private String name;
    private Integer status;
    private Integer sortOrder;
    @JsonProperty(value = "isParent")
    private Boolean isParent;
    private TbContentCategory parent;


}
