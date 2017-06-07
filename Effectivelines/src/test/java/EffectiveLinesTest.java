import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertSame;
/**
 * Created by hughgilbert on 2017/5/11.
 */

public class EffectiveLinesTest {
    @Test
    public void testEffectiveLines()
    {
        Effectivelines test = new Effectivelines("./test/java/Test.java");
        try {
            int result = test.getEffectiveLinesOftheFile();
            assertSame("should be same", result, 6);
        }
        catch(IOException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
