package QFC.CharacterCounter.POJO;

import java.util.HashMap;

/**
 * Created by root on 6/29/17.
 */
public class Result
{
    private int alphaCount;
    private int chineseCount;
    private int numberCount;
    private int otherCount;
    private Character chara1;
    private Character chara2;
    private Character chara3;
    private int num1;
    private int num2;
    private int num3;

    public int getAlphaCount() {
        return alphaCount;
    }

    public Result setAlphaCount(int alphaCount) {
        this.alphaCount = alphaCount;
        return this;
    }

    public void addAlp(){alphaCount++;}

    public int getChineseCount() {
        return chineseCount;
    }

    public Result setChineseCount(int chineseCount) {
        this.chineseCount = chineseCount;
        return this;
    }

    public void addCHN(){chineseCount++;}

    public int getNumberCount() {
        return numberCount;
    }

    public Result setNumberCount(int numberCount) {
        this.numberCount = numberCount;
        return this;
    }

    public void addNum(){numberCount++;}

    public int getOtherCount() {
        return otherCount;
    }

    public Result setOtherCount(int otherCount) {
        this.otherCount = otherCount;
        return this;
    }

    public void addPun(){otherCount++;}

    public Character getChara1()
    {
        return chara1;
    }

    public Result setChara1(Character chara1)
    {
        this.chara1 = chara1;
        return this;
    }

    public Character getChara2()
    {
        return chara2;
    }

    public Result setChara2(Character chara2) {
        this.chara2 = chara2;
        return this;
    }

    public Character getChara3() {
        return chara3;
    }

    public Result setChara3(Character chara3) {
        this.chara3 = chara3;
        return this;
    }

    public int getNum1() {
        return num1;
    }

    public Result setNum1(int num1) {
        this.num1 = num1;
        return this;
    }

    public int getNum2() {
        return num2;
    }

    public Result setNum2(int num2) {
        this.num2 = num2;
        return this;
    }

    public int getNum3() {
        return num3;
    }

    public Result setNum3(int num3) {
        this.num3 = num3;
        return this;
    }
}
