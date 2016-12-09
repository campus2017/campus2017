package com.youthlin.qunar.diff.model;

import java.util.Date;

/**
 * Created by youthlin.chen on 2016-11-16 016.
 * Different 实体
 */
public class Different {
    private Integer did;
    private Date compareTime;
    private String source;
    private String target;
    private String onlySource = "";
    private String onlyTarget = "";
    private String differing = "";
    private User user;

    @Override
    public String toString() {
        return "Different{" +
                "did=" + did +
                ", compareTime=" + compareTime +
                ", source='" + source + '\'' +
                ", target='" + target + '\'' +
                ", onlySource='" + onlySource + '\'' +
                ", onlyTarget='" + onlyTarget + '\'' +
                ", differing='" + differing + '\'' +
                ", user=" + user +
                '}';
    }

    public User getUser() {
        return user;
    }

    public Different setUser(User user) {
        this.user = user;
        return this;
    }

    public Integer getDid() {
        return did;
    }

    public Different setDid(Integer did) {
        this.did = did;
        return this;
    }

    public Date getCompareTime() {
        return compareTime;
    }

    public Different setCompareTime(Date compareTime) {
        this.compareTime = compareTime;
        return this;
    }

    public String getSource() {
        return source;
    }

    public Different setSource(String source) {
        this.source = source;
        return this;
    }

    public String getTarget() {
        return target;
    }

    public Different setTarget(String target) {
        this.target = target;
        return this;
    }

    public String getOnlySource() {
        return onlySource;
    }

    public Different setOnlySource(String onlySource) {
        this.onlySource = onlySource;
        return this;
    }

    public String getOnlyTarget() {
        return onlyTarget;
    }

    public Different setOnlyTarget(String onlyTarget) {
        this.onlyTarget = onlyTarget;
        return this;
    }

    public String getDiffering() {
        return differing;
    }

    public Different setDiffering(String differing) {
        this.differing = differing;
        return this;
    }
}
