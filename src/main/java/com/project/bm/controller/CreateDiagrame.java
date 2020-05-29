package com.project.bm.controller;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author :LX
 * @CreateTime :2020/5/11
 * @Description : 部署流程
 */
@RestController
public class CreateDiagrame {
    //管理流程定义
    @Autowired
    private RepositoryService repositoryService;
    //执行管理，包括启动、推进、删除流程实例等操作
    @Autowired
    private RuntimeService runtimeService;
    //任务管理
    @Autowired
    private TaskService taskService;

    /**
     * 部署流程
     * @return
     */
    @RequestMapping("/deploySGSP")
    public void deploySGSP(){
        //根据bpmn部署
        Deployment deployment = repositoryService.createDeployment() //创建一个部署对象
                .name("SGSP")//添加部署对象名称
                .addClasspathResource("static/diagram/SGSP.bpmn")
                .addClasspathResource("static/diagram/SGSP.bpmn.png")
                .deploy();//完成部署
       System.out.println("部署id"+deployment.getId());
        System.out.println("部署名称"+deployment.getName());
    }
}
