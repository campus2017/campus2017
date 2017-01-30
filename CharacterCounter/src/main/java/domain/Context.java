package domain;

import java.io.Serializable;

/**
 * Created by luvslu on 2017/1/28.
 */
public class Context implements Serializable{
    private static final long serialVersionUID = 1520961851058396786L;
    private String text;

    public Context(){
    }

    public Context(String text){
        this.text = text;
    }

    public String getText(){return text;}
    public void setText(String text){this.text = text;}
}
