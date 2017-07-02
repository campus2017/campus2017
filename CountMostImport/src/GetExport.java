import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/20.
 */
class GetExport{
    public void getPrint(ArrayList<Map.Entry<String,Integer>> list){
        System.out.println("被import最多的类是"+list.get(0).getKey());
        if(list.size()>=10)
        {
            System.out.println("被import最多的前十个包名为");
            for(int i=0;i<10;i++)
                System.out.println(list.get(i).getKey());
        }else {
            System.out.println("引入的包数小于十，它们的名字和数量为");
            for(Map.Entry<String,Integer> item:list)
                System.out.println(item.getKey()+" "+item.getValue());
        }
    }
}