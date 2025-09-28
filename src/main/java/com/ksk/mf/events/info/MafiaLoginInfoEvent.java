package com.ksk.mf.events.info;

import com.ksk.mf.events.EventHandler;
import com.ksk.mf.packet.response.ResponsePacket;
import com.ksk.mf.packet.response.impl.info.MafiaLoginInfoResponsePacket;
import io.netty.channel.ChannelHandlerContext;

import static com.ksk.mf.packet.PacketId.RESPONSE_USERINFO;

public class MafiaLoginInfoEvent implements EventHandler {
    @Override
    public int responseCode() {
        return RESPONSE_USERINFO.getPacketId();
    }

    @Override
    public Class<? extends ResponsePacket> responsePacket() {
        return MafiaLoginInfoResponsePacket.class;
    }

    @Override
    public void handleEvent(ChannelHandlerContext ctx, ResponsePacket packet) {
        MafiaLoginInfoResponsePacket response = ((MafiaLoginInfoResponsePacket) packet);
        log.info("현재 명성: {}", response.getInfo().getFame());
        log.info("현재 루블: {}", response.getInfo().getRuble());
    }
}
