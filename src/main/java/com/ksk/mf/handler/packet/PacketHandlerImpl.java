package com.ksk.mf.handler.packet;

import com.ksk.mf.events.EventHandler;
import com.ksk.mf.packet.response.ResponsePacket;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class PacketHandlerImpl implements PacketHandler {
    private static final Logger log = LoggerFactory.getLogger(PacketHandlerImpl.class);
    private final List<EventHandler> handlers = new ArrayList<>();
    private final Map<Integer, Class<? extends ResponsePacket>> packetMap = new HashMap<>();

    public PacketHandlerImpl() {
        this.initHandlers();
    }

    private void initHandlers() {
        Reflections reflections = new Reflections("com.ksk.mf.events");
        Set<Class<? extends EventHandler>> handlerClasses = reflections.getSubTypesOf(EventHandler.class);

        log.info("Found {} EventHandler implementations", handlerClasses.size());

        for (Class<? extends EventHandler> handlerClass : handlerClasses) {
            // 추상 클래스나 인터페이스는 스킵
            if (Modifier.isAbstract(handlerClass.getModifiers()) || handlerClass.isInterface()) {
                continue;
            }

            try {
                Constructor<? extends EventHandler> constructor = handlerClass.getDeclaredConstructor();
                constructor.setAccessible(true);
                EventHandler handler = constructor.newInstance();

                handlers.add(handler);
                packetMap.put(handler.responseCode(), handler.responsePacket());

                log.debug("Registered handler: {} for packet code: {}",
                    handlerClass.getSimpleName(), handler.responseCode());
            } catch (NoSuchMethodException e) {
                log.warn("Handler {} does not have a default constructor", handlerClass.getSimpleName());
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                log.error("Failed to instantiate handler: {}", handlerClass.getSimpleName(), e);
            }
        }

        log.info("Successfully registered {} handlers", handlers.size());
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
                try {
                    handler.handleEvent(ctx, packet);
                    return;
                } catch(Exception e) {
                    log.error("Error while handling packet!", e);
                }
            }
        }
    }
}
