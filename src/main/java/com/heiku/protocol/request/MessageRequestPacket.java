package com.heiku.protocol.request;

import com.heiku.protocol.Packet;

import lombok.Data;

import static com.heiku.protocol.command.Command.MESSAGE_REQUEST;

@Data
public class MessageRequestPacket extends Packet {

    private String message;

    public MessageRequestPacket(String message){
        this.message = message;
    }

    @Override
    public Byte getCommand() {
        return MESSAGE_REQUEST;
    }
}
