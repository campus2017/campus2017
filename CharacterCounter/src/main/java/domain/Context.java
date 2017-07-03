package domain;

import java.io.Serializable;

/**
 * Created by Wang Yishu on 2017/6/28.
 */
public class Context implements Serializable{

    private String text;

    public Context(){

    }

    public Context(String text){
        this.text = text;
    }

    public String getText(){return text;}
    public void setText(String text){this.text = text;}
}
