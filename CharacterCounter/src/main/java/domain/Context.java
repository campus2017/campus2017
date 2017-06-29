package domain;

import java.io.Serializable;

/**
 * Created by luotuomianyang on 2017/1/28.
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
