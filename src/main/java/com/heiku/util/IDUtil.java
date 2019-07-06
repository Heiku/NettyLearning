package com.heiku.util;

import java.util.UUID;

/**
 * @Author: Heiku
 * @Date: 2019/7/6
 */
public class IDUtil {

    public static String randomId(){
        return UUID.randomUUID().toString().split("-")[0];
    }
}
