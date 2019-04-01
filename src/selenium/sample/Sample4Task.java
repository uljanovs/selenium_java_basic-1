package selenium.sample;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.server.handler.SendKeys;

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
        WebElement textInput = driver.findElement(By.id("number"));
        WebElement showedtext = driver.findElement(By.id("result_number"));
        WebElement resultbutton = driver.findElement(By.id("result_button_number"));
        String sendKeys1 = "123";
        WebElement clearresultbutton = driver.findElement(By.id("clear_result_button_number"));

//        enter a number under "Number"
        textInput.sendKeys(sendKeys1);

//        check that button is not clickable "Clear Result"
        assertFalse(clearresultbutton.isEnabled());

//        check that text is not displayed
        assertFalse(showedtext.isDisplayed());

//        click on "Result" button
        resultbutton.click();

//        check that text is displayed
        assertTrue(showedtext.isDisplayed());

//        check that the correct Text appears ("You entered number: "NUMBER YOU ENTERED"")
        assertEquals("You entered number: " + "\"" + sendKeys1 + "5" +"\"", showedtext.getText());

//        check that the button "Clear Result" is clickable now
        assertTrue(clearresultbutton.isEnabled());

//        click on "Clear Result"
        clearresultbutton.click();

//        check that the text is still (""), but it is not displayed
        assertEquals("", showedtext.getText());
        assertFalse(showedtext.isDisplayed());
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
