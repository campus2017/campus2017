import action.CountMostImport;
import action.impl.CountMostImportImpl;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by dayong.gao on 2016/12/27.
 */
public class Test {
    private static final Logger logger = LoggerFactory.getLogger(Test.class);

    public static void main(String[] args) throws IOException {
        CountMostImport countMostImport =new CountMostImportImpl();
        List<Map.Entry<String, Integer>> resultList = Lists.newArrayList();
        resultList=countMostImport.getCount("D:\\idea");
        for (int i = 0; i <10 ; i++) {
            logger.info("Top Import:{}",resultList.get(i));
        }
    }
}
