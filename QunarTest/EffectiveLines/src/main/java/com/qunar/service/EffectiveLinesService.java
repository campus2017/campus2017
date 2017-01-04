package com.qunar.service;

import java.io.*;


/**
 * 源代码有效行统计服务
 * Created by 张竣伟 on 2017/1/3.
 */
public class EffectiveLinesService {

    private String filePath;

    /**
     * 传入需要统计的文件的路径
     * @param filePath 待统计文件路径
     */
    public EffectiveLinesService(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    //读取文件并统计有效行
    public int getEffectiveLinesCount() {
        int count = 0;
        File file = new File(filePath);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String line = "";
            while (null != (line = reader.readLine())) {
                if (isEffective(line)) {
                    count++;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("文件不存在");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return count;
    }

    /**
     * 判断当前行是否为有效行
     *
     * @param s
     * @return 有效返回true 无效返回false
     */
    private boolean isEffective(String s) {
        boolean flag = false;

        //判断是否为空行
        if (s.matches("^[\\s&&[^\\n]]*$")) {
            return flag;
        }

        //判断是否为注释行
        if (s.matches("\\s*/\\*{1,}.*(\\*/).*") || s.trim().startsWith("//")) {
            return flag;
        }
        return true;
    }
}
