package com.ksk.mf.packet.response.impl.postcard;

import com.ksk.mf.packet.response.ResponsePacket;
import com.ksk.mf.game.postcard.PostcardMessage;
import io.netty.buffer.ByteBuf;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class PostcardMessageResponsePacket extends ResponsePacket {
    private List<PostcardMessage> postcards;

    public PostcardMessageResponsePacket(ByteBuf buf) {
        super(buf);
    }

    @Override
    public void fromByteBuf(ByteBuf buf) {
        super.fromByteBuf(buf);
        int size = listSize();
        this.postcards = new ArrayList<>(size);

        for (int i = 0; i < size; i++) {
            PostcardMessage.Builder builder = new PostcardMessage.Builder();
            PostcardMessage postcard = builder.postcardMessageId(buf.readLong())
                    .nicknameColor(buf.readInt())
                    .userId(buf.readLong())
                    .usingCollection(buf.readLong())
                    .usingCollection2(buf.readLong())
                    .usingCollection3(buf.readLong())
                    .userNameTag(buf.readLong())
                    .userNameTagBgColor(buf.readLong())
                    .recommendTime(buf.readLong())
                    .postcardEndTime(buf.readLong())
                    .name(buf.readCharSequence(buf.readInt(), StandardCharsets.UTF_8).toString())
                    .thumbnail(buf.readCharSequence(buf.readInt(), StandardCharsets.UTF_8).toString())
                    .postcard(buf.readInt())
                    .frame(buf.readInt())
                    .reputationAmount(buf.readInt())
                    .cupidType(buf.readInt())
                    .isReply(buf.readBoolean())
                    .canReceiveNegativePostcard(buf.readBoolean()).build();
            this.postcards.add(postcard);
        }
    }

    public List<PostcardMessage> getPostcards() {
        return this.postcards;
    }
}
