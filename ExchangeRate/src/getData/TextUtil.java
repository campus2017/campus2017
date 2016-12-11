package getData;

/**
 * Created by Administrator on 2016/12/8.
 */
public class TextUtil {
    public static  boolean isEmpty(String str)
    {
        if(str == null || str.trim().length() == 0)
        {
            return true ;
        }
        return false ;
    }
}
