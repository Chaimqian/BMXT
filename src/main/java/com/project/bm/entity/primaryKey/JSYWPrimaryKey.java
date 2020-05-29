package com.project.bm.entity.primaryKey;

import javax.persistence.Id;
import java.io.Serializable;

/**
 * @Author :LX
 * @CreateTime :2020/5/20
 * @Description :角色业务环节关联表主键
 */
public class JSYWPrimaryKey implements Serializable {
    private Integer JSID;
    private Integer Unit_id;
    private Integer YWID;
    private Integer YWHJID;
}
