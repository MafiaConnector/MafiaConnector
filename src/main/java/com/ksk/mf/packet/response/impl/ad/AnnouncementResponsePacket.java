package com.ksk.mf.packet.response.impl.ad;

import com.ksk.mf.ad.AdType;
import com.ksk.mf.packet.response.ResponsePacket;
import io.netty.buffer.ByteBuf;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

@SuppressWarnings("unused")
public class AnnouncementResponsePacket extends ResponsePacket {
    private long adIndex;
    private AdType adType;
    private String message;
    private int userId;
    private int emoticonIndex;

    public AnnouncementResponsePacket(ByteBuf buf) {
        super(buf);
    }

    @Override
    public void fromByteBuf(ByteBuf messageBuf) {
        super.fromByteBuf(messageBuf);
        this.adIndex = messageBuf.readLong();
        this.adType = AdType.fromAdType(messageBuf.readInt());
        this.message = messageBuf.readCharSequence(messageBuf.readInt(), StandardCharsets.UTF_8).toString();
        this.userId = messageBuf.readInt();
        this.emoticonIndex = messageBuf.readInt();
    }

    public long getAdIndex() {
        return adIndex;
    }

    public AdType getAdType() {
        return adType;
    }

    public String getMessage() {
        return message;
    }

    public int getUserId() {
        return userId;
    }

    public int getEmoticonIndex() {
        return emoticonIndex;
    }

    @Override
    public String toString() {
        return "AnnouncementResponsePacket{" +
                "adIndex=" + adIndex +
                ", adType=" + adType +
                ", message='" + message + '\'' +
                ", userId=" + userId +
                ", emoticonIndex=" + emoticonIndex +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof AnnouncementResponsePacket)) return false;
        AnnouncementResponsePacket that = (AnnouncementResponsePacket) o;
        return adIndex == that.adIndex;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(adIndex);
    }
}
