package com.graphene.freshguide.model;

import java.io.Serializable;

/**
 * @author Mingxin Wang
 * @since 2017-06-15 下午9:28
 */
public class CounterResp implements Serializable {

  private static final long serialVersionUID = -6380734240372361581L;
  private Character character;
  private SymbolType type;
  private Integer count;

  public static Builder builder() {
    return new Builder();
  }

  public int incrCount() {
    if (count == null) {
      throw new RuntimeException("count has not been initialized");
    }
    return ++this.count;
  }

  @Override
  public String toString() {
    return "CounterResp{" +
        "character=" + character +
        ", type=" + type +
        ", count=" + count +
        '}';
  }

  public Character getCharacter() {
    return character;
  }

  public void setCharacter(Character character) {
    this.character = character;
  }

  public SymbolType getType() {
    return type;
  }

  public void setType(SymbolType type) {
    this.type = type;
  }

  public Integer getCount() {
    return count;
  }

  public void setCount(Integer count) {
    this.count = count;
  }

  public static class Builder {
    private Character character;
    private SymbolType type;
    private Integer count;

    public CounterResp build() {
      CounterResp resp = new CounterResp();
      resp.setCharacter(character);
      resp.setType(type);
      resp.setCount(count);
      return resp;
    }

    public Builder character(Character character) {
      this.character = character;
      return this;
    }

    public Builder type(SymbolType type) {
      this.type = type;
      return this;
    }

    public Builder count(Integer count) {
      this.count = count;
      return this;
    }
  }
}
