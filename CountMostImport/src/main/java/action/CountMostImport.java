package action;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by dayong.gao on 2016/12/27.
 */
public interface CountMostImport {
    List<Map.Entry<String, Integer>> getCount(String path) throws IOException;
}
