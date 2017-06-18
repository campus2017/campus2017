
import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;

import java.net.URI;

/** 
* EffectiveLines Tester. 
* 
* @author runlibo
* @since 06/05/2017 
* @version 1.0 
*/ 
public class EffectiveLinesTest { 

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
    *
    * Method: countLinesNum(String filePath)
    *
    */
    @Test
    public void testCountLinesNum() throws Exception {
    //TODO: Test goes here...
    }


    /**
    *
    * Method: checkFile(File file)
    *
    */
    @Test
    public void testCheckFile() throws Exception {

    }

    /**
    *
    * Method: calcEffectiveLines(BufferedReader reader)
    *
    */
    @Test
    public void testCalcEffectiveLines() throws Exception {
        //根据类路径获取resource下的文件的绝对路径    有中文的话要进行转换
        String filePath = new URI(EffectiveLines.class.getResource("test.java").toString()).getPath();

        LinesInfo info = new EffectiveLines().countLinesNum(filePath);
        if (info.getLine() != -1){
            System.out.println("有效行数为：" + info.getLine());
        }
        else{
            System.out.println(info.getErrorMsg());
        }

    }

} 
