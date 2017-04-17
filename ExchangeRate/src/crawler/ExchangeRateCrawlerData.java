package crawler;

/**
 * Created by Administrator on 2017/2/14.
 */
public class ExchangeRateCrawlerData {
    private String date;

    private double rmbToDollarRate;

    private double rmbToEuroRate;

    private double rmbToHKDollarRate;

    public String GetDate()
    {
        return this.date;
    }

    public void SetDate(String date)
    {
        this.date = date;
    }

    public double GetRMBToDollarRate()
    {
        return this.rmbToDollarRate;
    }

    public void SetRMBToDollarRate(double rate)
    {
        this.rmbToDollarRate = rate;
    }

    public double GetRMBToEuroRate()
    {
        return this.rmbToEuroRate;
    }

    public void SetRMBToEuroRate(double rate)
    {
        this.rmbToEuroRate = rate;
    }

    public double GetRMBToHKDollarRate()
    {
        return this.rmbToHKDollarRate;
    }

    public void SetRMBToHKDollarRate(double rate)
    {
        this.rmbToHKDollarRate = rate;
    }
}
