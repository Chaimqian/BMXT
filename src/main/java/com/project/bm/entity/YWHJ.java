package com.project.bm.entity;

import com.project.bm.entity.primaryKey.YWHJPrimaryKey;

import javax.persistence.*;

/**
 * @Author :LX
 * @CreateTime :2020/5/20
 * @Description : 业务环节实体
 */
@Entity
@Table(name = "service_links")
@IdClass(YWHJPrimaryKey.class)
public class YWHJ {
    @Id
    private Integer YWID;
    @Id
    private Integer Unit_id;
    @Id
    private Integer YWHJID;
    @Column
    private String YWHJNAME;
    @Column
    private String YWHJDES;

    public Integer getYWID() {
        return YWID;
    }

    public void setYWID(Integer YWID) {
        this.YWID = YWID;
    }

    public Integer getUnit_id() {
        return Unit_id;
    }

    public void setUnit_id(Integer unit_id) {
        Unit_id = unit_id;
    }

    public Integer getYWHJID() {
        return YWHJID;
    }

    public void setYWHJID(Integer YWHJID) {
        this.YWHJID = YWHJID;
    }

    public String getYWHJNAME() {
        return YWHJNAME;
    }

    public void setYWHJNAME(String YWHJNAME) {
        this.YWHJNAME = YWHJNAME;
    }

    public String getYWHJDES() {
        return YWHJDES;
    }

    public void setYWHJDES(String YWHJDES) {
        this.YWHJDES = YWHJDES;
    }
}
