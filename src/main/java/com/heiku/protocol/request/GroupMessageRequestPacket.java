package com.heiku.protocol.request;

import com.heiku.protocol.Packet;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.heiku.protocol.command.Command.GROUP_MESSAGE_REQUEST;

/**
 * @Author: Heiku
 * @Date: 2019/7/7
 */

@Data
@NoArgsConstructor
public class GroupMessageRequestPacket extends Packet {

    private String toGroupId;

    private String message;

    public GroupMessageRequestPacket(String toGroupId, String message){
        this.message = message;
        this.toGroupId = toGroupId;
    }

    @Override
    public Byte getCommand() {
        return GROUP_MESSAGE_REQUEST;
    }
}
