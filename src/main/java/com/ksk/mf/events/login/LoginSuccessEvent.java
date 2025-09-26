package com.ksk.mf.events.login;

import com.ksk.mf.events.EventHandler;
import com.ksk.mf.handler.init.InitializedHandler;
import com.ksk.mf.packet.response.ResponsePacket;
import com.ksk.mf.packet.response.impl.login.LoginSuccessResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleStateHandler;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import java.util.concurrent.TimeUnit;

import static com.ksk.mf.packet.PacketId.RESPONSE_LOGIN_SUCCESS;

public class LoginSuccessEvent implements EventHandler {
    private final Marker marker = MarkerFactory.getMarker("LOGIN");
    @Override
    public int responseCode() {
        return RESPONSE_LOGIN_SUCCESS.getPacketId();
    }

    @Override
    public Class<? extends ResponsePacket> responsePacket() {
        return LoginSuccessResponsePacket.class;
    }

    @Override
    public void handleEvent(ChannelHandlerContext ctx, ResponsePacket packet) {
        log.info(marker, "Login Success");
        ctx.pipeline().addFirst("idleStateHandler", new IdleStateHandler(0,5,0, TimeUnit.SECONDS));
        InitializedHandler.INSTANCE.getInitPackets().forEach(p -> p.sendPacket(ctx));
    }
}
