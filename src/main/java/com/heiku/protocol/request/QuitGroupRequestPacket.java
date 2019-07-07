package com.heiku.protocol.request;

import com.heiku.protocol.Packet;
import lombok.Data;

import static com.heiku.protocol.command.Command.QUIT_GROUP_REQUEST;

/**
 * @Author: Heiku
 * @Date: 2019/7/7
 */

@Data
public class QuitGroupRequestPacket extends Packet {

    private String groupId;

    @Override
    public Byte getCommand() {
        return QUIT_GROUP_REQUEST;
    }
}
