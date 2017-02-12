import com.springmvc.dao.CountDao;
import org.junit.Test;

/**
 * Created by gcy0904 on 2017/1/24.
 */
public class CountDaoTest {

    @Test
    public void testIsChinaChar() throws Exception {
        String s = "中";
        System.out.print(CountDao.isChinaChar(s));
    }
}