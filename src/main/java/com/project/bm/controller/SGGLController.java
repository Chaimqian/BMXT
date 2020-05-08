package com.project.bm.controller;

import com.project.bm.entity.CFFZ;
import com.project.bm.entity.JWZZ;
import com.project.bm.entity.SGSP;
import com.project.bm.entity.YSCG;
import com.project.bm.entity.Person;
import com.project.bm.repository.CFFZRepository;
import com.project.bm.repository.JWZZRepository;
import com.project.bm.repository.SGSPRepository;
import com.project.bm.repository.YSCGRepository;
import com.project.bm.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


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
    @GetMapping("/")
    public String hello(){
        return "index";
    }
    @GetMapping("/SGSP")
    public String hello1(HttpServletRequest request,Model model){
        String Person_id = (String) request.getSession().getAttribute("Person_id");
        Integer i = null;
        if(Person_id!=null){
            i = Integer.valueOf(Person_id);
        }
        Person per=PersonRepository.getOne(i);
        System.out.println(i);
        model.addAttribute("per",per);
        List<YSCG> yscg=YSCGRepository.findAll();
        model.addAttribute("List1", yscg);
        List<JWZZ> jwzz=JWZZRepository.findAll();
        model.addAttribute("List2", jwzz);
        List<CFFZ> cffz=CFFZRepository.findAll();
        model.addAttribute("List3", cffz);
        return "SGSP";
    }
    @GetMapping("/CGJLR")
    public String hello2(){
        return "CGJLR";
    }
    @GetMapping("/JWZZLR")
    public String hello3(){
        return "JWZZLR";
    }
    @GetMapping("/WFFZLR")
    public String hello4(){
        return "WFFZLR";
    }
    @ResponseBody
    @RequestMapping("/ses")
    public String hello6(HttpServletRequest request){
        String Person_id = (String) request.getSession().getAttribute("Person_id");
        return Person_id;
    }
    @GetMapping("/myMes")
    public String hello7(){
        return "myMes";
    }

    @GetMapping("/SGSPJG")
    public String hello5(HttpServletRequest request,Model model){
        String Person_id = (String) request.getSession().getAttribute("Person_id");
        Integer i = null;
        if(Person_id!=null){
            i = Integer.valueOf(Person_id);
        }
        Person person=PersonRepository.getOne(i);
        model.addAttribute("per", person);
        List<SGSP> SGSP=SGSPRepository.findAll();
        model.addAttribute("List", SGSP);
        return "SGBMSPtj";
    }

    @GetMapping("/sgsp1")
    public String insertsgsp1(SGSP s){
        SGSPRepository.save(s);
        return "redirect:/SGSPJG";
    }
    @GetMapping("/sgsp2")
    public String insertsgsp2(YSCG y){
        YSCGRepository.save(y);
        return "redirect:/JWZZLR";
    }
    @GetMapping("/sgsp3")
    public String insertsgsp3(JWZZ j){
        JWZZRepository.save(j);
        return "redirect:/WFFZLR";
    }
    @GetMapping("/sgsp4")
    public String insertsgsp4(CFFZ c){
        CFFZRepository.save(c);
        return "redirect:/SGSP";
    }
}
