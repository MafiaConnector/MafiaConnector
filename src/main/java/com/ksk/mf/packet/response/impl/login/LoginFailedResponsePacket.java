package com.ksk.mf.packet.response.impl.login;

import com.ksk.mf.packet.response.ResponsePacket;
import io.netty.buffer.ByteBuf;

public class LoginFailedResponsePacket extends ResponsePacket {
    public LoginFailedResponsePacket(ByteBuf buf) {
        super(buf);
    }
}
