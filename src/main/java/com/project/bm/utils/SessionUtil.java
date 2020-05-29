package com.project.bm.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @Author :LX
 * @CreateTime :2020/5/13
 * @Description :用于操作session的工具类
 */
public class SessionUtil {
    /**
     * 得到request
     * @return
     */
    public static HttpServletRequest getCurrentServletRequest(){
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes)
                RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        return request;
    }

    /**
     * 获取session
     * @return
     */
    public static HttpSession getCurrentSession(){
        return getCurrentServletRequest().getSession();
    }

    /**
     * 获取当前登录用户的id
     * @return
     */
    public static Integer getCurrentUserId(){
        return Integer.parseInt(getCurrentSession().getAttribute("userId").toString());
    }

    /**
     * 获取当前登录的用户名称
     * @return
     */
    public static String getCurrentUserName(){
        return getCurrentSession().getAttribute("userName").toString();
    }



}
