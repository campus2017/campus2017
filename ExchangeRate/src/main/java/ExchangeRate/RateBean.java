package ExchangeRate;

/**
 *  功能：汇率实体类
 * Created by yangyening on 2016/12/27.
 */
public class RateBean {
    private String date;
    private float usdPrice;
    private float eurPrice;
    private float hkdPrice;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public float getUsdPrice() {
        return usdPrice;
    }

    public void setUsdPrice(float usdPrice) {
        this.usdPrice = usdPrice;
    }

    public float getEurPrice() {
        return eurPrice;
    }

    public void setEurPrice(float eurPrice) {
        this.eurPrice = eurPrice;
    }

    public float getHkdPrice() {
        return hkdPrice;
    }

    public void setHkdPrice(float hkdPrice) {
        this.hkdPrice = hkdPrice;
    }

    public RateBean() {
    }
}
