package com.ksk.mf.game.info;

import java.util.HashMap;
import java.util.Map;

public class JobCard {
    private long cardId;
    private int userId;
    private int tier;
    private int experience;
    private int job;
    private int win;
    private int lose;
    private boolean isImputed;
    private boolean bookMark;
    private Map<Integer,Integer> skillMap = new HashMap<>();

    private JobCard(Builder builder) {
        this.cardId = builder.cardId;
        this.userId = builder.userId;
        this.tier = builder.tier;
        this.experience = builder.experience;
        this.job = builder.job;
        this.win = builder.win;
        this.lose = builder.lose;
        this.isImputed = builder.isImputed;
        this.bookMark = builder.bookMark;
        this.skillMap = builder.skillMap != null ? new HashMap<>(builder.skillMap) : new HashMap<>();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private long cardId;
        private int userId;
        private int tier;
        private int experience;
        private int job;
        private int win;
        private int lose;
        private boolean isImputed;
        private boolean bookMark;
        private Map<Integer,Integer> skillMap = new HashMap<>();

        public Builder cardId(long cardId) {
            this.cardId = cardId;
            return this;
        }

        public Builder userId(int userId) {
            this.userId = userId;
            return this;
        }

        public Builder tier(int tier) {
            this.tier = tier;
            return this;
        }

        public Builder experience(int experience) {
            this.experience = experience;
            return this;
        }

        public Builder job(int job) {
            this.job = job;
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

        public Builder isImputed(boolean isImputed) {
            this.isImputed = isImputed;
            return this;
        }

        public Builder bookMark(boolean bookMark) {
            this.bookMark = bookMark;
            return this;
        }

        public Builder skillMap(Map<Integer,Integer> skillMap) {
            this.skillMap = skillMap;
            return this;
        }

        public Builder addSkill(Integer skillId, Integer level) {
            this.skillMap.put(skillId, level);
            return this;
        }

        public Builder addSkills(Map<Integer,Integer> skills) {
            this.skillMap.putAll(skills);
            return this;
        }

        public JobCard build() {
            return new JobCard(this);
        }
    }

    public long getCardId() {
        return cardId;
    }

    public int getUserId() {
        return userId;
    }

    public int getTier() {
        return tier;
    }

    public int getExperience() {
        return experience;
    }

    public int getJob() {
        return job;
    }

    public int getWin() {
        return win;
    }

    public int getLose() {
        return lose;
    }

    public boolean isImputed() {
        return isImputed;
    }

    public boolean isBookMark() {
        return bookMark;
    }

    public Map<Integer, Integer> getSkillMap() {
        return skillMap;
    }
}
