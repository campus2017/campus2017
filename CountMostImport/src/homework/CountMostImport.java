package homework;

import java.io.*;
import java.util.*;


public class CountMostImport {
    private static Map<String, Integer> importClassRecord = new HashMap<String, Integer>();

    public static void parseDir(File file) {

        if (file.isDirectory()) {
            File[] fileList = file.listFiles();
            for (File f : fileList) {
                parseDir(f);
            }
        } else {
            countMostImport(file);
        }
    }

    public static void countMostImport(File file) {
        if (file.getName().endsWith(".java")) {
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
                String line = null;
                while ((line = bufferedReader.readLine()) != null) {
                    line = line.trim();
                    if (line.startsWith("import")) {
                        line = line.substring(6).trim();
                        if (importClassRecord.get(line) == null) {
                            importClassRecord.put(line, 1);
                        } else {
                            importClassRecord.put(line, importClassRecord.get(line) + 1);
                        }
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void printImportClass() {

        //对HaspMap中import类进行排序
        List<Map.Entry<String, Integer>> classSortList = new ArrayList<Map.Entry<String, Integer>>(importClassRecord.entrySet());

        Collections.sort(classSortList, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> entry1, Map.Entry<String, Integer> entry2) {
                return entry2.getValue() - entry1.getValue();
            }
        });

        //防止import类个数不足10个的情况
        int num = 10;//
        if (classSortList.size() < 10) {
            num = classSortList.size();
        }

        for (int i = 0; i < num; i++) {
            System.out.println(classSortList.get(i).getKey() + " : " + classSortList.get(i).getValue() + "次");
        }
    }


    public static void main(String[] args) {
        System.out.println("请输入要统计的文件或文件夹的路径：");
        Scanner scanner = new Scanner(System.in);
        File file = new File(scanner.nextLine());
        while (!file.exists()) {
            System.out.println("您输入的文件或文件夹路径错误！");
            System.out.println("请重新输入文件或文件夹路径：");
            file = new File(scanner.nextLine());
        }
        parseDir(file);
        if (importClassRecord.size() == 0) {
            System.out.println("您输入的文件不是java文件或者文件夹下没有java文件！");
        } else {
            System.out.println("被import最多的类中前十位的是：");
        }
        printImportClass();
        scanner.close();
    }
}