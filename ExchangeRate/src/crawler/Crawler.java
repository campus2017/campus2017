package crawler;

/**
 * Created by Administrator on 2017/2/14.
 */
public interface Crawler {
    public void Extract(Object rule);
    public void Export(String path);
}
