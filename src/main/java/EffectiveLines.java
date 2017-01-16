import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
/**
 * Created by zdl on 2017/1/10.
 */
public class EffectiveLines extends JFrame {
    public static void main(String[] args) {
        EffectiveLines el = new EffectiveLines();
    }
    EffectiveLines() {
        super("EffectiveLines");
        this.setLayout(new GridLayout(3, 1, 0, 0));
        JPanel jp1 = new JPanel();
        this.pathText = new JTextField(25);
        jp1.add(new JLabel("Path:"));
        jp1.add(pathText);
        this.openButton = new JButton("Open");
        /*选择文件路径*/
        openButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser jfc = new JFileChooser();
                jfc.setMultiSelectionEnabled(false);
                jfc.setCurrentDirectory(new File("."));
                int result = jfc.showOpenDialog(getParent());
                if (result == jfc.APPROVE_OPTION) {
                    String path = jfc.getSelectedFile().getPath();
                    pathText.setText(path);
                }
            }
        });
        jp1.add(openButton);
        this.getContentPane().add(jp1);
        JPanel jp2 = new JPanel();
        this.countButton = new JButton("count");
        /*count*/
        countButton.addActionListener(new ActionListener(){
            public void actionPerformed (ActionEvent e){
                File file = new File(pathText.getText());
                int res = count(file);
                countText.setText("" + res);
            }
        });
        jp2.add(countButton);
        this.getContentPane().add(jp2);
        JPanel jp3 = new JPanel();
        jp3.add(new JLabel("Effective Lines:"));
        this.getContentPane().add(jp3);
        this.countText = new JTextField(5);
        jp3.add(countText);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(300, 300, 400, 100);
        this.setResizable(false);
        this.setVisible(true);
    }
    private int count(File file){
        int res = 0;
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String line = null;
        boolean isMulti = false;
        try {
            while((line = br.readLine()) != null) {
                line = line.trim();
                /*处理多行
                * */
                if (isMulti){
                    if (line.endsWith("*/"))
                        isMulti = false;
                } else if(line.startsWith("/*")){
                    if (!line.endsWith("*/"))
                        isMulti = true;
                }
                else if (isValidLine(line))
                    ++res;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return res;
    }
    private boolean isValidLine(String line) {
        if("".equals(line) || line.startsWith("//"))
            return false;
        else
            return true;
    }
    private JButton openButton;
    private JButton countButton;
    private JTextField pathText;
    private JTextField countText;
}