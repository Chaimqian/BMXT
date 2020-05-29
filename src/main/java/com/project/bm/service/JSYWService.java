package com.project.bm.service;

import com.project.bm.entity.JSYW;

import java.util.List;

/**
 * @Author :LX
 * @CreateTime :2020/5/20
 * @Description :
 */
public interface JSYWService {
    //根据业务id和业务环节id查询
    JSYW findRoleByYwIdAndYwhjId(Integer ywId, Integer nextYwhjId);
    //根据业务id查询出所有的信息
    List<JSYW> findAllByYwId(Integer ywId);
}
