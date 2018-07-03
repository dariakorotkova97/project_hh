package pages;

import interfaces.InterfaceResultPanel;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HHResultResumePage {

    public static List<WebElement> resumeItem;
    public static ArrayList<String> url;
    public static ArrayList<JLabel> labels;
    public static ArrayList<String>  idHH;
    public static ArrayList<JButton> button;

    public HHResultResumePage(ChromeDriver driver){
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
    public static ChromeDriver driver;

    public static void getResume(int countResume) throws InterruptedException {
        resumeItem = (List<WebElement>) ((JavascriptExecutor) driver)
                .executeScript("return document.getElementsByClassName('search-item-name')");
        labels = new ArrayList<JLabel>();
        url = new ArrayList<String>();
        button = new ArrayList<JButton>();
        int shiftY=70;


        for (int i=0; i<countResume; i++) {
                labels.add(new JLabel(resumeItem.get(i).getText()));
                labels.get(i).setBounds(40, shiftY, 430, 30);
                InterfaceResultPanel.panelResume.add(labels.get(i));

                url.add(resumeItem.get(i).getAttribute("href"));
                button.add(new JButton("Перейти"));
                button.get(i).setBounds(520, shiftY, 100, 20);

                final int finalI = i;
                button.get(i).addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent ec) {
                        try {
                            Desktop.getDesktop().browse(java.net.URI.create(url.get(finalI)));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
                shiftY = shiftY + 30;
                InterfaceResultPanel.panelResume.add(button.get(i));
            }
        getIdResume();

    }

    public static void getIdResume(){
        idHH = new ArrayList<String>();
        for (int i=0; i<url.size(); i++){
            idHH.add(url.get(i).substring(url.get(i).indexOf("e/")+2,url.get(i).indexOf('?')));
        }
    }



}