package com.project.bm.controller;

import com.project.bm.entity.*;
import com.project.bm.service.*;
import com.project.bm.vo.ActCommentVo;
import com.project.bm.vo.ActTaskVo;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author :LX
 * @CreateTime :2020/5/22
 * @Description :上岗审批代办任务controller
 */
@Controller
public class SGSPDBRWController {

    @Autowired
    private WorkFlowService workFlowService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private YSCGService yscgService;
    @Autowired
    private JWZZService jwzzService;
    @Autowired
    private CFFZService cffzService;
    @Autowired
    private PersonService personService;
    @Autowired
    private SGSPService sgspService;
    @Autowired
    private YWService ywService;
    /**
     * 跳转到上岗审批任务办理页面
     * @return
     */
    @GetMapping("/SGSPDBRW")
    public String SGSPDBRW(){
        return "SGSPDBRW";
    }

    /**
     *初始化上岗审批待办任务
     * @return
     */
    @ResponseBody
    @GetMapping("/SGSPDBRW/initTable")
    public List<ActTaskVo> InitDBRWTable(HttpSession session){
        //获取当前用户的id
        Integer personId = Integer.parseInt(session.getAttribute("userId").toString());
        List<ActTaskVo> tasks = workFlowService.queryCurrentUserTask(personId);
        return tasks;
    }

    /**
     * 根据任务id和申请人id查询到人员信息和申请信息
     * @param params
     * @return
     */
    @ResponseBody
    @PostMapping("/SGSPDBRW/getInformationByTaskIdAndApplyId")
    public Map<String,Object> GetPersonAndSGSPByTaskId(@RequestBody Map params){
        //得到任务id和申请人id
        String taskId = params.get("taskId").toString();
        Integer applyId = Integer.parseInt(params.get("applyId").toString());
        Map<String,Object> map =new HashMap<>();
        //获取到申请人信息
        Person person = personService.findById(applyId);
        //根据任务id查询到申请信息
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        Integer sgnum = workFlowService.GetSgnumFromTask(task);
        SGSP sgsp = sgspService.findById(sgnum);
        //得到业务id
        Integer ywid = sgsp.getYWID();
        //查询业务
         YW yw= ywService.findById(ywid);
        //根据任务id查询连线信息
        List<String> outComeName = workFlowService.queryOutComeByTaskId(taskId);
        map.put("person",person);
        map.put("sgsp",sgsp);
        map.put("outcomes",outComeName);
        return map;
    }

    /**
     * 初始化因私出国表格
     * @return
     * applyId 申请人id
     */
    @ResponseBody
    @GetMapping("/SGSPDBRW/initYscgTable/{applyId}")
    public List<YSCG> InitYscgTable(@PathVariable(name = "applyId")Integer applyId, HttpSession session){
        //
        List<YSCG> yscgs = yscgService.findByPersonId(applyId);
        return yscgs;
    }

    /**
     * 初始化接受境外资助表格
     * @return
     */
    @ResponseBody
    @GetMapping("/SGSPDBRW/initJwzzTable/{applyId}")
    public List<JWZZ> InitJwzzTable(@PathVariable(name = "applyId")Integer applyId, HttpSession session){
        //applyId 申请人id
        List<JWZZ> jwzzes = jwzzService.findByPersion(applyId);
        return jwzzes;
    }

    /**
     * 初始化处分或者违法犯罪记录
     * @param session
     * @return
     */
    @ResponseBody
    @GetMapping("/SGSPDBRW/initCfwfTable/{applyId}")
    public List<CFFZ> InitCcfwTable(@PathVariable(name = "applyId")Integer applyId, HttpSession session){
        //applyId 申请人id
        List<CFFZ> cffzs = cffzService.finByPersionId(applyId);
        return cffzs;
    }

    /**
     * 根据任务id查询批注信息
     * @return
     */
    @ResponseBody
    @GetMapping("/SGSPDBRW/initCommentTable/{taskId}")
    public List<ActCommentVo> initCommentTable(@PathVariable(name = "taskId")String taskId){
        List<ActCommentVo> actCommentVos = workFlowService.queryCommentByTaskId(taskId);
        return actCommentVos;
    }

    /**
     * 办理任务
     * @return
     */
    @ResponseBody
    @PostMapping("/SGSPDBRW/toDoTask")
    public Map<String,Object> toDoTask(@RequestBody Map params,HttpSession session){
        //得到任务id post过来的参数
        String taskId = params.get("taskId").toString();
        //得到post过来的按钮值
        String val = params.get("val").toString();
        //得到批注信息
        String pizhu = params.get("pizhu").toString();
        //session中得到用户id
        Integer personId = Integer.parseInt(session.getAttribute("userId").toString());
        //session中获得用户名
        String personName = session.getAttribute("userName").toString();
        Map<String, Object> map = new HashMap<String,Object>();
        //办理任务
        try{
            workFlowService.completeTask(taskId,val,pizhu,personId,personName);
            map.put("statu","ok");
        }catch (Exception e){
            map.put("statu","no");
            map.put("msg","办理失败");
            e.printStackTrace();
        }
        return map;
    }
}
