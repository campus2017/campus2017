package com.qunar.hw.character_counter.bean;

/**
 * Created by runsheng.zhou on 2017/2/3.
 */
public class CountRes {
    private  StatisticsInfo statisticsInfo;
    private Top3Info top3Info;

    public StatisticsInfo getStatisticsInfo() {
        return statisticsInfo;
    }

    public void setStatisticsInfo(StatisticsInfo statisticsInfo) {
        this.statisticsInfo = statisticsInfo;
    }

    public Top3Info getTop3Info() {
        return top3Info;
    }

    public void setTop3Info(Top3Info top3Info) {
        this.top3Info = top3Info;
    }
}
