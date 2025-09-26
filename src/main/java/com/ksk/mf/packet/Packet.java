package com.ksk.mf.packet;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.util.function.Function;

public interface Packet {
    int PACKET_ID_LENGTH = 4;
    Function<byte[], ByteBuf> toByteBuf = Unpooled::wrappedBuffer;
    ByteBuf toByteBuf();
    void fromByteBuf(ByteBuf buf);
}
