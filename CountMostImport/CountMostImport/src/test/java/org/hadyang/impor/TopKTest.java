package org.hadyang.impor;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by AstaYang on 2016/11/13.
 */
public class TopKTest {
    @Test
    public void getTopK() throws Exception {
        List<CountResult> in = new ArrayList<>();
        in.add(new CountResult("a", 9));
        in.add(new CountResult("b", 2));
        in.add(new CountResult("c", 4));
        in.add(new CountResult("d", 13));
        in.add(new CountResult("e", 1));
        in.add(new CountResult("f", 7));
        in.add(new CountResult("g", 6));

        TopK<CountResult> topK = new TopK<>();
        List<CountResult> result = topK.getTopK(in, 5);
        for (CountResult countResult : result) {
            System.out.print(String.format("%d,", countResult.count));
        }
    }

}