package com.project.bm.service;

import com.project.bm.entity.Person;
import com.project.bm.vo.ActCommentVo;
import com.project.bm.vo.ActTaskVo;
import org.activiti.engine.task.Task;

import java.util.List;

/**
 * @Author :LX
 * @CreateTime :2020/5/14
 * @Description :
 */
public interface WorkFlowService {
    //启动上岗审批流程
    void startProcess(Integer id);
    //查询当前用户任务
    List<ActTaskVo> queryCurrentUserTask(Integer personId);
    //根据任务查询申请单id
    Integer GetSgnumFromTask(Task task);
    //根据任务id查询连线信息
    List<String> queryOutComeByTaskId(String taskId);
    //根据部门id和角色id查询用户
    List<String> getPersonIdsByDeptIdAndRoleId(Integer deptId, Integer jsId);
    //根据任务id查询批注信息
    List<ActCommentVo> queryCommentByTaskId(String taskId);
    //上岗审批，办理业务
    void completeTask(String taskId, String val, String pizhu, Integer personId, String personName);
    //根据申请单id查询批注信息
    List<ActCommentVo> queryCommentBySgnum(Integer sgnum);
}
