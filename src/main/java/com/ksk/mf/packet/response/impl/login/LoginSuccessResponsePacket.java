package com.ksk.mf.packet.response.impl.login;

import com.ksk.mf.packet.response.ResponsePacket;
import io.netty.buffer.ByteBuf;

public class LoginSuccessResponsePacket extends ResponsePacket {
    public LoginSuccessResponsePacket(ByteBuf buf) {
        super(buf);
    }
}
