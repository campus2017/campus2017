import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by dell--pc on 2016/11/12.
 */
public class MyFrame implements MouseListener {
    private JFrame frame;
    private JButton button;
    private JPanel panel;

    public MyFrame(){
        frame=new JFrame("EffctiveLines");
        button=new JButton("open");
        panel=new JPanel();
        frame.setContentPane(panel);
        frame.getContentPane().add(button, BorderLayout.CENTER);
        frame.setVisible(true);
        frame.setSize(300,200);
        button.addMouseListener(this);
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        //初始化文件选择框
        System.out.println("click me ");


        JFileChooser fDialog = new JFileChooser();
        //设置文件选择框的标题
        fDialog.setDialogTitle("请选择java文件");
        //弹出选择框
        int returnVal = fDialog.showOpenDialog(null);
        // 如果是选择了文件
        if(JFileChooser.APPROVE_OPTION == returnVal){
            //打印出文件的路径，你可以修改位 把路径值 写到 textField 中
            Scanner sc= null;
            try {
                sc = new Scanner(new File(String.valueOf(fDialog.getSelectedFile())));
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }
            int effectiveLines = 0;
            while (sc.hasNext()) {
                effectiveLines++;
                sc.next();
            }
            JFrame jFrame=new JFrame();
            JOptionPane.showMessageDialog(jFrame.getContentPane(),
                    "有效行数为："+effectiveLines, "系统信息", JOptionPane.INFORMATION_MESSAGE);
            System.out.println(effectiveLines);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("pressed");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println("release");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        System.out.println("move in");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        System.out.println("move out");
    }
}
