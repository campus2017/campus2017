package qunar.pre.commission;

/**
 * Created by hughgilbert on 06/06/2017.
 */
public class Pair implements Comparable<Pair>{

    private String className;
    private int count;

    public Pair(String className,int count){
        this.className = className;
        this.count = count;
    }

    public int compareTo(Pair object){
        return object.getCount()-this.count;
    }

    public int getCount(){
        return count;
    }

    public String toString(){
        return "(" + className + ":" + count + ")";
    }
}
