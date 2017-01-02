
import java.util.Scanner;
import java.util.List;

import leeyang.practice.qunar.Crawler;
import leeyang.practice.qunar.DataRate;
import leeyang.practice.qunar.WriteExcel;

/**
 * 爬取人民币对美元，欧元和港币的汇率中间价，并写入excel的主程序
 */

public class Main {
    public static void main(String[] args) {
        try {
            int days = getDays();
            String savePath = getSavePath();
            Crawler crawler = new Crawler(days);
            List<DataRate> listPrice = crawler.getFormateData();
            WriteExcel.Write(listPrice, savePath);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static int getDays() {
        Scanner sc = new Scanner(System.in);
        int days;
        System.out.println("请输入当前日期之前的天数");
        days = sc.nextInt();
        return days;
    }

    private static String getSavePath() {
        Scanner sc = new Scanner(System.in);
        String savePath;
        System.out.println("请输入要保存最终结果的文件路径及文件名:");
        savePath = sc.nextLine();
        if (!savePath.contains(".xls")) {
            savePath += ".xls";
        }
        return savePath;
    }
}
