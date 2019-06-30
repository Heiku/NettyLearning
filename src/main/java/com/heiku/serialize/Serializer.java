package com.heiku.serialize;

import com.heiku.serialize.impl.JSONSerializer;

/**
 * @Author: Heiku
 * @Date: 2019/6/30
 *
 * 序列化接口
 */
public interface Serializer {

    // 指定默认的serializer
    Serializer DEFAULT = new JSONSerializer();

    /**
     * 序列化算法
     * @return
     */
    byte getSerializerAlogrithm();


    /**
     * Java对象转成二进制
     *
     * @param object
     * @return
     */
    byte[] serialize(Object object);


    /**
     * 将二进制转成Java对象
     *
     * @param clazz
     * @param bytes
     * @param <T>
     * @return
     */
    <T> T deserialize(Class<T> clazz, byte[] bytes);
}
