package com.ksk.mf.packet.response.impl.versioncheck;

import com.ksk.mf.packet.response.ResponsePacket;
import io.netty.buffer.ByteBuf;

public class VersionCheckResponsePacket extends ResponsePacket {
    public VersionCheckResponsePacket(ByteBuf buf) {
        super(buf);
    }
}
