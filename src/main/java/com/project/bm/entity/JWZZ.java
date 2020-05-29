package com.project.bm.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity //告诉JPA这是一个实体类（和数据表映射的类）
@Table(name = "accepted_overseas_funding") //@Table来指定和哪个数据表对应;如果省略默认表名就是user；
public class JWZZ {
    @Id //这是一个主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增主键
    private Integer JWNUM;
    @Column
    private Integer Person_id;
    @Column //这是和数据表对应的一个列
    @JsonFormat(timezone = "GMT+8",pattern="yyyy-MM-dd")
    private Date TIME;
    @Column
    private String GJDQ;
    @Column
    private String JGNAME;
    @Column
    private String ZZNR;
    @Column
    private int MONEY;

    public Integer getJWNUM() {
        return JWNUM;
    }

    public void setJWNUM(Integer JWNUM) {
        this.JWNUM = JWNUM;
    }

    public Integer getPerson_id() {
        return Person_id;
    }

    public void setPerson_id(Integer person_id) {
        Person_id = person_id;
    }

    public Date getTIME() {
        return TIME;
    }

    public void setTIME(Date TIME) {
        this.TIME = TIME;
    }

    public String getGJDQ() {
        return GJDQ;
    }

    public void setGJDQ(String GJDQ) {
        this.GJDQ = GJDQ;
    }

    public String getJGNAME() {
        return JGNAME;
    }

    public void setJGNAME(String JGNAME) {
        this.JGNAME = JGNAME;
    }

    public String getZZNR() {
        return ZZNR;
    }

    public void setZZNR(String ZZNR) {
        this.ZZNR = ZZNR;
    }

    public int getMONEY() {
        return MONEY;
    }

    public void setMONEY(int MONEY) {
        this.MONEY = MONEY;
    }
}
