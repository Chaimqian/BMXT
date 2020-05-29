package com.project.bm.service.impl;

import com.project.bm.entity.RYJSGL;
import com.project.bm.repository.RYJSGLRepository;
import com.project.bm.service.RYJSGLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author :LX
 * @CreateTime :2020/5/20
 * @Description :人员角色管理
 */
@Service
public class RYJSGLServiceImpl implements RYJSGLService {
    @Autowired
    private RYJSGLRepository ryjsglRepository;
    /**
     * 根据人员id查询角色
     * @param personId
     * @return
     */
    @Override
    public List<RYJSGL> getAllByPersonId(Integer personId) {
        return ryjsglRepository.getAllByPersonId(personId);
    }

    /**
     * 根据角色id查询下一个任务的办理人
     * @param jsId
     * @return
     */
    @Override
    public List<String> findAllPersonByjsId(Integer jsId) {
        return ryjsglRepository.findAllPersonByjsId(jsId);
    }
}
