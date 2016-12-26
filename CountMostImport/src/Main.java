import java.util.Map;

public class Main {
    public static void main(String[] args) {
        ScanDir scan = new ScanDir("C://Users//luvslu//IdeaProjects//LearnJava//src");
        scan.setImportCountMap();
        Map<String, Integer> results = scan.getMostImportClass(10);
        for (Map.Entry<String, Integer> entry : results.entrySet()) {
            System.out.println("Class = " + entry.getKey() + ", Count = " + entry.getValue());
        }
    }
}