/**
 * Created by isc on 2016/12/19.
 */
public class MostImportClass implements Comparable<MostImportClass>{

    public String importName;
    public Integer value;
    MostImportClass(String importName, Integer value){
        this.importName=importName;
        this.value=value;
    }


    public int compareTo(MostImportClass o) {
        if(value.compareTo(o.value)>0) {
            return 1;
        }else{
            return 0;
        }
    }
}
