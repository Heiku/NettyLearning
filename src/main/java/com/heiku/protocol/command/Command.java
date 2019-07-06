package com.heiku.protocol.command;

/**
 * @Author: Heiku
 * @Date: 2019/6/30
 *
 * 请求标识
 */
public interface Command {

    // LOGIN
    Byte LOGIN_REQUEST = 1;

    Byte LOGIN_RESPONSE = 2;

    // MESSAGE
    Byte MESSAGE_REQUEST = 3;

    Byte MESSAGE_RESPONSE = 4;

    // LOGOUT
    Byte LOGOUT_REQUEST = 5;

    Byte LOGOUT_RESPONSE = 6;

    // CREATE_GROUP
    Byte CREATE_GROUP_REQUEST = 7;

    Byte CREATE_GROUP_RESPONSE = 8;
}
