package com.mc.hyxinfo.Util;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

import static org.junit.Assert.*;

public class UUIDUtilTest {
    public String UUIDS;
    @Test
    public void setUUID() {
        Random random = new Random();
        Integer number = random.nextInt(1000) + 100;
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyyMMddHHmm");
        System.out.println(dateFormat.format(new Date()));
        System.out.println(System.currentTimeMillis());
        System.out.println(UUIDS=dateFormat.format(new Date())+number);

    }
}