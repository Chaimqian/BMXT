package com.project.bm.entity;

import com.project.bm.entity.primaryKey.JSYWPrimaryKey;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

/**
 * @Author :LX
 * @CreateTime :2020/5/20
 * @Description :角色业务表
 */
@Entity
@IdClass(JSYWPrimaryKey.class)
@Table(name = "role_servicelinks_association")
@DynamicUpdate
public class JSYW {
    @Id
    private Integer JSID;
    @Id
    private Integer Unit_id;
    @Id
    private Integer YWID;
    @Id
    private Integer YWHJID;

    public Integer getJSID() {
        return JSID;
    }

    public void setJSID(Integer JSID) {
        this.JSID = JSID;
    }

    public Integer getUnit_id() {
        return Unit_id;
    }

    public void setUnit_id(Integer unit_id) {
        Unit_id = unit_id;
    }

    public Integer getYWID() {
        return YWID;
    }

    public void setYWID(Integer YWID) {
        this.YWID = YWID;
    }

    public Integer getYWHJID() {
        return YWHJID;
    }

    public void setYWHJID(Integer YWHJID) {
        this.YWHJID = YWHJID;
    }
}
