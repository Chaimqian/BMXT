package com.project.bm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Author :LX
 * @CreateTime :2020/5/22
 * @Description : 业务表
 */
@Entity
@Table(name = "service")
public class YW {
    @Id
    private Integer YWID;
    @Column
    private Integer Unit_id;
    @Column
    private String YWNAME;
    @Column
    private Integer YWHJNUM;
    @Column
    private Integer YWSZRY;

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

    public String getYWNAME() {
        return YWNAME;
    }

    public void setYWNAME(String YWNAME) {
        this.YWNAME = YWNAME;
    }

    public Integer getYWHJNUM() {
        return YWHJNUM;
    }

    public void setYWHJNUM(Integer YWHJNUM) {
        this.YWHJNUM = YWHJNUM;
    }

    public Integer getYWSZRY() {
        return YWSZRY;
    }

    public void setYWSZRY(Integer YWSZRY) {
        this.YWSZRY = YWSZRY;
    }
}
