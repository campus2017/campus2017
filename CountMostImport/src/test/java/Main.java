import java.util.List;
import java.util.Map;


/**
 * Created by libo on 2017/6/7.
 */
public class Main {


    public static void main(String[] args) {

        ImportInfo info = new CountMostImport().countMostImport("./src/main/resources/javafile");
        List<Map.Entry<String, Integer>> l = info.getInfo();
        if (l == null){
            System.out.println(info.getError());
        }
        else{
            for (Map.Entry item : l){
                System.out.println(item.getKey()+":"+item.getValue());
            }
        }

    }
}
