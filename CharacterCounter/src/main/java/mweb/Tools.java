package mweb;

import com.google.common.base.Charsets;
import com.google.common.primitives.*;
import org.mozilla.intl.chardet.nsDetector;
import org.mozilla.intl.chardet.nsPSMDetector;
import java.nio.charset.Charset;
import java.util.*;

public class Tools {
  private static class Box<T> {
    private T val;

    Box() {
    }

    Box(T val) {
      this.val = val;
    }

    boolean isEmpty() {
      return val == null;
    }

    void set(T val) {
      this.val = val;
    }

    T get() {
      return val;
    }
  }

  public static Charset getCharsets(byte[] bytes) {
    final Box<String> res = new Box<>();
    nsDetector detector = new nsDetector(nsPSMDetector.CHINESE);
    detector.Init(res::set);
    int size = bytes.length;
    if (detector.isAscii(bytes, size))
      return Charsets.US_ASCII;
    detector.DoIt(bytes, size, false);
    detector.DataEnd();
    return Charset.forName(res.get());
  }

  public static Count getCount(String str) {
    Count count = new Count();
    int[] charlist = new int[Character.MAX_VALUE];
    str.chars().forEach(i -> {
      int[] res = count.getNums();
      ++charlist[i];
      if (isELetter(i))
        res[0] = res[0] + 1;
      else if (Character.isDigit(i))
        res[1] = res[1] + 1;
      else if (Character.UnicodeScript.of(i) == Character.UnicodeScript.HAN)
        res[2] = res[2] + 1;
      else if (Character.toString((char) i).matches("\\p{P}"))
        res[3] = res[3] + 1;
      else
        --charlist[i];
    });
    int[] rChar = Arrays.stream(charlist).filter(i -> i != 0).toArray();
    int[] maxs = new int[3];
    for (int i = 0; i != maxs.length; ++i) {
      int index = rChar.length - 1 - i;
      nth_element(rChar, index);
      maxs[i] = rChar[index];
    }
    count.setCnums(maxs);
    for (int i = 0; i != maxs.length; ++i) {
      int indexOf = Ints.indexOf(charlist, maxs[i]);
      charlist[indexOf] = -1;
      count.getChars()[i] = (char)indexOf;
    }
    return count;
  }

  private static boolean isELetter(int i) {
    return ((char) i >= 'a' && (char) i <= 'z') || ((char) i >= 'A' && (char) i <= 'Z');
  }

  private static int partition(int[] array, int i, int j) {
    int pivot = array[i];
    while (i < j) {
      while (i < j && array[j] >= pivot)
        j--;
      if (i < j)
        array[i++] = array[j];
      while (i < j && array[i] <= pivot)
        i++;
      if (i < j)
        array[j--] = array[i];
    }
    array[i] = pivot;
    return i;
  }

  private static void nth_element(int[] array, int k) {
    int mid = partition(array, 0, array.length - 1);
    while (mid != k) {
      if (mid < k)
        mid = partition(array, mid + 1, array.length - 1);
      else
        mid = partition(array, 0, mid - 2);
    }
  }
}
