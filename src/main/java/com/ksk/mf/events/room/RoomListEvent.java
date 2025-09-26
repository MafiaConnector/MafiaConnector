package com.ksk.mf.events.room;

import com.ksk.mf.events.EventHandler;
import com.ksk.mf.packet.response.ResponsePacket;
import com.ksk.mf.packet.response.impl.room.RoomListResponsePacket;
import com.ksk.mf.room.Room;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import java.util.List;

import static com.ksk.mf.packet.PacketId.RESPONSE_ROOM_LIST;

public class RoomListEvent implements EventHandler {
    private final Marker marker = MarkerFactory.getMarker("ROOM");

    @Override
    public int responseCode() {
        return RESPONSE_ROOM_LIST.getPacketId();
    }

    @Override
    public Class<? extends ResponsePacket> responsePacket() {
        return RoomListResponsePacket.class;
    }

    @Override
    public void handleEvent(ChannelHandlerContext ctx, ResponsePacket packet) {
        RoomListResponsePacket roomList = (RoomListResponsePacket) packet;
        List<Room> rooms = roomList.getRoomList();
        for (Room room : rooms) {
            log.debug(marker, "{}번방, 방제 : {}, 플레이중: {}, 방 타입: {}, 방 스킨: {}, 익명: {}, 비번방: {}",
                    room.getRoomIndex(), room.getRoomName(), room.isPlaying(),
                    room.getRoomType().name(), room.getRoomSkin().name(), room.isAnonymous(), room.isLocked());
        }
    }
}
