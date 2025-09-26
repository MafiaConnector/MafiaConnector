package com.ksk.mf;

import com.ksk.mf.channel.ChannelData;
import com.ksk.mf.channel.ChannelProvider;
import com.ksk.mf.channel.MafiaChannel;
import com.ksk.mf.fcm.FcmTokenGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@SuppressWarnings("unused")
public class Main {
    private final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        Connector connector = new Connector(MafiaChannel.CHANNEL_RANK);
        connector.start();
    }

    public void printChannelData() {
        ChannelProvider provider = ChannelProvider.getInstance();
        final ChannelData rankChannel = provider.getChannelDataFromChannel(MafiaChannel.CHANNEL_RANK);
        this.logger.info("ChannelId: {}, Host: {}, Port: {}, ApiPort: {}", rankChannel.channelId, rankChannel.host, rankChannel.port, rankChannel.apiPort);
    }

    public void printFCMToken() {
        final FcmTokenGenerator fcmTokenGenerator = new FcmTokenGenerator();
        String fcmToken = fcmTokenGenerator.getFCMToken();
        this.logger.info("FCM Token: {}", fcmToken);
    }

    private static void setDeviceKey(final String key) {
        System.setProperty("deviceKey", key);
    }

    private static void setAuthToken(final String token) {
        System.setProperty("mafiaAuth", token);
    }

}
