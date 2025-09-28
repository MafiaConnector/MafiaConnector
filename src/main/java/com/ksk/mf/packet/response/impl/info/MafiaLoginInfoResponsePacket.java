package com.ksk.mf.packet.response.impl.info;

import com.ksk.mf.game.info.JobCard;
import com.ksk.mf.game.info.MafiaLoginInfo;
import io.netty.buffer.ByteBuf;

import java.nio.charset.StandardCharsets;

public class MafiaLoginInfoResponsePacket extends LoginInfoResponsePacket {
    private MafiaLoginInfo info;
    public MafiaLoginInfoResponsePacket(ByteBuf buf) {
        super(buf);
    }

    @Override
    public void fromByteBuf(ByteBuf buf) {
        super.fromByteBuf(buf);
        MafiaLoginInfo.Builder builder = MafiaLoginInfo.mafiaBuilder();
        builder.loginInfo(getLoginInfo())
                .exp(buf.readInt())
                .win(buf.readInt())
                .lose(buf.readInt())
                .rankPoint(buf.readInt())
                .officialJob(buf.readInt())
                .questValue(buf.readInt())
                .questMaxValue(buf.readInt())
                .questReward(buf.readInt())
                .coupleId(buf.readInt())
                .coupleColor(buf.readInt())
                .coupleDateCount(buf.readInt())
                .officialJobScore(buf.readInt())
                .hasNewGuildChat(buf.readInt())
                .questCount(buf.readInt())
                .maxPostcard(buf.readInt())
                .mileage(buf.readInt())
                .ranking(buf.readInt())
                .levelReward(buf.readInt())
                .deathCauseType(buf.readInt())
                .mainJobCardTier(buf.readInt())
                .maxJobCard(buf.readInt())
                .rankPointDuel(buf.readInt())
                .roomSkinType(buf.readInt())
                .mentorId(buf.readInt())
                .rankQuestCount(buf.readInt())
                .rankQuestMaxCount(buf.readInt())
                .collectingPoint(buf.readInt())
                .profileFrame(buf.readInt())
                .currentQuest(buf.readInt())
                .recentEnteredJob(buf.readInt())
                .currentGem(buf.readInt())
                .userTitle(buf.readInt())
                .currentBubble(buf.readInt())
                .skin(buf.readLong())
                .curSkinTheme(buf.readLong())
                .mainJobCardId(buf.readLong())
                .curNameTagBgColor(buf.readLong())
                .registrationTime(buf.readLong())
                .isHiddenGem(buf.readBoolean())
                .isDeckOpen(buf.readBoolean())
                .isGuildRewardReceived(buf.readBoolean())
                .isMentor(buf.readBoolean())
                .deathCause(buf.readCharSequence(buf.readInt(), StandardCharsets.UTF_8).toString())
                .coupleNickname(buf.readCharSequence(buf.readInt(), StandardCharsets.UTF_8).toString())
                .mentorNickname(buf.readCharSequence(buf.readInt(), StandardCharsets.UTF_8).toString());
        int count = buf.readInt();
        for (int i = 0; i < count; i++) {
            JobCard.Builder jobBuild = JobCard.builder();
            int thingy = buf.readInt();
            jobBuild.cardId(buf.readLong())
                    .userId(buf.readInt())
                    .tier(buf.readInt())
                    .experience(buf.readInt())
                    .job(buf.readInt())
                    .win(buf.readInt())
                    .lose(buf.readInt())
                    .isImputed(buf.readBoolean())
                    .bookMark(buf.readBoolean());
            int size = buf.readInt();
            for (int i1 = 0; i1 < size; i1++) {
                jobBuild.addSkill(buf.readInt(), buf.readInt());
            }
            builder.addJobCard(thingy, jobBuild.build());
        }
        int count2 = buf.readInt();
        for (int i = 0; i < count2; i++) {
            builder.addCurrentSkin(buf.readInt(), buf.readInt());
        }
        this.info = builder.build();
    }

    public MafiaLoginInfo getInfo() {
        return info;
    }
}
