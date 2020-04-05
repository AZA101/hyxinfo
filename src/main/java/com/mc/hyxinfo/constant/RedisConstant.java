package com.mc.hyxinfo.constant;

public interface RedisConstant {
    /*设置token存储的key以token_为前缀*/
    String TOKEN_PREFIX = "token_%s";
    Integer EXPIRE = 7200;//两个小时
}
