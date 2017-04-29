import java.util.Comparator;

/**
 * Created by dang on 2017/4/29.
 * All right reserved.
 */
public class UnitMsgBean implements Comparable<Object> {

    private String name;
    private String date;
    private String msg;

    public UnitMsgBean() {
    }

    public UnitMsgBean(String name, String date, String msg) {
        this.name = name;
        this.date = date;
        this.msg = msg;
    }

    @Override
    public String toString() {
        return name + "    " +
                date + "    " +
                msg;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int compareTo(Object o) {
        if (this == o) {
            return 0;
        } else if (o != null && o instanceof UnitMsgBean) {
            UnitMsgBean u = (UnitMsgBean) o;
            if (date.compareTo(u.getDate()) < 0) {
                return -1;
            } else if (date.compareTo(u.getDate()) > 0) {
                return 1;
            } else {
                return name.compareTo(u.getName());
            }
        } else {
            return -1;
        }
    }
}
