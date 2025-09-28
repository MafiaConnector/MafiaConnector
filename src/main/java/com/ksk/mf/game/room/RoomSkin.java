package com.ksk.mf.game.room;

public enum RoomSkin {
    MAGICALGIRL(807, 2),
    MADAM(514, 5),
    MAFIA(323, 4),
    SOLIDER(324, 4),
    CHRISTMAS(283, 3),
    NONE(0, 6),
    SKETCH(1, 5),
    BIRTH2024(1998, 1)
    ;

    private final int roomSkin;
    private final int priority;

    RoomSkin(int roomSkin, int priority) {
        this.roomSkin = roomSkin;
        this.priority = priority;
    }

    public int getRoomSkin() {
        return roomSkin;
    }

    public int getPriority() {
        return priority;
    }

    public static RoomSkin getRoomSkin(int roomSkin) {
        for (RoomSkin skin : RoomSkin.values()) {
            if (skin.getRoomSkin() == roomSkin) {
                return skin;
            }
        }
        return null;
    }
}
