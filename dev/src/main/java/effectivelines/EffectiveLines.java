package effectivelines;

import com.google.common.base.Charsets;
import com.google.common.collect.ImmutableList;
import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;

public class EffectiveLines {
    public static final String BLANK_CODE_REGEX = "^[\\s]*$";

    public static void main(String[] args) {

        Integer count = countEffectiveLines("代码文件路径");
        System.out.println("该文件的有效代码行数为" + count + "行");
    }

    public static Integer countEffectiveLines(String path) {
        File file = new File(path);
        Integer effectiveCount = 0;
        boolean comment = false;
        try {
            ImmutableList<String> strings = Files.asCharSource(file, Charsets.UTF_8).readLines();
            for (String line : strings) {
                line = line.trim();
                if (line.matches(BLANK_CODE_REGEX)) {
                    //代码空行不做统计
                } else if (line.startsWith("/*") && !line.endsWith("*/")) {
                    comment = true;
                    //下行代码处于注释中
                } else if (comment) {
                    //注释中的代码不做统计
                    if (line.endsWith("*/")) {
                        comment = false;
                    }
                } else if (line.startsWith("//")) {
                    //单行注释不做统计
                } else {
                    //有效代码行+1
                    effectiveCount++;
                }
            }
            return effectiveCount;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }

}
