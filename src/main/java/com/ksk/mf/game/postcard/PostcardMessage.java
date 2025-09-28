package com.ksk.mf.game.postcard;

import java.util.Date;

public class PostcardMessage {
    private final boolean canReceiveNegativePostcard;
    private final int cupidType;
    private final int frame;
    private final boolean isReply;
    private final String name;
    private final int nicknameColor;
    private final int postcard;
    private final long postcardEndTime;
    private final long postcardMessageId;
    private final long recommendTime;
    private final int reputationAmount;
    private final String thumbnail;
    private final long userId;
    private final long userNameTag;
    private final long userNameTagBgColor;
    private final long usingCollection;
    private final long usingCollection2;
    private final long usingCollection3;

    private PostcardMessage(Builder builder) {
        this.canReceiveNegativePostcard = builder.canReceiveNegativePostcard;
        this.cupidType = builder.cupidType;
        this.frame = builder.frame;
        this.isReply = builder.isReply;
        this.name = builder.name;
        this.nicknameColor = builder.nicknameColor;
        this.postcard = builder.postcard;
        this.postcardEndTime = builder.postcardEndTime;
        this.postcardMessageId = builder.postcardMessageId;
        this.recommendTime = builder.recommendTime;
        this.reputationAmount = builder.reputationAmount;
        this.thumbnail = builder.thumbnail;
        this.userId = builder.userId;
        this.userNameTag = builder.userNameTag;
        this.userNameTagBgColor = builder.userNameTagBgColor;
        this.usingCollection = builder.usingCollection;
        this.usingCollection2 = builder.usingCollection2;
        this.usingCollection3 = builder.usingCollection3;
    }

    public boolean isCanReceiveNegativePostcard() {
        return canReceiveNegativePostcard;
    }

    public int getCupidType() {
        return cupidType;
    }

    public int getFrame() {
        return frame;
    }

    public boolean isReply() {
        return isReply;
    }

    public String getName() {
        return name;
    }

    public int getNicknameColor() {
        return nicknameColor;
    }

    public int getPostcard() {
        return postcard;
    }

    public long getPostcardEndTime() {
        return postcardEndTime;
    }

    public long getPostcardMessageId() {
        return postcardMessageId;
    }

    public long getRecommendTime() {
        return recommendTime;
    }

    public int getReputationAmount() {
        return reputationAmount;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public long getUserId() {
        return userId;
    }

    public long getUserNameTag() {
        return userNameTag;
    }

    public long getUserNameTagBgColor() {
        return userNameTagBgColor;
    }

    public long getUsingCollection() {
        return usingCollection;
    }

    public long getUsingCollection2() {
        return usingCollection2;
    }

    public long getUsingCollection3() {
        return usingCollection3;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private boolean canReceiveNegativePostcard;
        private int cupidType;
        private int frame;
        private boolean isReply;
        private String name = "";
        private int nicknameColor;
        private int postcard;
        private long postcardEndTime = new Date().getTime() + 2592000000L;
        private long postcardMessageId;
        private long recommendTime;
        private int reputationAmount;
        private String thumbnail = "";
        private long userId;
        private long userNameTag;
        private long userNameTagBgColor;
        private long usingCollection;
        private long usingCollection2;
        private long usingCollection3;

        public Builder canReceiveNegativePostcard(boolean canReceiveNegativePostcard) {
            this.canReceiveNegativePostcard = canReceiveNegativePostcard;
            return this;
        }

        public Builder cupidType(int cupidType) {
            this.cupidType = cupidType;
            return this;
        }

        public Builder frame(int frame) {
            this.frame = frame;
            return this;
        }

        public Builder isReply(boolean isReply) {
            this.isReply = isReply;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder nicknameColor(int nicknameColor) {
            this.nicknameColor = nicknameColor;
            return this;
        }

        public Builder postcard(int postcard) {
            this.postcard = postcard;
            return this;
        }

        public Builder postcardEndTime(long postcardEndTime) {
            this.postcardEndTime = postcardEndTime;
            return this;
        }

        public Builder postcardMessageId(long postcardMessageId) {
            this.postcardMessageId = postcardMessageId;
            return this;
        }

        public Builder recommendTime(long recommendTime) {
            this.recommendTime = recommendTime;
            return this;
        }

        public Builder reputationAmount(int reputationAmount) {
            this.reputationAmount = reputationAmount;
            return this;
        }

        public Builder thumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
            return this;
        }

        public Builder userId(long userId) {
            this.userId = userId;
            return this;
        }

        public Builder userNameTag(long userNameTag) {
            this.userNameTag = userNameTag;
            return this;
        }

        public Builder userNameTagBgColor(long userNameTagBgColor) {
            this.userNameTagBgColor = userNameTagBgColor;
            return this;
        }

        public Builder usingCollection(long usingCollection) {
            this.usingCollection = usingCollection;
            return this;
        }

        public Builder usingCollection2(long usingCollection2) {
            this.usingCollection2 = usingCollection2;
            return this;
        }

        public Builder usingCollection3(long usingCollection3) {
            this.usingCollection3 = usingCollection3;
            return this;
        }

        public PostcardMessage build() {
            return new PostcardMessage(this);
        }
    }
}
