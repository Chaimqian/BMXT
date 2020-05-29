package com.project.bm.service;

import com.project.bm.entity.RYJSGL;

import java.util.List;

/**
 * @Author :LX
 * @CreateTime :2020/5/20
 * @Description :
 */
public interface RYJSGLService {
    //根据人员id查询角色
    List<RYJSGL> getAllByPersonId(Integer personId);
    //根据角色id查询下一个任务的办理人
    List<String> findAllPersonByjsId(Integer jsId);
}
