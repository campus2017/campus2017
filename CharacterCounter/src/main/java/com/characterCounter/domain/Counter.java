package com.characterCounter.domain;


import com.google.common.collect.Multiset;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/24.
 */
public class Counter implements Serializable {

    private static final long serialVersionUID = -1222298863292202989L;

    private Long engAlphabet; //英文字母
    private Long number; //数字
    private Long chiCharacters; //中文汉字
    private Long punctuation; //中英文标点符号
    private List<Multiset.Entry<String>> highFrequency; // 频率最高的三个字

    public Long getEngAlphabet() {
        return engAlphabet;
    }

    public void setEngAlphabet(Long engAlphabet) {
        this.engAlphabet = engAlphabet;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public Long getChiCharacters() {
        return chiCharacters;
    }

    public void setChiCharacters(Long chiCharacters) {
        this.chiCharacters = chiCharacters;
    }

    public Long getPunctuation() {
        return punctuation;
    }

    public void setPunctuation(Long punctuation) {
        this.punctuation = punctuation;
    }

    public List<Multiset.Entry<String>> getHighFrequency() {
        return highFrequency;
    }

    public void setHighFrequency(List<Multiset.Entry<String>> highFrequency) {
        this.highFrequency = highFrequency;
    }

    @Override
    public String toString() {
        return "Counter{" +
                "engAlphabet=" + engAlphabet +
                ", number=" + number +
                ", chiCharacters=" + chiCharacters +
                ", punctuation=" + punctuation +
                ", highFrequency=" + highFrequency +
                '}';
    }
}
