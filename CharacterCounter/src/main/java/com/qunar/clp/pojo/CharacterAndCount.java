package com.qunar.clp.pojo;

/**
 * Created by nipingchen on 16-12-20.
 */
public class CharacterAndCount {
    private Character character;//字符
    private Integer counts;//出现的次数
    public CharacterAndCount(Character character, Integer counts) {
        this.character = character;
        this.counts = counts;
    }

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

    public Integer getCounts() {
        return counts;
    }

    public void setCounts(Integer counts) {
        this.counts = counts;
    }
}
