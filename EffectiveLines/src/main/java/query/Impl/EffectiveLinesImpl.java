package query.Impl;

import com.google.common.base.Strings;
import com.google.common.io.Files;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import query.EffectiveLines;
import utils.CharsetUtil;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

/**
 * Created by dayong.gao on 2016/12/26.
 */
public class EffectiveLinesImpl implements EffectiveLines{
    private static final Logger logger = LoggerFactory.getLogger(EffectiveLinesImpl.class);
    @Override
    public int getCount(String filepath){
        File file = new File(filepath);
        List<String> lines = null;
        try {
            lines = Files.readLines(file, Charset.forName(CharsetUtil.getCharset(filepath)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        int num=0;
        for (String tem : lines) {
            String line =tem.trim();
            if(!Strings.isNullOrEmpty(line)&&!line.startsWith("//")&&!line.startsWith("/*")){
                logger.info("line :{}",line);
                num++;
            }
        }
        return num;
    }
}
