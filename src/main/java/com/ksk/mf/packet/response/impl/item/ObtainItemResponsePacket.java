package com.ksk.mf.packet.response.impl.item;

import com.ksk.mf.packet.response.ResponsePacket;
import io.netty.buffer.ByteBuf;

import java.nio.charset.StandardCharsets;

public class ObtainItemResponsePacket extends ResponsePacket {
    private int itemCode;
    private int itemAmount;
    private int lotteryLevel;
    private String additional;

    public ObtainItemResponsePacket() {

    }

    public ObtainItemResponsePacket(ByteBuf buf) {
        super(buf);
    }

    @Override
    public void fromByteBuf(ByteBuf buf) {
        super.fromByteBuf(buf);
    }

    protected void fromDataBuf(ByteBuf buf) {
        this.itemCode = buf.readInt();
        this.itemAmount = buf.readInt();
        this.lotteryLevel = buf.readInt();
        this.additional = buf.readCharSequence(buf.readInt(), StandardCharsets.UTF_8).toString();
    }

    public int getItemCode() {
        return itemCode;
    }

    public int getItemAmount() {
        return itemAmount;
    }

    public int getLotteryLevel() {
        return lotteryLevel;
    }

    public String getAdditional() {
        return additional;
    }
}
