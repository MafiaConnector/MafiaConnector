package com.ksk.mf.packet.response.impl.info;

import com.ksk.mf.game.info.LoginInfo;
import com.ksk.mf.packet.response.ResponsePacket;
import io.netty.buffer.ByteBuf;

import java.nio.charset.StandardCharsets;

public class LoginInfoResponsePacket extends ResponsePacket {
    private LoginInfo loginInfo;

    public LoginInfoResponsePacket(ByteBuf buf) {
        super(buf);
    }

    @Override
    public void fromByteBuf(ByteBuf buf) {
        super.fromByteBuf(buf);
        int listSize = listSize();
        assert listSize == 1;
        LoginInfo.Builder builder = new LoginInfo.Builder();
        builder.ruble(buf.readInt())
                .luna(buf.readInt())
                .fame(buf.readInt())
                .nicknameColor(buf.readInt())
                .maxFriend(buf.readInt())
                .newFriendChat(buf.readInt())
                .tmpInt1(buf.readInt())
                .tmpInt2(buf.readInt())
                .guildInitialColor(buf.readInt())
                .guildInitialBackgroundColor(buf.readInt())
                .guildId(buf.readInt())
                .guildPoint(buf.readInt())
                .frame(buf.readInt())
                .randomBoxAmount(buf.readInt())
                .state(buf.readInt())
                .guildLevel(buf.readInt())
                .id(buf.readLong())
                .nameTag(buf.readLong())
                .curNameTag(buf.readLong())
                .collection(buf.readLong())
                .curCollection(buf.readLong())
                .collection2(buf.readLong())
                .curCollection2(buf.readLong())
                .collection3(buf.readLong())
                .curCollection3(buf.readLong())
                .emoticon(buf.readLong())
                .lastLoginTime(buf.readLong())
                .facebookId(buf.readLong())
                .isFriend(buf.readBoolean())
                .isPostcardSent(buf.readBoolean())
                .userName(buf.readCharSequence(buf.readInt(), StandardCharsets.UTF_8).toString())
                .introduce(buf.readCharSequence(buf.readInt(), StandardCharsets.UTF_8).toString())
                .guildInitial(buf.readCharSequence(buf.readInt(), StandardCharsets.UTF_8).toString())
                .guildName(buf.readCharSequence(buf.readInt(), StandardCharsets.UTF_8).toString());
        int count = buf.readInt();
        for (int i = 0; i < count; i++) {
            String itemName = buf.readCharSequence(buf.readInt(), StandardCharsets.UTF_8).toString();
            int itemCode =  buf.readInt();
            builder.addInventoryItem(itemName, itemCode);
        }
        this.loginInfo =  builder.build();
    }

    public LoginInfo getLoginInfo() {
        return loginInfo;
    }
}
