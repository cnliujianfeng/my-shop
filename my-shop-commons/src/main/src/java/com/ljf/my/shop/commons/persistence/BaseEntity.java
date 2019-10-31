package com.ljf.my.shop.commons.persistence;

import java.io.Serializable;
import java.util.Date;

/**
 * ClassNmae:BaseEntity
 * Package:com.ljf.my.shop.commons.persistence
 * Description:
 *
 * @DATE:2019/10/31 19:43
 * @Author:
 */
public abstract class BaseEntity implements Serializable {
    private Long id;
    private Date created;
    private Date updated;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }
}
