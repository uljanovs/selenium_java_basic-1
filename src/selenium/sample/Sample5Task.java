package selenium.sample;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertEquals;

public class Sample5Task {
    WebDriver driver;

    // method which is being run before each test
    @Before
    public void startingTests() throws Exception {
        // from Sample 1:
        String libWithDriversLocation = System.getProperty("user.dir") + "\\lib\\";
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver.exe");
        // declaration above:
        driver = new ChromeDriver();
        //open page:
        driver.get("https://kristinek.github.io/site/examples/alerts_popups");
    }

    // method which is being run after each test
    @After
    public void endingTests() throws Exception {
        driver.quit();
    }

    @Test
    public void goToAlertedPageViaButton() throws Exception {
//         TODO:
//        click on "To go to alerted page press Ok. Or stay here" button
        WebElement navigateButton = driver.findElement(By.className("w3-blue"));
        navigateButton.click();

//        switch to alert
        Alert alert = driver.switchTo().alert();

//        click ok
        alert.accept();

//        switch to second alert
        alert = driver.switchTo().alert();

//        verify alert text
        String alertText = "Booooooooo!";
        assertEquals(alertText, alert.getText());

//        click ok on second alert
        alert.accept();

//        verify that the correct page is opened
        String alertedPageURL = "https://kristinek.github.io/site/examples/alerted_page";
        assertEquals(alertedPageURL, driver.getCurrentUrl());

    }

    @Test
    public void doNotGoToAlertedPageViaButton() throws Exception {
//         TODO:
        WebElement textForAlerts = driver.findElement(By.id("textForAlerts"));
        String expectedTextMessage = "So you desided to say? Good!";

//        click on "To go to alerted page press Ok. Or stay here" button
        WebElement navigateButton = driver.findElement(By.className("w3-blue"));
        navigateButton.click();

//        switch to alert
        Alert alert = driver.switchTo().alert();

//        click cancel
        alert.dismiss();

//        verify the text on page
        assertEquals(expectedTextMessage, textForAlerts.getText());
    }
}
