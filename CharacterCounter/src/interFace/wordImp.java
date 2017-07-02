package interFace;

import net.sf.json.JSONObject;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2017/1/28.
 */
public interface wordImp {
    public String getFileContent(MultipartFile file) throws IOException;

    public JSONObject getJSONResult(String content);

    public HashMap<Character, Integer> analysisWord(String content);

    public List sortMap(HashMap<Character, Integer> map, String sortWay);

    public int judgeWord(Character word);

    public HashMap<String, Integer> statistcsWord(HashMap<Character, Integer> content);


}
