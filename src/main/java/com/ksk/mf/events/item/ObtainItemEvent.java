package com.ksk.mf.events.item;

import com.ksk.mf.events.EventHandler;
import com.ksk.mf.packet.response.ResponsePacket;
import com.ksk.mf.packet.response.impl.item.ObtainItemResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import static com.ksk.mf.packet.PacketId.RESPONSE_OBTAIN_ITEM;

public class ObtainItemEvent implements EventHandler {
    private final Marker marker = MarkerFactory.getMarker("ITEM");

    @Override
    public int responseCode() {
        return RESPONSE_OBTAIN_ITEM.getPacketId();
    }

    @Override
    public Class<? extends ResponsePacket> responsePacket() {
        return ObtainItemResponsePacket.class;
    }

    @Override
    public void handleEvent(ChannelHandlerContext ctx, ResponsePacket packet) {
        ObtainItemResponsePacket obtain = (ObtainItemResponsePacket) packet;
        int itemCode = obtain.getItemCode();
        int itemAmount = obtain.getItemAmount();
        int lotteryLevel = obtain.getLotteryLevel();
        String additional = obtain.getAdditional();
        log.info(marker, "아이템 수령[ItemCode: {}, ItemAmount: {}, LotteryLevel: {}, Additional: {}]", itemCode, itemAmount, lotteryLevel, additional);
    }
}
