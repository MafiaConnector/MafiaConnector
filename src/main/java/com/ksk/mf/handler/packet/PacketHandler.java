package com.ksk.mf.handler.packet;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

public interface PacketHandler {
    void handlePacket(ChannelHandlerContext ctx, ByteBuf buf);
}
