/**
 * Created by libo on 2017/6/5.
 */
public class LinesInfo {
    private int line = -1;
    private String errorMsg = null;

    public LinesInfo(){}


    /**
     * 方法调用成功后的构造方式
     * @param line
     */
    public LinesInfo(int line){
        this.line = line;
    }

    /**
     * 方法调用错误后的构造方式
     * @param msg
     */
    public LinesInfo(String msg){
        this.errorMsg = msg;
    }


    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }


    public boolean isSuccess(){
        return line == -1 ? false : true;
    }
}
