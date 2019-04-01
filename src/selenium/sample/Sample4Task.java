package selenium.sample;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.*;

public class Sample4Task {
    WebDriver driver;
    String base_url = "https://kristinek.github.io/site/examples/actions";

    // method which is being run before each test
    @Before
    public void startingTests() throws Exception {
        // from Sample 1:
        String libWithDriversLocation = System.getProperty("user.dir") + "\\lib\\";
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver.exe");
        // declaration above:
        driver = new ChromeDriver();
        //open page:
        driver.get(base_url);
    }

    // method which is being run after each test
    @After
    public void endingTests() throws Exception {
        driver.quit();
    }

    @Test
    public void enterNumber() throws Exception {
//         TODO:
//        enter a number under "Number"
        assertEquals(base_url, driver.getCurrentUrl());
        driver.findElement(By.id("number")).sendKeys("123");
        assertFalse(driver.findElement(By.id("clear_result_button_number")). isEnabled());
        assertFalse(driver.findElement(By.id("result_number")). isDisplayed());
        driver.findElement(By.id("result_button_number")).click();
        assertTrue(driver.findElement(By.id("result_number")).isDisplayed());
        driver.findElement(By.id("clear_result_button_number")).click();
        assertEquals(" ", driver.findElement(By.id("result_number")).isDisplayed());



//        check that button is not clickable "Clear Result"
//        check that text is not displayed
//        click on "Result" button
//        check that text is displayed
//        check that the correct Text appears ("You entered number: "NUMBER YOU ENTERED"")
//        check that the button "Clear Result" is clickable now
//        click on "Clear Result"
//        check that the text is still (""), but it is not displayed
    }

    @Test
    public void clickOnLink() throws Exception {
//         TODO:

//        check current url is base_url
        assertEquals(base_url, driver.getCurrentUrl());
//        click on "This is a link to Homepage"
        driver.findElement(By.id("homepage_link")).click();
//        check that current url is not base_url
        assertFalse(base_url.equals(driver.getCurrentUrl()));
//        verify that current url is homepage
        assertTrue(driver.getCurrentUrl().equals("https://kristinek.github.io/site/"));
    }
}
