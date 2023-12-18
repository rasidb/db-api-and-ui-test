package com.ui;

import com.utilities.BrowserUtils;
import com.utilities.Driver;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class FastFingersAutomation {
    private By byWordsList;
    private By byInput;
    private By byCloseCookie;
    WebDriverWait wait;
    private By bySonuc;

    @BeforeTest
    public void init() {
        wait = new WebDriverWait(Driver.getDriver(), 10);
        Driver.getDriver().get("https://10fastfingers.com/typing-test/turkish");
        byWordsList = By.xpath("//div[@id='row1']/span");
        byInput = By.id("inputfield");
        byCloseCookie = By.id("CybotCookiebotDialogBodyButtonDecline");
        bySonuc = By.xpath("//td[@id='wpm']/strong");
    }

    @Test
    public void automation() {
        Driver.getDriver().manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        try{
            WebElement closeCookie = Driver.getDriver().findElement(byCloseCookie);
            wait.until(ExpectedConditions.visibilityOf(closeCookie));
            BrowserUtils.scrollByJs(closeCookie);
            closeCookie.click();
        }catch (NoSuchElementException e){

        }
        List<WebElement> wordsList = Driver.getDriver().findElements(byWordsList);
        WebElement input = Driver.getDriver().findElement(byInput);
        for (int i = 0; i < wordsList.size(); i++) {
            input.sendKeys(wordsList.get(i).getText() + " ");
        }
        WebElement timer;
        while (true) {
            try {
                timer = Driver.getDriver().findElement(By.id("timer"));
                if (timer.getText().equals("0:00")) {
                    break;
                }
            } catch (NoSuchElementException e) {
                break;
            } catch (UnhandledAlertException e) {
                try {
                    Alert alert = Driver.getDriver().switchTo().alert();
                    alert.accept();
                } catch (NoAlertPresentException ex) {
                }
            }
            BrowserUtils.wait(0.5);
        }

        Driver.getDriver().manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        WebElement sonuc = Driver.getDriver().findElement(bySonuc);

        System.out.println("test başarılı skorunuz: " + sonuc.getText());
    }

    @AfterTest
    public void shutDown() {
        Driver.closeDriver();
    }

}
