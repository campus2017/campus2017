import java.util.*;

/**
 * Created by Administrator on 2016/12/19.
 */
public class Main {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        System.out.println("请输入项目目录");
        String path=sc.nextLine();
        //得到统计结果
        GetRecords gr=new GetRecords(path);
        HashMap<String, Integer> records=gr.getImportClassRecords();
        //对map进行按值排序
        GetSortedMap gsm=new GetSortedMap();
        ArrayList<Map.Entry<String,Integer>> list=gsm.getSortedMap(records);
        //输出结果
        GetExport ge=new GetExport();
        ge.getPrint(list);
    }
}


