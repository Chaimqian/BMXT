package com.project.bm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/")
public class SessionController {
    @ResponseBody
    @RequestMapping(value = "/session")
    public Map<String, Object> getSession(HttpServletRequest request) {
        request.getSession().setAttribute("Person_id", "12");
        Map<String, Object> map = new HashMap<>();
        map.put("sessionId", request.getSession().getId());
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/get")
    public String get(HttpServletRequest request) {
        String Person_id = (String) request.getSession().getAttribute("Person_id");
        return Person_id;
    }
}
