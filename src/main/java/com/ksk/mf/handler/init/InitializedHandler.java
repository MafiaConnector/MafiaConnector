package com.ksk.mf.handler.init;

import com.ksk.mf.packet.request.RequestPacket;
import com.ksk.mf.packet.request.impl.RoomListRequestPacket;

import java.util.ArrayList;
import java.util.List;

import static com.ksk.mf.packet.PacketId.REQUEST_LIMITED_ITEM_LIST;

public class InitializedHandler {
    public static InitializedHandler INSTANCE = new InitializedHandler();
    private final List<RequestPacket> requestPackets = new ArrayList<>();


    /**
     * 서버 접속 성공시 먼저 보내져야하는 패킷을 미리 등록하여 1회 보내줍니다.
     * 지속적인 패킷 요청이 필요할시
     * @code WebSocketMessageHandler.userEventTriggered() 에서 처리하도록 합시다.
     */
    private InitializedHandler() {
        this.requestPackets.add(new RequestPacket(REQUEST_LIMITED_ITEM_LIST));
        this.requestPackets.add(new RoomListRequestPacket(true));
    }

    public List<RequestPacket> getInitPackets() {
        return this.requestPackets;
    }


}
