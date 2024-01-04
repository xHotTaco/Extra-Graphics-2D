package animation2;

import javax.swing.*;

public class MyWindow extends JFrame {
    public MyWindow(){
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setLocationRelativeTo(null);
        setSize(800,800);
        MyPanel panel = new MyPanel();
        add(panel);
        panel.startAnimation();
        setVisible(true);
    }

}
