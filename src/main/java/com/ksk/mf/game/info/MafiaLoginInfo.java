package com.ksk.mf.game.info;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MafiaLoginInfo extends LoginInfo{
    private int exp;
    private int win;
    private int lose;
    private int rankPoint;
    private int officialJob;
    private int questValue;
    private int questMaxValue;
    private int questReward;
    private int coupleId;
    private int coupleColor;
    private int coupleDateCount;
    private int officialJobScore;
    private int hasNewGuildChat;
    private int questCount;
    private int maxPostcard;
    private int mileage;
    private int ranking;
    private int levelReward;
    private int deathCauseType;
    private int mainJobCardTier;
    private int maxJobCard;
    private int rankPointDuel;
    private int roomSkinType;
    private int mentorId;
    private int rankQuestCount;
    private int rankQuestMaxCount;
    private int collectingPoint;
    private int profileFrame;
    private int currentQuest;
    private int recentEnteredJob;
    private int currentGem;
    private int userTitle;
    private int currentBubble;
    private long skin;
    private long curSkinTheme;
    private long mainJobCardId;
    private long curNameTagBgColor;
    private long registrationTime;
    private boolean isHiddenGem;
    private boolean isDeckOpen;
    private boolean isGuildRewardReceived;
    private boolean isMentor;
    private String deathCause;
    private String coupleNickname;
    private String mentorNickname;
    private Map<Integer,JobCard> jobCardMap = new HashMap<>();
    private Map<Integer, Integer> currentSkinMap = new HashMap<>();
    private Set<String> favoriteItems = new HashSet<>();

    public MafiaLoginInfo() {}

    private MafiaLoginInfo(Builder builder) {
        // Copy LoginInfo fields directly if loginInfo is provided
        if (builder.loginInfo != null) {
            this.ruble = builder.loginInfo.getRuble();
            this.luna = builder.loginInfo.getLuna();
            this.fame = builder.loginInfo.getFame();
            this.nicknameColor = builder.loginInfo.getNicknameColor();
            this.maxFriend = builder.loginInfo.getMaxFriend();
            this.newFriendChat = builder.loginInfo.getNewFriendChat();
            this.tmpInt1 = builder.loginInfo.getTmpInt1();
            this.tmpInt2 = builder.loginInfo.getTmpInt2();
            this.guildInitialColor = builder.loginInfo.getGuildInitialColor();
            this.guildInitialBackgroundColor = builder.loginInfo.getGuildInitialBackgroundColor();
            this.guildId = builder.loginInfo.getGuildId();
            this.guildPoint = builder.loginInfo.getGuildPoint();
            this.frame = builder.loginInfo.getFrame();
            this.randomBoxAmount = builder.loginInfo.getRandomBoxAmount();
            this.online = builder.loginInfo.isOnline();
            this.guildLevel = builder.loginInfo.getGuildLevel();
            this.id = builder.loginInfo.getId();
            this.nameTag = builder.loginInfo.getNameTag();
            this.curNameTag = builder.loginInfo.getCurNameTag();
            this.collection = builder.loginInfo.getCollection();
            this.curCollection = builder.loginInfo.getCurCollection();
            this.collection2 = builder.loginInfo.getCollection2();
            this.curCollection2 = builder.loginInfo.getCurCollection2();
            this.collection3 = builder.loginInfo.getCollection3();
            this.curCollection3 = builder.loginInfo.getCurCollection3();
            this.emoticon = builder.loginInfo.getEmoticon();
            this.lastLoginTime = builder.loginInfo.getLastLoginTime();
            this.facebookId = builder.loginInfo.getFacebookId();
            this.isFriend = builder.loginInfo.isFriend();
            this.isPostcardSent = builder.loginInfo.isPostcardSent();
            this.userName = builder.loginInfo.getUserName();
            this.introduce = builder.loginInfo.getIntroduce();
            this.guildInitial = builder.loginInfo.getGuildInitial();
            this.guildName = builder.loginInfo.getGuildName();
            this.inventory = new HashMap<>(builder.loginInfo.getInventory());
        }

        // Set MafiaLoginInfo specific fields
        this.exp = builder.exp;
        this.win = builder.win;
        this.lose = builder.lose;
        this.rankPoint = builder.rankPoint;
        this.officialJob = builder.officialJob;
        this.questValue = builder.questValue;
        this.questMaxValue = builder.questMaxValue;
        this.questReward = builder.questReward;
        this.coupleId = builder.coupleId;
        this.coupleColor = builder.coupleColor;
        this.coupleDateCount = builder.coupleDateCount;
        this.officialJobScore = builder.officialJobScore;
        this.hasNewGuildChat = builder.hasNewGuildChat;
        this.questCount = builder.questCount;
        this.maxPostcard = builder.maxPostcard;
        this.mileage = builder.mileage;
        this.ranking = builder.ranking;
        this.levelReward = builder.levelReward;
        this.deathCauseType = builder.deathCauseType;
        this.mainJobCardTier = builder.mainJobCardTier;
        this.maxJobCard = builder.maxJobCard;
        this.rankPointDuel = builder.rankPointDuel;
        this.roomSkinType = builder.roomSkinType;
        this.mentorId = builder.mentorId;
        this.rankQuestCount = builder.rankQuestCount;
        this.rankQuestMaxCount = builder.rankQuestMaxCount;
        this.collectingPoint = builder.collectingPoint;
        this.profileFrame = builder.profileFrame;
        this.currentQuest = builder.currentQuest;
        this.recentEnteredJob = builder.recentEnteredJob;
        this.currentGem = builder.currentGem;
        this.userTitle = builder.userTitle;
        this.currentBubble = builder.currentBubble;
        this.skin = builder.skin;
        this.curSkinTheme = builder.curSkinTheme;
        this.mainJobCardId = builder.mainJobCardId;
        this.curNameTagBgColor = builder.curNameTagBgColor;
        this.registrationTime = builder.registrationTime;
        this.isHiddenGem = builder.isHiddenGem;
        this.isDeckOpen = builder.isDeckOpen;
        this.isGuildRewardReceived = builder.isGuildRewardReceived;
        this.isMentor = builder.isMentor;
        this.deathCause = builder.deathCause;
        this.coupleNickname = builder.coupleNickname;
        this.mentorNickname = builder.mentorNickname;
        this.jobCardMap = builder.jobCardMap != null ? new HashMap<>(builder.jobCardMap) : new HashMap<>();
        this.currentSkinMap = builder.currentSkinMap != null ? new HashMap<>(builder.currentSkinMap) : new HashMap<>();
        this.favoriteItems = builder.favoriteItems != null ? new HashSet<>(builder.favoriteItems) : new HashSet<>();
    }

    @Override
    public String toString() {
        return "MafiaLoginInfo{" +
                "exp=" + exp +
                ", win=" + win +
                ", lose=" + lose +
                ", rankPoint=" + rankPoint +
                ", coupleId=" + coupleId +
                ", coupleColor=" + coupleColor +
                ", coupleDateCount=" + coupleDateCount +
                ", officialJobScore=" + officialJobScore +
                ", hasNewGuildChat=" + hasNewGuildChat +
                ", ranking=" + ranking +
                ", deathCauseType=" + deathCauseType +
                ", rankPointDuel=" + rankPointDuel +
                ", mentorId=" + mentorId +
                ", profileFrame=" + profileFrame +
                ", recentEnteredJob=" + recentEnteredJob +
                ", currentGem=" + currentGem +
                ", curSkinTheme=" + curSkinTheme +
                ", curNameTagBgColor=" + curNameTagBgColor +
                ", isHiddenGem=" + isHiddenGem +
                ", isDeckOpen=" + isDeckOpen +
                ", isMentor=" + isMentor +
                ", deathCause='" + deathCause + '\'' +
                ", coupleNickname='" + coupleNickname + '\'' +
                ", mentorNickname='" + mentorNickname + '\'' +
                ", jobCardMap=" + jobCardMap +
                ", currentSkinMap=" + currentSkinMap +
                ", fame=" + fame +
                ", nicknameColor=" + nicknameColor +
                ", newFriendChat=" + newFriendChat +
                ", guildInitialColor=" + guildInitialColor +
                ", guildInitialBackgroundColor=" + guildInitialBackgroundColor +
                ", guildId=" + guildId +
                ", guildPoint=" + guildPoint +
                ", frame=" + frame +
                ", online=" + online +
                ", guildLevel=" + guildLevel +
                ", id=" + id +
                ", curNameTag=" + curNameTag +
                ", curCollection=" + curCollection +
                ", curCollection2=" + curCollection2 +
                ", curCollection3=" + curCollection3 +
                ", lastLoginTime=" + lastLoginTime +
                ", isFriend=" + isFriend +
                ", isPostcardSent=" + isPostcardSent +
                ", userName='" + userName + '\'' +
                ", introduce='" + introduce + '\'' +
                ", guildInitial='" + guildInitial + '\'' +
                ", guildName='" + guildName + '\'' +
                ", inventory=" + inventory +
                ", favoriteItems=" + favoriteItems +
                '}';
    }

    public static Builder mafiaBuilder() {
        return new Builder();
    }

    public static class Builder {
        private LoginInfo loginInfo;

        // MafiaLoginInfo fields
        private int exp;
        private int win;
        private int lose;
        private int rankPoint;
        private int officialJob;
        private int questValue;
        private int questMaxValue;
        private int questReward;
        private int coupleId;
        private int coupleColor;
        private int coupleDateCount;
        private int officialJobScore;
        private int hasNewGuildChat;
        private int questCount;
        private int maxPostcard;
        private int mileage;
        private int ranking;
        private int levelReward;
        private int deathCauseType;
        private int mainJobCardTier;
        private int maxJobCard;
        private int rankPointDuel;
        private int roomSkinType;
        private int mentorId;
        private int rankQuestCount;
        private int rankQuestMaxCount;
        private int collectingPoint;
        private int profileFrame;
        private int currentQuest;
        private int recentEnteredJob;
        private int currentGem;
        private int userTitle;
        private int currentBubble;
        private long skin;
        private long curSkinTheme;
        private long mainJobCardId;
        private long curNameTagBgColor;
        private long registrationTime;
        private boolean isHiddenGem;
        private boolean isDeckOpen;
        private boolean isGuildRewardReceived;
        private boolean isMentor;
        private String deathCause;
        private String coupleNickname;
        private String mentorNickname;
        private Map<Integer,JobCard> jobCardMap = new HashMap<>();
        private Map<Integer, Integer> currentSkinMap = new HashMap<>();
        private Set<String> favoriteItems = new HashSet<>();

        public Builder exp(int exp) {
            this.exp = exp;
            return this;
        }

        public Builder win(int win) {
            this.win = win;
            return this;
        }

        public Builder lose(int lose) {
            this.lose = lose;
            return this;
        }

        public Builder rankPoint(int rankPoint) {
            this.rankPoint = rankPoint;
            return this;
        }

        public Builder officialJob(int officialJob) {
            this.officialJob = officialJob;
            return this;
        }

        public Builder questValue(int questValue) {
            this.questValue = questValue;
            return this;
        }

        public Builder questMaxValue(int questMaxValue) {
            this.questMaxValue = questMaxValue;
            return this;
        }

        public Builder questReward(int questReward) {
            this.questReward = questReward;
            return this;
        }

        public Builder coupleId(int coupleId) {
            this.coupleId = coupleId;
            return this;
        }

        public Builder coupleColor(int coupleColor) {
            this.coupleColor = coupleColor;
            return this;
        }

        public Builder coupleDateCount(int coupleDateCount) {
            this.coupleDateCount = coupleDateCount;
            return this;
        }

        public Builder officialJobScore(int officialJobScore) {
            this.officialJobScore = officialJobScore;
            return this;
        }

        public Builder hasNewGuildChat(int hasNewGuildChat) {
            this.hasNewGuildChat = hasNewGuildChat;
            return this;
        }

        public Builder questCount(int questCount) {
            this.questCount = questCount;
            return this;
        }

        public Builder maxPostcard(int maxPostcard) {
            this.maxPostcard = maxPostcard;
            return this;
        }

        public Builder mileage(int mileage) {
            this.mileage = mileage;
            return this;
        }

        public Builder ranking(int ranking) {
            this.ranking = ranking;
            return this;
        }

        public Builder levelReward(int levelReward) {
            this.levelReward = levelReward;
            return this;
        }

        public Builder deathCauseType(int deathCauseType) {
            this.deathCauseType = deathCauseType;
            return this;
        }

        public Builder mainJobCardTier(int mainJobCardTier) {
            this.mainJobCardTier = mainJobCardTier;
            return this;
        }

        public Builder maxJobCard(int maxJobCard) {
            this.maxJobCard = maxJobCard;
            return this;
        }

        public Builder rankPointDuel(int rankPointDuel) {
            this.rankPointDuel = rankPointDuel;
            return this;
        }

        public Builder roomSkinType(int roomSkinType) {
            this.roomSkinType = roomSkinType;
            return this;
        }

        public Builder mentorId(int mentorId) {
            this.mentorId = mentorId;
            return this;
        }

        public Builder rankQuestCount(int rankQuestCount) {
            this.rankQuestCount = rankQuestCount;
            return this;
        }

        public Builder rankQuestMaxCount(int rankQuestMaxCount) {
            this.rankQuestMaxCount = rankQuestMaxCount;
            return this;
        }

        public Builder collectingPoint(int collectingPoint) {
            this.collectingPoint = collectingPoint;
            return this;
        }

        public Builder profileFrame(int profileFrame) {
            this.profileFrame = profileFrame;
            return this;
        }

        public Builder currentQuest(int currentQuest) {
            this.currentQuest = currentQuest;
            return this;
        }

        public Builder recentEnteredJob(int recentEnteredJob) {
            this.recentEnteredJob = recentEnteredJob;
            return this;
        }

        public Builder currentGem(int currentGem) {
            this.currentGem = currentGem;
            return this;
        }

        public Builder userTitle(int userTitle) {
            this.userTitle = userTitle;
            return this;
        }

        public Builder currentBubble(int currentBubble) {
            this.currentBubble = currentBubble;
            return this;
        }

        public Builder skin(long skin) {
            this.skin = skin;
            return this;
        }

        public Builder curSkinTheme(long curSkinTheme) {
            this.curSkinTheme = curSkinTheme;
            return this;
        }

        public Builder mainJobCardId(long mainJobCardId) {
            this.mainJobCardId = mainJobCardId;
            return this;
        }

        public Builder curNameTagBgColor(long curNameTagBgColor) {
            this.curNameTagBgColor = curNameTagBgColor;
            return this;
        }

        public Builder registrationTime(long registrationTime) {
            this.registrationTime = registrationTime;
            return this;
        }

        public Builder isHiddenGem(boolean isHiddenGem) {
            this.isHiddenGem = isHiddenGem;
            return this;
        }

        public Builder isDeckOpen(boolean isDeckOpen) {
            this.isDeckOpen = isDeckOpen;
            return this;
        }

        public Builder isGuildRewardReceived(boolean isGuildRewardReceived) {
            this.isGuildRewardReceived = isGuildRewardReceived;
            return this;
        }

        public Builder isMentor(boolean isMentor) {
            this.isMentor = isMentor;
            return this;
        }

        public Builder deathCause(String deathCause) {
            this.deathCause = deathCause;
            return this;
        }

        public Builder coupleNickname(String coupleNickname) {
            this.coupleNickname = coupleNickname;
            return this;
        }

        public Builder mentorNickname(String mentorNickname) {
            this.mentorNickname = mentorNickname;
            return this;
        }

        public Builder jobCardMap(Map<Integer,JobCard> jobCardMap) {
            this.jobCardMap = jobCardMap;
            return this;
        }

        public Builder addJobCard(Integer cardId, JobCard jobCard) {
            this.jobCardMap.put(cardId, jobCard);
            return this;
        }

        public Builder addJobCards(Map<Integer,JobCard> jobCards) {
            this.jobCardMap.putAll(jobCards);
            return this;
        }

        public Builder currentSkinMap(Map<Integer,Integer> currentSkinMap) {
            this.currentSkinMap = currentSkinMap;
            return this;
        }

        public Builder addCurrentSkin(Integer skinId, Integer value) {
            this.currentSkinMap.put(skinId, value);
            return this;
        }

        public Builder addCurrentSkins(Map<Integer,Integer> skins) {
            this.currentSkinMap.putAll(skins);
            return this;
        }

        public Builder favoriteItems(Set<String> favoriteItems) {
            this.favoriteItems = favoriteItems;
            return this;
        }

        public Builder addFavoriteItem(String item) {
            this.favoriteItems.add(item);
            return this;
        }

        public Builder loginInfo(LoginInfo loginInfo) {
            this.loginInfo = loginInfo;
            return this;
        }


        public MafiaLoginInfo build() {
            return new MafiaLoginInfo(this);
        }
    }

    public int getExp() {
        return exp;
    }

    public int getWin() {
        return win;
    }

    public int getLose() {
        return lose;
    }

    public int getRankPoint() {
        return rankPoint;
    }

    public int getOfficialJob() {
        return officialJob;
    }

    public int getQuestValue() {
        return questValue;
    }

    public int getQuestMaxValue() {
        return questMaxValue;
    }

    public int getQuestReward() {
        return questReward;
    }

    public int getCoupleId() {
        return coupleId;
    }

    public int getCoupleColor() {
        return coupleColor;
    }

    public int getCoupleDateCount() {
        return coupleDateCount;
    }

    public int getOfficialJobScore() {
        return officialJobScore;
    }

    public int getHasNewGuildChat() {
        return hasNewGuildChat;
    }

    public int getQuestCount() {
        return questCount;
    }

    public int getMaxPostcard() {
        return maxPostcard;
    }

    public int getMileage() {
        return mileage;
    }

    public int getRanking() {
        return ranking;
    }

    public int getLevelReward() {
        return levelReward;
    }

    public int getDeathCauseType() {
        return deathCauseType;
    }

    public int getMainJobCardTier() {
        return mainJobCardTier;
    }

    public int getMaxJobCard() {
        return maxJobCard;
    }

    public int getRankPointDuel() {
        return rankPointDuel;
    }

    public int getRoomSkinType() {
        return roomSkinType;
    }

    public int getMentorId() {
        return mentorId;
    }

    public int getRankQuestCount() {
        return rankQuestCount;
    }

    public int getRankQuestMaxCount() {
        return rankQuestMaxCount;
    }

    public int getCollectingPoint() {
        return collectingPoint;
    }

    public int getProfileFrame() {
        return profileFrame;
    }

    public int getCurrentQuest() {
        return currentQuest;
    }

    public int getRecentEnteredJob() {
        return recentEnteredJob;
    }

    public int getCurrentGem() {
        return currentGem;
    }

    public int getUserTitle() {
        return userTitle;
    }

    public int getCurrentBubble() {
        return currentBubble;
    }

    public long getSkin() {
        return skin;
    }

    public long getCurSkinTheme() {
        return curSkinTheme;
    }

    public long getMainJobCardId() {
        return mainJobCardId;
    }

    public long getCurNameTagBgColor() {
        return curNameTagBgColor;
    }

    public long getRegistrationTime() {
        return registrationTime;
    }

    public boolean isHiddenGem() {
        return isHiddenGem;
    }

    public boolean isDeckOpen() {
        return isDeckOpen;
    }

    public boolean isGuildRewardReceived() {
        return isGuildRewardReceived;
    }

    public boolean isMentor() {
        return isMentor;
    }

    public String getDeathCause() {
        return deathCause;
    }

    public String getCoupleNickname() {
        return coupleNickname;
    }

    public String getMentorNickname() {
        return mentorNickname;
    }

    public Map<Integer, JobCard> getJobCardMap() {
        return jobCardMap;
    }

    public Map<Integer, Integer> getCurrentSkinMap() {
        return currentSkinMap;
    }

    public Set<String> getFavoriteItems() {
        return favoriteItems;
    }
}
