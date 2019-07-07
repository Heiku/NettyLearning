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

    // LIST_GROUP
    Byte LIST_GROUP_MEMBERS_REQUEST = 9;

    Byte LIST_GROUP_MEMBERS_RESPONSE = 10;

    // JOIN_GROUP
    Byte JOIN_GROUP_REQUEST = 11;

    Byte JOIN_GROUP_RESPONSE = 12;

    // QUIT_GROUP
    Byte QUIT_GROUP_REQUEST = 13;

    Byte QUIT_GROUP_RESPONSE = 14;

    // GROUP_MESSAGE
    Byte GROUP_MESSAGE_REQUEST = 15;

    Byte GROUP_MESSAGE_RESPONSE = 16;
}
