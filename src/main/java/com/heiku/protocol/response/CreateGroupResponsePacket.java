package com.heiku.protocol.response;

import com.heiku.protocol.Packet;
import lombok.Data;

import java.util.List;

import static com.heiku.protocol.command.Command.CREATE_GROUP_RESPONSE;

/**
 * @Author: Heiku
 * @Date: 2019/7/6
 */

@Data
public class CreateGroupResponsePacket extends Packet {

    private boolean success;

    private String groupId;

    private List<String> userNameList;

    @Override
    public Byte getCommand() {
        return CREATE_GROUP_RESPONSE;
    }
}
