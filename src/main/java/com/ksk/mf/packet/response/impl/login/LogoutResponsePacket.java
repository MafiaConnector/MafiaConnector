package com.ksk.mf.packet.response.impl.login;

import com.ksk.mf.packet.response.ResponsePacket;
import io.netty.buffer.ByteBuf;

public class LogoutResponsePacket extends ResponsePacket {
    public LogoutResponsePacket(ByteBuf buf) {
        super(buf);
    }
}
