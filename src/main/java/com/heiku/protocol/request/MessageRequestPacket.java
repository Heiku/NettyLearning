package com.heiku.protocol.request;

import com.heiku.protocol.Packet;

import lombok.Data;

import static com.heiku.protocol.command.Command.MESSAGE_REQUEST;

@Data
public class MessageRequestPacket extends Packet {

    private String toUserId;

    private String message;

    public MessageRequestPacket(){}

    public MessageRequestPacket(String toUserId, String message){
        this.toUserId = toUserId;
        this.message = message;
    }

    @Override
    public Byte getCommand() {
        return MESSAGE_REQUEST;
    }
}
