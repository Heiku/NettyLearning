package com.heiku.protocol.response;

import com.heiku.protocol.Packet;
import com.heiku.session.Session;
import lombok.Data;

import java.util.List;

import static com.heiku.protocol.command.Command.LIST_GROUP_MEMBERS_RESPONSE;

/**
 * @Author: Heiku
 * @Date: 2019/7/7
 */

@Data
public class ListGroupMembersResponsePacket extends Packet {

    private String groupId;

    private List<Session> sessionList;

    @Override
    public Byte getCommand() {
        return LIST_GROUP_MEMBERS_RESPONSE;
    }
}
