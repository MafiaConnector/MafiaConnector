package com.ksk.mf.events.item;

import com.ksk.mf.events.EventHandler;
import com.ksk.mf.packet.response.ResponsePacket;
import com.ksk.mf.packet.response.impl.item.ObtainItemLotteryResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import static com.ksk.mf.packet.PacketId.RESPONSE_OBTAIN_LOTTERY;

public class ObtainItemLotteryEvent implements EventHandler {
    private final Marker marker = MarkerFactory.getMarker("ITEM");

    @Override
    public int responseCode() {
        return RESPONSE_OBTAIN_LOTTERY.getPacketId();
    }

    @Override
    public Class<? extends ResponsePacket> responsePacket() {
        return ObtainItemLotteryResponsePacket.class;
    }

    @Override
    public void handleEvent(ChannelHandlerContext ctx, ResponsePacket packet) {
        ObtainItemLotteryResponsePacket lottery = (ObtainItemLotteryResponsePacket) packet;
        lottery.getObtains().forEach(item ->
                log.info(marker, "아이템 수령[ItemCode: {}, ItemAmount: {}, LotteryLevel: {}, Additional: {}]", item.getItemCode(), item.getItemAmount(),
                        item.getLotteryLevel(), item.getAdditional()));
    }
}
