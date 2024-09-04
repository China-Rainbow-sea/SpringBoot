package com.rainbowsea.springboot.utils;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.SimpleTimeZone;

public class WebUtils {
    private WebUtils() {
    }

    public static String getYearMonthDay() {
        // 如何得到当前的日期-》Java基础 日期，三代类
        LocalDateTime localDateTime = LocalDateTime.now();
        int year = localDateTime.getYear();
        int monthValue = localDateTime.getMonthValue();
        int dayOfMonth = localDateTime.getDayOfMonth();
        String yearMonthDay = year + "-" + monthValue + "-" + dayOfMonth;

        return yearMonthDay;
    }


    /**
     * 以年月日创建目录
     * @return
     */
    public static String getUploadFileDirectory() {
        return "static/images/" + new SimpleDateFormat("yyyy/MM/dd").format(new Date());
    }

    // 测试一下
    public static void main(String[] args) {
        System.out.println(WebUtils.getYearMonthDay());
        System.out.println(getUploadFileDirectory());
    }
}
