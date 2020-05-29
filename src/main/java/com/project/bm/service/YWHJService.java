package com.project.bm.service;

import com.project.bm.entity.YWHJ;

import java.util.List;

/**
 * @Author :LX
 * @CreateTime :2020/5/20
 * @Description :
 */
public interface YWHJService {
    List<YWHJ> getAllByYwId(int ywId);
}
