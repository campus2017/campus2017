package entity;

import conf.Config;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;

/**
 * Created by Leon on 2017/4/24.
 */
public class RequestData {

    private HashMap<String, String> mRequestData = new HashMap<>();

    public RequestData(Date endDate, int days) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(endDate);
        calendar.add(Calendar.DATE, -days);
        Date startDate = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat(Config.DATE_FORMAT);
        mRequestData.put("startDate", sdf.format(startDate));
        mRequestData.put("endDate", sdf.format(endDate));

    }

    public HashMap<String, String> getRequestData() {
        return mRequestData;
    }

}
