package com.project.bm.service.impl;

import com.project.bm.entity.JSYW;
import com.project.bm.repository.JSYWRepository;
import com.project.bm.service.JSYWService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author :LX
 * @CreateTime :2020/5/20
 * @Description :
 */
@Service
public class JSYWServiceImpl implements JSYWService {
    @Autowired
    private JSYWRepository jsywRepository;

    /**
     * //根据业务id和业务环节id查询
     * @param YWID
     * @param YWHJID
     * @return
     */
    @Override
    public JSYW findRoleByYwIdAndYwhjId(Integer YWID, Integer YWHJID) {
        return jsywRepository.findRoleByYwIdAndYwhjId(YWID,YWHJID);
    }

    /**
     * 根据业务id查询
     * @param ywId
     * @return
     */
    @Override
    public List<JSYW> findAllByYwId(Integer ywId) {
        return jsywRepository.findAllByYwId(ywId);
    }
}
