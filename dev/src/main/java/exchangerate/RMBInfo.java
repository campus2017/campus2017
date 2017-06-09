package exchangerate;

import lombok.Data;

import java.util.Date;

@Data
class RMBInfo{
    private String dollarRate;
    private String euroRate;
    private String hkDollarRate;
    private Date time;
    @Override
    public String toString(){
        return time+",人民币对美元的中间价为"+dollarRate+",人民币对欧元的中间价为"+euroRate+",人民币对港币的中间价为"+hkDollarRate;
    }
}