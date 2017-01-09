package bean;

import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;

/**
 * Created by gzx on 16-12-31.
 */
public class ImportCount implements Comparable<ImportCount>{
    private String className;
    private Integer cnt;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Integer getCnt() {
        return cnt;
    }

    public void setCnt(Integer cnt) {
        this.cnt = cnt;
    }

    @Override
    public int compareTo(ImportCount o) {
        return ComparisonChain.start().compare(this.cnt, o.cnt).compare(this.className, o.className).result();
    }

    @Override
    public String toString(){
        return Objects.toStringHelper(this).omitNullValues().add("className", className).add("count", cnt).toString();
    }
}
