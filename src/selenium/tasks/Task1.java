package selenium.tasks;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static java.lang.String.format;
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
        WebElement inputField = driver.findElement(By.id("numb"));
        WebElement submitButton = driver.findElement(By.className("w3-orange"));

//        TODO
//        enter a text instead of a number, check that correct error is seen
        inputField.sendKeys("Not a number.");
        submitButton.click();
        assertEquals("Please enter a number", driver.findElement(By.id("ch1_error")).getText());
    }

    @Test
    public void errorOnNumberTooSmall() {
        WebElement inputField = driver.findElement(By.id("numb"));
        WebElement submitButton = driver.findElement(By.className("w3-orange"));

//        TODO
//        enter number which is too small (below 50), check that correct error is seen
        inputField.sendKeys("1");
        submitButton.click();
        assertEquals("Number is too small", driver.findElement(By.id("ch1_error")).getText());
    }

    @Test
    public void errorOnNumberTooBig() {
        WebElement inputField = driver.findElement(By.id("numb"));
        WebElement submitButton = driver.findElement(By.className("w3-orange"));


//        BUG: if I enter number 666 no errors where seen
//        TODO
//        enter number which is too big (above 100), check that correct error is seen
        inputField.sendKeys("101");
        submitButton.click();
        assertEquals("Number is too big", driver.findElement(By.id("ch1_error")).getText());

        inputField.sendKeys("666");
        submitButton.click();
        assertFalse("".equals(driver.findElement(By.id("ch1_error")).getText()));
    }

    @Test
    public void correctSquareRootWithoutRemainder() {
        WebElement inputField = driver.findElement(By.id("numb"));
        WebElement submitButton = driver.findElement(By.className("w3-orange"));
        String myNumber = "49";
        double myIntNumber = Integer.parseInt(myNumber);
        String mySqrt = format("%.2f", Math.sqrt(myIntNumber));


//        TODO
//        enter a number between 50 and 100 digit in the input (square root of which doesn't have a remainder, e.g. 2 is square root of 4),
//        then and press submit and check that correct no error is seen and check that square root is calculated correctly
        inputField.sendKeys(myNumber);
        submitButton.click();
        Alert alert = driver.switchTo().alert();
        assertEquals("Square root of " + myNumber + " is " + mySqrt, alert.getText());
        alert.accept();
        assertEquals("", driver.findElement(By.id("ch1_error")).getText());
    }

    @Test
    public void correctSquareRootWithRemainder() {
        WebElement inputField = driver.findElement(By.id("numb"));
        WebElement submitButton = driver.findElement(By.className("w3-orange"));
        String myNumber = "66";
        double myIntNumber = Integer.parseInt(myNumber);
        String mySqrt = format("%.2f", Math.sqrt(myIntNumber));

//        TODO
//        enter a number between 50 and 100 digit in the input (square root of which doesn't have a remainder, e.g. 1.732.. is square root of 3) and press submit,
//        then check that correct no error is seen and check that square root is calculated correctly
        inputField.sendKeys(myNumber);
        submitButton.click();
        Alert alert = driver.switchTo().alert();
        assertEquals("Square root of " + myNumber + " is " + mySqrt, alert.getText());
        alert.accept();
        assertEquals("", driver.findElement(By.id("ch1_error")).getText());



    }
}
