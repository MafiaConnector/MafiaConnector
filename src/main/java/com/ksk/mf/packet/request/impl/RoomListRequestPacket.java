package com.ksk.mf.packet.request.impl;

import com.ksk.mf.packet.PacketId;
import com.ksk.mf.packet.request.RequestPacket;

public class RoomListRequestPacket extends RequestPacket {

    /**
     * 방 리스트 를 보여주는 패킷입니다.
     * @param isShowPlaying 플레이중인 방도 보여줄것인지, 대기방만 보여줄것인지 선택 할 수 있습니다.
     */
    public RoomListRequestPacket(boolean isShowPlaying) {
        super(PacketId.REQUEST_ROOM_LIST, isShowPlaying ? "1" : "0");
    }
}
