package cn.xuchunh.effectivelines;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created on 2017/4/5.
 *
 * @author XCH
 */
public class EffectiveLines {

    public static int effectiveLines(String path) throws IOException {

        Preconditions.checkArgument(!Strings.isNullOrEmpty(path), "path is invalid");
        Preconditions.checkArgument(path.endsWith(".java"), "there is no valid java file");

        int effectiveNum = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String s;
            while ((s = reader.readLine()) != null) {
                s = s.trim();
                if (s.startsWith("/*")) {
                    // 非单行结束
                    if (!s.endsWith("*/")) {
                        while ((s = reader.readLine()) != null) {
                            if (s.endsWith("*/")) {
                                break;
                            }
                        }
                    }
                } else if (!s.startsWith("//") && !Strings.isNullOrEmpty(s)) {
                    effectiveNum++;
                }
            }
        }

        return effectiveNum;
    }

}
