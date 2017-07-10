/**
 * Created by qunar on 17-7-6.
 */

// function :
//      caculate the effective line number of a java file


import  java.io.* ;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class EffectiveLineCount {


    private String fileName = null;
    private int count = 0;                                 //  store line num ;


    public EffectiveLineCount()
    {
        fileName = "/home/qunar/MyCode/IdeaProjects/QunarHomeWork/EffectiveLines/out/production/EffectiveLines/EffectiveLineCount.java";
        //  default file name ;
        count = 0;
    }

    public EffectiveLineCount(String inputFileName)
    {
        fileName = inputFileName;
        count = 0;
    }


    public int run() throws IOException
    {
        BufferedReader bf = getIOStream(fileName);

        CountEffectiveLineNum(bf);

        return count;
    };


   // get io stream
   private BufferedReader getIOStream(String filename) throws IOException
   {
       FileReader fr = new FileReader(filename);

       BufferedReader bf = new BufferedReader(fr);

       return bf;
   };


   //  calculate effectiveLIneNum
   private void CountEffectiveLineNum(BufferedReader bf) throws IOException
   {
       String tmp = bf.readLine();
       while(tmp != null)
       {
           //System.out.println(tmp);
          if(isBlankLine(tmp)) {
              tmp = bf.readLine();
              continue;
          }

           if(isSingleLineComment(tmp)) {
               tmp = bf.readLine();
               continue;
           }

           if(isMultiLineComment(tmp, bf)) {
               tmp = bf.readLine();
               continue;
           }
          count++;
           tmp = bf.readLine();
       }
   }



    private boolean isBlankLine(String str)
    {
        String result = "";
        if (str!=null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");      // delete space, \n , etc...
            Matcher m = p.matcher(str);
            result = m.replaceAll("");
        }

        if("".equals(result)) {
            return true;
        }
        return false;
    }


    //  delete  single line comment.
    private boolean isSingleLineComment(String str)
    {
        //single line comment
        if(str.matches( "^\\s*//+.*" ))
            return true;

        return false;
    }

    //   delete  multiline comment.
    private boolean isMultiLineComment(String str, BufferedReader bf) throws IOException
    {
        if(str.matches( "^\\s*/\\*.*" ))              // detect the " /* "
        {
            String tmp = str;
            while( !(tmp.matches( ".*\\*/.*" )) )     //detect the  " */ "
            {
                tmp = bf.readLine();
            }
            return true;
        }
        return false;
    }

}
