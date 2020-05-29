package com.project.bm.service.impl;

import com.project.bm.entity.YSCG;
import com.project.bm.repository.YSCGRepository;
import com.project.bm.service.YSCGService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author :LX
 * @CreateTime :2020/5/17
 * @Description :
 */
@Service
public class YSCGServiceImpl implements YSCGService {
    @Autowired
    private YSCGRepository yscgRepository;
    /**
     * 根据用户id查询因私出国记录
     * @param personId
     * @return
     */
    @Override
    public List<YSCG> findByPersonId(Integer personId) {
        return yscgRepository.findByPersonId(personId);
    }
}
