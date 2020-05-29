package com.project.bm.service;

import com.project.bm.entity.YW;

import java.util.List;

/**
 * @Author :LX
 * @CreateTime :2020/5/22
 * @Description :业务表
 */
public interface YWService {
    //查询所有业务
    List<YW> findAll();

    YW findById(Integer ywid);
    //根据id查询业务
    
}
