package com.heiku.protocol.command;

/**
 * @Author: Heiku
 * @Date: 2019/6/30
 *
 * 请求标识
 */
public interface Command {

    Byte LOGIN_REQUEST = 1;

    Byte LOGIN_RESPONSE = 2;

    Byte MESSAGE_REQUEST = 3;

    Byte MESSAGE_RESPONSE = 4;
}
