package com.ksk.mf.events.login;

import com.ksk.mf.events.EventHandler;
import com.ksk.mf.packet.response.ResponsePacket;
import com.ksk.mf.packet.response.impl.login.LogoutResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import static com.ksk.mf.packet.PacketId.RESPONSE_LOGOUT;

public class LogoutEvent implements EventHandler {
    private final Marker marker = MarkerFactory.getMarker("LOGIN");
    @Override
    public int responseCode() {
        return RESPONSE_LOGOUT.getPacketId();
    }

    @Override
    public Class<? extends ResponsePacket> responsePacket() {
        return LogoutResponsePacket.class;
    }

    @Override
    public void handleEvent(ChannelHandlerContext ctx, ResponsePacket packet) {
        ctx.close().addListener(future -> log.info(marker, "Logout Success"));
    }
}
