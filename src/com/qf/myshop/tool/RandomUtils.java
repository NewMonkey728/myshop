package com.qf.myshop.tool;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

/**
 * 生成邮箱验证码，激活码使用的随机数
 */
public class RandomUtils {
    //获得当前时间
    public static String getTime(){
        return new SimpleDateFormat("yyyyMMddHHmmssSSS").format(Calendar.getInstance().getTime());
    }
    //获得激活码
    public static String createActive(){
        return getTime()+Integer.toHexString(new Random().nextInt(900)+100);
    }
    //生成订单编号
    public static String createOrderId(){
        return getTime();
    }


}
