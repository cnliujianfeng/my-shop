package com.ljf.my.shop.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ljf.my.shop.commons.persistence.BaseEntity;


/**
 * 分类管理
 * ClassNmae:TbContentCategory
 * Package:com.ljf.my.shop.domain
 * Description:
 *
 * @DATE:2019/11/7 18:14
 * @Author:
 */
public class TbContentCategory extends BaseEntity {
    private Long parentId;
    private String name;
    private Integer status;
    private Integer sortOrder;
    @JsonProperty(value = "isParent")
    private boolean isParent;

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    public boolean isParent() {
        return isParent;
    }

    public void setParent(boolean parent) {
        isParent = parent;
    }
}
