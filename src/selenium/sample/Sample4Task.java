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
        int testNum = 63263;
        String testNumber = Integer.toString(testNum);
        WebElement number = driver.findElement(By.id("number"));
        String originalNumber = number.getAttribute("Value");
        number.sendKeys(testNumber);


//        check that button is not clickable "Clear Result"
        WebElement hideButton = driver.findElement(By.id("clear_result_button_number"));
        assertFalse(hideButton.isEnabled());

//        check that text is not displayed
        WebElement text = driver.findElement(By.id("result_number"));
        assertFalse(text.isDisplayed());

//        click on "Result" button

        WebElement resultButton = driver.findElement(By.id("result_button_number"));
        resultButton.click();

//        check that text is displayed
        assertTrue(text.isDisplayed());

//        check that the correct Text appears ("You entered number: "NUMBER YOU ENTERED"")
        assertEquals("You entered number: " + '"' + testNumber + originalNumber + '"', text.getText());

//        check that the button "Clear Result" is clickable now
        assertTrue(hideButton.isEnabled());

//        click on "Clear Result"
        hideButton.click();

//        check that the text is still (""), but it is not displayed
        assertEquals("", text.getText());
        assertFalse(text.isDisplayed());

    }

    @Test
    public void clickOnLink() throws Exception {
//         TODO:
//        check current url is base_url
        assertEquals(base_url, driver.getCurrentUrl());

//        click on "This is a link to Homepage"
        WebElement link = driver.findElement(By.id("homepage_link"));
        link.click();

//        check that current url is not base_url
        assertNotEquals(base_url, driver.getCurrentUrl());

//        verify that current url is homepage
        String homePage = "https://kristinek.github.io/site/";
        assertEquals(homePage, driver.getCurrentUrl());

    }
}
