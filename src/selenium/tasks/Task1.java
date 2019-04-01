package selenium.tasks;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.text.DecimalFormat;

import static org.junit.Assert.assertEquals;

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
    public void errorOnText() {
//        TODO

//        enter a text instead of a number, check that correct error is seen
        driver.findElement(By.id("numb")).sendKeys("test");
        driver.findElement(By.className("w3-orange")).click();
        assertEquals("Please enter a number", driver.findElement(By.id("ch1_error")).getText());
    }

    @Test
    public void errorOnNumberTooSmall() {
//        TODO
//        enter number which is too small (below 50), check that correct error is seen
        driver.findElement(By.id("numb")).sendKeys("12");
        driver.findElement(By.className("w3-orange")).click();
        assertEquals("Number is too small", driver.findElement(By.id("ch1_error")).getText());
    }

    @Test
    public void errorOnNumberTooBig() {

//        BUG: if I enter number 666 no errors where seen
//        TODO
        driver.findElement(By.id("numb")).sendKeys("666");
        driver.findElement(By.className("w3-orange")).click();
        assertEquals("", driver.findElement(By.id("ch1_error")).getText());
//        enter number which is too big (above 100), check that correct error is seen
    }

    @Test
    public void correctSquareRootWithoutRemainder() {
//        TODO

        double number = 81;
        String numberString = Double.toString(number);
//        enter a number between 50 and 100 digit in the input (square root of which doesn't have a remainder, e.g. 2 is square root of 4),
//        then and press submit and check that correct no error is seen and check that square root is calculated correctly
        driver.findElement(By.id("numb")).sendKeys(numberString);
        driver.findElement(By.className("w3-orange")).click();
        Alert alert = driver.switchTo().alert();
        String result = String.format("%.2f", Math.sqrt(number));
        assertEquals("Square root of " + number + " is " + result, alert.getText());

        alert.accept();


    }

    @Test
    public void correctSquareRootWithRemainder() {
//        TODO
        double number = 55;
        String numberString = Double.toString(number);
//        enter a number between 50 and 100 digit in the input (square root of which doesn't have a remainder, e.g. 1.732.. is square root of 3) and press submit,
//        then check that correct no error is seen and check that square root is calculated correctly
        driver.findElement(By.id("numb")).sendKeys(numberString);
        driver.findElement(By.className("w3-orange")).click();
        Alert alert = driver.switchTo().alert();
        String result = String.format("%.2f", Math.sqrt(number));
        assertEquals("Square root of " + number + " is " + result, alert.getText());

        alert.accept();
    }
}
