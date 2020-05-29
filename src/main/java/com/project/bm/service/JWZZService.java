package com.project.bm.service;

import com.project.bm.entity.JWZZ;

import java.util.List;

/**
 * @Author :LX
 * @CreateTime :2020/5/17
 * @Description :
 */
public interface JWZZService {
    //根据人员id查询
    List<JWZZ> findByPersion(Integer personId);
}
