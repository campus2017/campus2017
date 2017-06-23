import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
//import jjjjjjjjjjjjjjjjjj;
/**
 * Created by lenovo on 2017/6/23.
 */
public class Main {
    private static ArrayList[] lists = new ArrayList[2];

    public static void main(String[] args) throws Exception{
        Scanner input = new Scanner(System.in);
        String path = input.nextLine();
        lists[0] = new ArrayList<String>();
        lists[1] = new ArrayList<Integer>();

        openAllFile(path);
        sortList();

        System.out.println("被import最多的前十个类及其次数为：");
        int size = lists[0].size() >= 10?10:lists[0].size();
        for (int i = 0; i < size; i++) {
            System.out.println(lists[0].get(i)+"("+lists[1].get(i)+")");
        }
    }

    private static void openAllFile(String path) throws Exception{
        File file = new File(path);
        if (file.exists()) {
            File[] files = file.listFiles();
            if (files.length == 0) {
                System.out.println("文件夹是空的!");
            } else {
                for (File file2 : files) {
                    if (file2.isDirectory()) {
                        openAllFile(file2.getAbsolutePath());
                    } else {
                        String fileName = file2.getName();
                        if (fileName.indexOf(".java") != -1) {//只统计java文件
                            countClass(file2);
                        }
                    }
                }
            }
        } else {
            System.out.println("文件不存在!");
            return;
        }
    }

    private static void countClass(File file) throws Exception{
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(file));
            String str = null;

            while ((str = bufferedReader.readLine()) != null) {
                str = str.replace(" ", "");
                if (str.indexOf("import") == 0) {//import位于首位
                    int index = str.indexOf("import")+"import".length();
                    String tempStr = str.substring(index,str.length()-1);
                    if (lists[0].size()==0) {
                        lists[0].add(0, tempStr);
                        lists[1].add(0, 1);
                    } else {
                        boolean isExist = false;
                        for (int i = 0; i < lists[0].size(); i++) {
                            if (tempStr.equals(lists[0].get(i))) {
                                int tempInt = (int)lists[1].get(i);
                                tempInt++;
                                lists[1].set(i, tempInt);
                                isExist = true;
                                break;
                            }
                        }
                        if (!isExist) {
                            lists[0].add(lists[0].size(), tempStr);
                            lists[1].add(lists[1].size(), 1);
                        }
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void sortList() {
        int size = lists[1].size();
        for(int i = 0 ; i < size-1; i ++) {
            for(int j = 0 ;j < size-1-i ; j++) {
                int one = (int)lists[1].get(j);
                int two = (int)lists[1].get(j+1);
                if(one < two) {
                    String first = (String)lists[0].get(j);
                    String second = (String)lists[0].get(j+1);
                    lists[1].set(j, two);
                    lists[1].set(j+1,one);
                    lists[0].set(j,second);
                    lists[0].set(j+1,first);
                }
            }
        }
    }

}
