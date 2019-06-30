package com.heiku.serialize.impl;

import com.alibaba.fastjson.JSON;
import com.heiku.serialize.Serializer;
import com.heiku.serialize.SerializerAlogrithm;

/**
 * @Author: Heiku
 * @Date: 2019/6/30
 */
public class JSONSerializer implements Serializer {

    /**
     * json序列化标识
     *
     * @return
     */
    @Override
    public byte getSerializerAlogrithm() {
        return SerializerAlogrithm.JSON;
    }

    @Override
    public byte[] serialize(Object object) {
        return JSON.toJSONBytes(object);
    }


    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes) {
        return JSON.parseObject(bytes, clazz);
    }
}
