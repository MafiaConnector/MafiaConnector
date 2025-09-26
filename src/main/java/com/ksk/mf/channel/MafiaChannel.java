package com.ksk.mf.channel;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unused")
public enum MafiaChannel {
    NEWBIE(0, "초보 채널"),
    CHANNEL_ONE(1, "1채널"),
    CHANNEL_TWO(2,  "2채널"),
    CHANNEL_THREE(3, "3채널"),
    CHANNEL_ADULT(19, "성인 채널"),
    CHANNEL_RANK(20, "랭크 채널"),
    CHANNEL_EVENT(42, "이벤트 채널"),
    NO_CHANNEL(-1, "채널이 아닙니다")
    ;

    public final int channelId;
    public final String name;
    static final Map<Integer, MafiaChannel> map = new HashMap<>();
    static final Map<String, MafiaChannel> map2 = new HashMap<>();

    MafiaChannel(int channelId, String name) {
        this.channelId = channelId;
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name + " = " + this.channelId;
    }

    public static MafiaChannel getChannelFromChannelId(int channelId) {
        for (Map.Entry<Integer,MafiaChannel> entry : map.entrySet()) {
            if(entry.getKey() == channelId) {
                return map.get(channelId);
            }
        }
        return NO_CHANNEL;
    }

    public static MafiaChannel getChannelFromChannelName(String channelName) {
        for (Map.Entry<String, MafiaChannel> entry : map2.entrySet()) {
            if(entry.getKey().equals(channelName)) {
                return map2.get(channelName);
            }
        }
        return NO_CHANNEL;
    }

    static {
        for (MafiaChannel value : values()) {
            map.put(value.channelId, value);
            map2.put(value.name, value);
        }
    }
}