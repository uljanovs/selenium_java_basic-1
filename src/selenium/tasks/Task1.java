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

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

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

        String sendKeys1 = "abc";
        WebElement textInput = driver.findElement(By.id("numb"));
        WebElement submittbutton = driver.findElement(By.className("w3-btn"));

        //enter a text instead of a number
        textInput.sendKeys(sendKeys1);
        submittbutton.click();

        // check that correct error is seen
        assertEquals("Please enter a number", driver.findElement(By.id("ch1_error")).getText());

    }

    @Test
    public void errorOnNumberTooSmall() {
//        TODO
        String sendKeys1 = "12";
        WebElement textInput = driver.findElement(By.id("numb"));
        WebElement submittbutton = driver.findElement(By.className("w3-btn"));

//        enter number which is too small (below 50), check that correct error is seen 'Number is too small'
        textInput.sendKeys(sendKeys1);
        submittbutton.click();
        assertEquals("Number is too small", driver.findElement(By.id("ch1_error")).getText());

    }

    @Test
    public void errorOnNumberTooBig() {

//        BUG: if I enter number 666 no errors where seen
//        TODO
        String sendKeys1 = "120";
        WebElement textInput = driver.findElement(By.id("numb"));
        WebElement submittbutton = driver.findElement(By.className("w3-btn"));

//        enter number which is too big (above 100), check that correct error is seen
        textInput.sendKeys(sendKeys1);
        submittbutton.click();
        assertEquals("Number is too big", driver.findElement(By.id("ch1_error")).getText());
    }

    @Test
    public void correctSquareRootWithoutRemainder() {

        String sendKeys1 = "81";
        WebElement textInput = driver.findElement(By.id("numb"));
        WebElement submittbutton = driver.findElement(By.className("w3-btn"));

//        TODO
//        enter a number between 50 and 100 digit in the input (square root of which doesn't have a remainder, e.g. 2 is square root of 4),
//        then and press submit and check that correct no error is seen and check that square root is calculated correctly
        textInput.sendKeys(sendKeys1);

        submittbutton.click();

        Alert alert = driver.switchTo().alert();

        assertEquals("Square root of 81 is 9.00", alert.getText());

        alert.accept();

        assertFalse(driver.findElement(By.id("ch1_error")).isDisplayed());

        //check message - easy
        //how to calculate rootsquare using java
    }

    @Test
    public void correctSquareRootWithoutRemainder2() {

        String sendKeys1 = "81";
        double sendKeysint = 81;
        double square = Math.sqrt(sendKeysint);
        WebElement textInput = driver.findElement(By.id("numb"));
        WebElement submittbutton = driver.findElement(By.className("w3-btn"));

//        TODO
//        enter a number between 50 and 100 digit in the input (square root of which doesn't have a remainder, e.g. 2 is square root of 4),
//        then and press submit and check that correct no error is seen and check that square root is calculated correctly
        textInput.sendKeys(sendKeys1);

        submittbutton.click();

        Alert alert = driver.switchTo().alert();

        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        String numberAsString = decimalFormat.format(square);

        assertEquals("Square root of " + sendKeys1 + " is " + numberAsString, alert.getText());

        alert.accept();

        assertFalse(driver.findElement(By.id("ch1_error")).isDisplayed());
    }

        @Test
    public void correctSquareRootWithRemainder() {

        String sendKeys1 = "80";
        WebElement textInput = driver.findElement(By.id("numb"));
        WebElement submittbutton = driver.findElement(By.className("w3-btn"));

//        TODO
//        enter a number between 50 and 100 digit in the input (square root of which doesn't have a remainder, e.g. 1.732.. is square root of 3) and press submit,
//        then check that correct no error is seen and check that square root is calculated correctly

        textInput.sendKeys(sendKeys1);

        submittbutton.click();

        Alert alert = driver.switchTo().alert();

        assertEquals("Square root of 80 is 8.94", alert.getText());

        alert.accept();

        assertFalse(driver.findElement(By.id("ch1_error")).isDisplayed());
    }

    // after click ok - there is no error message
    @Test
    public void correctSquareRootWithRemainder2() {

        String sendKeys1 = "80";
        double sendKeysint = 80;
        double square = Math.sqrt(sendKeysint);
        WebElement textInput = driver.findElement(By.id("numb"));
        WebElement submittbutton = driver.findElement(By.className("w3-btn"));

//        TODO
//        enter a number between 50 and 100 digit in the input (square root of which doesn't have a remainder, e.g. 1.732.. is square root of 3) and press submit,
//        then check that correct no error is seen and check that square root is calculated correctly

        textInput.sendKeys(sendKeys1);

        submittbutton.click();

        Alert alert = driver.switchTo().alert();

        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        String numberAsString = decimalFormat.format(square);

        assertEquals("Square root of " + sendKeys1 +" is " + numberAsString, alert.getText());


        alert.accept();

        assertFalse(driver.findElement(By.id("ch1_error")).isDisplayed());
    }

}
