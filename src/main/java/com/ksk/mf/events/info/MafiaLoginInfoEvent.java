package com.ksk.mf.events.info;

import com.ksk.mf.events.EventHandler;
import com.ksk.mf.gui.MainController;
import com.ksk.mf.packet.response.ResponsePacket;
import com.ksk.mf.packet.response.impl.info.MafiaLoginInfoResponsePacket;
import io.netty.channel.ChannelHandlerContext;

import static com.ksk.mf.packet.PacketId.RESPONSE_SEARCH_USERNAME;

public class MafiaLoginInfoEvent implements EventHandler {
    @Override
    public int responseCode() {
        return RESPONSE_SEARCH_USERNAME.getPacketId();
    }

    @Override
    public Class<? extends ResponsePacket> responsePacket() {
        return MafiaLoginInfoResponsePacket.class;
    }

    @Override
    public void handleEvent(ChannelHandlerContext ctx, ResponsePacket packet) {
        MafiaLoginInfoResponsePacket response = ((MafiaLoginInfoResponsePacket) packet);
        MainController.getInstance().onLoginInfoReceived(response.getInfo());
        log.info("현재 명성: {}", response.getInfo().getFame());
        response.getInfo().getCurrentSkinMap().forEach((skinMap,skin)-> log.info("{} : {}", skinMap, skin));
        log.info("{}", response.getInfo().getHasNewGuildChat());
        log.info("{} 님의 마지막 로그아웃 시간: {}", response.getInfo().getUserName() ,response.getInfo().getLastLoginTime());
        log.info("{} 님의 현재 온라인 상태: {}", response.getInfo().getUserName(), response.getInfo().isOnline());
        if(response.getInfo().getCoupleId() != 0) {
            int color = response.getInfo().getCoupleColor();
            int red = (color >> 16) & 0xFF;
            int green = (color >> 8) & 0xFF;
            int blue = color & 0xFF;
            log.info("{} 님의 큐피드 : {} 와 {} 일 째 연애중!, 하트색: [{}, {}, {}]", response.getInfo().getUserName(), response.getInfo().getCoupleNickname(), response.getInfo().getCoupleDateCount(), red, green, blue);
        }
        log.info("{} 님의 새 접챗 개수: {}", response.getInfo().getUserName(), response.getInfo().getNewFriendChat());
        log.info("{} 님의 중요 아이템: {}", response.getInfo().getUserName(), response.getInfo().getFavoriteItems());
        log.info("MafiaLoginInfo: {}", response.getInfo());
    }
}
