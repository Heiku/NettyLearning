package com.heiku.protocol.request;

import com.heiku.protocol.Packet;
import lombok.Data;

import static com.heiku.protocol.command.Command.LIST_GROUP_MEMBERS_REQUEST;

/**
 * @Author: Heiku
 * @Date: 2019/7/7
 */

@Data
public class ListGroupMembersRequestPacket extends Packet {

    private String groupId;

    @Override
    public Byte getCommand() {
        return LIST_GROUP_MEMBERS_REQUEST;
    }
}
