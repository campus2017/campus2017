/**
 * Created by mml on 17-7-3.
 */

import java.util.ArrayList;
import java.util.List;

public class GetTopK extends MinHeap{

    public  List<ClassAndValue> getTopK(List<ClassAndValue> input, int k)
    {
        createLitteHeap(input,k);
        List<ClassAndValue> a = new ArrayList<ClassAndValue>();
        for(int i=k;i<input.size();i++){
            if(input.get(i).times>cvHeap.get(0).times){
                push(input.get(i));
            }
        }
        return cvHeap;
    }
}
