package kevin.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import kevin.service.ICountService;
import kevin.vo.CountResult;
import kevin.vo.TopCharacter;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import static kevin.utils.CollectionUtils.addToMap;

@Service
public class CountServiceImpl implements ICountService {


    @Override
    public Object[] countFromStream(List<InputStream> streams) {
        StringBuilder sb = new StringBuilder();  // 提高连接效率，不需要线程安全

        for (InputStream stream : streams) {
            byte[] buffer = new byte[1024];
            try {
                int len = stream.read(buffer);
                while (len > 0){
                    sb.append(new String(buffer,0,len,"UTF-8"));
                    len =  stream.read(buffer);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                try {
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return countFromString(sb.toString());
    }

    @Override
    public Object[] countFromString(String streams) {
        int eng, num, chinese, punch;
        eng = num = chinese = punch = 0;
        Map<Character,Integer> map = Maps.newHashMap();
        if (streams == null || streams.length() == 0) return null;
        char[] chars = streams.toCharArray();
        for (char c : chars) {
            boolean flag =false;
            if (c >= '0' && c <= '9') {
                num++;
                flag=true;
            } else if (c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z') {
                eng++;
                flag=true;
            } else if ( c >= 19968 && c <= 40869) { //Character.toString(c).matches("[\\u4e00-\\u9fa5]")
                chinese++;
                flag=true;

            } else if (Character.toString(c).matches("[\\pP+~$`^=|<>～｀＄＾＋＝｜＜＞￥×]")) {
                punch++;
                flag=true;
            }
            if(flag) addToMap(map,c);
        }
        List<Map.Entry<Character, Integer>> sortedList = sortMap(map);
        CountResult countResult = new CountResult(eng,num,chinese,punch);
        ArrayList<TopCharacter> topThree = Lists.newArrayList();
        for (int i = 0; i < 3 && i < sortedList.size(); i++) {
            Map.Entry<Character, Integer> ent = sortedList.get(i);
            topThree.add(new TopCharacter(ent.getKey(),ent.getValue()));
        }
        Object[] res = {countResult,topThree}; // 返回结果封装到数组中
        return res;
    }

    private List<Map.Entry<Character, Integer>> sortMap(Map<Character, Integer> map) {
        Set<Map.Entry<Character, Integer>> set = map.entrySet();
        ArrayList<Map.Entry<Character, Integer>> list = Lists.newArrayList(set);
       /* Collections.sort(list,(o1, o2) -> {
            return o2.getValue()-o1.getValue();
        }); why?*/
        Collections.sort(list,( Map.Entry<Character, Integer> o1,Map.Entry<Character, Integer>  o2) -> o2.getValue()-o1.getValue()); // lambda表达式
        return list;
    }

    public static void main(String[] args) {
        Object[] res = new CountServiceImpl().countFromString("abcefaefeff1男2男女女男3男女4女男男女5女男6男女女，，，，，，，，，，，，，，，，，，.>>>>>");
        for (Object re : res) {
            System.out.println(re);
        }


    }
}
