
import java.util.*;

public class HelloWord{

    private String id; //id

    private String senderName; //发送人姓名
    //不能超过120个中文字符
    private String title;
    //邮件正文
    private String content;
    //附件，如果有的话
    private String attach;
    //总发送人数
    private String totalCount;
    //成功发送的人数
    private String successCount;
    //0不删除 1删除
    private Integer isDelete;
    //目前不支持定时 所以创建后即刻发送
    private Date createTime;
    /** The value is used for characterstorage. */
    private  char value[];
    /** The offset is the first index of thestorage that is used. */
    private  int offset;
    /** The count is the number of charactersin the String. */
    private  int count;
    /** Cache the hash code for the string */
    // Default to 0
    private int hash;
    public static void main(String[] args) {
        System.out.println("Helloword");
        }

    public HelloWord(String name){
    }



     /**
     * 为按钮添加颜色
     *@param color
    按钮的颜色
     *@return
     *@exception  (方法有异常的话加)
     * @author Administrator
     * @Time2012-11-20 15:02:29
     */

    public void addColor(String color){
    }
}