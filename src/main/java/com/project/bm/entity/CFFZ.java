package com.project.bm.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

//使用JPA注解配置映射关系
@Entity //告诉JPA这是一个实体类（和数据表映射的类）
@Table(name = "disciplinary_criminal_record") //@Table来指定和哪个数据表对应;如果省略默认表名就是user；
public class CFFZ {
    @Id //这是一个主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增主键
    private Integer CFNUM;
    @Column
    private Integer Person_id;
    @Column
    @DateTimeFormat(pattern="yyyy-mm-dd")
    private Date TIME;
    @Column
    private String CLJG;
    @Column
    private String CLYY;
    @Column
    private String CLJGNAME;

    public Integer getCFNUM() {
        return CFNUM;
    }

    public void setCFNUM(Integer CFNUM) {
        this.CFNUM = CFNUM;
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

    public String getCLJG() {
        return CLJG;
    }

    public void setCLJG(String CLJG) {
        this.CLJG = CLJG;
    }

    public String getCLYY() {
        return CLYY;
    }

    public void setCLYY(String CLYY) {
        this.CLYY = CLYY;
    }

    public String getCLJGNAME() {
        return CLJGNAME;
    }

    public void setCLJGNAME(String CLJGNAME) {
        this.CLJGNAME = CLJGNAME;
    }
}
