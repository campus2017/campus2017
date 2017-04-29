import java.io.*;
import java.util.*;

/**
 * Created by dang on 2017/4/29.
 * All right reserved.
 */
public class Exam1 {

    public static void main(String[] args) throws Exception {
        String readFileName = "./classpath/unorderedmsg.txt";
        String orderedFileName = "./classpath/orderedmsg.txt";
        String countFileName = "./classpath/count.txt";
        processFile(readFileName, orderedFileName, countFileName);
    }

    private static void processFile(String readFileName, String orderedFileName, String countFileName) throws FileNotFoundException, UnsupportedEncodingException {
        File file = new File(readFileName);
        BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream(file), "Unicode"));
        List<UnitMsgBean> timeOrderedMsgBean= new ArrayList<UnitMsgBean>();
        HashMap<String, Integer> nameApearCounter = new HashMap<String, Integer>();
        String line;
        try {
            while ((line = bf.readLine()) != null) {
                String msgArray[] = line.split("    ");
                UnitMsgBean unitMsgBean = new UnitMsgBean(msgArray[0], msgArray[1], msgArray[2]);
                timeOrderedMsgBean.add(unitMsgBean);

                String viewName[] = msgArray[0].split("】");
                String trueName = viewName[1];
                if (nameApearCounter.get(trueName) == null) {
                    nameApearCounter.put(trueName, 1);
                } else {
                    Integer count = nameApearCounter.get(trueName);
                    nameApearCounter.put(trueName, count + 1);
                }
            }

            BufferedWriter writer = new BufferedWriter(new FileWriter(orderedFileName));
            Collections.sort(timeOrderedMsgBean, new Comparator<UnitMsgBean>() {
                public int compare(UnitMsgBean o1, UnitMsgBean o2) {
                    return o1.compareTo(o2);
                }
            });
            for (UnitMsgBean aTimeOrderedMsgBean : timeOrderedMsgBean) {
                writer.write(aTimeOrderedMsgBean.toString());
                writer.newLine();
            }
            writer.close();

            writer = new BufferedWriter(new FileWriter(countFileName));
            for (Map.Entry<String, Integer> entry : nameApearCounter.entrySet()) {
                writer.write(entry.getKey() + "    " + entry.getValue());
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
