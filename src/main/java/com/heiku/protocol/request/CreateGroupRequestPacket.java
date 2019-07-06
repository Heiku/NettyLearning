package com.heiku.protocol.request;

import com.heiku.protocol.Packet;
import lombok.Data;

import java.util.List;

import static com.heiku.protocol.command.Command.CREATE_GROUP_REQUEST;

/**
 * @Author: Heiku
 * @Date: 2019/7/6
 */

@Data
public class CreateGroupRequestPacket extends Packet {

    private List<String> userIdList;

    @Override
    public Byte getCommand() {
        return CREATE_GROUP_REQUEST;
    }
}
