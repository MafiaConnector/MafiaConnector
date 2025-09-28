package com.ksk.mf.packet.response.impl.room;

import com.ksk.mf.packet.response.ResponsePacket;
import com.ksk.mf.game.room.Room;
import com.ksk.mf.game.room.RoomSkin;
import com.ksk.mf.game.room.RoomType;
import io.netty.buffer.ByteBuf;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class RoomListResponsePacket extends ResponsePacket {
    private List<Room> roomList;

    public RoomListResponsePacket(ByteBuf buf) {
        super(buf);
    }

    @Override
    public void fromByteBuf(ByteBuf buf) {
        super.fromByteBuf(buf);
        int listSize = super.listSize();
        this.roomList = new ArrayList<>(listSize);
        for (int i = 0; i < listSize; i++) {
            Room.Builder builder = new Room.Builder();
            Room room = builder.setRoomId(buf.readLong())
                    .setMaxUser(buf.readInt())
                    .setUserCount(buf.readInt())
                    .setRoomIndex(buf.readInt())
                    .setRoomType(RoomType.fromRoomType(buf.readInt()))
                    .setLocked(buf.readBoolean())
                    .setRoomName(buf.readCharSequence(buf.readInt(), StandardCharsets.UTF_8).toString())
                    .setRoomSkin(RoomSkin.getRoomSkin(buf.readInt()))
                    .setMumbleRoom(buf.readBoolean())
                    .setAnonymous(buf.readBoolean())
                    .build();
            this.roomList.add(room);
        }
        this.roomList.sort(null);
    }

    public List<Room> getRoomList() {
        return this.roomList;
    }
}
