package FileManager;

/**
 * Created by Administrator on 2017/2/19.
 */
public class ClassEntity implements Comparable<ClassEntity>{
    public String className = "";
    public Integer count = 0;

    public int compareTo(ClassEntity classEntity)
    {
        return this.count - classEntity.count;
    }

    public String toString()
    {
        return "className:" + className + ",count：" + count;
    }
}
