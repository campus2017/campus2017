
import java.util.List;
import java.util.Map;

/**
 * Created by libo on 2017/6/6.
 *
 * 返回给用户的结果，如果info为null表示调用出错，error中可以获取到错误信息
 */
public class ImportInfo {
    private List<Map.Entry<String, Integer>> info = null;
    private String error = null;

    public ImportInfo(){}

    public ImportInfo(String error){
        this.error = error;
    }

    public ImportInfo(List<Map.Entry<String, Integer>> info){
        this.info = info;
    }


    public List<Map.Entry<String, Integer>> getInfo() {
        return info;
    }

    public void setInfo(List<Map.Entry<String, Integer>> info) {
        this.info = info;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "ImportInfo{" +
                "info=" + info +
                ", error='" + error + '\'' +
                '}';
    }
}