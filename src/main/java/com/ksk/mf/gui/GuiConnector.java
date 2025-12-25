package com.ksk.mf.gui;

import com.ksk.mf.Connector;
import com.ksk.mf.channel.MafiaChannel;
import com.ksk.mf.packet.request.RequestPacket;
import io.netty.channel.ChannelHandlerContext;
import javafx.concurrent.Task;

public class GuiConnector extends Task<Void> {
    private final Connector connector;
    private final ConnectorCallback callback;
    private static ChannelHandlerContext currentContext;

    public GuiConnector(MafiaChannel channel, ConnectorCallback callback) {
        this.connector = new Connector(channel);
        this.callback = callback;
    }

    // 정적 메서드로 현재 연결 컨텍스트를 저장
    public static void setCurrentContext(ChannelHandlerContext ctx) {
        currentContext = ctx;
    }

    // 패킷 전송 메서드
    public static void sendPacket(RequestPacket packet) {
        if (currentContext != null) {
            packet.sendPacket(currentContext);
        } else {
            throw new IllegalStateException("No connection context available");
        }
    }

    public static boolean isConnected() {
        return currentContext != null && currentContext.channel().isActive();
    }

    @Override
    protected Void call() {
        try {
            if (callback != null) {
                callback.onConnected();
            }
            connector.start();
        } catch (Exception e) {
            if (callback != null) {
                callback.onError(e.getMessage());
            }
            throw e;
        } finally {
            if (callback != null) {
                callback.onDisconnected();
            }
        }
        return null;
    }

    public void disconnect() {
        if (connector != null) {
            connector.workerGroup.shutdownGracefully();
        }
        this.cancel();
    }
}