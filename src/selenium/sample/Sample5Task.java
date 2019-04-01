package selenium.sample;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
       driver.findElement(By.className("w3-blue")).click();
        //driver.findElement(By.xpath("//*[@onclick='goToAlertedPage()']")).click();
//        switch to alert
        Alert alert_1 = driver.switchTo().alert();
//        click ok
        alert_1.accept();
//        switch to second alert
        Alert alert_2 = driver.switchTo().alert();
//        verify alert text
        assertEquals("Booooooooo!",alert_2.getText());
//        click ok on second alert
        alert_2.accept();
//        verify that the correct page is opened
        assertTrue(driver.getCurrentUrl().equals("https://kristinek.github.io/site/examples/alerted_page"));
    }

    @Test
    public void doNotGoToAlertedPageViaButton() throws Exception {
//         TODO:
//        click on "To go to alerted page press Ok. Or stay here" button
        driver.findElement(By.className("w3-blue")).click();
//        switch to alert
        driver.switchTo().alert();
//        click cancel
        driver.switchTo().alert().dismiss();
//        verify the text on page
        assertTrue(driver.findElement(By.id("textForAlerts")).isDisplayed());
        assertEquals(driver.findElement(By.id("textForAlerts")).getText(), "So you desided to say? Good!");
    }
}
