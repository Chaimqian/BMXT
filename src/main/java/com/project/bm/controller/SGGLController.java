package com.project.bm.controller;

import com.project.bm.entity.*;
import com.project.bm.service.*;
import com.project.bm.utils.Constant;
import com.project.bm.utils.StringConverterDateUtil;
import com.project.bm.vo.ActCommentVo;
import com.project.bm.vo.ActTaskVo;
import com.project.bm.vo.ComboBoxVo;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.identity.Authentication;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import com.project.bm.repository.CFFZRepository;
import com.project.bm.repository.JWZZRepository;
import com.project.bm.repository.SGSPRepository;
import com.project.bm.repository.YSCGRepository;
import com.project.bm.repository.PersonRepository;
import com.project.bm.vo.SGSPVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;


@Controller
public class SGGLController {
    @Autowired
    SGSPRepository SGSPRepository;
    @Autowired
    YSCGRepository YSCGRepository;
    @Autowired
    JWZZRepository JWZZRepository;
    @Autowired
    CFFZRepository CFFZRepository;
    @Autowired
    PersonRepository PersonRepository;
    @Autowired
    private SGSPService sgspService;
    @Autowired
    private PersonService personService;
    @Autowired
    private WorkFlowService workFlowService;
    @Autowired
    private YSCGService yscgService;
    @Autowired
    private JWZZService jwzzService;
    @Autowired
    private CFFZService cffzService;
    @Autowired
    private YWService ywService;
    @Autowired
    private StringConverterDateUtil stringConverterDateUtil;
    /**
     * 跳转到上岗审批管理页面
     * @return
     */
    @GetMapping("/SGSPJG")
    public String SGSPManager()
    {
        return "SGSPJG";
    }


    /**
     * 初始化上岗审批表格，即获取到表格显示数据
     * @return 上岗审批的json
     */
    @ResponseBody
    @GetMapping("/SGSP/initTable")
    public List<SGSPVo> InitTable(HttpSession session){
        //从session里面获取到用户信息
        Integer personId = Integer.parseInt(session.getAttribute("userId").toString());
        List<SGSP> list= sgspService.findByPersonIdOrderByApplyTime(personId);
        Person person = personService.findById(personId);
        List<SGSPVo> sgspVos =new ArrayList<SGSPVo>();
        for (SGSP sgsp:list) {
            SGSPVo sgspVo =new SGSPVo();
            //复制属性 把sgsp 复制到sgspvo
            BeanUtils.copyProperties(sgsp,sgspVo);
            sgspVo.setName(person.getRYNAME());
            sgspVos.add(sgspVo);
        }
        return sgspVos;
    }

