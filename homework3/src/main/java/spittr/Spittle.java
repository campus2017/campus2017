package spittr;
import sun.security.provider.ConfigFile;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.Date;

/**
 * @author zhaojun
 * @date 2016年12月15日
 * @reviewer
 * @see
 */
public class Spittle {
    private Long id;
    private String name;
    private String message;
    private Date time;
    private Long number;
    private Long egold;
    private String status;

    public Spittle(Long id, String message, Date time, Long number, Long egold, String status,String name) {
        this.id = id;
        this.message = message;
        this.time = time;
        this.number = number;
        this.egold = egold;
        this.status = status;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public Long getEgold() {
        return egold;
    }

    public void setEgold(Long egold) {
        this.egold = egold;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object that){
        return EqualsBuilder.reflectionEquals(this,that,"id","time");
    }
    @Override
    public int hashCode(){
        return HashCodeBuilder.reflectionHashCode(this,"id","time");
    }
}
