package selenium.tasks;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static java.lang.Math.sqrt;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;


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
//        enter a text instead of a number, check that correct error is seen
        assertFalse(driver.findElement(By.id("ch1_error")).isDisplayed());
        driver.findElement(By.id("numb")).sendKeys("asdfsadf");
        driver.findElement(By.className("w3-orange")).click();
        assertTrue(driver.findElement(By.id("ch1_error")).isDisplayed());
        assertEquals("Please enter a number", driver.findElement(By.id("ch1_error")).getText());
    }

    @Test
    public void errorOnNumberTooSmall() {
//        enter number which is too small (below 50), check that correct error is seen
        assertFalse(driver.findElement(By.id("ch1_error")).isDisplayed());
        driver.findElement(By.id("numb")).sendKeys("48");
        driver.findElement(By.className("w3-orange")).click();
        assertTrue(driver.findElement(By.id("ch1_error")).isDisplayed());
        assertEquals("Number is too small", driver.findElement(By.id("ch1_error")).getText());
    }

    @Test
    public void errorOnNumberTooBig() {

//        BUG: if I enter number 666 no errors where seen
//        enter number which is too big (above 100), check that correct error is seen
        assertFalse(driver.findElement(By.id("ch1_error")).isDisplayed());
        driver.findElement(By.id("numb")).sendKeys("200");
        driver.findElement(By.className("w3-orange")).click();
        assertTrue(driver.findElement(By.id("ch1_error")).isDisplayed());
        assertEquals("Number is too big", driver.findElement(By.id("ch1_error")).getText());
    }

    @Test
    public void correctSquareRootWithoutRemainder() {
//        enter a number between 50 and 100 digit in the input (square root of which doesn't have a remainder, e.g. 2 is square root of 4),
//        then and press submit and check that correct no error is seen and check that square root is calculated correctly
        assertFalse(driver.findElement(By.id("ch1_error")).isDisplayed());
        driver.findElement(By.id("numb")).sendKeys("64");
        driver.findElement(By.className("w3-orange")).click();
        assertTrue(driver.switchTo().alert().getText().equals("Square root of 64 is 8.00"));
        driver.switchTo().alert().accept();
        assertFalse(driver.findElement(By.id("ch1_error")).isDisplayed());
        assertEquals(sqrt(64), 8.00);
    }

    @Test
    public void correctSquareRootWithRemainder() {
//        enter a number between 50 and 100 digit in the input (square root of which doesn't have a remainder, e.g. 1.732.. is square root of 3) and press submit,
//        then check that correct no error is seen and check that square root is calculated correctly

        assertFalse(driver.findElement(By.id("ch1_error")).isDisplayed());
        driver.findElement(By.id("numb")).sendKeys("55");
        driver.findElement(By.className("w3-orange")).click();
        assertTrue(driver.switchTo().alert().getText().equals("Square root of 55 is 7.42"));
        driver.switchTo().alert().accept();
        assertFalse(driver.findElement(By.id("ch1_error")).isDisplayed());
        assertEquals(String.format("%.2f", sqrt(55)), "7.42");
    }
}
