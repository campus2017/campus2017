package cn.EffectiveLines.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

/**
 * Created by 朱潇翔 on 2017/1/21.
 */
public class EffectiveMainFrame extends JFrame {
    private JButton startButton;
    private JButton chooseFile;
    private JTextField jTextField;
    private JLabel totalLabel;
    private JLabel spaceLabel;
    private JLabel comLabel;
    private JLabel effLabel;

    private File filename;

    private int totalLines; //总行数
    private int spaceLines; //空行
    private int comLines; //注释行

    public EffectiveMainFrame() {
        super();
        //初始化
        this.init();

        //添加组件
        this.addComponent();

        //为组件添加监听
        this.addButtonLister();

    }

    private void addButtonLister() {

        this.chooseFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("请选择文件");
                int returnVal = fileChooser.showOpenDialog(null);
                if(JFileChooser.APPROVE_OPTION == returnVal) {
                    filename = fileChooser.getSelectedFile();
                    String str = filename.toString();
                    if(!str.matches(".*\\.java")) {
                      try {
                            throw new Exception();
                        } catch (Exception e1) {
                          JOptionPane.showMessageDialog(null, "请选择java文件");
                      }
                    }
                    else {
                        jTextField.setText(str);
                    }
                    //System.out.println(filename);
                }
            }
        });



        this.startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)  {
                totalLines = 0;
                spaceLines = 0;
                comLines = 0;

                BufferedReader bufferedReader = null;
                try {
                    bufferedReader = new BufferedReader(new FileReader(filename));
                    String linetxt = null;
                    // \s匹配任何不可见字符，包括空格、制表符、换行符等
                    String spaceStr = "\\s*"; //匹配空行
                    String regxStr = "\\s*//.*"; //匹配注释行
                    while ((linetxt = bufferedReader.readLine()) != null) {
                        ++totalLines;
                        if (linetxt.matches(spaceStr)) {
                            ++spaceLines;
                        } else if (linetxt.matches(regxStr)) {
                            ++comLines;
                        }
                    }
                    bufferedReader.close();
                } catch (FileNotFoundException g) {
                    g.printStackTrace();
                } catch (IOException g) {
                    g.printStackTrace();
                }
                totalLabel.setText("总行数：" + totalLines);
                effLabel.setText("有效行数：" + (totalLines - spaceLines - comLines));
                spaceLabel.setText("空行数：" + spaceLines);
                comLabel.setText("注释行数：" + comLines);
            }

        });
    }

    private void addComponent() {
        //创建一个面板
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new GridLayout(6, 1));

        JPanel upPanel = new JPanel();
        upPanel.setLayout(new GridLayout(1, 2));
        JLabel chooseFileLabel = new JLabel("请选择java文件");
        this.chooseFile = new JButton("选择文件");
        upPanel.add(chooseFileLabel, BorderLayout.WEST);
        upPanel.add(this.chooseFile, BorderLayout.EAST);
        jPanel.add(upPanel, BorderLayout.NORTH);

        this.jTextField = new JTextField(37);
        this.jTextField.setEditable(false);
        this.totalLabel = new JLabel("总行数：");
        this.effLabel = new JLabel("有效行数：");
        this.comLabel = new JLabel("注释行数：");
        this.spaceLabel = new JLabel("空行数：");


        JPanel downPanel = new JPanel();
        downPanel.add(jTextField, BorderLayout.SOUTH);

        this.startButton = new JButton("统计");
        downPanel.add(this.startButton);

        jPanel.add(downPanel, BorderLayout.SOUTH);
        jPanel.add(this.totalLabel);
        jPanel.add(this.effLabel);
        jPanel.add(this.spaceLabel);
        jPanel.add(this.comLabel);
        //设置jPanel的显示位置
        this.add(jPanel, BorderLayout.NORTH);
    }

    private void init() {
        //设置标题
        this.setTitle("朱潇翔统计有效行");
        //设置窗口大小
        this.setSize(500, 300);
        //设置窗口显示位置
        this.setLocation(450, 150);
        //设置窗口为固定大小
        this.setResizable(false);
        //设置窗口默认关闭动作
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}
