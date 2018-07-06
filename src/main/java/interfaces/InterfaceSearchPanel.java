package interfaces;

import database.DataBase;
import selenium.LogicSelenium;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

import static com.google.common.base.Strings.isNullOrEmpty;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class InterfaceSearchPanel extends JPanel{

    public static JFrame resultFrame = new JFrame();
    public static String textSkils;
    public static int flag = 0;
    public static int countResume = 0;
    public static String minWage;
    public static String maxWage;

    public InterfaceSearchPanel(){
        setLayout(null);

        JLabel title = new JLabel("HeadHunter");
        title.setBounds(180,30, 200, 35);
        title.setFont(new Font("Serif",Font.BOLD,30));
        add(title);

        final JLabel skilsLabel = new JLabel("Ключевые слова");
        skilsLabel.setBounds(50,127, 200, 30);
        skilsLabel.setFont(new Font("Serif",Font.PLAIN,20));
        add(skilsLabel);

        JLabel experienceLabel = new JLabel("Опыт");
        experienceLabel.setBounds(50,187, 100, 30);
        experienceLabel.setFont(new Font("Serif",Font.PLAIN,20));
        add(experienceLabel);

        final TextField skilsText = new TextField();
        skilsText.setBounds(250,130,180,30);
        add(skilsText);

        ButtonGroup group = new ButtonGroup();
        final JRadioButton skilsRB1 = new JRadioButton("нет опыта", true);
        skilsRB1.setBounds(250,187, 190, 30);
        skilsRB1.setFont(new Font("Serif",Font.PLAIN,16));
        add(skilsRB1);
        group.add(skilsRB1);

        final JRadioButton skilsRB2 = new JRadioButton("от 1 года до 3 лет", false);
        skilsRB2.setBounds(250,217, 190, 30);
        skilsRB2.setFont(new Font("Serif",Font.PLAIN,16));
        add(skilsRB2);
        group.add(skilsRB2);

        final JRadioButton skilsRB3 = new JRadioButton("от 3 лет до 6 лет", false);
        skilsRB3.setBounds(250,247, 190, 30);
        skilsRB3.setFont(new Font("Serif",Font.PLAIN,16));
        add(skilsRB3);
        group.add(skilsRB3);

        final JRadioButton skilsRB4 = new JRadioButton("более 6 лет", false);
        skilsRB4.setBounds(250,277, 190, 30);
        skilsRB4.setFont(new Font("Serif",Font.PLAIN,16));
        add(skilsRB4);
        group.add(skilsRB4);

        JLabel salaryLabel = new JLabel("Зарплата");
        salaryLabel.setBounds(50,320, 90, 30);
        salaryLabel.setFont(new Font("Serif",Font.PLAIN,20));
        add(salaryLabel);

        JLabel showLabel = new JLabel("Количество работников");
        showLabel.setBounds(50,390, 210, 30);
        showLabel.setFont(new Font("Serif",Font.PLAIN,20));
        add(showLabel);

        JLabel fromLabel = new JLabel("от");
        fromLabel.setBounds(250,320, 20, 30);
        fromLabel.setFont(new Font("Serif",Font.PLAIN,20));
        add(fromLabel);

        final TextField salaryTextFrom = new TextField();
        salaryTextFrom.setBounds(280,320,60,30);
        add(salaryTextFrom);

        JLabel toLabel = new JLabel("до");
        toLabel.setBounds(360,320, 20, 30);
        toLabel.setFont(new Font("Serif",Font.PLAIN,20));
        add(toLabel);

        final TextField salaryTextTo = new TextField();
        salaryTextTo.setBounds(390,320,60,30);
        add(salaryTextTo);

        final TextField showText = new TextField();
        showText.setBounds(280,390,60,30);
        add(showText);

        JButton search = new JButton("Найти");
        search.setBounds(330,470, 120, 50);
        search.setFont(new Font("Serif",Font.PLAIN,20));
        add(search);

        search.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    remove(error);
                    updateUI();
                    if (isNullOrEmpty(skilsText.getText()) || isNullOrEmpty(showText.getText())
                            || isNullOrEmpty(salaryTextFrom.getText()) || isNullOrEmpty(salaryTextTo.getText()) ){
                        getErrorMassage("Заполните все поля!");
                        return;
                    }
                    minWage = salaryTextFrom.getText();
                    maxWage = salaryTextTo.getText();
                    textSkils = skilsText.getText();

                    if (skilsRB1.isSelected()) flag =1;
                    if (skilsRB2.isSelected()) flag =2;
                    if (skilsRB3.isSelected()) flag =3;
                    if (skilsRB4.isSelected()) flag =4;
                    if ((!skilsRB1.isSelected()) && (!skilsRB2.isSelected())
                            &&(!skilsRB3.isSelected()) && (!skilsRB4.isSelected())){
                            getErrorMassage("Выберите опыт!");
                        return;
                    }

                    if (validatePattern(maxWage, Pattern.compile("[0-9]+"))
                            || validatePattern(minWage, Pattern.compile("[0-9]+"))
                            || validatePattern(showText.getText(), Pattern.compile("[0-9]+"))) {
                        getErrorMassage("<html>Зарплата и работники - это числовые, целые, неотрицательные значения.</html>");
                        return;
                    }

                    if (validatePattern(textSkils, Pattern.compile("^[а-яА-ЯёЁa-zA-Z0-9\\s]+$"))){
                        getErrorMassage("<html>Недопустимы символы в поле \"Ключевые слова\".</html>");
                        return;
                    }
                    if ((minWage.charAt(0) == '0') || (maxWage.charAt(0) == '0')
                            || (showText.getText().charAt(0) == '0')){
                        getErrorMassage("<html>Не может зарплата или количество начинаться на 0!!!!</html>");
                        return;
                    }

                    if (Integer.valueOf(minWage) > Integer.valueOf(maxWage)) {
                        getErrorMassage("Введите корректный диапазон зарплаты!");
                        return;
                    }
                    countResume = Integer.parseInt(showText.getText());
                    if (countResume > 15){
                        getErrorMassage("<html>Наша программа выводит<br> не более 15 резюме!</br>");
                        return;
                    }
                    JPanel panelResume =new InterfaceResultPanel();
                    resultFrame.setBounds(200,30,670,630);
                    resultFrame.setTitle("Результаты поиска");
                    resultFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
                    resultFrame.add(InterfaceResultPanel.panelResume);
                    resultFrame.setResizable(false);
                    LogicSelenium.findResume();
                    resultFrame.setVisible(true);
                    DataBase.connectDateBase();
                    DataBase.createDataBase();

                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        });


    }
    public JLabel error = new JLabel();
    public void getErrorMassage(String errorMassage){
        remove(error);
        error.setText(errorMassage);
        error.setBounds(50,470, 270, 60);
        error.setFont(new Font("Serif",Font.PLAIN,15));
        error.setForeground(Color.RED);
        add(error);
        updateUI();

    }

    public static boolean validatePattern(String str, Pattern pattern) {
        return !pattern.matcher(str).matches();
    }

}
