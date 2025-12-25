package com.ksk.mf.game.info;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;

public class LoginInfo {
    protected int ruble;
    protected int luna;
    protected int fame;
    protected int nicknameColor;
    protected int maxFriend;
    protected int newFriendChat;
    protected int tmpInt1;
    protected int tmpInt2;
    protected int guildInitialColor;
    protected int guildInitialBackgroundColor;
    protected int guildId;
    protected int guildPoint;
    protected int frame;
    protected int randomBoxAmount;
    protected boolean online;
    protected int guildLevel;
    protected long id;
    protected long nameTag;
    protected long curNameTag;
    protected long collection;
    protected long curCollection;
    protected long collection2;
    protected long curCollection2;
    protected long collection3;
    protected long curCollection3;
    protected long emoticon;
    protected LocalDateTime lastLoginTime;
    protected long facebookId;
    protected boolean isFriend;
    protected boolean isPostcardSent;
    protected String userName;
    protected String introduce;
    protected String guildInitial;
    protected String guildName;
    protected Map<String,Integer> inventory = new HashMap<>();

    public LoginInfo() {}

    // Protected constructor for inheritance
    protected LoginInfo(Builder builder) {
        this.ruble = builder.ruble;
        this.luna = builder.luna;
        this.fame = builder.fame;
        this.nicknameColor = builder.nicknameColor;
        this.maxFriend = builder.maxFriend;
        this.newFriendChat = builder.newFriendChat;
        this.tmpInt1 = builder.tmpInt1;
        this.tmpInt2 = builder.tmpInt2;
        this.guildInitialColor = builder.guildInitialColor;
        this.guildInitialBackgroundColor = builder.guildInitialBackgroundColor;
        this.guildId = builder.guildId;
        this.guildPoint = builder.guildPoint;
        this.frame = builder.frame;
        this.randomBoxAmount = builder.randomBoxAmount;
        this.online = builder.online;
        this.guildLevel = builder.guildLevel;
        this.id = builder.id;
        this.nameTag = builder.nameTag;
        this.curNameTag = builder.curNameTag;
        this.collection = builder.collection;
        this.curCollection = builder.curCollection;
        this.collection2 = builder.collection2;
        this.curCollection2 = builder.curCollection2;
        this.collection3 = builder.collection3;
        this.curCollection3 = builder.curCollection3;
        this.emoticon = builder.emoticon;
        this.lastLoginTime = builder.lastLoginTime;
        this.facebookId = builder.facebookId;
        this.isFriend = builder.isFriend;
        this.isPostcardSent = builder.isPostcardSent;
        this.userName = builder.userName;
        this.introduce = builder.introduce;
        this.guildInitial = builder.guildInitial;
        this.guildName = builder.guildName;
        this.inventory = builder.inventory != null ? new HashMap<>(builder.inventory) : new HashMap<>();
    }

    public static Builder builder() {
        return new Builder();
    }



    public static class Builder {
        private int ruble;
        private int luna;
        private int fame;
        private int nicknameColor;
        private int maxFriend;
        private int newFriendChat;
        private int tmpInt1;
        private int tmpInt2;
        private int guildInitialColor;
        private int guildInitialBackgroundColor;
        private int guildId;
        private int guildPoint;
        private int frame;
        private int randomBoxAmount;
        private boolean online;
        private int guildLevel;
        private long id;
        private long nameTag;
        private long curNameTag;
        private long collection;
        private long curCollection;
        private long collection2;
        private long curCollection2;
        private long collection3;
        private long curCollection3;
        private long emoticon;
        private LocalDateTime lastLoginTime;
        private long facebookId;
        private boolean isFriend;
        private boolean isPostcardSent;
        private String userName;
        private String introduce;
        private String guildInitial;
        private String guildName;
        private Map<String,Integer> inventory = new HashMap<>();

        public Builder ruble(int ruble) {
            this.ruble = ruble;
            return this;
        }

        public Builder luna(int luna) {
            this.luna = luna;
            return this;
        }

        public Builder fame(int fame) {
            this.fame = fame;
            return this;
        }

        public Builder nicknameColor(int nicknameColor) {
            this.nicknameColor = nicknameColor;
            return this;
        }

        public Builder maxFriend(int maxFriend) {
            this.maxFriend = maxFriend;
            return this;
        }

        public Builder newFriendChat(int newFriendChat) {
            this.newFriendChat = newFriendChat;
            return this;
        }

        public Builder tmpInt1(int tmpInt1) {
            this.tmpInt1 = tmpInt1;
            return this;
        }

        public Builder tmpInt2(int tmpInt2) {
            this.tmpInt2 = tmpInt2;
            return this;
        }

        public Builder guildInitialColor(int guildInitialColor) {
            this.guildInitialColor = guildInitialColor;
            return this;
        }

        public Builder guildInitialBackgroundColor(int guildInitialBackgroundColor) {
            this.guildInitialBackgroundColor = guildInitialBackgroundColor;
            return this;
        }

