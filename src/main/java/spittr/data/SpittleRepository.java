package spittr.data;
import java.util.List;
import spittr.Spittle;

/**
 * @author zhaojun
 * @date 2016年12月15日
 * @reviewer
 * @see
 */
public interface SpittleRepository {
    List<Spittle> findSpittles(long start,long end,String beginTime,String endTime);

}
