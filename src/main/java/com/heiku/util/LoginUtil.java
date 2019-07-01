package com.heiku.util;

import com.heiku.attribute.Attributes;
import io.netty.channel.Channel;
import io.netty.util.Attribute;

/**
 * 用于设置 channel中的attr，保存登录标识符，类似于Map结构（AttributeKey, Boolean）
 *
 * @Author: Heiku
 * @Date: 2019/7/1
 */
public class LoginUtil {

    public static void markAsLogin(Channel channel){
        channel.attr(Attributes.LOGIN).set(true);
    }

    public static boolean hasLogin(Channel channel){
        Attribute<Boolean> loginAttr = channel.attr(Attributes.LOGIN);

        return loginAttr != null;
    }
}
