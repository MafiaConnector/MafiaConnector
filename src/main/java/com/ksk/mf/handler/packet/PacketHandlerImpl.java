package com.ksk.mf.handler.packet;

import com.ksk.mf.events.EventHandler;
import com.ksk.mf.events.ad.AnnouncementEvent;
import com.ksk.mf.events.ad.FriendChatEvent;
import com.ksk.mf.events.item.DailyAdEvent;
import com.ksk.mf.events.item.LimitedItemEvent;
import com.ksk.mf.events.item.ObtainItemEvent;
import com.ksk.mf.events.item.ObtainItemLotteryEvent;
import com.ksk.mf.events.login.LoginAskEvent;
import com.ksk.mf.events.login.LoginFailedEvent;
import com.ksk.mf.events.login.LoginSuccessEvent;
import com.ksk.mf.events.login.LogoutEvent;
import com.ksk.mf.events.mafiasafe.MafiaSafeEvent;
import com.ksk.mf.events.room.RoomListEvent;
import com.ksk.mf.events.versioncheck.VersionCheckEvent;
import com.ksk.mf.packet.response.ResponsePacket;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class PacketHandlerImpl implements PacketHandler {
    private static final Logger log = LoggerFactory.getLogger(PacketHandlerImpl.class);
    private final List<EventHandler> handlers = new ArrayList<>();
    private final Map<Integer, Class<? extends ResponsePacket>> packetMap = new HashMap<>();

    public PacketHandlerImpl() {
        this.initHandlers();
    }

    private void initHandlers() {
        this.addHandler(ObtainItemEvent::new);
        this.addHandler(ObtainItemLotteryEvent::new);

        this.addHandler(LimitedItemEvent::new);
        this.addHandler(DailyAdEvent::new);
        // TODO 매 7시마다 우편함 체크 하는 코드 추가 필요함
        this.addHandler(FriendChatEvent::new);
        this.addHandler(AnnouncementEvent::new);

        this.addHandler(VersionCheckEvent::new);

        this.addHandler(LoginFailedEvent::new);
        this.addHandler(LoginSuccessEvent::new);
        this.addHandler(LoginAskEvent::new);
        this.addHandler(LogoutEvent::new);

        this.addHandler(MafiaSafeEvent::new);

        this.addHandler(RoomListEvent::new);
    }

    private void addHandler(Supplier<EventHandler> supplier) {
        EventHandler handler = supplier.get();
        handlers.add(handler);
        packetMap.put(handler.responseCode(), handler.responsePacket());
    }

    @Override
    public void handlePacket(ChannelHandlerContext ctx, ByteBuf buf) {
        ResponsePacket packet = getPacket(buf);
        if(packet == null) {
            return;
        }
        this.handlePacket(ctx, packet);
    }

    private ResponsePacket getPacket(ByteBuf buf) {
        if(buf.readableBytes() < 4)
            return null;
        if(buf.readableBytes() > 8) {
            buf.markReaderIndex();
            buf.readInt();
            int packetId = buf.readInt();
            buf.resetReaderIndex();
            Class<? extends ResponsePacket> clazz = packetMap.get(packetId);
            if(clazz == null) {
                return new ResponsePacket(buf);
            }

            try {
                Constructor<? extends ResponsePacket> constructor = clazz.getDeclaredConstructor(ByteBuf.class);
                return constructor.newInstance(buf);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                log.error("Error while creating packet!", e);
            }
        }
        return new ResponsePacket(buf);
    }

    public void handlePacket(ChannelHandlerContext ctx, ResponsePacket packet) {
        for (EventHandler handler : handlers) {
            if (handler.responseCode() == packet.getPacketId()) {
                handler.handleEvent(ctx, packet);
                return;
            }
        }
    }
}
