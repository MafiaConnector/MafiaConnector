package com.ksk.mf.events.postcard;

import com.ksk.mf.events.EventHandler;
import com.ksk.mf.packet.response.ResponsePacket;
import com.ksk.mf.packet.response.impl.postcard.PostcardMessageResponsePacket;
import com.ksk.mf.game.postcard.PostcardMessage;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import java.util.List;

import static com.ksk.mf.packet.PacketId.RESPONSE_CODE_COMMON_POSTCARD_ALERT_LIST;

public class PostcardMessageEvent implements EventHandler {
    private final Marker marker = MarkerFactory.getMarker("POSTCARD");
    @Override
    public int responseCode() {
        return RESPONSE_CODE_COMMON_POSTCARD_ALERT_LIST.getPacketId();
    }

    @Override
    public Class<? extends ResponsePacket> responsePacket() {
        return PostcardMessageResponsePacket.class;
    }

    @Override
    public void handleEvent(ChannelHandlerContext ctx, ResponsePacket packet) {
        PostcardMessageResponsePacket responsePacket = castAs(packet);
        List<PostcardMessage> postcards = responsePacket.getPostcards();
        for (PostcardMessage postcard : postcards) {
            log.info(marker, "{} , {} , {}", postcard.getName(), postcard.getPostcard(), postcard.getUserId());
        }
    }
}
