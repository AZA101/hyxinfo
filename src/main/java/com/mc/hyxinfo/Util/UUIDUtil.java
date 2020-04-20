package com.mc.hyxinfo.Util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @author admin
 * 用来创建货运单号的工具类
 */
public class UUIDUtil {

    public static String SetUUID(){
        String UUIDS;
        Random random = new Random();
        Integer number = random.nextInt(1000) + 100;
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyyMMdd");
        UUIDS=dateFormat.format(new Date())+number;
        return UUIDS;
    }
}
