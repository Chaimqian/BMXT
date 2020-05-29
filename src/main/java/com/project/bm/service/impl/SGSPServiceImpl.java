package com.project.bm.service.impl;

import com.project.bm.entity.SGSP;
import com.project.bm.repository.SGSPRepository;
import com.project.bm.service.SGSPService;
import com.project.bm.utils.Constant;
import com.project.bm.utils.SessionUtil;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @Author :LX
 * @CreateTime :2020/5/11
 * @Description :
 */

@Service
public class SGSPServiceImpl implements SGSPService {

    @Autowired
    private SGSPRepository sgspRepository;


    //根据用户id查询 按时间降序排序
    @Override
    public List<SGSP> findByPersonIdOrderByApplyTime(Integer personId) {
        return sgspRepository.findByPersonIdOrderByApplyTime(personId);
    }



    /**
     * 根据主键查询
     * @param i
     * @return
     */
    @Override
    public SGSP findById(int i) {
        return sgspRepository.findById(i).get();
    }

    /**
     * 保存
     * @param sgsp
     */
    @Override
    public void save(SGSP sgsp) {
        sgspRepository.save(sgsp);
    }
}
