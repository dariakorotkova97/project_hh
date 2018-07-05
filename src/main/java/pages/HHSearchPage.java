package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import selenium.LogicSelenium;

public class HHSearchPage {

    public HHSearchPage(ChromeDriver driver){
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
    public static ChromeDriver driver;
    public static Actions actions;

    @FindBy(xpath = ".//span[@class='bloko-icon-dynamic']")
    private WebElement closeButton;

    @FindBy(xpath = ".//input[@class='bloko-button']")
    private  WebElement searchButton;

    public void clickCloseButton() {
        LogicSelenium.wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//span[@class='bloko-icon-dynamic']")));
        closeButton.click();
    }

    public  void clickSearchButton(){
        LogicSelenium.wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//input[@class='bloko-button']")));
        searchButton.click();
    }

    public void inputSectionSkils(String value) {
        WebElement skilsField = (WebElement)((JavascriptExecutor) driver).
                executeScript("return this.$(\"input:visible\").get(1)");
        skilsField.sendKeys(value);
    }

    public void inputSectionSalary(String value1,String value2 ) {
        WebElement skilsField = (WebElement)((JavascriptExecutor) driver).
                executeScript("return this.$(\"input:visible\").get(4)");
        skilsField.sendKeys(value1);
        skilsField = (WebElement)((JavascriptExecutor) driver).
                executeScript("return this.$(\"input:visible\").get(5)");
        skilsField.sendKeys(value2);
    }

    public void selectShowSalary(){
        actions = new Actions(driver);
        WebElement element = (WebElement)((JavascriptExecutor) driver).
                executeScript("return this.$(\"input:visible\").get(6)");
        actions.moveToElement(element).click().build().perform();
    }

    public static void selectSectionExperience(int i){
        actions = new Actions(driver);
        WebElement element = (WebElement)((JavascriptExecutor) driver).
                executeScript("return this.$(\"input:visible\").get("+(i+6)+")");
        actions.moveToElement(element).click().build().perform();
    }

    public void selectDecreasingSalary(){
        actions = new Actions(driver);
        WebElement element = (WebElement)((JavascriptExecutor) driver).
                executeScript("return document.getElementsByClassName('bloko-radio__input')[5]");
        actions.moveToElement(element).click().build().perform();
    }

    public void selectRegion(String value){
        WebElement element = (WebElement)((JavascriptExecutor) driver).
                executeScript("return this.$(\"input:visible\").get(3)");
        element.sendKeys(value);
    }

}
