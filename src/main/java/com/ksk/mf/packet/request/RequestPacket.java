package com.ksk.mf.packet.request;

import com.ksk.mf.packet.Packet;
import com.ksk.mf.packet.PacketId;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;

import java.nio.charset.StandardCharsets;

@SuppressWarnings("unused")
public class RequestPacket implements Packet {
    private int packetId;
    private String context;

    public RequestPacket(int packetId, String context) {
        this.packetId = packetId;
        this.context = context;
    }

    public RequestPacket(int packetId) {
        this.packetId = packetId;
    }

    public RequestPacket(PacketId packet) {
        this.packetId = packet.getPacketId();
    }

    public RequestPacket(PacketId packet, String context) {
        this.packetId = packet.getPacketId();
        this.context = context;
    }

    @Override
    public ByteBuf toByteBuf() {
        byte[] send = getByte(context);
        int size = 4 + PACKET_ID_LENGTH + send.length;
        ByteBuf buf = ByteBufAllocator.DEFAULT.buffer(size);
        buf.writeInt(size - 4);
        buf.writeInt(packetId);
        buf.writeBytes(send);
        return buf;
    }

    public void sendPacket(ChannelHandlerContext ctx) {
        ctx.writeAndFlush(new BinaryWebSocketFrame(toByteBuf())).addListener(ChannelFutureListener.CLOSE_ON_FAILURE);
    }

    @Override
    public void fromByteBuf(ByteBuf buf) {
        throw new UnsupportedOperationException("Don't call this method.");
    }

    private byte[] getByte(String str) {
        if(str == null)
            return new byte[0];
        return str.getBytes(StandardCharsets.UTF_8);
    }

    public int getPacketId() {
        return this.packetId;
    }


    public String getContext() {
        return this.context;
    }

    public void setPacketId(int packetId) {
        this.packetId = packetId;
    }

    public void setContext(String context) {
        this.context = context;
    }

}
