package cn.xuchunh.imports;

/**
 * Created on 2017/4/6.
 *
 * @author XCH
 */
public class ClassNode {

    private String className;
    private int frequency;

    public ClassNode() {
    }

    public ClassNode(String className, int frequency) {
        this.className = className;
        this.frequency = frequency;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public void frequencyIncrement(){
        frequency++;
    }


}
