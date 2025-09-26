package com.ksk.mf.events.versioncheck;

import com.ksk.mf.AuthUtil;
import com.ksk.mf.events.EventHandler;
import com.ksk.mf.packet.request.RequestPacket;
import com.ksk.mf.packet.response.ResponsePacket;
import com.ksk.mf.packet.response.impl.versioncheck.VersionCheckResponsePacket;
import io.netty.channel.ChannelHandlerContext;

import static com.ksk.mf.packet.PacketId.REQUEST_SEND_AUTH;
import static com.ksk.mf.packet.PacketId.RESPONSE_VERSION_CHECK;

public class VersionCheckEvent implements EventHandler {
    @Override
    public int responseCode() {
        return RESPONSE_VERSION_CHECK.getPacketId();
    }

    @Override
    public Class<? extends ResponsePacket> responsePacket() {
        return VersionCheckResponsePacket.class;
    }


    @Override
    public void handleEvent(ChannelHandlerContext ctx, ResponsePacket packet) {
        //VERSION CHECK SUCCESS NOW SEND AUTH
        AuthUtil auth = AuthUtil.instance;
        String deviceKey = auth.getDeviceKey();
        String authToken = auth.getAuthToken();
        String fcmToken = auth.getFcmToken();
        String context = authToken + '\n' +
                System.getenv("MAFIA_CONNECTING_CODE") + '\n' +
                System.getenv("MAFIA_DEVICE_TYPE") + '\n' +
                fcmToken + '\n' + '\n' +
                deviceKey + '\n';
        new RequestPacket(REQUEST_SEND_AUTH, context).sendPacket(ctx);
    }
}
