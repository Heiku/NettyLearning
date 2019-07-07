package com.heiku.protocol.response;

import com.heiku.protocol.Packet;
import lombok.Data;

import static com.heiku.protocol.command.Command.GROUP_MESSAGE_RESPONSE;

/**
 * @Author: Heiku
 * @Date: 2019/7/7
 */

@Data
public class GroupMessageResponsePacket extends Packet {

    private String fromGroupId;

    private String fromUser;

    private String message;

    @Override
    public Byte getCommand() {
        return GROUP_MESSAGE_RESPONSE;
    }
}
