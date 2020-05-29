package com.project.bm.service.impl;

import com.project.bm.entity.*;
import com.project.bm.repository.PersonRepository;
import com.project.bm.repository.SGSPRepository;
import com.project.bm.service.*;
import com.project.bm.utils.Constant;
import com.project.bm.utils.SessionUtil;
import com.project.bm.vo.ActCommentVo;
import com.project.bm.vo.ActTaskVo;
import com.sun.xml.bind.v2.TODO;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;

import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.impl.identity.Authentication;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;

import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @Author :LX
 * @CreateTime :2020/5/14
 * @Description :
 */

@Service
@Transactional
public class WorkFlowServiceImpl implements WorkFlowService {
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private SGSPRepository sgspRepository;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private YWHJService ywhjService;
    @Autowired
    private JSYWService jsywService;
    @Autowired
    private RYJSGLService ryjsglService;
    @Autowired
    private SGSPService sgspService;
    @Autowired
    private PersonService personService;
    @Autowired
    private WorkFlowService workFlowService;
    @Autowired
    private HistoryService historyService;
    /**
     * 启动上岗审批流程
     * @param id 上岗申请id
     */
    @Override
    public void startProcess(Integer id) {
        //得到流程定义的key，这里流程的key就是SGSP,即entity里面类的名称
        String processDefineKey = SGSP.class.getSimpleName();
        //流程和上岗申请需要绑定在一起
        String businessKey = processDefineKey+":"+id; //SGSP:1
        //设置流程变量去设置下一个任务的办理人，这里就是提交的办理人
        Integer personId = SessionUtil.getCurrentUserId();
        Map<String,Object> variable = new HashMap<String,Object>();
        variable.put("userid",personId);
        runtimeService.startProcessInstanceByKey(processDefineKey,businessKey,variable);
        //更新信息，状态从0变为1
        SGSP sgsp = sgspRepository.findById(id).get();
        sgsp.setStatus(Constant.STATE_SGSP_ONE);
        sgspRepository.save(sgsp);
    }
    /**
     * 查询当前登录用户任务
     * @param personId
     * @return
     */
    @Override
    public List<ActTaskVo> queryCurrentUserTask(Integer personId) {
        //获得流程定义的key
        String key = SGSP.class.getSimpleName();
        //根据key查询到流程定义id
        String processInstanceId = repositoryService.createProcessDefinitionQuery().processDefinitionKey(key).singleResult().getId();
        //查询到当前用户的代办任务
        List<Task> tasks =  taskService.createTaskQuery().processDefinitionId(processInstanceId).taskAssignee(personId.toString()).list();
        List<ActTaskVo> actTaskVos = new ArrayList<>();
        for (Task task:tasks) {
            //获取任务办理人id
            Integer assigneeId = Integer.parseInt(task.getAssignee());
            //查询出任务办理人名称
            String assigneeName = personRepository.findById(assigneeId).get().getRYNAME();
            //查询出申请人名称
                //1.根据任务查询到对用的申请单id
            Integer sgnum =  GetSgnumFromTask(task);
                //2、根据申请id得到申请人名称
            Integer applyId = sgspRepository.findById(sgnum).get().getPerson_id();
            String applyName = personRepository.findById(applyId).get().getRYNAME();
            ActTaskVo actTaskVo=new ActTaskVo();
            BeanUtils.copyProperties(task,actTaskVo);
            //设置办理人名称
            actTaskVo.setAssigneeName(assigneeName);
            actTaskVo.setApplyName(applyName);
            actTaskVo.setApplyId(applyId);
            actTaskVos.add(actTaskVo);
        }
        return actTaskVos;
    }

    /**
     * 从任务中获取到申请单id
     * @return
     */
    @Override
    public  Integer GetSgnumFromTask(Task task){
        //根据任务id查询出流程实例id
        String processInstanceId = task.getProcessInstanceId();
        //根据流程实例id查询流程实例
        ProcessInstance processInstance =  runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
        //取出business_key
        String business_key = processInstance.getBusinessKey();  //SGSP:1 1就是申请单id
        String sgnum = business_key.split(":")[1];
        return Integer.parseInt(sgnum);
    }

