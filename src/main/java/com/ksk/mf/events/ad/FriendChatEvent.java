package com.ksk.mf.events.ad;

import com.ksk.mf.events.EventHandler;
import com.ksk.mf.packet.response.ResponsePacket;
import com.ksk.mf.packet.response.impl.ad.FriendChatResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import static com.ksk.mf.packet.PacketId.RESPONSE_FRIEND_CHAT;

public class FriendChatEvent implements EventHandler {
    private final Marker marker = MarkerFactory.getMarker("FRIEND_CHAT");
    @Override
    public int responseCode() {
        return RESPONSE_FRIEND_CHAT.getPacketId();
    }

    @Override
    public Class<? extends ResponsePacket> responsePacket() {
        return FriendChatResponsePacket.class;
    }

    @Override
    public void handleEvent(ChannelHandlerContext ctx, ResponsePacket packet) {
        FriendChatResponsePacket friend = castAs(packet);
        log.info(marker, "접챗[{} : {}]", friend.getSender(), friend.getMessage());
    }
}
