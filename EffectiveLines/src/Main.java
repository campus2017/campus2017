import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class EffectiveLines {
    public int main(String[] args) {
        if(args == null){
            return 0;
        }
        FileReader File_reader = null;
        BufferedReader Buffer_reader = null;
        int count =0;
        try {
            File_reader = new FileReader(args[0]);
            Buffer_reader = new BufferedReader(File_reader);
            string line = null;
            while((line = reader.readLine())!=null){
                line = line.trim();
                if(!line.isEmpty()&&!isAnnotation(line)){
                    count++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                reader.close();
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return count;
    }
	System.out.println(count);
}
