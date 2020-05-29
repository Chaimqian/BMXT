package com.project.bm.controller;

import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.lang.annotation.Target;
import java.util.List;

/**
 * @Author :LX
 * @CreateTime :2020/5/9
 * @Description :
 */
@Controller
public class IndexController {

    @Autowired
    private TaskService taskService;
    /**
     * 跳转到首页
     * @return
     */
    @GetMapping("/")
    public String index(HttpSession session)
    {
        //提交申请
//        Integer personId = 12;
//        String personName = "小张";
        //部门审核人
        Integer personId = 13;
        String personName = "张三";
        //保密办主任
//        Integer personId = 16;
//        String personName = "李四";
//        Integer personId = 18;
//        String personName = "赵六";
        session.setAttribute("userId",personId);
        session.setAttribute("userName",personName);
        session.setMaxInactiveInterval(-1);
        //登录时查看组任务
        List<Task> tasks =taskService.createTaskQuery().taskCandidateUser(personId.toString()).list();
        if (null!= tasks && tasks.size()>0){
            for (Task task:tasks) {
                //拿到taskid
                String taskId = task.getId();
                //进行任务拾取
                taskService.claim(taskId,personId.toString());
            }
        }
        return "index";
    }
    /**
     * 定级审查跳转跳转
     * @return
     */
    @RequestMapping("/RYDJSP")
    public String RYDJSP(){
        return "RYDJSP";
    }

    /**
     * 上岗资格审批跳转
     * @return
     */
    @RequestMapping("/CGJLR")
    public String CGJLR(){
        return "CGJLR";
    }


}
