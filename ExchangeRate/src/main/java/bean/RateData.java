package bean;

/**
 * Created by libo on 2017/6/12.
 *
 * 人民币汇率中间价实体类，包含时间，对美元、欧元、港币的汇率
 */
public class RateData {
    private String date;
    private String dollar;
    private String euro;
    private String hkDollar;

    public RateData(){}

    public RateData(String date, String dollar, String euro, String hkDollar){
        this.date = date;
        this.dollar = dollar;
        this.euro = euro;
        this.hkDollar = hkDollar;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDollar() {
        return dollar;
    }

    public void setDollar(String dollar) {
        this.dollar = dollar;
    }

    public String getEuro() {
        return euro;
    }

    public void setEuro(String euro) {
        this.euro = euro;
    }

    public String getHkDollar() {
        return hkDollar;
    }

    public void setHkDollar(String hkDollar) {
        this.hkDollar = hkDollar;
    }

    @Override
    public String toString() {
        return "RateData{" +
                "date='" + date + '\'' +
                ", dollar='" + dollar + '\'' +
                ", euro='" + euro + '\'' +
                ", hkDollar='" + hkDollar + '\'' +
                '}';
    }
}
