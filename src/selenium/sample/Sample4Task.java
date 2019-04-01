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
        WebElement enter = driver.findElement(By.id("number"));
        WebElement buttonclearresult = driver.findElement(By.id("clear_result_button_number"));
        WebElement buttonresult = driver.findElement((By.id("result_button_number")));
        WebElement text = driver.findElement(By.id("result_number"));
        String Displayedtext = "You entered number: \"10\"";
       enter.click();
       enter.clear();
//        enter a number under "Number"
        enter.sendKeys("10");
//        check that button is not clickable "Clear Result"
        assertFalse(buttonclearresult.isEnabled());
//        check that text is not displayed
        assertFalse(text.isDisplayed());
//        click on "Result" button
        buttonresult.click();
//        check that text is displayed
        assertTrue(text.isDisplayed());
//        check that the correct Text appears ("You entered number: "NUMBER YOU ENTERED"")
       assertEquals(Displayedtext,text.getText());
//        check that the button "Clear Result" is clickable now
        assertTrue(buttonclearresult.isDisplayed());
//        click on "Clear Result"
        buttonclearresult.click();
//        check that the text is still (""), but it is not displayed
        assertFalse(text.isDisplayed());
    }

    @Test
    public void clickOnLink() throws Exception {
//         TODO:
        WebElement homepagelink = driver.findElement(By.id("homepage_link"));
//        check current url is base_url
        assertEquals(base_url, driver.getCurrentUrl());
//        click on "This is a link to Homepage"
        homepagelink.click();
//        check that current url is not base_url
        assertNotEquals(base_url, driver.getCurrentUrl() );
//        verify that current url is homepage
        assertEquals("This is a home page", driver.findElement(By.id("h1")).getText());
    }
}
