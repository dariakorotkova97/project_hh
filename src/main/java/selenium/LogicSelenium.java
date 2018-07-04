package selenium;

import pages.HHResultResumePage;
import pages.HHSearchPage;
import interfaces.InterfaceSearchPanel;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.concurrent.TimeUnit;

public class LogicSelenium {

    public static HHSearchPage hhSearchPage;
    public static HHResultResumePage hhResultResumePage;
    public static ChromeOptions options;
    public static ChromeDriver chromeDriver;

    public static void findResume() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C://chromedriver/chromedriver.exe");
        options = new ChromeOptions();
        options.addArguments("--headless");
        chromeDriver = new ChromeDriver(options);
        hhSearchPage = new HHSearchPage(chromeDriver);
        hhResultResumePage = new HHResultResumePage(chromeDriver);
        chromeDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        chromeDriver.get("https://hh.ru/search/resume/advanced");
        hhSearchPage.clickCloseButton();
        hhSearchPage.inputSectionSkils(InterfaceSearchPanel.textSkils);
        hhSearchPage.inputSectionSalary(InterfaceSearchPanel.minWage, InterfaceSearchPanel.maxWage);
        hhSearchPage.selectShowSalary();
        hhSearchPage.selectSectionExperience(InterfaceSearchPanel.flag);
        hhSearchPage.selectDecreasingSalary();
        hhSearchPage.selectRegion("Россия");
        hhSearchPage.clickSearchButton();
        hhResultResumePage.getResume(InterfaceSearchPanel.countResume);
    }

}

