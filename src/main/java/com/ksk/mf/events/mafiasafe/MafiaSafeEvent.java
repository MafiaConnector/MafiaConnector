package com.ksk.mf.events.mafiasafe;

import com.ksk.mf.events.EventHandler;
import com.ksk.mf.packet.response.ResponsePacket;
import com.ksk.mf.packet.response.impl.mafiasafe.MafiaSafeResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import static com.ksk.mf.packet.PacketId.RESPONSE_MAFIA_SAFE;

public class MafiaSafeEvent implements EventHandler {
    private static final Marker marker = MarkerFactory.getMarker("LOGIN");
    @Override
    public int responseCode() {
        return RESPONSE_MAFIA_SAFE.getPacketId();
    }

    @Override
    public Class<? extends ResponsePacket> responsePacket() {
        return MafiaSafeResponsePacket.class;
    }

    @Override
    public void handleEvent(ChannelHandlerContext ctx, ResponsePacket packet) {
        MafiaSafeResponsePacket safe = ((MafiaSafeResponsePacket) packet);
        String nickname = safe.getNickname();
        log.info(marker, "마피아 세이프 인증[닉네임 : {}]", nickname);
        boolean isSafe = safe.isSafe();
        if(!isSafe) {
            ctx.close().addListener(future -> log.error(marker, "마피아 세이프에 인증되지 않은 디바이스 키를 사용하셨습니다. 본인 PC 에서 쓰는 deviceKey를 넣어주세요."));
        }
    }
}
