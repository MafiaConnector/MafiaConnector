package com.ksk.mf.packet.response.impl.item;

import com.ksk.mf.item.LimitedItem;
import com.ksk.mf.packet.response.ResponsePacket;
import io.netty.buffer.ByteBuf;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class LimitedItemResponsePacket extends ResponsePacket {
    private List<LimitedItem> items;

    public LimitedItemResponsePacket(ByteBuf buf) {
        super(buf);
    }

    public void fromByteBuf(ByteBuf itemBuf) {
        super.fromByteBuf(itemBuf);
        if (super.listSize() < 1)
            return;
        int listSize = super.listSize();
        this.items = new ArrayList<>(listSize);
        for (int i = 0; i < listSize; i++) {
            LimitedItem.Builder builder = LimitedItem.builder();
            this.items.add(builder.limitedItemId(itemBuf.readLong())
                    .closingTime(itemBuf.readLong())
                    .limitedItemType(LimitedItem.LimitedItemType.fromLimitedItemType(itemBuf.readInt()))
                    .itemCode(itemBuf.readInt())
                    .itemAmount(itemBuf.readInt())
                    .forAll(itemBuf.readBoolean())
                    .message(itemBuf.readCharSequence(itemBuf.readInt(), StandardCharsets.UTF_8).toString())
                    .sender(itemBuf.readCharSequence(itemBuf.readInt(), StandardCharsets.UTF_8).toString())
                    .additional(itemBuf.readCharSequence(itemBuf.readInt(), StandardCharsets.UTF_8).toString()).build());
        }
    }

    public List<LimitedItem> getLimitedItems() {
        return items;
    }

}

