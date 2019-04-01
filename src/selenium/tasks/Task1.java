package selenium.tasks;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import static java.lang.Math.sqrt;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.fail;

public class Task1 {

    private WebDriver driver;

    @Before
    public void openPage() {

        String libWithDriversLocation = System.getProperty("user.dir") + "\\lib\\";
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://kristinek.github.io/site/tasks/enter_a_number");
    }

    @After
    public void closeBrowser() {
        driver.quit();
    }

    @Test
    public void errorOnText() {
//        enter a text instead of a number, check that correct error is seen
        driver.findElement(By.id("numb")).sendKeys("five");
        driver.findElement(By.xpath("//button[text()='Submit']")).click();
        assertEquals("rgba(255, 221, 221, 1)", driver.findElement(By.id("numb")).getCssValue("background-color"));
        assertEquals("Please enter a number", driver.findElement(By.id("ch1_error")).getText());
    }

    @Test
    public void errorOnNumberTooSmall() {
//        "BUG": if I enter number 13 color is red instead of pale red
//        enter number which is too small (below 50), check that correct error is seen
        driver.findElement(By.id("numb")).sendKeys("48");
        driver.findElement(By.xpath("//button[text()='Submit']")).click();
        try{
            driver.switchTo().alert();
            fail("Alert should not be provoked here");
        } catch (NoAlertPresentException e){
            // NoAlertPresentException exception is expected
        }
        assertEquals("rgba(255, 221, 221, 1)", driver.findElement(By.id("numb")).getCssValue("background-color"));
        assertEquals("Number is too small", driver.findElement(By.id("ch1_error")).getText());

        driver.navigate().refresh();

        driver.findElement(By.id("numb")).sendKeys("-10");
        driver.findElement(By.xpath("//button[text()='Submit']")).click();
        try{
            driver.switchTo().alert();
            fail("Alert should not be provoked here");
        } catch (NoAlertPresentException e){
            // NoAlertPresentException exception is expected
        }
        assertEquals("rgba(255, 221, 221, 1)", driver.findElement(By.id("numb")).getCssValue("background-color"));
        assertEquals("", driver.findElement(By.id("ch1_error")).getText());
    }

    @Test
    public void errorOnNumberTooSmallOn49() {
//        BUG: this is a bug, answer for 49 is calculated
//        enter number which is too small (below 50), check that correct error is seen
        driver.findElement(By.id("numb")).sendKeys("49");
        driver.findElement(By.xpath("//button[text()='Submit']")).click();
        try{
            driver.switchTo().alert();
            fail("Alert should not be provoked here");
        } catch (NoAlertPresentException e){
            // NoAlertPresentException exception is expected
        }
        assertEquals("rgba(255, 221, 221, 1)", driver.findElement(By.id("numb")).getCssValue("background-color"));
        assertEquals("Please enter a number", driver.findElement(By.id("ch1_error")).getText());
    }

    @Test
    public void errorOnNumberTooBig() {
//        "BUG": if I enter number 666 no errors where seen
//        enter number which is too big (above 100), check that correct error is seen
        driver.findElement(By.id("numb")).sendKeys("101");
        driver.findElement(By.xpath("//button[text()='Submit']")).click();
        try{
            driver.switchTo().alert();
            fail("Alert should not be provoked here");
        } catch (NoAlertPresentException e){
            // NoAlertPresentException exception is expected
        }
        assertEquals("rgba(255, 221, 221, 1)", driver.findElement(By.id("numb")).getCssValue("background-color"));
        assertEquals("Number is too big", driver.findElement(By.id("ch1_error")).getText());
    }

    @Test
    public void correctSquareRootWithoutRemainder() {
//        enter a number between 50 and 100 digit in the input (square root of which doesn't have a remainder, e.g. 2 is square root of 4),
//        then and press submit and check that correct no error is seen and check that square root is calculated correctly
        driver.findElement(By.id("numb")).sendKeys("64");
        driver.findElement(By.xpath("//button[text()='Submit']")).click();
        Alert alert = driver.switchTo().alert();
        Assert.assertEquals("Square root of " + 64 + " is 8.00", alert.getText());
        alert.accept();
        assertEquals("rgba(241, 241, 241, 1)", driver.findElement(By.id("numb")).getCssValue("background-color"));
        assertEquals("", driver.findElement(By.id("numb")).getAttribute("value"));
        assertFalse(driver.findElement(By.id("ch1_error")).isDisplayed());
    }

    @Test
    public void correctSquareRootWithRemainder() {
//        enter a number between 50 and 100 digit in the input (square root of which doesn't have a remainder, e.g. 1.732.. is square root of 3) and press submit,
//        then check that correct no error is seen and check that square root is calculated correctly
        driver.findElement(By.id("numb")).sendKeys("70");
        driver.findElement(By.xpath("//button[text()='Submit']")).click();
        Alert alert = driver.switchTo().alert();
        Assert.assertEquals("Square root of " + 70 + " is " + String.format("%.2f", sqrt(70)), alert.getText());
        alert.accept();
        assertEquals("rgba(241, 241, 241, 1)", driver.findElement(By.id("numb")).getCssValue("background-color"));
        assertEquals("", driver.findElement(By.id("numb")).getAttribute("value"));
        assertFalse(driver.findElement(By.id("ch1_error")).isDisplayed());
    }

    @Test
    public void iSeeYourEasterEggs() {
        driver.findElement(By.id("numb")).sendKeys("42");
        driver.findElement(By.xpath("//button[text()='Submit']")).click();
        Alert alert = driver.switchTo().alert();
        Assert.assertEquals("Sorry you have asked the wrong answer", alert.getText());
        alert.accept();
        assertEquals("rgba(241, 241, 241, 1)", driver.findElement(By.id("numb")).getCssValue("background-color"));
        assertEquals("", driver.findElement(By.id("numb")).getAttribute("value"));
        assertFalse(driver.findElement(By.id("ch1_error")).isDisplayed());

        driver.navigate().refresh();

        driver.findElement(By.id("numb")).sendKeys("bug");
        driver.findElement(By.xpath("//button[text()='Submit']")).click();
        assertEquals("rgba(255, 221, 221, 1)", driver.findElement(By.id("numb")).getCssValue("background-color"));
        assertEquals("Yes, this form has 6 features, which some people call bugs you just found 1",
                driver.findElement(By.id("ch1_error")).getText());

        driver.navigate().refresh();

        driver.findElement(By.id("numb")).sendKeys("666");
        driver.findElement(By.xpath("//button[text()='Submit']")).click();
        assertEquals("rgba(241, 241, 241, 1)", driver.findElement(By.id("numb")).getCssValue("background-color"));
        assertFalse(driver.findElement(By.id("ch1_error")).isDisplayed());

        driver.navigate().refresh();

        driver.findElement(By.id("numb")).sendKeys("13");
        driver.findElement(By.xpath("//button[text()='Submit']")).click();
        assertEquals("rgba(244, 67, 54, 1)", driver.findElement(By.id("numb")).getCssValue("background-color"));
        assertEquals("Number is too small", driver.findElement(By.id("ch1_error")).getText());
    }
}
