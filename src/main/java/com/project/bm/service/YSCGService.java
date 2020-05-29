package com.project.bm.service;

import com.project.bm.entity.YSCG;

import java.util.List;

/**
 * @Author :LX
 * @CreateTime :2020/5/17
 * @Description :
 */
public interface YSCGService {
    List<YSCG> findByPersonId(Integer personId);
}
