package com.ksk.mf.channel;

import com.google.gson.Gson;
import com.google.gson.JsonArray;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ChannelProvider {
    private final Gson gson = new Gson();
    private final static ChannelProvider instance = new ChannelProvider();

    public static ChannelProvider getInstance() {
        return instance;
    }

    private final String body = getBodyFromUrl();

    /**
     * Get mafia connecting url from a website.
     *
     * @return all body.
     */
    private String getBodyFromUrl() {
        try {
            URL obj = new URL(System.getenv("CHANNEL_URL"));
            HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
            conn.setRequestProperty("User-Agent", System.getenv("CHANNEL_USER_AGENT"));
            String response;
            try (InputStream in = conn.getInputStream()) {
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                byte[] array = new byte[1024];
                int len;
                while ((len = in.read(array)) != -1) {
                    out.write(array, 0, len);
                }
                response = out.toString();
            }
            return response;
        } catch (IOException ignored) {
            return "null";
        }
    }


    private ChannelData[] getChannelData() {
        return gson.fromJson(gson.fromJson(body, JsonArray.class), ChannelData[].class);
    }

    public ChannelData getChannelDataFromChannel(MafiaChannel mafiaChannel) {
        for (ChannelData data : getChannelData()) {
            if (data.channelId == mafiaChannel.channelId) {
                return data;
            }
        }
        return null;
    }
}
