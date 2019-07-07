package com.heiku.protocol.response;

import com.heiku.protocol.Packet;
import lombok.Data;

import static com.heiku.protocol.command.Command.JOIN_GROUP_RESPONSE;

/**
 * @Author: Heiku
 * @Date: 2019/7/7
 */
@Data
public class JoinGroupResponsePacket extends Packet {

    private String groupId;

    private boolean success;

    private String reason;

    @Override
    public Byte getCommand() {
        return JOIN_GROUP_RESPONSE;
    }
}