    /**
     * 根据任务id查询连线信息
     * @param taskId
     * @return
     */
    @Override
    public List<String> queryOutComeByTaskId(String taskId) {
        List<String> names = new ArrayList<>();
        //1根据任务id 获得任务实例
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        //2取出流程定义id
        String processDefinitionId = task.getProcessDefinitionId();
        //3取出流程实例id
        String processInstanceId = task.getProcessInstanceId();
        //4根据流程实例id查询到流程实例
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
        //5根据流程定义id查询到流程定义的xml信息
        ProcessDefinitionEntity processDefinitionEntity = (ProcessDefinitionEntity) repositoryService.getProcessDefinition(processDefinitionId);
        //6从流程实例对象里面取出当前活动节点id
        String activityId = processInstance.getActivityId(); //apply
        //7使用活动id取出xml和当前活动id相关的节点数据
        ActivityImpl activityImp = processDefinitionEntity.findActivity(activityId);
        //8取出连线信息
        List<PvmTransition> pvmTransitions = activityImp.getOutgoingTransitions();
        if (null !=pvmTransitions && pvmTransitions.size()>0){
            for (PvmTransition pvmTrasition: pvmTransitions) {
                String name = pvmTrasition.getProperty("name").toString();
                names.add(name);
//                System.out.println(name);
            }
        }

        return names;
    }
    //根据部门id和角色id查询用户
    @Override
    public List<String> getPersonIdsByDeptIdAndRoleId(Integer deptId, Integer jsId) {
        return personRepository.getPersonIdsByDeptIdAndRoleId(deptId,jsId);
    }

    /**
     * 根据任务id查询批注信息
     * @param taskId
     * @return
     */
    @Override
    public List<ActCommentVo> queryCommentByTaskId(String taskId) {
        //1根据任务id查询出任务实例
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        //2从任务里面取出流程实例id
        String processInstanceId = task.getProcessInstanceId();
        //3根据流程实例id查询批注
        List<Comment> comments = taskService.getProcessInstanceComments(processInstanceId);
        //装换为能返回的vo
        List<ActCommentVo> actCommentVos = new ArrayList<>();
        if(null!=comments && comments.size()>0){
            for (Comment comment: comments) {
                ActCommentVo actCommentVo = new ActCommentVo();
                BeanUtils.copyProperties(comment,actCommentVo);
                actCommentVos.add(actCommentVo);
            }
        }
        return actCommentVos;
    }

