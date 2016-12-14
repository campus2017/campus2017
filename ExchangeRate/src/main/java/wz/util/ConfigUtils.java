package wz.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * ConfigUtils工具类 获取配置文件中的配置
 *
 * @author wz
 * @date 16/11/12 17:57
 */
public class ConfigUtils {

    private static Properties properties;

    static {
        try {
            InputStream in = ConfigUtils.class.getClassLoader().getResourceAsStream("config.properties");
            properties = new Properties();
            properties.load(in);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

}
