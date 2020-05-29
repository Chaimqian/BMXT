package com.project.bm.utils;

/**
 * @Author :LX
 * @CreateTime :2020/5/13
 * @Description : 存放一些系统常量
 */
public class Constant {
    /**
     * 上岗审批单状态
     */
    public static final Integer STATE_SGSP_ZORO = 0; //未提交
    public static final Integer STATE_SGSP_ONE = 1; //待办理
    public static final Integer STATE_SGSP_TWO = 2;  //审批中
    public static final Integer STATE_SGSP_THREE = 3; //审批完成
    public static final Integer STATE_SGSP_FOURE = 4; //放弃

    /**
     * 是否删除
     */
    public static final Integer DEL_ZORO = 0; //不删除
    public static final Integer DEL_ONE = 1; //删除
}
