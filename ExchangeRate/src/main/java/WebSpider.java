import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by dang on 2017/4/21.
 * All right reserved.
 */
public class WebSpider {

    public static void main(String[] args){
        URL url = null;
        URLConnection urlConnection = null;
        BufferedReader reader = null;
        PrintWriter writer = null;
        String regex = "(http|https)://[\\w+\\.?/?]+\\.[A-Za-z\\S]+";
        Pattern pattern = Pattern.compile(regex);
        try {
            url = new URL("https://www.baidu.com/");
            urlConnection = url.openConnection();
            writer = new PrintWriter(new FileWriter("F:\\IdeaProjects\\campus2017\\ExchangeRate\\url.txt"),true);
            reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String buf;
            while((buf=reader.readLine())!=null){
                Matcher matcher = pattern.matcher(buf);
                while (matcher.find()){
                    writer.println(matcher.group());
                }
            }
            System.out.println("获取成功");
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        writer.close();
    }
}
