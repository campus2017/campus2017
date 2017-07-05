package action.impl;

import action.CountMostImport;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.io.Files;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.CharsetUtil;
import utils.MapUtil;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

/**
 * Created by dayong.gao on 2016/12/27.
 */
public class CountMostImportImpl implements CountMostImport{
    private static final Logger logger = LoggerFactory.getLogger(CountMostImportImpl.class);

    List<String> stringList = Lists.newArrayList();
    private void getImport(String path) throws IOException {
        File rootDir = new File(path);
        if(!rootDir.isDirectory()){
            List<String> lines = Files.readLines(rootDir, Charset.forName(CharsetUtil.getCharset(path))); //获取编码方式进行相应的读取
            for (String line : lines) {
                if(line.trim().startsWith("import")){
                   stringList.add(line);
                }
            }
        }else{
            String[] fileList =  rootDir.list();
            for (int i = 0; i < fileList.length; i++) {
                path = rootDir.getAbsolutePath()+"\\"+fileList[i];
                getImport(path);
            }
        }
    }

    @Override
    public List<Map.Entry<String, Integer>> getCount(String path) throws IOException {
        getImport(path);
        Map<String, Integer> countMap = Maps.newHashMap();
        List<String> tem =Lists.newArrayList();
        for (String line : stringList) {
            tem= Splitter.on(' ').splitToList(line);
            if (!countMap.containsKey(line)) {
                countMap.put(line, 1);
            } else {
                countMap.put(line, countMap.get(line) + 1);
            }
        }
        logger.info("import:{}",stringList);
        return MapUtil.valuesort(countMap);
    }
}

