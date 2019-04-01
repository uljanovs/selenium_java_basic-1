package selenium.tasks;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.text.DecimalFormat;

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
    public void errorOnText() {
//        TODO
//        enter a text instead of a number, check that correct error is seen
        WebElement numField = driver.findElement(By.id("numb"));
        numField.sendKeys("abc");
        WebElement button = driver.findElement(By.className("w3-btn"));
        button.click();

        WebElement errorText = driver.findElement(By.id("ch1_error"));
        String errorMessage = "Please enter a number";
        assertEquals(errorMessage, errorText.getText());

    }

    @Test
    public void errorOnNumberTooSmall() {
//        TODO
//        enter number which is too small (below 50), check that correct error is seen

        WebElement numField = driver.findElement(By.id("numb"));
        int testNumInt = 32;
        String testNum = Integer.toString(testNumInt);
        numField.sendKeys(testNum);
        WebElement button = driver.findElement(By.className("w3-btn"));
        button.click();

        WebElement errorText = driver.findElement(By.id("ch1_error"));
        String errorMessage = "Number is too small";
        assertEquals(errorMessage, errorText.getText());
    }

    @Test
    public void errorOnNumberTooBig() {

//        BUG: if I enter number 666 no errors where seen
//        TODO
//        enter number which is too big (above 100), check that correct error is seen
        WebElement numField = driver.findElement(By.id("numb"));
        int testNumInt = 351;
        String testNum = Integer.toString(testNumInt);
        numField.sendKeys(testNum);
        WebElement button = driver.findElement(By.className("w3-btn"));
        button.click();

        WebElement errorText = driver.findElement(By.id("ch1_error"));
        String errorMessage = "Number is too big";
        assertEquals(errorMessage, errorText.getText());

    }

    @Test
    public void correctSquareRootWithoutRemainder() {
//        TODO
//        enter a number between 50 and 100 digit in the input (square root of which doesn't have a remainder, e.g. 2 is square root of 4),
//        then and press submit and check that correct no error is seen and check that square root is calculated correctly
        WebElement numField = driver.findElement(By.id("numb"));
        int testNumInt = 64;
        String testNum = Integer.toString(testNumInt);
        numField.sendKeys(testNum);
        WebElement button = driver.findElement(By.className("w3-btn"));
        button.click();

        double result = Math.sqrt(testNumInt);
        DecimalFormat df = new DecimalFormat("#.00");
        String resultTwoDecimal = df.format(result);

        Alert alert = driver.switchTo().alert();
        String allertMessage = "Square root of " + testNumInt + " is " + resultTwoDecimal;
        assertEquals(allertMessage, alert.getText());

    }

    @Test
    public void correctSquareRootWithRemainder() {
//        TODO
//        enter a number between 50 and 100 digit in the input (square root of which doesn't have a remainder, e.g. 1.732.. is square root of 3) and press submit,
//        then check that correct no error is seen and check that square root is calculated correctly

        WebElement numField = driver.findElement(By.id("numb"));
        int testNumInt = 68;
        String testNum = Integer.toString(testNumInt);
        numField.sendKeys(testNum);
        WebElement button = driver.findElement(By.className("w3-btn"));
        button.click();

        double result = Math.sqrt(testNumInt);
        DecimalFormat df = new DecimalFormat("#.00");
        String resultTwoDecimal = df.format(result);

        Alert alert = driver.switchTo().alert();
        String allertMessage = "Square root of " + testNumInt + " is " + resultTwoDecimal;
        assertEquals(allertMessage, alert.getText());
    }
}
