package com.ksk.mf.events.login;

import com.ksk.mf.events.EventHandler;
import com.ksk.mf.packet.response.ResponsePacket;
import com.ksk.mf.packet.response.impl.login.LoginFailedResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import static com.ksk.mf.packet.PacketId.RESPONSE_LOGIN_FAILED;

public class LoginFailedEvent implements EventHandler {
    private final Marker marker = MarkerFactory.getMarker("LOGIN");
    @Override
    public int responseCode() {
        return RESPONSE_LOGIN_FAILED.getPacketId();
    }

    @Override
    public Class<? extends ResponsePacket> responsePacket() {
        return LoginFailedResponsePacket.class;
    }

    @Override
    public void handleEvent(ChannelHandlerContext ctx, ResponsePacket packet) {
        ctx.close().addListener(future -> log.error(marker, "AccessToken 만료 되었으니 새로 넣어주셔야 합니다."));
    }
}
