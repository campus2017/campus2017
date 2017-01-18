package spittr;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @author zhaojun
 * @date 2016年12月16日
 * @reviewer
 * @see
 */
public class Spitter {
   private long totalCount;
    private List<Spittle> listInfo;

    public Spitter(long totalCount, List<Spittle> listInfo) {
        this.totalCount = totalCount;
        this.listInfo = listInfo;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public List<Spittle> getListInfo() {
        return listInfo;
    }

    public void setListInfo(List<Spittle> listInfo) {
        this.listInfo = listInfo;
    }
}
