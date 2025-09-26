package com.ksk.mf.packet.response.impl.item;

import io.netty.buffer.ByteBuf;

import java.util.ArrayList;
import java.util.List;

public class ObtainItemLotteryResponsePacket extends ObtainItemResponsePacket {
    private List<ObtainItemResponsePacket> obtains;
    public ObtainItemLotteryResponsePacket(ByteBuf buf) {
        super(buf);
    }

    @Override
    public void fromByteBuf(ByteBuf lottery) {
        super.fromByteBuf(lottery);
        int size = super.listSize();
        this.obtains = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            ObtainItemResponsePacket obtainItemResponsePacket = new ObtainItemResponsePacket();
            obtainItemResponsePacket.fromDataBuf(lottery);
            //EventLotteryData
            this.obtains.add(obtainItemResponsePacket);
        }
    }

    public List<ObtainItemResponsePacket> getObtains() {
        return obtains;
    }
}
