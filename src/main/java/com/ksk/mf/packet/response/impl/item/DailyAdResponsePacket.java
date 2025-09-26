package com.ksk.mf.packet.response.impl.item;

import com.ksk.mf.packet.response.ResponsePacket;
import io.netty.buffer.ByteBuf;

public class DailyAdResponsePacket extends ResponsePacket {
    private long itemCode;

    public DailyAdResponsePacket(ByteBuf buf) {
        super(buf);
    }

    @Override
    public void fromByteBuf(ByteBuf buf) {
        super.fromByteBuf(buf);
        if(super.listSize() < 1)
            return;
        this.itemCode = buf.readLong();
    }

    public long getItemCode() {
        return itemCode;
    }

}
