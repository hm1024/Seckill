package com.minghai.seckill.util;

import java.util.UUID;

/**
 * @author minghai
 * @description
 * @date 2020/3/23
 */
public class UUIDUtil {

    public static String uuid(){
        return UUID.randomUUID().toString().replace("-","");
    }

}
