package com.ksk.mf.game.ad;

public enum AdType {
    NORMAL(1, "일반 확성기"),
    LONG(2, "강화 확성기"),
    WEDDING(3, "연인"),
    RANDOM_BOX(4, "행운 상자"),
    THANKSGIVING(5, "한가위 확성기"),
    HALLOWEEN(6, "할로윈 확성기"),
    HALLOWEEN_GHOST_GAME(7, "할로윈 고스트 게임 확성기"),
    CHRISTMAS(8, "크리스마스 확성기"),
    CHILDREN_DAY(9, "장난감 확성기"),
    SUMMER(10, "해변가 확성기"),
    SCHOOL(11, "방송부의 스피커"),
    NEW_YEAR(12, "설날 확성기"),
    ONE_CARD(13, "광대의 확성기"),
    BIRTH2024(14, "10주년 확성기"),
    NONE(-1, "NONE");

    private final int adType;
    private final String name;

    AdType(int adType, String name) {
        this.adType = adType;
        this.name = name;
    }

    public static AdType fromAdType(int adType) {
        for(AdType type : AdType.values()) {
            if(type.adType == adType) {
                return type;
            }
        }
        return AdType.NONE;
    }

    public String getName() {
        return this.name;
    }
}
