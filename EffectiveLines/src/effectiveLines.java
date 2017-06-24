
/*
* 1、file_io
*
*
* */

import com.google.common.base.Optional;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class effectiveLines {

    private int effective_lines;

public effectiveLines(){

    effective_lines = 0;

}

// file reader
    public void readFromFile(String file_name)
    {
        try {
            BufferedReader line_reader = new BufferedReader(new FileReader(file_name));

            while (true)  {
                Optional<String> an_line =  Optional.fromNullable(line_reader.readLine());

                if(an_line.isPresent()==false)
                {
                    break;
                }
                else {

                    int state = judgeAnLine(an_line.get());

                    if(state <0)
                    {
                        continue;

                    }else {

                        effective_lines ++;

                    }

                }

            }

        } catch (FileNotFoundException e) { // file not found exception
            e.printStackTrace();
        } catch (IOException e) {  // IO exception
            e.printStackTrace();
        }

        System.out.println(effective_lines);

    }

// judge an line

    /*
    * state
    *
    * -1 : 表示无效行
    *
    * 1 :  表示有效行
    *
    * */
    public int judgeAnLine(String an_line){

        Optional<String> after_trim = Optional.fromNullable(an_line.trim());  // fromNullable 可以用空对象创建，如果用of则不能用空对象创建，会一直抛出异常

        if(after_trim.get().length()<=0 || after_trim.isPresent()==false)  // case 空行
        {
            return -1;
        }else{  // case 注释内容为空

            if(after_trim.get().startsWith("//"))
            {

               Optional<String> sub_string=Optional.fromNullable( after_trim.get().substring(2,after_trim.get().length()));

                if(sub_string.isPresent() ==false || sub_string.get().trim().length()<=0 )
                {
                    return -1;
                }
                else{
                    return 1;
                }

            }
            else if(after_trim.get().startsWith("/*") && after_trim.get().endsWith("*/"))
            {
                Optional<String> sub_string = Optional.fromNullable( after_trim.get().substring(2,after_trim.get().length()-2));

                if(sub_string.isPresent()==false || sub_string.get().trim().length()<=0 )
                {
                    return -1;
                }
                else{
                    return 1;
                }

            }


        }

return 1;

    }

}
