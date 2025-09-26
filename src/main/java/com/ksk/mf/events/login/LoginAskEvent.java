package com.ksk.mf.events.login;

import com.ksk.mf.events.EventHandler;
import com.ksk.mf.packet.request.RequestPacket;
import com.ksk.mf.packet.response.ResponsePacket;
import com.ksk.mf.packet.response.impl.login.LoginAskResponsePacket;
import io.netty.channel.ChannelHandlerContext;

import static com.ksk.mf.packet.PacketId.REQUEST_ASK_LOGIN;
import static com.ksk.mf.packet.PacketId.RESPONSE_ASK_LOGIN;

public class LoginAskEvent implements EventHandler {

    @Override
    public int responseCode() {
        return RESPONSE_ASK_LOGIN.getPacketId();
    }

    @Override
    public Class<? extends ResponsePacket> responsePacket() {
        return LoginAskResponsePacket.class;
    }

    @Override
    public void handleEvent(ChannelHandlerContext ctx, ResponsePacket packet) {
        new RequestPacket(REQUEST_ASK_LOGIN).sendPacket(ctx);
    }
}
