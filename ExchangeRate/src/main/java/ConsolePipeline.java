import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.io.FileOutputStream;
import java.io.IOException;
import java.rmi.server.ExportException;
import java.util.Map;

/**
 * Created by woo on 1/5.
 */
public class ConsolePipeline implements Pipeline {

    @Override
    public void process(ResultItems resultItems, Task task) {
        System.out.println("get page: " + resultItems.getRequest().getUrl());
        //遍历所有结果，输出到控制台，上面例子中的"author"、"name"、"readme"都是一个key，其结果则是对应的value
        for (Map.Entry<String, Object> entry : resultItems.getAll().entrySet()) {
            String tempStr = entry.getValue().toString();
            tempStr = tempStr.substring(1,tempStr.length() - 1);
            String key = entry.getKey();
            System.out.println(key + ":\t" + tempStr);
            String[] arr = tempStr.split(",");
            int i = 1;
            for(String str : arr){
                //System.out.println(str);
            }
        }
    }
}