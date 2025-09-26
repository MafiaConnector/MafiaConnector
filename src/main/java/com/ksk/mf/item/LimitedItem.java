package com.ksk.mf.item;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class LimitedItem {
    private final long limitedItemId;
    private final LocalDateTime closingTime;
    private final LimitedItemType limitedItemType;
    private final int itemCode;
    private final int itemAmount;
    private final boolean forAll;
    private final String message;
    private final String sender;
    private final String additional;

    private LimitedItem(Builder builder) {
        this.limitedItemId = builder.limitedItemId;
        this.closingTime = builder.closingTime;
        this.limitedItemType = builder.limitedItemType;
        this.itemCode = builder.itemCode;
        this.itemAmount = builder.itemAmount;
        this.forAll = builder.forAll;
        this.message = builder.message;
        this.sender = builder.sender;
        this.additional = builder.additional;
    }


    public long getLimitedItemId() {
        return limitedItemId;
    }

    public LocalDateTime getClosingTime() {
        return closingTime;
    }

    public LimitedItemType getLimitedItemType() {
        return limitedItemType;
    }

    public int getItemCode() {
        return itemCode;
    }

    public int getItemAmount() {
        return itemAmount;
    }

    public boolean isForAll() {
        return forAll;
    }

    public String getMessage() {
        return message;
    }

    public String getSender() {
        return sender;
    }

    public String getAdditional() {
        return additional;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private long limitedItemId;
        private LocalDateTime closingTime;
        private LimitedItemType limitedItemType = LimitedItemType.NONE;
        private int itemCode;
        private int itemAmount;
        private boolean forAll;
        private String message = "";
        private String sender = "";
        private String additional = "";

        public Builder limitedItemId(long limitedItemId) {
            this.limitedItemId = limitedItemId;
            return this;
        }

        public Builder closingTime(long closingTime) {
            this.closingTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(closingTime), ZoneId.systemDefault());
            return this;
        }

        public Builder limitedItemType(LimitedItemType limitedItemType) {
            this.limitedItemType = limitedItemType;
            return this;
        }

        public Builder itemCode(int itemCode) {
            this.itemCode = itemCode;
            return this;
        }

        public Builder itemAmount(int itemAmount) {
            this.itemAmount = itemAmount;
            return this;
        }

        public Builder forAll(boolean forAll) {
            this.forAll = forAll;
            return this;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Builder sender(String sender) {
            this.sender = sender;
            return this;
        }

        public Builder additional(String additional) {
            this.additional = additional;
            return this;
        }

        public LimitedItem build() {
            return new LimitedItem(this);
        }
    }

    public enum LimitedItemType {
        NORMAL_REWARD(1),
        LEVEL_UP_REWARD(2),
        DAILY_AD_REWARD(3),
        NONE(-1);

        private final int type;

        LimitedItemType(int type) {
            this.type = type;
        }

        public int getType() {
            return this.type;
        }

        public static LimitedItemType fromLimitedItemType(int type) {
            for (LimitedItemType itemType : LimitedItemType.values()) {
                if (itemType.type == type) {
                    return itemType;
                }
            }
            return NONE;
        }
    }
}
