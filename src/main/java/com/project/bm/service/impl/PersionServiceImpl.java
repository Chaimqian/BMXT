package com.project.bm.service.impl;

import com.project.bm.entity.Person;
import com.project.bm.repository.PersonRepository;
import com.project.bm.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author :LX
 * @CreateTime :2020/5/13
 * @Description :
 */
@Service
public class PersionServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;
    /**
     * 根据id查询
     * @param personId
     * @return
     */
    @Override
    public Person findById(Integer personId) {

        return personRepository.findById(personId).get();
    }
}
