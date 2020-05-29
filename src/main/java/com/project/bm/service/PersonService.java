package com.project.bm.service;

import com.project.bm.entity.Person;

/**
 * @Author :LX
 * @CreateTime :2020/5/13
 * @Description :
 */
public interface PersonService {

    Person findById(Integer personId);
}
