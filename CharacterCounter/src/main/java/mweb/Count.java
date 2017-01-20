package mweb;

import com.google.common.base.Joiner;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Count {
  private char[] chars;
  private int[] cnums;
  private int[] nums;

  public Count() {
    chars = new char[3];
    cnums = new int[3];
    nums = new int[4];
  }

  public void setNums(int[] nums) {
    this.nums = nums;
  }

  public int[] getNums() {
    return nums;
  }

  public void setChars(char[] chars) {
    this.chars = chars;
  }

  public char[] getChars() {
    return chars;
  }

  public void setCnums(int[] cnums) {
    this.cnums = cnums;
  }

  public int[] getCnums() {
    return cnums;
  }

  @Override
  public String toString() {
    return Joiner.on('\n').join(
        Arrays.toString(chars),
        Arrays.toString(cnums),
        Arrays.toString(nums));
  }
}
