package spittr.data;

import org.springframework.stereotype.Component;
import spittr.Spittle;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author zhaojun
 * @date 2016年12月15日
 * @reviewer
 * @see
 */
@Component
public class SpittleRepositoryImpl implements SpittleRepository {
    @Override
    public List<Spittle> findSpittles(long start, long end,String beginTime,String endTime){

        List<Spittle> spittleList = new ArrayList<>();
        for(long i = start;i<start + end;i++){
            Spittle spittle = null;
            beginTime = beginTime.trim();
            endTime = endTime.trim();
            if(beginTime.equals("0")|| endTime.equals("0")){
                 spittle = new Spittle(i,"E币已退回",new Date(),1L,190L,"成功","手机50元话费充值"+i);
            }else{
                int year = Integer.valueOf(beginTime.substring(0,4)) - 1900;
                int month = Integer.valueOf(beginTime.substring(4,6));
                int day = Integer.valueOf(beginTime.substring(6,8));
                if(month == 12){
                    month = 1;
                    year = year +1;
                }else{
                    month = month + 1;
                }
                Date randomDate = new Date(year,month,day);

                spittle = new Spittle(i,"E币已退回",randomDate,1L,190L,"成功","手机50元话费充值"+i);
            }
            spittleList.add(spittle);
        }
        return spittleList;
    }

}
