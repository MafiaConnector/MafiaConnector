package com.ksk.mf.packet.response;

import com.ksk.mf.packet.Packet;
import io.netty.buffer.ByteBuf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ResponsePacket implements Packet {
    private static final Logger log = LoggerFactory.getLogger(ResponsePacket.class);
    private int packetId;
    private int listSize;

    public ResponsePacket() {
        // call fromByteBuf(ByteBuf buf) after create object manually;
    }

    public ResponsePacket(ByteBuf buf) {
        this.fromByteBuf(buf);
    }

    @Override
    public ByteBuf toByteBuf() {
        throw new UnsupportedOperationException("THIS PACKET IS ONLY FOR deserialize");
    }


    /**
     * 각 패킷의 전체 size, packet opcode, 하위 데이터 배열의 길이를 결정해줍니다
     *
     * @param buf Netty의 ByteBuf 를 사용해서 안전하게 데이터를 관리합니다.
     */
    @Override
    public void fromByteBuf(ByteBuf buf) {
        int size = buf.readInt();
        if(buf.readableBytes() < size - 4) {
            log.info("Packet size is not match.");
        }
        this.packetId = buf.readInt();
        this.listSize = buf.readInt();
    }

    public int getPacketId() {
        return this.packetId;
    }


    public void setPacketId(int packetId) {
        this.packetId = packetId;
    }

    public int listSize() {
        return this.listSize;
    }
}
