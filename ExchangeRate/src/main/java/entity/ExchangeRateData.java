package entity;

/**
 * Created by Leon on 2017/4/24.
 */
public class ExchangeRateData {

    private String mDate;
    private double mUSD;
    private double mEUR;
    private double mHKD;


    public ExchangeRateData(String mDate, double mUSD, double mEUR, double mHKD) {
        this.mDate = mDate;
        this.mUSD = mUSD;
        this.mEUR = mEUR;
        this.mHKD = mHKD;
    }

    public String getmDate() {
        return mDate;
    }

    public void setmDate(String mDate) {
        this.mDate = mDate;
    }

    public double getmUSD() {
        return mUSD;
    }

    public void setmUSD(double mUSD) {
        this.mUSD = mUSD;
    }

    public double getmEUR() {
        return mEUR;
    }

    public void setmEUR(double mEUR) {
        this.mEUR = mEUR;
    }

    public double getmHKD() {
        return mHKD;
    }

    public void setmHKD(double mHKD) {
        this.mHKD = mHKD;
    }
}
