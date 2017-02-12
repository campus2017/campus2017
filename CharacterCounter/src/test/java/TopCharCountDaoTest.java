import com.springmvc.CountBean.CharCountBean;
import com.springmvc.dao.TopCharCountDao;
import org.junit.Test;

import java.util.List;

/**
 * Created by gcy0904 on 2017/1/24.
 */
public class TopCharCountDaoTest {

    @Test
    public void testGetMostCharCount() throws Exception {
        String str = "aaaaaaab";
        TopCharCountDao topCharCountDao = new TopCharCountDao(str);
        CharCountBean mostCharCount = topCharCountDao.getMostCharCount();
        System.out.print(mostCharCount.getCharName());
        System.out.print(mostCharCount.getCharCount());
    }

    @Test
    public void testGetTopCharCount() throws Exception {
        String str = "aaaaaaabccddd";
        TopCharCountDao charCount = new TopCharCountDao(str);
        List<CharCountBean> topCharCount = charCount.getTopCharCount(2);
        System.out.print(topCharCount);
        for (int i = 0; i < topCharCount.size(); i++) {
            String charName = topCharCount.get(i).getCharName();
            int charCount1 = topCharCount.get(i).getCharCount();

            System.out.println();
            System.out.print(charName);
            System.out.print(charCount1);
        }
    }
}