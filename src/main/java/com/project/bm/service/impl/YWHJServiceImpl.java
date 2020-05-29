package com.project.bm.service.impl;

import com.project.bm.entity.YWHJ;
import com.project.bm.repository.YWHJRepository;
import com.project.bm.service.YWHJService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author :LX
 * @CreateTime :2020/5/20
 * @Description :
 */
@Service
public class YWHJServiceImpl implements YWHJService {

    @Autowired
    private YWHJRepository ywhjRepository;
    /**
     * 根据业务id查询到所有的业务流程
     * @param ywId
     * @return
     */
    @Override
    public List<YWHJ> getAllByYwId(int ywId) {
        return ywhjRepository.findAllByYwId(ywId);
    }
}
