package com.ksk.mf.channel;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class ChannelData {
    @SerializedName("channel_id")
    public int channelId;
    public String host;
    public int port;
    @SerializedName("api_port")
    public int apiPort;

    @Override
    public String toString() {
        return "ChannelData{" +
                "channelId=" + channelId +
                ", host='" + host + '\'' +
                ", port=" + port +
                ", apiPort=" + apiPort +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof ChannelData))
          return false;
        ChannelData data = (ChannelData)obj;
        return this.channelId == data.channelId;
    }

    @Override
    public int hashCode() {
        return this.channelId;
    }
}

