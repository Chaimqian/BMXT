package com.project.bm.entity;

import com.project.bm.entity.primaryKey.RYJSGLPrimaryKey;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

/**
 * @Author :LX
 * @CreateTime :2020/5/20
 * @Description :人员角色配置实体
 */
@Entity
@IdClass(RYJSGLPrimaryKey.class)
@Table(name = "person_role_association")
public class RYJSGL {
    @Id
    private Integer Person_id;
    @Id
    private Integer JSID;

    public Integer getPerson_id() {
        return Person_id;
    }

    public void setPerson_id(Integer person_id) {
        Person_id = person_id;
    }

    public Integer getJSID() {
        return JSID;
    }

    public void setJSID(Integer JSID) {
        this.JSID = JSID;
    }
}
