package com.ksk.mf.handler.init;

import com.ksk.mf.packet.request.RequestPacket;
import com.ksk.mf.packet.request.impl.RoomListRequestPacket;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import static com.ksk.mf.packet.PacketId.*;

public class InitializedHandler {
    public static InitializedHandler INSTANCE = new InitializedHandler();
    private final List<RequestPacket> requestPackets = new ArrayList<>();


    /**
     * 서버 접속 성공시 먼저 보내져야하는 패킷을 미리 등록하여 1회 보내줍니다.
     * 지속적인 패킷 요청이 필요할시
     * @code WebSocketMessageHandler.userEventTriggered() 에서 처리하도록 합시다.
     */
    private InitializedHandler() {
        addRequest(() -> new RequestPacket(REQUEST_LIMITED_ITEM_LIST), checkEnv("CHECK_LIMITED_ITEM_LIST_ON_INIT"));
        addRequest(() -> new RoomListRequestPacket(true), checkEnv("CHECK_ROOM_LIST_ON_INIT"));
        addRequest(() -> new RequestPacket(REQUEST_CODE_POSTCARD_ALERT_LIST), checkEnv("CHECK_POSTCARD_ALERT_ON_INIT"));
        addRequest(() -> new RequestPacket(REQUEST_USERINFO), checkEnv("CHECK_USERINFO_ON_INIT"));
    }

    public void addRequest(Supplier<RequestPacket> packet, boolean test) {
        if(test) {
            this.requestPackets.add(packet.get());
        }
    }

    public List<RequestPacket> getInitPackets() {
        return this.requestPackets;
    }

    private boolean checkEnv(String env) {
        return System.getenv(env) != null && Boolean.parseBoolean(System.getenv(env));
    }

}
