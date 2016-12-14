package wz.test;

import org.junit.Test;
import wz.PackageUtils;

import java.io.File;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * PackageUtilsTest
 *
 * @author wz
 * @date 16/12/11 20:13
 */
public class PackageUtilsTest {
    @Test
    public void getClassName() throws Exception {
//        ClassLoader loader = Thread.currentThread().getContextClassLoader();
//        URL url = loader.getResource("wz");
//        File file = new File(url.getFile().replaceAll("%20"," "));
//        System.out.println(file.getAbsolutePath());
//        File[] files = file.listFiles();
//        System.out.println(files.length);

        Set<String> set = PackageUtils.getClassName("wz", false);

        System.out.println(set.size());
        for (String s : set) {
            System.out.println(s);
        }
    }

}