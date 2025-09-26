package com.ksk.mf.packet.response.impl.mafiasafe;

import com.ksk.mf.packet.response.ResponsePacket;
import io.netty.buffer.ByteBuf;

import java.nio.charset.StandardCharsets;

@SuppressWarnings("unused")
public class MafiaSafeResponsePacket extends ResponsePacket {
    private int id;
    private int frame;
    private int nicknameColor;
    private int experience;
    private long curNameTag;
    private long curNameTagBgColor;
    private long curGem;
    private long curCollection;
    private long curCollection2;
    private long curCollection3;
    private boolean isHiddenGem;
    private String nickname;
    private boolean isSafe;

    public MafiaSafeResponsePacket(ByteBuf buf) {
        super(buf);
    }

    @Override
    public void fromByteBuf(ByteBuf safeBuf) {
        super.fromByteBuf(safeBuf);
        this.id = safeBuf.readInt();
        this.frame = safeBuf.readInt();
        this.nicknameColor = safeBuf.readInt();
        this.experience = safeBuf.readInt();
        this.curNameTag = safeBuf.readLong();
        this.curNameTagBgColor = safeBuf.readLong();
        this.curGem = safeBuf.readLong();
        this.curCollection = safeBuf.readLong();
        this.curCollection2 = safeBuf.readLong();
        this.curCollection3 = safeBuf.readLong();
        this.isHiddenGem = safeBuf.readBoolean();
        this.nickname = safeBuf.readCharSequence(safeBuf.readInt(), StandardCharsets.UTF_8).toString();
        this.isSafe = safeBuf.readInt() == 1;
    }

    public int getId() {
        return id;
    }

    public int getFrame() {
        return frame;
    }

    public int getNicknameColor() {
        return nicknameColor;
    }

    public int getExperience() {
        return experience;
    }

    public long getCurNameTag() {
        return curNameTag;
    }

    public long getCurNameTagBgColor() {
        return curNameTagBgColor;
    }

    public long getCurGem() {
        return curGem;
    }

    public long getCurCollection() {
        return curCollection;
    }

    public long getCurCollection2() {
        return curCollection2;
    }

    public boolean isHiddenGem() {
        return isHiddenGem;
    }

    public long getCurCollection3() {
        return curCollection3;
    }

    public String getNickname() {
        return nickname;
    }

    public boolean isSafe() {
        return isSafe;
    }
}
