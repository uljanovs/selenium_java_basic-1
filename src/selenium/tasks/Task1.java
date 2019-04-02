package selenium.tasks;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static java.lang.String.format;

import java.lang.Math;

import static org.junit.Assert.*;

public class Task1 {
    WebDriver driver;

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
    public void errorOnText() throws Exception {
//        TODO
//        enter a text instead of a number, check that correct error is seen
        driver.findElement(By.id("numb")).sendKeys("text");
        driver.findElement(By.className("w3-orange")).click();
        assertEquals("Please enter a number", driver.findElement(By.id("ch1_error")).getText());
    }

    @Test
    public void errorOnNumberTooSmall() {
//        TODO
//        enter number which is too small (below 50), check that correct error is seen
        driver.findElement(By.id("numb")).sendKeys("33");
        driver.findElement(By.className("w3-orange")).click();
        assertEquals("Number is too small", driver.findElement(By.id("ch1_error")).getText());
    }

    @Test
    public void errorOnNumberTooBig() {

//        BUG: if I enter number 666 no errors where seen
//        TODO
//        enter number which is too big (above 100), check that correct error is seen
        driver.findElement(By.id("numb")).sendKeys("133");
        driver.findElement(By.className("w3-orange")).click();
        assertEquals("Number is too big", driver.findElement(By.id("ch1_error")).getText());
    }

    @Test
    public void correctSquareRootWithoutRemainder() {
//        TODO
//        enter a number between 50 and 100 digit in the input (square root of which doesn't have a remainder, e.g. 2 is square root of 4),
//        then and press submit and check that correct no error is seen and check that square root is calculated correctly
        String input;
        input = "64";
        double inp = Integer.parseInt(input);
        String inpSquare = format("%.2f", (Math.sqrt(inp)));
        driver.findElement(By.id("numb")).sendKeys(input);
        driver.findElement(By.className("w3-orange")).click();
        Alert alert = driver.switchTo().alert();
        assertEquals("Square root of " + input + " is " + inpSquare, alert.getText());
        alert.accept();
        assertFalse(driver.findElement(By.id("ch1_error")).isDisplayed());
        assertEquals("", driver.findElement(By.id("ch1_error")).getText());
    }

    @Test
    public void correctSquareRootWithRemainder() {
//        TODO
//        enter a number between 50 and 100 digit in the input (square root of which doesn't have a remainder, e.g. 1.732.. is square root of 3) and press submit,
//        then check that correct no error is seen and check that square root is calculated correctly

        String input;
        input = "75";
        double inp = Integer.parseInt(input);
        String inpSquare = format("%.2f", (Math.sqrt(inp)));
        driver.findElement(By.id("numb")).sendKeys(input);
        driver.findElement(By.className("w3-orange")).click();
        Alert alert = driver.switchTo().alert();
        assertEquals("Square root of " + input + " is " + inpSquare, alert.getText());
        alert.accept();
        assertFalse(driver.findElement(By.id("ch1_error")).isDisplayed());
        assertEquals("", driver.findElement(By.id("ch1_error")).getText());
    }
}
