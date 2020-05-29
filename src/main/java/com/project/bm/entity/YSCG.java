package com.project.bm.entity;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity //告诉JPA这是一个实体类（和数据表映射的类）
@Table(name = "private_travel_abroad") //@Table来指定和哪个数据表对应;如果省略默认表名就是user；
public class YSCG {
    @Id //这是一个主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增主键
    private int CJNUM;

    @Column
    private int Person_id;
    @Column //省略默认列名就是属性名
    @JsonFormat(timezone = "GMT+8",pattern="yyyy-MM-dd")
    private Date QITIME=new Date();
    @Column
    @JsonFormat(timezone = "GMT+8",pattern="yyyy-MM-dd")
    private Date JSTIME=new Date();
    @Column
    private String SDGJDQ;
    @Column
    private String CJSY;

    public int getCJNUM() {
        return CJNUM;
    }

    public void setCJNUM(int CJNUM) {
        this.CJNUM = CJNUM;
    }

    public int getPerson_id() {
        return Person_id;
    }

    public void setPerson_id(int person_id) {
        Person_id = person_id;
    }

    public Date getQITIME() {
        return QITIME;
    }

    public void setQITIME(Date QITIME) {
        this.QITIME = QITIME;
    }

    public Date getJSTIME() {
        return JSTIME;
    }

    public void setJSTIME(Date JSTIME) {
        this.JSTIME = JSTIME;
    }

    public String getSDGJDQ() {
        return SDGJDQ;
    }

    public void setSDGJDQ(String SDGJDQ) {
        this.SDGJDQ = SDGJDQ;
    }

    public String getCJSY() {
        return CJSY;
    }

    public void setCJSY(String CJSY) {
        this.CJSY = CJSY;
    }
}
