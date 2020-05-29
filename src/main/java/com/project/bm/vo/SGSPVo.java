package com.project.bm.vo;

import com.project.bm.entity.SGSP;

/**
 * @Author :LX
 * @CreateTime :2020/5/13
 * @Description :上岗审批返回到前台的类
 */
public class SGSPVo extends SGSP {
    //申请者名称
    private String name;

    ///业务名称
    private String sgspType;

    public String getSgspType() {
        return sgspType;
    }

    public void setSgspType(String sgspType) {
        this.sgspType = sgspType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
