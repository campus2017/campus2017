package com.youthlin.qunar.fresh.charactercount.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.ImmutableMultiset;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.collect.Multiset;
import com.google.common.collect.Multisets;

import java.io.Serializable;
import java.util.List;

/**
 * Created by youthlin.chen on 2016-12-9 009.
 * 统计结果
 */
public class CountResult implements Serializable {
    private long enWords;
    private long numbers;
    private long cnWords;
    private long symbols;
    @JsonIgnore
    private Multiset<Character> words = HashMultiset.create();
    private List<Multiset.Entry<Character>> top3;

    public List<Multiset.Entry<Character>> getTopkWords(int k) {
        ImmutableMultiset<Character> tmp = Multisets.copyHighestCountFirst(words);
        ImmutableSet<Multiset.Entry<Character>> set = tmp.entrySet();
        List<Multiset.Entry<Character>> entries = Lists.newArrayList();
        int count = 0;
        for (Multiset.Entry<Character> e : set) {
            if (++count > k) {
                break;
            }
            entries.add(e);
        }
        return entries;
    }

    @Override
    public String toString() {
        return "CountResult{" +
                "enWords=" + enWords +
                ", numbers=" + numbers +
                ", cnWords=" + cnWords +
                ", symbols=" + symbols +
                ", words=" + words +
                ", top3=" + top3 +
                '}';
    }

    public long getEnWords() {
        return enWords;
    }

    public CountResult setEnWords(long enWords) {
        this.enWords = enWords;
        return this;
    }

    public long getNumbers() {
        return numbers;
    }

    public CountResult setNumbers(long numbers) {
        this.numbers = numbers;
        return this;
    }

    public long getCnWords() {
        return cnWords;
    }

    public CountResult setCnWords(long cnWords) {
        this.cnWords = cnWords;
        return this;
    }

    public long getSymbols() {
        return symbols;
    }

    public CountResult setSymbols(long symbols) {
        this.symbols = symbols;
        return this;
    }

    public Multiset<Character> getWords() {
        return words;
    }

    public CountResult setWords(Multiset<Character> words) {
        this.words = words;
        return this;
    }

    public List<Multiset.Entry<Character>> getTop3() {
        return top3;
    }

    public CountResult setTop3(List<Multiset.Entry<Character>> top3) {
        this.top3 = top3;
        return this;
    }
}
