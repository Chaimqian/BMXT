package com.project.bm.utils;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author :LX
 * @CreateTime :2020/5/22
 * @Description : String 转换成Date
 */
@Component
public class StringConverterDateUtil implements Converter<String, Date> {
    @Override
    public Date convert(String s) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            if (null != s && s!=""){
                return format.parse(s);
            }else {
                return null;
            }
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
}
