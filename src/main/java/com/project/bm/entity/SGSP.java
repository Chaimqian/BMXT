package com.project.bm.entity;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity //告诉JPA这是一个实体类（和数据表映射的类）
@Table(name = "onduty_qualification_approval_form") //@Table来指定和哪个数据表对应;如果省略默认表名就是user；
public class SGSP {
    public int getSGNUM() {
        return SGNUM;
    }

    public void setSGNUM(int SGNUM) {
        this.SGNUM = SGNUM;
    }

    public int getPerson_id() {
        return Person_id;
    }

    public void setPerson_id(int person_id) {
        Person_id = person_id;
    }

//    public int getUnit_id() {
//        return Unit_id;
//    }
//
//    public void setUnit_id(int unit_id) {
//        Unit_id = unit_id;
//    }

    public boolean isPXOK() {
        return PXOK;
    }

    public void setPXOK(boolean PXOK) {
        this.PXOK = PXOK;
    }

    public Date getPXTIME() {
        return PXTIME;
    }

    public void setPXTIME(Date PXTIME) {
        this.PXTIME = PXTIME;
    }

    public boolean isSGCERT() {
        return SGCERT;
    }

    public void setSGCERT(boolean SGCERT) {
        this.SGCERT = SGCERT;
    }

    public Date getSGZSXTIME() {
        return SGZSXTIME;
    }

    public void setSGZSXTIME(Date SGZSXTIME) {
        this.SGZSXTIME = SGZSXTIME;
    }

    public String getQISMOK() {
        return QISMOK;
    }

    public void setQISMOK(String QISMOK) {
        this.QISMOK = QISMOK;
    }

    public String getBMSHIDEA() {
        return BMSHIDEA;
    }

    public void setBMSHIDEA(String BMSHIDEA) {
        this.BMSHIDEA = BMSHIDEA;
    }

    public String getBMSHRY() {
        return BMSHRY;
    }

    public void setBMSHRY(String BMSHRY) {
        this.BMSHRY = BMSHRY;
    }

    public Date getBMSHTIME() {
        return BMSHTIME;
    }

    public void setBMSHTIME(Date BMSHTIME) {
        this.BMSHTIME = BMSHTIME;
    }



    public String getBMBSHRY() {
        return BMBSHRY;
    }

    public void setBMBSHRY(String BMBSHRY) {
        this.BMBSHRY = BMBSHRY;
    }

    public Date getBMBSHTIME() {
        return BMBSHTIME;
    }

    public void setBMBSHTIME(Date BMBSHTIME) {
        this.BMBSHTIME = BMBSHTIME;
    }



    public String getBMXZSHIDEA() {
        return BMXZSHIDEA;
    }

    public void setBMXZSHIDEA(String BMXZSHIDEA) {
        this.BMXZSHIDEA = BMXZSHIDEA;
    }

    public String getBMXZSHRY() {
        return BMXZSHRY;
    }

    public void setBMXZSHRY(String BMXZSHRY) {
        this.BMXZSHRY = BMXZSHRY;
    }

    public Date getBMXZSHTIME() {
        return BMXZSHTIME;
    }

    public void setBMXZSHTIME(Date BMXZSHTIME) {
        this.BMXZSHTIME = BMXZSHTIME;
    }



    public int getSGZRSID() {
        return SGZRSID;
    }

    public void setSGZRSID(int SGZRSID) {
        this.SGZRSID = SGZRSID;
    }

    public String getBMSHSTATE() {
        return BMSHSTATE;
    }

    public void setBMSHSTATE(String BMSHSTATE) {
        this.BMSHSTATE = BMSHSTATE;
    }

    public String getBMBSHSTATE() {
        return BMBSHSTATE;
    }

    public void setBMBSHSTATE(String BMBSHSTATE) {
        this.BMBSHSTATE = BMBSHSTATE;
    }

    public String getBMXZSHSTATE() {
        return BMXZSHSTATE;
    }

    public void setBMXZSHSTATE(String BMXZSHSTATE) {
        this.BMXZSHSTATE = BMXZSHSTATE;
    }

    public Date getSGZSXXTIME() {
        return SGZSXXTIME;
    }

    public void setSGZSXXTIME(Date SGZSXXTIME) {
        this.SGZSXXTIME = SGZSXXTIME;
    }
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(Date applyDate) {
        this.applyDate = applyDate;
    }

    public int getYWID() {
        return YWID;
    }

    public void setYWID(int YWID) {
        this.YWID = YWID;
    }

    public int getIsDel() {
        return isDel;
    }

    public void setIsDel(int isDel) {
        this.isDel = isDel;
    }

    public String getBMBSHIDEA() {
        return BMBSHIDEA;
    }

    public void setBMBSHIDEA(String BMBSHIDEA) {
        this.BMBSHIDEA = BMBSHIDEA;
    }

    @Id //这是一个主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增主键
    private int SGNUM;
    @Column
    private int Person_id;
    private boolean PXOK;
    @JsonFormat(timezone = "GMT+8",pattern="yyyy-MM-dd")
    private Date PXTIME;
    private boolean SGCERT;
    @JsonFormat(timezone = "GMT+8",pattern="yyyy-MM-dd")
    private Date SGZSXTIME;
    @JsonFormat(timezone = "GMT+8",pattern="yyyy-MM-dd")
    private Date SGZSXXTIME;
    private String QISMOK;

    private String BMSHIDEA;
    private String BMSHRY;
    @JsonFormat(timezone = "GMT+8",pattern="yyyy-MM-dd")
    private Date BMSHTIME;
    private String BMSHSTATE;

    private String BMBSHRY;
    @JsonFormat(timezone = "GMT+8",pattern="yyyy-MM-dd")
    private Date BMBSHTIME;
    private String BMBSHSTATE;

    private String BMXZSHIDEA;
    private String BMXZSHRY;
    @JsonFormat(timezone = "GMT+8",pattern="yyyy-MM-dd")
    private Date BMXZSHTIME;
    private String BMXZSHSTATE;

    private int SGZRSID;
    private int status; //表示状态
    private int YWID;
//    @DateTimeFormat(pattern="yyyy-mm-dd")
    @JsonFormat(timezone = "GMT+8",pattern="yyyy-MM-dd")
    private Date applyDate; //申请时间
    //是否删除
    private int isDel;

    private String BMBSHIDEA;

}
