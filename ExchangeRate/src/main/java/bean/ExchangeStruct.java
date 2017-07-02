package bean;

public class ExchangeStruct {
    private String dateStr;
    private String middlePrice;
    private String des;

    public String getDateStr() {
        return dateStr;
    }

    public void setDateStr(String dateStr) {
        this.dateStr = dateStr;
    }

    public String getMiddlePrice() {
        return middlePrice;
    }

    public void setMiddlePrice(String middlePrice) {
        this.middlePrice = middlePrice;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    @Override
    public String toString() {
        return "ExchangeStruct{" +
                "dateStr='" + dateStr + '\'' +
                ", middlePrice='" + middlePrice + '\'' +
                ", des='" + des + '\'' +
                '}';
    }
}
