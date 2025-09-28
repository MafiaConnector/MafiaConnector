package com.ksk.mf.game.room;

public enum RoomType {
    MAFIA_NORMAL(1),
    MAFIA_GUILD(2),
    MAFIA_RANK(3),
    MAFIA_EVENT(4),
    JAQUIZ_NORMAL(5),
    CHAT(6),
    WATERGUN(7),
    HALLOWEEN(8),
    GUILD_RANK(9),
    JOB_GAME(10),
    HALLOWEEN_GHOST(11),
    TERRORIST(12),
    DUEL(13),
    RANK_DUEL(14),
    JAQUIZ_TEAM(15),
    CD_SINGLE_BATTLE(16),
    SNOWBALL(17),
    BI_NORMAL(18),
    LOVERRUN(19),
    RABBIT(20),
    MANSION(21),
    RANK_PRACTICE(22),
    RANK_DUEL_PRACTICE(23),
    JEGI(24),
    SKETCH(25),
    CARD2019(26),
    SCHOOL2019(27),
    SCHOOL2019_DUEL(28),
    GUILD_RANK_DUEL(29),
    BOMB(30),
    BOMB_RANK(31),
    MAFIA_COMPETITION(32),
    ACCOMPLICE(33),
    CULTLEADER(34),
    LAB(35),
    NOVICE(36),
    JAQUIZ_EVENT(37),
    FOOD_CHAIN(38),
    NOVICE_DEFAULT(39),
    NONE(-1)
    ;


    private final int type;

    RoomType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public static RoomType fromRoomType(int type) {
        for (RoomType roomType : RoomType.values()) {
            if (roomType.type == type) {
                return roomType;
            }
        }
        return RoomType.NONE;
    }
}
