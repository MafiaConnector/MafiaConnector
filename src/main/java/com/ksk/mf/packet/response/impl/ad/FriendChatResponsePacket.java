package com.ksk.mf.packet.response.impl.ad;

import com.ksk.mf.packet.response.ResponsePacket;
import io.netty.buffer.ByteBuf;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

@SuppressWarnings("unused")
public class FriendChatResponsePacket extends ResponsePacket {
    private int senderId;
    private int receiverId;
    private int emoticonIndex;
    private long chatIndex;
    private long emoticon;
    private String sender;
    private String message;

    public FriendChatResponsePacket(ByteBuf buf) {
        super(buf);
    }

    @Override
    public void fromByteBuf(ByteBuf messageBuf) {
        super.fromByteBuf(messageBuf);
        this.senderId = messageBuf.readInt();
        this.receiverId = messageBuf.readInt();
        this.emoticonIndex = messageBuf.readInt();
        this.chatIndex = messageBuf.readLong();
        this.emoticon = messageBuf.readLong();
        this.sender = messageBuf.readCharSequence(messageBuf.readInt(), StandardCharsets.UTF_8).toString();
        this.message = messageBuf.readCharSequence(messageBuf.readInt(), StandardCharsets.UTF_8).toString();
    }

    public int getSenderId() {
        return senderId;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public int getEmoticonIndex() {
        return emoticonIndex;
    }

    public long getChatIndex() {
        return chatIndex;
    }

    public long getEmoticon() {
        return emoticon;
    }

    public String getSender() {
        return sender;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof FriendChatResponsePacket)) return false;
        FriendChatResponsePacket that = (FriendChatResponsePacket) o;
        return chatIndex == that.chatIndex;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(chatIndex);
    }
}
