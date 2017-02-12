import com.springmvc.CountBean.EveryCountBean;
import com.springmvc.service.CountService;
import org.junit.Test;

/**
 * Created by gcy0904 on 2017/1/24.
 */
public class CountServiceTest {

    @Test
    public void testGetEveryCount() throws Exception {
        String s = "我是。.guoch12345";
        CountService countService = new CountService();
        EveryCountBean everyCount = countService.getEveryCount(s);
        System.out.println(everyCount.getChinaChar());
        System.out.println(everyCount.getEnglishChar());
        System.out.println(everyCount.getNum());
        System.out.println(everyCount.getPunctuation());
    }
}