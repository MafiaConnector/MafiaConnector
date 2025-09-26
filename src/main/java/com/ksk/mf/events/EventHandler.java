package com.ksk.mf.events;

import com.ksk.mf.packet.response.ResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface EventHandler {
    Logger log = LoggerFactory.getLogger(EventHandler.class);
    int responseCode();
    Class<? extends ResponsePacket> responsePacket();
    void handleEvent(ChannelHandlerContext ctx, ResponsePacket packet);
}
