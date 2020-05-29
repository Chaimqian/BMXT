package com.project.bm.service;

import com.project.bm.entity.CFFZ;

import java.util.List;

/**
 * @Author :LX
 * @CreateTime :2020/5/17
 * @Description :
 */
public interface CFFZService {
    //根据人员id查询
    List<CFFZ> finByPersionId(Integer personId);
}
