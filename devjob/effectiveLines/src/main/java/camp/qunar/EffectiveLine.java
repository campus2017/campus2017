package camp.qunar;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Created by honglin.li on 2017/6/11.
 */
public class EffectiveLine {
    private String filename = "";
    private int effectiveLineCount = 0;

    public EffectiveLine(String filename) {
        this.filename = filename;
    }

    public void outputCount() throws Exception{

        BufferedReader br = new BufferedReader(new FileReader(filename));
        String s;
        StringBuffer sb = new StringBuffer();

        while((s = br.readLine()) != null) {
//            System.out.println(s);
            effectiveLineCount += judge(s);
        }

        System.out.println("the file " + filename + " has effective line " + effectiveLineCount + " lines");

    }

    int judge(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        if(s.startsWith("//")) {
            return 0;
        };
        return 1;
    }
}
