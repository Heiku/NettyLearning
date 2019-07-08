package com.heiku.protocol.response;

import com.heiku.protocol.Packet;
import lombok.Data;

import static com.heiku.protocol.command.Command.HEARTBEAT_RESPONSE;

@Data
public class HeartBeatResponsePacket extends Packet {

    @Override
    public Byte getCommand() {
        return HEARTBEAT_RESPONSE;
    }
}
