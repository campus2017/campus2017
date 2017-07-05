package entity;

/**
 * Created by zhanghe on 2017/7/5.
 */
public class ImportClassBean implements Comparable<ImportClassBean>{
    private String className;
    private Integer counts;

    public ImportClassBean() {
    }

    public ImportClassBean(String className, Integer counts) {
        this.className = className;
        this.counts = counts;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Integer getCounts() {
        return counts;
    }

    public void setCounts(Integer counts) {
        this.counts = counts;
    }

    @Override
    public String toString() {
        return "ImportClassBean{" +
                "className='" + className + '\'' +
                ", counts=" + counts +
                '}';
    }

    @Override
    public int compareTo(ImportClassBean o) {
        if(null == o){
            throw new NullPointerException("所比较对象不能为空");
        }
        //降序添加
        return  o.getCounts() - this.counts;

    }
}
