package com.ksk.mf.events.item;

import com.ksk.mf.events.EventHandler;
import com.ksk.mf.packet.request.RequestPacket;
import com.ksk.mf.packet.response.ResponsePacket;
import com.ksk.mf.packet.response.impl.item.DailyAdResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import static com.ksk.mf.packet.PacketId.*;

public class DailyAdEvent implements EventHandler {
    private final Marker marker = MarkerFactory.getMarker("ITEM");

    @Override
    public int responseCode() {
        return RESPONSE_DAILY_AD_RECEIVE_REWARD.getPacketId();
    }

    @Override
    public Class<? extends ResponsePacket> responsePacket() {
        return DailyAdResponsePacket.class;
    }

    @Override
    public void handleEvent(ChannelHandlerContext ctx, ResponsePacket packet) {
        DailyAdResponsePacket daily = castAs(packet);
        new RequestPacket(REQUEST_DAILY_AD_CHECK_REWARDED, Long.toString(daily.getItemCode())).sendPacket(ctx);
        log.info(marker, "42잭팟 수령 완료.");
        new RequestPacket(REQUEST_LIMITED_ITEM_LIST).sendPacket(ctx);
    }
}
