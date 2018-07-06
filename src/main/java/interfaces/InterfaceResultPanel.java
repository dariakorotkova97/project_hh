package interfaces;

import database.DataBase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;

public class InterfaceResultPanel extends JPanel {

    public static JPanel panelResume = new JPanel();

    public InterfaceResultPanel(){
        panelResume.removeAll();
        panelResume.setLayout(null);
        JButton buttonSave;
        JButton buttonShowAll;
        panelResume.setBounds(50, 50, 400,500);
        JLabel title = new JLabel("Список подходящих резюме");
        title.setBounds(180,30, 300, 30);
        title.setFont(new Font("Serif",Font.BOLD,20));
        panelResume.add(title);

        buttonSave = new JButton("Cохранить");
        buttonSave.setBounds(60,540, 120, 30);
        buttonShowAll = new JButton("Выгрузить сохранённые резюме");
        buttonShowAll.setBounds(360,540, 240, 30);
        panelResume.add(buttonSave);
        panelResume.add(buttonShowAll);

        buttonSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DataBase.insertDataBase();
            }
        });

        buttonShowAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    DataBase.saveListResume();
                } catch (IOException e1) {
                    e1.printStackTrace();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });


    }


}