    /**
     * 上岗审批办理业务
     * @param taskId
     * @param val
     * @param pizhu
     * @param personId
     * @param personName
     */
    @Override
    public void completeTask(String taskId, String val, String pizhu, Integer personId, String personName) {
        //查询任务
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        //得到申请单id
        Integer sgnum = workFlowService.GetSgnumFromTask(task);

        //从任务中取出流程实例
        String processInstanceId = task.getProcessInstanceId();
        //设置批注人
        Authentication.setAuthenticatedUserId(personName);
        //添加批注信息
        taskService.addComment(taskId,processInstanceId,"【"+val+"】"+pizhu);
        Map<String,Object> variable = new HashMap<>();
        List<String> personIds = null;
        SGSP sgsp = sgspService.findById(sgnum);
        Integer statu = sgsp.getStatus();
        //获取到业务id
        Integer ywId = sgsp.getYWID();
        //根据业务id查询到业务环节
        List<YWHJ> ywhjs =ywhjService.getAllByYwId(ywId);
        String jsName="";
        //待办理状态，自己办理之后提交给部门领导办理
        if (statu == Constant.STATE_SGSP_ONE){
            Person person = personService.findById(personId);
            //得到申请人的部门
            Integer deptId = person.getSSBMID();
            if (null!=ywhjs&&ywhjs.size()>0){
                //得到第一个元素
                YWHJ ywhj = ywhjs.get(0);
                //得到第一个业务环节的序号
                Integer ywhjId = ywhj.getYWHJID();
                //序号+1就是下一个业务环节
                Integer nextYwhjId = ywhjId+1;
                //查出下一个业务环节办理的角色,根据业务id和业务环节id
                JSYW jsyw = jsywService.findRoleByYwIdAndYwhjId(ywId,nextYwhjId);
                Integer jsId = jsyw.getJSID();
                //根据部门id和角色id查询下一个任务的办理人
                personIds =  getPersonIdsByDeptIdAndRoleId(deptId,jsId);
            }

        }else{
            //业务环节id
            Integer ywhjId = 0;
            //下一个环节办理人角色id
            Integer jsId = 0 ;
            //当前角色办理人id,用于办理此业务的id
            Integer currentJsId = 0;
            //查询出当前登录用户的角色，可能有多个
            List<RYJSGL> ryjsgls = ryjsglService.getAllByPersonId(personId);
            //根据业务id查询角色业务表
            List<JSYW> jsyws = jsywService.findAllByYwId(ywId);
            //查询到业务环节序号
            if ((null != ryjsgls && ryjsgls.size()>0) && (null!=jsyws && jsyws.size()>0)){
                for (RYJSGL ryjsgl: ryjsgls) {
                    for (JSYW jsyw:jsyws) {
                        if (ryjsgl.getJSID() == jsyw.getJSID()){
                            ywhjId = jsyw.getYWHJID();
                            currentJsId = jsyw.getJSID();
                            break;
                        }
                    }
                }
            }
            //根据当前登录人角色id，查询到当前登录人用于此次流程的角色名称
//            TODO
            //业务环节序号+1就是下一个环节，查询下一个环节的办理人角色
            for (JSYW jsyw:jsyws) {
                if (jsyw.getYWHJID() == ywhjId+1){
                    jsId = jsyw.getJSID();
                }
            }
            //根据角色id查询下一个任务的办理人
            personIds = ryjsglService.findAllPersonByjsId(jsId);

            //部门审核
            if (ywhjId == 2){
                //设置状态
                if (val.equals("驳回")){
                    //0表示通过，1表示未通过
                    sgsp.setBMSHSTATE("1");
                }else {
                    sgsp.setBMSHSTATE("0");
                }
                //设置意见
                sgsp.setBMSHIDEA(pizhu);
                //设置审核人
                sgsp.setBMSHRY(personName);
                //设置审核时间
                sgsp.setBMSHTIME(new Date());
                sgspService.save(sgsp);
            }
            //保密办审核
            if (ywhjId == 3){
                //设置状态
                if (val.equals("驳回")){
                    //0表示通过，1表示未通过
                    sgsp.setBMBSHSTATE("1");
                }else {
                    sgsp.setBMBSHSTATE("0");
                }
                //设置意见
                sgsp.setBMBSHIDEA(pizhu);
                //设置审核人
                sgsp.setBMBSHRY(personName);
                //设置审核时间
                sgsp.setBMBSHTIME(new Date());
                sgspService.save(sgsp);
            }
            //保密小组审核
            if (ywhjId == 4){
                //设置状态
                if (val.equals("驳回")){
                    //0表示通过，1表示未通过
                    sgsp.setBMXZSHSTATE("1");
                }else {
                    sgsp.setBMXZSHSTATE("0");
                }
                //设置意见
                sgsp.setBMXZSHIDEA(pizhu);
                //设置审核人
                sgsp.setBMXZSHRY(personName);
                //设置审核时间
                sgsp.setBMXZSHTIME(new Date());
                sgspService.save(sgsp);
            }
        }
        String assign = "";
        //设置下一个任务办理人，使用流程变量设置
        if (null != personIds && personIds.size()>0){
            for (String p:personIds) {
                assign = assign+p+',';
            }
        }
        //去除最后的","
        if(assign.length()>0){
            assign = assign.substring(0,assign.length()-1);
        }
        variable.put("userids",assign);
        variable.put("outcome",val);
        taskService.complete(taskId,variable);

        //修改状态
        if (val.equals("提交")){
            sgsp.setStatus(Constant.STATE_SGSP_TWO);
            sgspService.save(sgsp);
        }
        //判断流程是否结束
        ProcessInstance processInstance =runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
        if (null == processInstance){
            if (val.equals("放弃")){
                sgsp.setStatus(Constant.STATE_SGSP_FOURE);

            }else if(val.equals("驳回")){
                sgsp.setStatus(Constant.STATE_SGSP_ONE);
            }else{
                sgsp.setStatus(Constant.STATE_SGSP_THREE);
            }
            sgspService.save(sgsp);
        }
    }

    /**
     * 根据申请单id查询批注信息
     * @param sgnum
     * @return
     */
    @Override
    public List<ActCommentVo> queryCommentBySgnum(Integer sgnum) {
        //1组装businesskey
        String businessKey = SGSP.class.getSimpleName()+":"+sgnum;
        //2根据业务id查询历史流程实例
        HistoricProcessInstance historicProcessInstance = historyService.createHistoricProcessInstanceQuery()
                .processInstanceBusinessKey(businessKey).singleResult();
        //流程实例id查询历史批注
        List<Comment> comments = taskService.getProcessInstanceComments(historicProcessInstance.getId());
        List<ActCommentVo> actCommentVos = new ArrayList<>();
        if (null != comments && comments.size()>0){
            for (Comment comment:comments) {
                ActCommentVo actCommentVo = new ActCommentVo();
                BeanUtils.copyProperties(comment,actCommentVo);
                actCommentVos.add(actCommentVo);
            }
        }
        return actCommentVos;
    }
}
