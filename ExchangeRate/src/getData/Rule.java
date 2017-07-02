package getData;

/**
 * function:配置url 和请求类型
 * Created by Administrator on 2016/12/8.
 */
public class Rule {
    /**
     * 链接
     */
    private String url;
    private  int type;
    /**
     * GET / POST
     * 请求的类型，默认GET
     */

    public Rule(String url) {
        super();
        this.url = url;


    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


}
