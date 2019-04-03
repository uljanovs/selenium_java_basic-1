package selenium.tasks;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

public class Task2 {
    WebDriver driver;

    @Before
    public void openPage() {
        String libWithDriversLocation = System.getProperty("user.dir") + "\\lib\\";
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://kristinek.github.io/site/tasks/provide_feedback");
    }

    @After
    public void closeBrowser() {
        driver.quit();
    }

    @Test
    public void initialFeedbackPage() throws Exception {
        // TODO:
        // check that all field are empty and no tick are clicked
        assertEquals("", driver.findElement(By.id("fb_name")).getText());
        assertEquals("", driver.findElement(By.id("fb_age")).getText());
        assertEquals("", driver.findElement(By.name("comment")).getText());

        List<WebElement> languages = driver.findElements(By.name("language"));
        for (WebElement language : languages) {
            assertFalse(language.isSelected());
        }

        // "Don't know" is selected in "Genre"
        assertEquals("", driver.findElements(By.name("gender")).get(2).getAttribute("value"));

        // "Choose your option" in "How do you like us?"
        Select dropdown = new Select(driver.findElement(By.id("like_us")));
        WebElement selectedOption = dropdown.getFirstSelectedOption();
        assertEquals("Choose your option", selectedOption.getText());

        // check that the button send is blue with white letters
        WebElement sendButton = driver.findElement(By.className("w3-btn-block"));
        assertEquals("rgba(33, 150, 243, 1)", sendButton.getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", sendButton.getCssValue("color"));
    }

    @Test
    public void emptyFeedbackPage() throws Exception {
        // TODO:
        // click "Send" without entering any data
        WebElement submitButton = driver.findElement(By.className("w3-btn-block"));
        submitButton.click();

        // check fields are empty or null
        assertEquals("", driver.findElement(By.id("name")).getText());
        assertEquals("", driver.findElement(By.id("age")).getText());
        assertEquals("", driver.findElement(By.id("language")).getText());
        assertEquals("null", driver.findElement(By.id("gender")).getText());
        assertEquals("null", driver.findElement(By.id("option")).getText());
        assertEquals("", driver.findElement(By.id("comment")).getText());

        // check button colors
        assertEquals("rgba(76, 175, 80, 1)", driver.findElement(By.className("w3-green")).getCssValue("background-color"));
        assertEquals("rgba(244, 67, 54, 1)", driver.findElement(By.className("w3-red")).getCssValue("background-color"));

        // (green with white letter and red with white letters)
        assertEquals("rgba(255, 255, 255, 1)", driver.findElement(By.className("w3-green")).getCssValue("color"));
        assertEquals("rgba(255, 255, 255, 1)", driver.findElement(By.className("w3-red")).getCssValue("color"));
    }

    @Test
    public void notEmptyFeedbackPage() throws Exception {
        // TODO:
        // fill the whole form, click "Send"
        driver.findElement(By.id("fb_name")).sendKeys("Anna");
        driver.findElement(By.id("fb_age")).sendKeys("35");
        driver.findElement(By.name("comment")).sendKeys("Hello world!");
        driver.findElement(By.xpath("//input[@name='language' and @value='English']")).click();
        driver.findElement(By.xpath("//input[@name='gender' and @value='female']")).click();
        Select dropdown = new Select(driver.findElement(By.id("like_us")));
        dropdown.selectByValue("Good");

        WebElement submitButton = driver.findElement(By.className("w3-btn-block"));
        submitButton.click();

        // check fields are filled correctly
        assertEquals("Anna", driver.findElement(By.id("name")).getText());
        assertEquals("35", driver.findElement(By.id("age")).getText());
        assertEquals("English", driver.findElement(By.id("language")).getText());
        assertEquals("female", driver.findElement(By.id("gender")).getText());
        assertEquals("Good", driver.findElement(By.id("option")).getText());
        assertEquals("Hello world!", driver.findElement(By.id("comment")).getText());

        // check button colors
        assertEquals("rgba(76, 175, 80, 1)", driver.findElement(By.className("w3-green")).getCssValue("background-color"));
        assertEquals("rgba(244, 67, 54, 1)", driver.findElement(By.className("w3-red")).getCssValue("background-color"));

        // (green with white letter and red with white letters)
        assertEquals("rgba(255, 255, 255, 1)", driver.findElement(By.className("w3-green")).getCssValue("color"));
        assertEquals("rgba(255, 255, 255, 1)", driver.findElement(By.className("w3-red")).getCssValue("color"));
    }

    @Test
    public void yesOnWithNameFeedbackPage() throws Exception {
        // TODO:
        // enter only name
        driver.findElement(By.id("fb_name")).sendKeys("Anna");

        // click "Send"
        WebElement submitButton = driver.findElement(By.className("w3-btn-block"));
        submitButton.click();

        // click "Yes"
        WebElement yesButton = driver.findElement(By.className("w3-green"));
        yesButton.click();

        // check message text: "Thank you, NAME, for your feedback!"
        assertEquals("Thank you, Anna, for your feedback!", driver.findElement(By.id("message")).getText());

        // color of text is white with green on the background
        assertEquals("rgba(76, 175, 80, 1)", driver.findElement(By.className("w3-panel")).getCssValue("background-color"));
    }

    @Test
    public void yesOnWithoutNameFeedbackPage() throws Exception {
        // TODO:
        // click "Send" (without entering anything
        WebElement submitButton = driver.findElement(By.className("w3-btn-block"));
        submitButton.click();

        // click "Yes"
        WebElement yesButton = driver.findElement(By.className("w3-green"));
        yesButton.click();

        // check message text: "Thank you for your feedback!"
        assertEquals("Thank you for your feedback!", driver.findElement(By.id("message")).getText());

        // color of text is white with green on the background
        assertEquals("rgba(76, 175, 80, 1)", driver.findElement(By.className("w3-panel")).getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", driver.findElement(By.className("w3-panel")).getCssValue("color"));
    }

    @Test
    public void noOnFeedbackPage() throws Exception {
        // TODO:
        // fill the whole form
        driver.findElement(By.id("fb_name")).sendKeys("Anna");
        driver.findElement(By.id("fb_age")).sendKeys("35");
        driver.findElement(By.name("comment")).sendKeys("Hello world!");
        driver.findElement(By.xpath("//input[@name='language' and @value='English']")).click();
        driver.findElement(By.xpath("//input[@name='gender' and @value='male']")).click();
        Select dropdown = new Select(driver.findElement(By.id("like_us")));
        dropdown.selectByValue("Good");

        // click "Send"
        WebElement submitButton = driver.findElement(By.className("w3-btn-block"));
        submitButton.click();

        // click "No"
        WebElement noButton = driver.findElement(By.className("w3-red"));
        noButton.click();

        // check fields are filled correctly
        assertEquals("Anna", driver.findElement(By.id("fb_name")).getAttribute("value"));
        assertEquals("35", driver.findElement(By.id("fb_age")).getAttribute("value"));
        assertEquals("Hello world!", driver.findElement(By.name("comment")).getAttribute("value"));
        assertTrue(driver.findElement(By.xpath("//input[@name='language' and @value='English']")).isSelected());
        assertTrue(driver.findElement(By.xpath("//input[@name='gender' and @value='male']")).isSelected());
        assertEquals("Good", driver.findElement(By.id("like_us")).getAttribute("value"));
    }
}



