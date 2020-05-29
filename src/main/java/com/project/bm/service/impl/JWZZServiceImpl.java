package com.project.bm.service.impl;

import com.project.bm.entity.JWZZ;
import com.project.bm.repository.JWZZRepository;
import com.project.bm.service.JWZZService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author :LX
 * @CreateTime :2020/5/17
 * @Description :
 */
@Service
public class JWZZServiceImpl implements JWZZService {
    @Autowired
    private JWZZRepository jwzzRepository;
    /**
     * 根据人员id查询
     * @param personId
     * @return list
     */
    @Override
    public List<JWZZ> findByPersion(Integer personId) {
        return jwzzRepository.findByPersonId(personId);
    }
}
