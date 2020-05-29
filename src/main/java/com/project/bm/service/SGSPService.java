package com.project.bm.service;

import com.project.bm.entity.SGSP;
import com.project.bm.repository.SGSPRepository;
import com.project.bm.vo.SGSPVo;
import org.activiti.engine.task.Task;

import java.util.List;
import java.util.Optional;

/**
 * @Author :LX
 * @CreateTime :2020/5/11
 * @Description :
 */
public interface SGSPService {
    //根据用户id查询申请 按照时间降序排序
    List<SGSP> findByPersonIdOrderByApplyTime(Integer personId);


    SGSP findById(int i);
    //保存
    void save(SGSP sgsp);
}
