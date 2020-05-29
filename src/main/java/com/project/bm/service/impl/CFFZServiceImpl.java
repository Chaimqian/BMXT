package com.project.bm.service.impl;

import com.project.bm.entity.CFFZ;
import com.project.bm.repository.CFFZRepository;
import com.project.bm.service.CFFZService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author :LX
 * @CreateTime :2020/5/17
 * @Description :
 */
@Service
public class CFFZServiceImpl implements CFFZService {
    @Autowired
    private CFFZRepository cffzRepository;
    /**
     * 根据人员id查询
     * @param personId
     * @return
     */
    @Override
    public List<CFFZ> finByPersionId(Integer personId) {
        return cffzRepository.findByPersonId(personId);
    }
}