    /**
     * 提交申请后，启动流程
     * @return
     */
    @ResponseBody
    @PostMapping("/SGSP/startProcess")
    public Map<String,Object> StartProcess(@RequestBody  Map parame){
        //获取post传过来的申请单id值
        Integer id = Integer.parseInt(parame.get("id").toString());
        Map<String, Object> map = new HashMap<String,Object>();
        try{
            workFlowService.startProcess(id);
            map.put("statu","ok");
        }catch (Exception e){
            map.put("statu","no");
            map.put("msg","提交失败");
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 根据登录信息查询登录人信息
     * @return
     */
    @ResponseBody
    @PostMapping("/SGSP/getPerson")
    public Person getPerson(HttpSession session){
        //从session中取出用户id
        Integer personId = Integer.parseInt(session.getAttribute("userId").toString());
        Person person = personService.findById(personId);
        return person;
    }

    /**
     * 初始化因此出国表格
     * @param session
     * @return
     */
    @ResponseBody
    @GetMapping("/SGSP/initYscgTable")
    public List<YSCG> InitYscgTable(HttpSession session){
        //从session中取出用户id
        Integer personId = Integer.parseInt(session.getAttribute("userId").toString());
        List<YSCG> yscgs =yscgService.findByPersonId(personId);
        return yscgs;
    }
    /**
     * 初始化接受境外资助
     * @param session
     * @return
     */
    @ResponseBody
    @GetMapping("/SGSP/initJwzzTable")
    public List<JWZZ> InitJwzzTable(HttpSession session){
        //从session中取出用户id
        Integer personId = Integer.parseInt(session.getAttribute("userId").toString());
        List<JWZZ> jwzzes =jwzzService.findByPersion(personId);
        return jwzzes;
    }

    /**
     * 初始化处分或违法乱纪
     * @param session
     * @return
     */
    @ResponseBody
    @GetMapping("/SGSP/initCfwfTable")
    public List<CFFZ> InitCfwfTable(HttpSession session){
        //从session中取出用户id
        Integer personId = Integer.parseInt(session.getAttribute("userId").toString());
        List<CFFZ> cffzs =cffzService.finByPersionId(personId);
        return cffzs;
    }

    /**
     * 初始化业务下拉框
     * @return
     */

    @ResponseBody
    @GetMapping("/SGSP/initSgapType")
    public List<ComboBoxVo> initSgapType(){
        List<YW> yws = ywService.findAll();
        List<ComboBoxVo> comboBoxVos = new ArrayList<>();
        if (null != yws && yws.size()>0){
            for (YW yw:yws) {
                ComboBoxVo comboBoxVo = new ComboBoxVo();
                comboBoxVo.setId(yw.getYWID());
                comboBoxVo.setText(yw.getYWNAME());
                comboBoxVos.add(comboBoxVo);
            }
        }
        return comboBoxVos;
    }

    /**
     * 新增  这里没有用实体映射，是因为里面有bool值
     * @param params
     * @return
     */
    @ResponseBody
    @PostMapping("/SGSP/addSubmit")
    public Map<String,Object> addSubmit(@RequestBody  Map params){
        Map<String,Object> map = new HashMap<>();
        Integer person_id = Integer.parseInt(params.get("Person_id").toString()); //用户id
        Integer PXOk = Integer.parseInt(params.get("PXOK").toString());// 是否接受教育
        String PXTIME =  params.get("PXTIME").toString();  //接收教育时间
        Integer SGCERT = Integer.parseInt(params.get("SGCERT").toString());//是否取得资格证书
        String startTime =  params.get("SGZSXTIME").toString(); //资格证书有效开始时间
        String endtTime =  params.get("SGZSXXTIME").toString(); //资格证书有效截止时间
        String QISMOK = params.get("QISMOK").toString();  //其他说明情况
        Integer YWID = Integer.parseInt(params.get("YWID").toString()); //申请单类型
        SGSP sgsp = new SGSP();
        sgsp.setPerson_id(person_id);
        sgsp.setPXOK(PXOk>0);
        sgsp.setPXTIME(stringConverterDateUtil.convert(PXTIME));
        sgsp.setSGCERT(SGCERT>0);
        sgsp.setSGZSXTIME(stringConverterDateUtil.convert(startTime));
        sgsp.setSGZSXXTIME(stringConverterDateUtil.convert(endtTime));
        sgsp.setQISMOK(QISMOK);
        sgsp.setYWID(YWID);
        //设置申请单状态
        sgsp.setStatus(Constant.STATE_SGSP_ZORO);
        //设置不删除
        sgsp.setIsDel(Constant.DEL_ZORO);
        sgsp.setApplyDate(new Date());
        try{
            sgspService.save(sgsp);
            map.put("statu","ok");
            map.put("msg","添加成功");
        }catch (Exception e){
            map.put("statu","no");
            map.put("msg","添加失败");
            e.printStackTrace();
        }
        return map;
    }
    @ResponseBody
    @PostMapping("/SGSP/getPersonAndSgspBySgnum")
    public Map<String,Object> getPersonAndSgspBySgnum(@RequestBody Map param,HttpSession session){
        //获得申请单
        Integer sgnum = Integer.parseInt(param.get("sgnum").toString());
        //从session中获得person_id
        Integer personId = Integer.parseInt(session.getAttribute("userId").toString());
        //查询到用户信息
        Person person = personService.findById(personId);
        //查询到申请单信息
        SGSP sgsp = sgspService.findById(sgnum);
        Integer ywid = sgsp.getYWID();
        YW yw = ywService.findById(ywid);
        SGSPVo sgspVo = new SGSPVo();
        BeanUtils.copyProperties(sgsp,sgspVo);
        sgspVo.setSgspType(yw.getYWNAME());
        Map<String,Object> map = new HashMap<>();
        map.put("person",person);
        map.put("sgsp",sgspVo);
        return map;
    }

    /**
     * 删除，进行假删除
     * @param param
     * @return
     */
    @ResponseBody
    @PostMapping("/SGSP/delSgsp")
    public Map<String,Object> delete(@RequestBody Map param){
        Integer sgnum = Integer.parseInt(param.get("sgnum").toString());
        Map<String,Object> map = new HashMap<>();
        SGSP sgsp = sgspService.findById(sgnum);
        sgsp.setIsDel(Constant.DEL_ONE);
        try {
            sgspService.save(sgsp);
            map.put("statu","ok");
            map.put("msg","删除成功");
        }catch (Exception e) {
            map.put("statu", "no");
            map.put("msg", "删除失败");
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 编辑操作
     * @param
     * @param sgnum
     * @return
     */
    @ResponseBody
    @PostMapping("/SGSP/editSubmit/{sgnum}")
    public Map<String,Object> editSubmit(@RequestBody Map params,@PathVariable(name = "sgnum")Integer sgnum){
        Map<String,Object> map = new HashMap<>();
        Integer PXOk = Integer.parseInt(params.get("edit_PXOK").toString());// 是否接受教育
        String PXTIME =  params.get("edit_jsjysj").toString();  //接收教育时间
        Integer SGCERT = Integer.parseInt(params.get("edit_SGCERT").toString());//是否取得资格证书
        String startTime =  params.get("edit_startTime").toString(); //资格证书有效开始时间
        String endtTime =  params.get("edit_endTime").toString(); //资格证书有效截止时间
        String QISMOK = params.get("edit_otherDescribe").toString();  //其他说明情况
        Integer YWID = Integer.parseInt(params.get("edit_sgspType").toString()); //申请单类型

        //根据id查询到上岗审批单
        SGSP sgsp = sgspService.findById(sgnum);
        sgsp.setPXOK(PXOk>0);
        sgsp.setPXTIME(stringConverterDateUtil.convert(PXTIME));
        sgsp.setSGCERT(SGCERT>0);
        sgsp.setSGZSXTIME(stringConverterDateUtil.convert(startTime));
        sgsp.setSGZSXXTIME(stringConverterDateUtil.convert(endtTime));
        sgsp.setQISMOK(QISMOK);
        sgsp.setYWID(YWID);
        try{
            sgspService.save(sgsp);
            map.put("statu","ok");
            map.put("msg","修改成功");
        }catch (Exception e){
            map.put("statu","no");
            map.put("msg","修改失败");
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 根据申请单id查询批注信息
     * @param sgnum
     * @return
     */
    @ResponseBody
    @GetMapping("/SGSP/initCommentBySgnum/{sgnum}")
    public List<ActCommentVo> initComment(@PathVariable(name = "sgnum")Integer sgnum){
        return workFlowService.queryCommentBySgnum(sgnum);
    }

    /**
     * 导出打印
     * @param session
     * @param model
     * @return
     */
    @GetMapping ("/SGSP/export_print/{sgnum}")
    public String export_print(@PathVariable(name = "sgnum")Integer sgnum,HttpSession session,Model model){
        //从session中获得person_id
        Integer personId = Integer.parseInt(session.getAttribute("userId").toString());
        Person person = personService.findById(personId);
        model.addAttribute("per",person);
        List<YSCG> yscgs = yscgService.findByPersonId(personId);
        model.addAttribute("yscgs",yscgs);
        List<JWZZ> jwzzes = jwzzService.findByPersion(personId);
        model.addAttribute("jwzzws",jwzzes);
        List<CFFZ> cffzs = cffzService.finByPersionId(personId);
        model.addAttribute("cffzs",cffzs);
        SGSP sgsp = sgspService.findById(sgnum);
        model.addAttribute("sgsp",sgsp);
        return "SGSP";
    }
}
