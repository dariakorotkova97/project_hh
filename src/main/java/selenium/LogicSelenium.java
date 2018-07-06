package selenium;

import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.HHResultResumePage;
import pages.HHSearchPage;
import interfaces.InterfaceSearchPanel;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class LogicSelenium {

    public static HHSearchPage hhSearchPage;
    public static HHResultResumePage hhResultResumePage;
    public static ChromeOptions options;
    public static ChromeDriver chromeDriver;
    public static WebDriverWait wait;

    public static void findResume() throws InterruptedException{
        System.setProperty("webdriver.chrome.driver", ".//chromedriver.exe");
        options = new ChromeOptions();
        options.addArguments("--headless");

        chromeDriver = new ChromeDriver(options);
        wait = new WebDriverWait(chromeDriver, 10);
        hhSearchPage = new HHSearchPage(chromeDriver);
        hhResultResumePage = new HHResultResumePage(chromeDriver);
        //chromeDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
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

