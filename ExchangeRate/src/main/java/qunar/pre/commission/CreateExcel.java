package qunar.pre.commission;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by hughgilbert on 2017/5/14.
 */
public class CreateExcel {

    private WritableWorkbook writeBook = null;
    private WritableSheet sheet = null;

    public CreateExcel(String fileName)
    {
        try {
            this.writeBook = Workbook.createWorkbook(new File(fileName));
            this.sheet = writeBook.createSheet("30天内人民币汇率统计表", 1);
            Label label1 = new Label(0, 0, "日期");
            this.sheet.addCell(label1);
            Label label2 = new Label(1, 0, "美元对人民币");
            this.sheet.addCell(label2);
            Label label3 = new Label(2, 0, "欧元对人民币");
            this.sheet.addCell(label3);
            Label label4 = new Label(3, 0, "港元对人民币");
            this.sheet.addCell(label4);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch(WriteException e)
        {
            e.printStackTrace();
        }
    }

    public void write(ArrayList<String> data)
    {
        int row = this.sheet.getRows();
        try {
            for (int i = 0; i <= 3; i++) {
                Label label = new Label(i,row, data.get(i));
                this.sheet.addCell(label);
            }
        }
        catch(WriteException e)
        {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            writeBook.write();
            writeBook.close();
        }
        catch (IOException e )
        {
            e.printStackTrace();
        }
        catch(WriteException e)
        {
            e.printStackTrace();
        }
    }
}
