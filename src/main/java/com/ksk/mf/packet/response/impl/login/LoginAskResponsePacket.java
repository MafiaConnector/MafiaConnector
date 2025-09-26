package com.ksk.mf.packet.response.impl.login;

import com.ksk.mf.packet.response.ResponsePacket;
import io.netty.buffer.ByteBuf;

public class LoginAskResponsePacket extends ResponsePacket {
    public LoginAskResponsePacket(ByteBuf buf) {
        super(buf);
    }
}
