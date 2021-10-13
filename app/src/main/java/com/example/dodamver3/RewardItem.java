package com.example.dodamver3;

import android.media.Image;

public class RewardItem {
    byte[] rewardImage;
    String rewardExp;
    String rewardDetailExp;

    public byte[] getRewardImage() {

        return rewardImage;
    }

    public void setRewardImage(byte[] rewardImage) {
        this.rewardImage = rewardImage;
    }

    public String getRewardExp() {
        return rewardExp;
    }

    public void setRewardExp(String rewardExp) {
        this.rewardExp = rewardExp;
    }

    public String getRewardDetailExp() {
        return rewardDetailExp;
    }

    public void setRewardDetailExp(String rewardDetailExp) {
        this.rewardDetailExp = rewardDetailExp;
    }
}
