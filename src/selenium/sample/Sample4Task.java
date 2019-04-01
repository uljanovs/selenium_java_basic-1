package selenium.sample;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import javax.xml.transform.Result;

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

        String NewNumberText = "7";
        String DisplayedText = "You entered number: \"7\"";

        WebElement Numberplace = driver.findElement(By.id("number"));
        Numberplace.clear();
        Numberplace.sendKeys(NewNumberText);

        //check that button is not clickable "Clear Result"
        WebElement ClearResultButton = driver.findElement(By.id("clear_result_button_text_area"));
        assertFalse(ClearResultButton.isEnabled());

//        check that text is not displayed
        assertFalse(ClearResultButton.isEnabled());

//        click on "Result" button
        WebElement ResultButton = driver.findElement(By.id("result_button_number"));
        ResultButton.click();

//        check that text is displayed
        assertTrue(ResultButton.isDisplayed());

//        check that the correct Text appears ("You entered number: "NUMBER YOU ENTERED"")

        WebElement text = driver.findElement(By.id("result_number"));
        assertEquals(DisplayedText,text.getText());

//        check that the button "Clear Result" is clickable now
        assertTrue(ClearResultButton.isEnabled());

//        click on "Clear Result"
        ClearResultButton.click();


//        check that the text is still (""), but it is not displayed
        assertEquals("",Numberplace.getText());
        assertFalse(driver.findElement(By.id("result_number")).isDisplayed());
    }

    @Test
    public void clickOnLink() throws Exception {
//         TODO:
//        check current url is base_url
        assertEquals(base_url, driver.getCurrentUrl());
//        click on "This is a link to Homepage"
        driver.findElement(By.id("homepage_link")).click();
//        check that current url is not base_url
        assertFalse(driver.getCurrentUrl().equals(base_url));
//        verify that current url is homepage
        //assertNotEquals(base_url, driver.getCurrentUrl());
        assertTrue(driver.getCurrentUrl().equals (base_url));
    }
}
