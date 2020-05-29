package com.project.bm.service.impl;

import com.project.bm.entity.YW;
import com.project.bm.repository.YWRepository;
import com.project.bm.service.YWService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @Author :LX
 * @CreateTime :2020/5/22
 * @Description :
 */
@Service
public class YWServiceImpl implements YWService {
    @Autowired
    private YWRepository ywRepository;
    /**
     * 查询业务表所有
     * @return
     */
    @Override
    public List<YW> findAll() {
        return ywRepository.findAll();
    }

    /**
     * 根基业务id，查询业务
     * @param ywid
     * @return
     */
    @Override
    public YW findById(Integer ywid) {
        YW yw = ywRepository.findById(ywid).get();
        return yw;
    }
}
