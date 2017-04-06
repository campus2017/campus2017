package cn.xuchunh.imports.test;

import cn.xuchunh.imports.ClassNode;
import cn.xuchunh.imports.CountMostImport;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created on 2017/4/6.
 *
 * @author XCH
 */
public class CountMostImportTest {

    @Test
    public void testCountMostImportTest(){
        String path = "C:\\dev\\projects\\temp\\campus2017\\ExchangeRate\\src";
        try {
            List<ClassNode> nodes = CountMostImport.countMostImport(path, 10);
            for (ClassNode node : nodes) {
                System.out.println(node.getClassName() + ": " + node.getFrequency());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void temp(){
        Pattern pattern = Pattern.compile("(public|abstract|class).+");
        System.out.println(pattern.matcher("public final class Pattern").matches());
    }

}
