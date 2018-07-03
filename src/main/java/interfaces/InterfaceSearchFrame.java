package interfaces;

import javax.swing.*;

public class InterfaceSearchFrame extends JFrame{

    public static JFrame frame = new JFrame();

    public InterfaceSearchFrame(){
        frame.setBounds(400,30,500,600);
        frame.setTitle("HeadHunter");
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.add(new InterfaceSearchPanel());
        frame.setVisible(true);
        frame.setResizable(false);
    }
}

