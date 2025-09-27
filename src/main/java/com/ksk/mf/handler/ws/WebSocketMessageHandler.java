package com.ksk.mf.handler.ws;

import com.ksk.mf.handler.packet.PacketHandler;
import com.ksk.mf.handler.packet.PacketHandlerImpl;
import com.ksk.mf.packet.request.RequestPacket;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketClientProtocolHandler;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;

import static com.ksk.mf.packet.PacketId.*;

public class WebSocketMessageHandler extends SimpleChannelInboundHandler<WebSocketFrame> {
    private final Logger logger = LoggerFactory.getLogger(WebSocketMessageHandler.class);
    private final Marker marker = MarkerFactory.getMarker("WS");
    private final PacketHandler handler = new PacketHandlerImpl();
    
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, WebSocketFrame frame) {
        if (frame instanceof BinaryWebSocketFrame) {
            BinaryWebSocketFrame binary = (BinaryWebSocketFrame) frame;
            ByteBuf buf = binary.content();
            handler.handlePacket(ctx, buf);
        }
    }

    /**
     * 웹소켓 핸드쉐이킹, 특정 간격(현재는 5초) 마다 패킷 요청을 해야하는 경우 이쪽에서 처리해야 합니다.
     * @param ctx ChannelHandlerContext
     * @param evt Object
     */
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if(evt instanceof WebSocketClientProtocolHandler.ClientHandshakeStateEvent) {
            WebSocketClientProtocolHandler.ClientHandshakeStateEvent event =
                    (WebSocketClientProtocolHandler.ClientHandshakeStateEvent) evt;
            if (event == WebSocketClientProtocolHandler.ClientHandshakeStateEvent.HANDSHAKE_COMPLETE) {
                new RequestPacket(REQUEST_VERSION_CHECK).sendPacket(ctx);
            }
        }

        if(evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;
            if (event.state() == IdleState.WRITER_IDLE) {
                new RequestPacket(REQUEST_PING_CHECK, Long.toString(System.currentTimeMillis())).sendPacket(ctx);
            }
        }
        super.userEventTriggered(ctx, evt);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        logger.info(marker, "WebSocket Connection Active");
        scheduleDailyTask(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        logger.info(marker, "WebSocket Connection Inactive");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        logger.error(marker, "Exception caught in WebSocketMessageHandler: ", cause);
        ctx.close();
    }

    private void scheduleDailyTask(ChannelHandlerContext ctx) {
        LocalTime targetTime = LocalTime.of(19, 10);
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime nextRun = now.with(targetTime);

        if (now.isAfter(nextRun)) {
            nextRun = nextRun.plusDays(1);
        }

        long initialDelay = ChronoUnit.MILLIS.between(now, nextRun);

        ctx.channel().eventLoop().scheduleAtFixedRate(() ->
                new RequestPacket(REQUEST_LIMITED_ITEM_LIST).sendPacket(ctx)
                , initialDelay, TimeUnit.DAYS.toMillis(1), TimeUnit.MILLISECONDS);

    }
}