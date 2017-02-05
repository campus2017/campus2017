package com.qunar.hw.character_counter.bean;

import java.util.*;

/**
 * 字符及出现次数，top3
 * Created by runsheng.zhou on 2017/2/5.
 */
public class Top3Info {
    private CharCount most;
    private CharCount secondMore;
    private CharCount thirdMore;

    public CharCount getMost() {
        return most;
    }

    public void setMost(CharCount most) {
        this.most = most;
    }

    public CharCount getSecondMore() {
        return secondMore;
    }

    public void setSecondMore(CharCount secondMore) {
        this.secondMore = secondMore;
    }

    public CharCount getThirdMore() {
        return thirdMore;
    }

    public void setThirdMore(CharCount thirdMore) {
        this.thirdMore = thirdMore;
    }
}
