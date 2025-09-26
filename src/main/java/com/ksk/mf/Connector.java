package com.ksk.mf;

import com.ksk.mf.channel.ChannelData;
import com.ksk.mf.channel.ChannelProvider;
import com.ksk.mf.channel.MafiaChannel;
import com.ksk.mf.handler.ws.WebSocketMessageHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.kqueue.KQueueSocketChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshakerFactory;
import io.netty.handler.codec.http.websocketx.WebSocketClientProtocolHandler;
import io.netty.handler.codec.http.websocketx.WebSocketVersion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.net.URI;

public class Connector {
    private static final Logger log = LoggerFactory.getLogger(Connector.class);
    public final EventLoopGroup workerGroup = new NioEventLoopGroup();
    private final String host;
    private final int port;

    private Bootstrap bootstrap;
    private final Class<? extends SocketChannel> socketChannel = System.getProperty("os.name").toLowerCase().contains("win") ? NioSocketChannel.class : KQueueSocketChannel.class;

    /**
     * Connector constructor.
     * <p>
     * You can use a given host and port
     * @param host address
     * @param port port
     */
    public Connector(String host, int port) {
        this.host = host;
        this.port = port;
    }

    /**
     * Connector constructor.
     * <p>
     * You can enter a game room with MafiaChannel enum
     * @param mafiaChannel Mafia Game Channel
     */
    public Connector(MafiaChannel mafiaChannel) {
        final ChannelProvider provider = ChannelProvider.getInstance();
        final ChannelData channelData = provider.getChannelDataFromChannel(mafiaChannel);
        this.host = channelData.host;
        this.port = channelData.apiPort;
    }

    public void start() {
        try {
            this.bootstrap = new Bootstrap();
            bootstrap.group(workerGroup)
                    .option(ChannelOption.AUTO_READ, true)
                    .channel(this.socketChannel)
                    .handler(new ChannelInitializer<Channel>() {
                        @Override
                        protected void initChannel(Channel ch) {
                            ch.pipeline().addLast(
                                // HTTP 코덱 추가
                                new HttpClientCodec(),
                                // HTTP 메시지 집계
                                new HttpObjectAggregator(8192),
                                // WebSocket 프로토콜 핸들러
                                new WebSocketClientProtocolHandler(
                                    WebSocketClientHandshakerFactory.newHandshaker(
                                        URI.create("ws://" + host + ":" + port),
                                        WebSocketVersion.V13,
                                        null,
                                        false,
                                        new DefaultHttpHeaders()
                                    )
                                ),
                                // 실제 메시지 처리를 위한 커스텀 핸들러
                                new WebSocketMessageHandler()
                            );
                        }
                    });
            ChannelFuture future = bootstrap.connect(new InetSocketAddress(this.host, this.port)).syncUninterruptibly();
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            log.error("Exception on Connector", e);
        } finally {
            workerGroup.shutdownGracefully();
        }
    }
}
