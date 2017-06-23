package getWeb;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by yaoguoli on 2017/6/3.
 */
public class web {
    public static void main(String[] args) throws Exception {

        //连接网址，获取输入流
        URL url = new URL("http://www.safe.gov.cn/AppStructured/view/project_RMBQuery.action");
        BufferedReader br = null;
        //br = new BufferedReader(new InputStreamReader(url.openConnection().getInputStream()));//打开到url的连接，获得其输入流
        br = new BufferedReader(new InputStreamReader(url.openStream()));

        //从输入流br读取结果
        StringBuffer buffer = new StringBuffer();//字符串变量，可以扩充和修改
        String str1 = null;
        while((str1 = br.readLine())!=null){
            buffer.append(str1);
        }
        String str2 = buffer.toString();
        String Data = getData(str2);
        br.close();
        System.out.println(Data);
    }

    private static String getData(String str) {
        StringBuffer buffer = new StringBuffer();
        String str1 = "";
        String str2 = "";

        Pattern p = Pattern.compile("(.*)(<table class=\"list\" id=\"InfoTable\" width=\"100%\" border=\"0\" align=\"center\" cellpadding=\"3\" cellspacing=\"1\">)(.*?)(</table>)(.*)");
        Matcher m = p.matcher(str);

        if(m.matches()){
            str1 = m.group(3);
            //匹配币种
            p = Pattern.compile("(.*)(<th width=\"8%\" class=\"table_head\" style=\"cursor:'default'\" id=\"comtemplatename\" >)(.*?)(</th>)(.*)");
            m = p.matcher(str1);
            if(m.matches()){
                str2 = m.group(3);
                buffer.append(str2);
                buffer.append("\n");
            }

            //匹配中间价
            p = Pattern.compile("(.*)(<td width=\"8%\" align=\"center\"  >)(.*?)(/td)(.*)");
            m = p.matcher(str1);
            if(m.matches()){
                str2 = m.group(3);
                buffer.append(str2);
            }
        }

        String result = buffer.toString();
        return result;
    }
}
