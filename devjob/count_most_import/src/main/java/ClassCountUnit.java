import java.util.Comparator;

/**
 * Created by honglin.li on 2017/6/30.
 */
public class ClassCountUnit {

    private String classname;
    private int cnt;


    public ClassCountUnit(String classname, int cnt) {
        this.classname = classname;
        this.cnt = cnt;
    }

    @Override
    public String toString() {
        return "ClassCountUnit{" +
                "classname='" + classname + '\'' +
                ", cnt=" + cnt +
                '}';
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }
}
