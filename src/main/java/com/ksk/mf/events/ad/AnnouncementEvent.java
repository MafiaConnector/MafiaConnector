package com.ksk.mf.events.ad;

import com.ksk.mf.game.ad.AdType;
import com.ksk.mf.events.EventHandler;
import com.ksk.mf.packet.response.ResponsePacket;
import com.ksk.mf.packet.response.impl.ad.AnnouncementResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.ksk.mf.packet.PacketId.RESPONSE_ANNOUNCEMENT;

public class AnnouncementEvent implements EventHandler {
    private final Marker marker = MarkerFactory.getMarker("ANNOUNCEMENT");
    private final Marker postMarker = MarkerFactory.getMarker("엽서 캡처");
    private final Pattern postcardPattern = Pattern.compile("(.{1,6} : )(.*?)엽?\\s*(칼반|무반|무한반사|ㅁㅂ).*");
    private final boolean filterPostcard = System.getenv("FILTER_POSTCARD_FROM_ANNOUNCEMENT") != null && Boolean.parseBoolean(System.getenv("FILTER_POSTCARD_FROM_ANNOUNCEMENT"));

    @Override
    public int responseCode() {
        return RESPONSE_ANNOUNCEMENT.getPacketId();
    }

    @Override
    public Class<? extends ResponsePacket> responsePacket() {
        return AnnouncementResponsePacket.class;
    }

    @Override
    public void handleEvent(ChannelHandlerContext ctx, ResponsePacket packet) {
        AnnouncementResponsePacket ad = castAs(packet);
        log.info(marker, "{}, userId: {}, AdType: {}", ad.getMessage(), ad.getUserId(), ad.getAdType().getName());
        if (!filterPostcard || ad.getAdType() == AdType.SCHOOL)
            return;
        String message = ad.getMessage();
        Matcher matcher = postcardPattern.matcher(message);
        if(matcher.matches()) {
            log.debug(postMarker, "{} {} ",matcher.group(1), matcher.group(2));
        }
    }
}
