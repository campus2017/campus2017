package crawler;

/**
 * Created by Administrator on 2017/2/14.
 */
public class ExchangeRateCrawlerRule {
    //request url
    private String url;

    //request params
    private String[] params;

    //request values
    private String[] values;

    //html element tag name
    private String tagName;

    //html element tag type
    private HtmlElement tagType = HtmlElement.ID ;

    //request method
    private HttpMethod requestMethod = HttpMethod.GET ;

    //filter by html element
    public static enum HtmlElement{
        ID,
        CLASS,
        SELECTION
    }

    //http request method type
    public static enum HttpMethod{
        GET,
        POST
    }

    public ExchangeRateCrawlerRule() {
    }

    public ExchangeRateCrawlerRule(String url, String[] params, String[] values,
                       String tagName, HtmlElement tagType, HttpMethod requestMethod) {
        super();
        this.url = url;
        this.params = params;
        this.values = values;
        this.tagName = tagName;
        this.tagType = tagType;
        this.requestMethod = requestMethod;
    }

    public String GetUrl()
    {
        return url;
    }

    public void SetUrl(String url)
    {
        this.url = url;
    }

    public String[] GetParams()
    {
        return params;
    }

    public void SetParams(String[] params)
    {
        this.params = params;
    }

    public String[] GetValues()
    {
        return values;
    }

    public void SetValues(String[] values)
    {
        this.values = values;
    }

    public String GetTagName()
    {
        return tagName;
    }

    public void SetResultTagName(String tagName)
    {
        this.tagName = tagName;
    }

    public HtmlElement GetTagType()
    {
        return tagType;
    }

    public void SetTagType(HtmlElement tagType)
    {
        this.tagType = tagType;
    }

    public HttpMethod GetRequestMethod()
    {
        return requestMethod;
    }

    public void SetRequestMethod(HttpMethod requestMethod)
    {
        this.requestMethod = requestMethod;
    }
}