        public Builder guildId(int guildId) {
            this.guildId = guildId;
            return this;
        }

        public Builder guildPoint(int guildPoint) {
            this.guildPoint = guildPoint;
            return this;
        }

        public Builder frame(int frame) {
            this.frame = frame;
            return this;
        }

        public Builder randomBoxAmount(int randomBoxAmount) {
            this.randomBoxAmount = randomBoxAmount;
            return this;
        }

        public Builder online(int online) {
            this.online = online != 0;
            return this;
        }

        public Builder guildLevel(int guildLevel) {
            this.guildLevel = guildLevel;
            return this;
        }

        public Builder id(long id) {
            this.id = id;
            return this;
        }

        public Builder nameTag(long nameTag) {
            this.nameTag = nameTag;
            return this;
        }

        public Builder curNameTag(long curNameTag) {
            this.curNameTag = curNameTag;
            return this;
        }

        public Builder collection(long collection) {
            this.collection = collection;
            return this;
        }

        public Builder curCollection(long curCollection) {
            this.curCollection = curCollection;
            return this;
        }

        public Builder collection2(long collection2) {
            this.collection2 = collection2;
            return this;
        }

        public Builder curCollection2(long curCollection2) {
            this.curCollection2 = curCollection2;
            return this;
        }

        public Builder collection3(long collection3) {
            this.collection3 = collection3;
            return this;
        }

        public Builder curCollection3(long curCollection3) {
            this.curCollection3 = curCollection3;
            return this;
        }

        public Builder emoticon(long emoticon) {
            this.emoticon = emoticon;
            return this;
        }

        public Builder lastLoginTime(long lastLoginTime) {
            this.lastLoginTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(lastLoginTime), ZoneId.systemDefault());
            return this;
        }

        public Builder facebookId(long facebookId) {
            this.facebookId = facebookId;
            return this;
        }

        public Builder isFriend(boolean isFriend) {
            this.isFriend = isFriend;
            return this;
        }

        public Builder isPostcardSent(boolean isPostcardSent) {
            this.isPostcardSent = isPostcardSent;
            return this;
        }

        public Builder userName(String userName) {
            this.userName = userName;
            return this;
        }

        public Builder introduce(String introduce) {
            this.introduce = introduce;
            return this;
        }

        public Builder guildInitial(String guildInitial) {
            this.guildInitial = guildInitial;
            return this;
        }

        public Builder guildName(String guildName) {
            this.guildName = guildName;
            return this;
        }

        public Builder inventory(Map<String,Integer> inventory) {
            this.inventory = inventory;
            return this;
        }

        public Builder addInventoryItem(String itemName, Integer quantity) {
            this.inventory.put(itemName, quantity);
            return this;
        }

        public Builder addInventoryItems(Map<String,Integer> items) {
            this.inventory.putAll(items);
            return this;
        }

        public LoginInfo build() {
            return new LoginInfo(this);
        }
    }

    public int getRuble() {
        return ruble;
    }

    public int getLuna() {
        return luna;
    }

    public int getFame() {
        return fame;
    }

    public int getNicknameColor() {
        return nicknameColor;
    }

    public int getMaxFriend() {
        return maxFriend;
    }

    public int getNewFriendChat() {
        return newFriendChat;
    }

    public int getTmpInt1() {
        return tmpInt1;
    }

    public int getTmpInt2() {
        return tmpInt2;
    }

    public int getGuildInitialColor() {
        return guildInitialColor;
    }

    public int getGuildInitialBackgroundColor() {
        return guildInitialBackgroundColor;
    }

    public int getGuildId() {
        return guildId;
    }

    public int getGuildPoint() {
        return guildPoint;
    }

    public int getFrame() {
        return frame;
    }

    public int getRandomBoxAmount() {
        return randomBoxAmount;
    }

    public boolean isOnline() {
        return online;
    }

    public int getGuildLevel() {
        return guildLevel;
    }

    public long getId() {
        return id;
    }

    public long getNameTag() {
        return nameTag;
    }

    public long getCurNameTag() {
        return curNameTag;
    }

    public long getCollection() {
        return collection;
    }

    public long getCurCollection() {
        return curCollection;
    }

    public long getCollection2() {
        return collection2;
    }

    public long getCurCollection2() {
        return curCollection2;
    }

    public long getCollection3() {
        return collection3;
    }

    public long getCurCollection3() {
        return curCollection3;
    }

    public long getEmoticon() {
        return emoticon;
    }

    public LocalDateTime getLastLoginTime() {
        return lastLoginTime;
    }

    public long getFacebookId() {
        return facebookId;
    }

    public boolean isFriend() {
        return isFriend;
    }

    public boolean isPostcardSent() {
        return isPostcardSent;
    }

    public String getUserName() {
        return userName;
    }

    public String getIntroduce() {
        return introduce;
    }

    public String getGuildInitial() {
        return guildInitial;
    }

    public String getGuildName() {
        return guildName;
    }

    public Map<String, Integer> getInventory() {
        return inventory;
    }
}
