package com.heiku.protocol;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @Author: Heiku
 * @Date: 2019/6/30
 *
 * 通信协议：魔数（4）+ 版本号（1）+ 序列化算法（1）+ 指令（1） + 数据长度（4）+ 数据（N）
 *
 *      魔数：这个魔数之后，服务端首先取出前面四个字节进行比对，能够在第一时间识别出这个数据包并非是遵循自定义协议的，也就是无效数据包，
 *           为了安全考虑可以直接关闭连接以节省资源
 */


@Data
public abstract class Packet {

    @JSONField(deserialize = false, serialize = false)
    private Byte version = 1;

    @JSONField(serialize = false)
    public abstract Byte getCommand();
}
