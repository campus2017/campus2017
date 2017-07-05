import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import query.EffectiveLines;
import query.Impl.EffectiveLinesImpl;

/**
 * Created by dayong.gao on 2016/12/26.
 */
public class Test {
    private static final Logger logger = LoggerFactory.getLogger(Test.class);

    public static void main(String[] args) {
        EffectiveLines effectiveLines =new EffectiveLinesImpl();
        int num=effectiveLines.getCount("test.txt");
        logger.info("line num:{}",num);
    }
}
