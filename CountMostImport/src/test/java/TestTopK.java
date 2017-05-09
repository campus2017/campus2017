import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by isc on 2016/12/19.
 */
public class TestTopK {

    @Ignore
    public void testTopK(){
        List<MostImportClass> list = new ArrayList<MostImportClass>();
        list.add(new MostImportClass("a",0));
        list.add(new MostImportClass("s",5));
        list.add(new MostImportClass("d",4));
        list.add(new MostImportClass("f",7));
        list.add(new MostImportClass("g",3));
        list.add(new MostImportClass("h",6));
        list.add(new MostImportClass("j",9));
        list.add(new MostImportClass("k",9));
        list.add(new MostImportClass("l",8));
        list.add(new MostImportClass("q",2));

        list = TopK.getTopK(list,4);
        for(int i=0;i<4;i++){
            System.out.println(list.get(i).importName+":"+list.get(i).value);
        }
    }

    @Test
    public void testCountMostImport(){
        String dir = "G:\\IDEA_Workspace_2016_12_16\\CountMostImport\\src\\main\\java";
        CountMostImport miCls = new CountMostImport(dir);
        miCls.getTopKParams(10);
    }
}
