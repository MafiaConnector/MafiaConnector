package com.ksk.mf.game.room;

import org.jetbrains.annotations.NotNull;

public class Room implements Comparable<Room> {
    private final boolean isAnonymous;
    private final boolean isLocked;
    private final boolean isMumbleRoom;
    private final int maxUser;
    private final long roomId;
    private final int roomIndex;
    private final String roomName;
    private final RoomSkin roomSkin;
    private final RoomType roomType;
    private final int userCount;
    private final boolean isPlaying;

    private Room(Builder builder) {
        this.isAnonymous = builder.isAnonymous;
        this.isLocked = builder.isLocked;
        this.isMumbleRoom = builder.isMumbleRoom;
        this.maxUser = builder.maxUser;
        this.roomId = builder.roomId;
        this.roomIndex = builder.roomIndex;
        this.roomName = builder.roomName;
        this.roomSkin = builder.roomSkin;
        this.roomType = builder.roomType;
        this.userCount = builder.userCount;
        this.isPlaying = builder.isPlaying;
    }

    public boolean isAnonymous() {
        return isAnonymous;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public boolean isMumbleRoom() {
        return isMumbleRoom;
    }

    public int getMaxUser() {
        return maxUser;
    }

    public long getRoomId() {
        return roomId;
    }

    public int getRoomIndex() {
        return roomIndex;
    }

    public String getRoomName() {
        return roomName;
    }

    public RoomSkin getRoomSkin() {
        return roomSkin;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public int getUserCount() {
        return userCount;
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    @Override
    public int compareTo(@NotNull Room o) {
        if (this.roomType == RoomType.CHAT && o.roomType != RoomType.CHAT) {
            return 1;
        }
        if (this.roomType != RoomType.CHAT && o.roomType == RoomType.CHAT) {
            return -1;
        }

        return Integer.compare(this.roomSkin.getPriority(), o.roomSkin.getPriority());
    }

    @Override
    public String toString() {
        return "Room{" +
                "isAnonymous=" + isAnonymous +
                ", isLocked=" + isLocked +
                ", isMumbleRoom=" + isMumbleRoom +
                ", maxUser=" + maxUser +
                ", roomId=" + roomId +
                ", roomIndex=" + roomIndex +
                ", roomName='" + roomName + '\'' +
                ", roomSkin=" + roomSkin +
                ", roomType=" + roomType.name() +
                ", userCount=" + userCount +
                '}';
    }

    public static class Builder {
        private boolean isAnonymous = false;
        private boolean isLocked = false;
        private boolean isMumbleRoom = false;
        private int maxUser = 0;
        private long roomId = 0L;
        private int roomIndex = 0;
        private String roomName = "";
        private RoomSkin roomSkin = RoomSkin.NONE;
        private RoomType roomType = RoomType.NONE;
        private int userCount = 0;
        private boolean isPlaying = false;

        public Builder setAnonymous(boolean isAnonymous) {
            this.isAnonymous = isAnonymous;
            return this;
        }

        public Builder setLocked(boolean isLocked) {
            this.isLocked = isLocked;
            return this;
        }

        public Builder setMumbleRoom(boolean isMumbleRoom) {
            this.isMumbleRoom = isMumbleRoom;
            return this;
        }

        public Builder setMaxUser(int maxUser) {
            this.maxUser = maxUser;
            return this;
        }

        public Builder setRoomId(long roomId) {
            this.roomId = roomId;
            return this;
        }

        public Builder setRoomIndex(int roomIndex) {
            this.roomIndex = roomIndex;
            return this;
        }

        public Builder setRoomName(String roomName) {
            this.roomName = roomName != null ? roomName : "";
            return this.setPlaying(this.roomName.contains("\ufeff"));
        }

        public Builder setRoomSkin(RoomSkin roomSkin) {
            this.roomSkin = roomSkin;
            return this;
        }

        public Builder setRoomType(RoomType roomType) {
            this.roomType = roomType;
            return this;
        }

        public Builder setUserCount(int userCount) {
            this.userCount = userCount;
            return this;
        }

        public Builder setPlaying(boolean isPlaying) {
            this.isPlaying = isPlaying;
            return this;
        }

        public Room build() {
            return new Room(this);
        }
    }
}
