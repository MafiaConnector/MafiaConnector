package com.ksk.mf.events.item;

import com.ksk.mf.events.EventHandler;
import com.ksk.mf.game.item.LimitedItem;
import com.ksk.mf.packet.request.RequestPacket;
import com.ksk.mf.packet.response.ResponsePacket;
import com.ksk.mf.packet.response.impl.item.LimitedItemResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import java.util.List;

import static com.ksk.mf.packet.PacketId.*;

public class LimitedItemEvent implements EventHandler {
    private final Marker marker = MarkerFactory.getMarker("ITEM");

    @Override
    public int responseCode() {
        return RESPONSE_LIMITED_ITEM_LIST.getPacketId();
    }

    @Override
    public Class<? extends ResponsePacket> responsePacket() {
        return LimitedItemResponsePacket.class;
    }

    @Override
    public void handleEvent(ChannelHandlerContext ctx, ResponsePacket packet) {
        LimitedItemResponsePacket limited = (LimitedItemResponsePacket) packet;
        if (limited.listSize() < 1) {
            log.info(marker, "우편함이 비어있습니다.");
            return;
        }
        List<LimitedItem> items = limited.getLimitedItems();
        for (LimitedItem item : items) {
            log.info(marker, "LimitedItemId: {}, ClosingTime: {}, LimitedItemType: {}, ItemCode: {}, ItemAmount: {}, ForAll: {}, Message: {}, Sender: {}, Additional: {}",
                    item.getLimitedItemId(), item.getClosingTime(), item.getLimitedItemType(), item.getItemCode(), item.getItemAmount(), item.isForAll(), item.getMessage(), item.getSender(), item.getAdditional());
            switch (item.getLimitedItemType()) {
                case LEVEL_UP_REWARD:
                    new RequestPacket(REQUEST_RECEIVE_LEVEL_REWARD).sendPacket(ctx);
                    log.info(marker, "레벨업 보상[ItemCode: {}, ItemAmount: {}]", item.getItemCode(), item.getItemAmount());
                    break;
                case NORMAL_REWARD:
                    new RequestPacket(REQUEST_LIMITED_ITEM_RECEIVE, item.getLimitedItemId() + "\n" + (item.isForAll() ? 1 : 0)).sendPacket(ctx);
                    log.info(marker, "한정 아이템[ItemCode: {}, ItemAmount: {}] Received", item.getItemCode(), item.getItemAmount());
                    break;
                case DAILY_AD_REWARD:
                    new RequestPacket(REQUEST_DAILY_AD_RECEIVE_REWARD).sendPacket(ctx);
                    log.info(marker, "42잭팟 수령 시도중.");
                    break;
            }
        }
    }
}
