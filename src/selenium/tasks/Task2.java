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
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

public class Task2 {
    private WebDriver driver;

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
//         check that all field are empty and no tick are clicked
        assertEquals("", driver.findElement(By.id("fb_name")).getAttribute("value"));
        assertEquals("", driver.findElement(By.id("fb_age")).getAttribute("value"));
        List<WebElement> checkboxes = driver.findElements(By.xpath("//input[@type='checkbox' and @name='language']"));
        for (WebElement checkbox: checkboxes) {
            assertFalse(checkbox.isSelected());
        }
        assertEquals("", driver.findElement(By.xpath("//textarea[@name='comment']")).getAttribute("value"));
//         "Don't know" is selected in "Genre"
        assertTrue(driver.findElement(By.xpath("//input[@name='gender' and @value='']")).isSelected());
//         "Choose your option" in "How do you like us?"
        Select dropdown = new Select(driver.findElement(By.id("like_us")));
        assertEquals("Choose your option", dropdown.getFirstSelectedOption().getText());
//         check that the button send is blue with white letters
        assertEquals("rgba(33, 150, 243, 1)", driver.findElement(By.tagName("button")).getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", driver.findElement(By.tagName("button")).getCssValue("color"));
    }

    @Test
    public void emptyFeedbackPage() throws Exception {
//         click "Send" without entering any data
        driver.findElement(By.tagName("button")).click();
//         check fields are empty or null
        assertEquals("", driver.findElement(By.id("name")).getText());
        assertEquals("", driver.findElement(By.id("age")).getText());
        assertEquals("", driver.findElement(By.id("language")).getText());
        assertEquals("null", driver.findElement(By.id("gender")).getText());
        assertEquals("null", driver.findElement(By.id("option")).getText());
        assertEquals("", driver.findElement(By.id("comment")).getText());
//         check button colors
//         (green with white letter and red with white letters)
        assertEquals("rgba(76, 175, 80, 1)", driver.findElement(By.xpath("//button[.='Yes']")).getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", driver.findElement(By.xpath("//button[.='Yes']")).getCssValue("color"));
        assertEquals("rgba(244, 67, 54, 1)", driver.findElement(By.xpath("//button[.='No']")).getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", driver.findElement(By.xpath("//button[.='No']")).getCssValue("color"));
    }

    @Test
    public void notEmptyFeedbackPage() throws Exception {
//         fill the whole form, click "Send"
        driver.findElement(By.id("fb_name")).sendKeys("name");
        driver.findElement(By.id("fb_age")).sendKeys("30");
        List<WebElement> checkboxes = driver.findElements(By.xpath("//input[@type='checkbox' and @name='language']"));
        for (WebElement checkbox: checkboxes) {
            checkbox.click();
        }
        driver.findElement(By.xpath("//input[@name='gender' and @value='male']")).click();
        Select dropdown = new Select(driver.findElement(By.id("like_us")));
        dropdown.selectByVisibleText("Why me?");
        driver.findElement(By.xpath("//textarea[@name='comment']")).sendKeys("Some comment, test, test");
        driver.findElement(By.tagName("button")).click();
//         check fields are filled correctly
        assertEquals("name", driver.findElement(By.id("name")).getText());
        assertEquals("30", driver.findElement(By.id("age")).getText());
        assertEquals("English,French,Spanish,Chinese", driver.findElement(By.id("language")).getText());
        assertEquals("male", driver.findElement(By.id("gender")).getText());
        assertEquals("Why me?", driver.findElement(By.id("option")).getText());
        assertEquals("Some comment, test, test", driver.findElement(By.id("comment")).getText());
//         check button colors
//         (green with white letter and red with white letters)
        assertEquals("rgba(76, 175, 80, 1)", driver.findElement(By.xpath("//button[.='Yes']")).getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", driver.findElement(By.xpath("//button[.='Yes']")).getCssValue("color"));
        assertEquals("rgba(244, 67, 54, 1)", driver.findElement(By.xpath("//button[.='No']")).getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", driver.findElement(By.xpath("//button[.='No']")).getCssValue("color"));
    }

    @Test
    public void yesOnWithNameFeedbackPage() throws Exception {
//         enter only name
        driver.findElement(By.id("fb_name")).sendKeys("name");
//         click "Send"
        driver.findElement(By.tagName("button")).click();
//         click "Yes"
        driver.findElement(By.xpath("//button[.='Yes']")).click();
//         check message text: "Thank you, NAME, for your feedback!"
        assertEquals("Thank you, name, for your feedback!", driver.findElement(By.id("message")).getText());
//         color of text is white with green on the background
        assertEquals("rgba(76, 175, 80, 1)", driver.findElement(By.xpath("//div[@id='fb_thx']/div")).getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", driver.findElement(By.xpath("//div[@id='fb_thx']/div")).getCssValue("color"));
    }

    @Test
    public void yesOnWithoutNameFeedbackPage() throws Exception {
//         click "Send" (without entering anything
        driver.findElement(By.tagName("button")).click();
//         click "Yes"
        driver.findElement(By.xpath("//button[.='Yes']")).click();
//         check message text: "Thank you for your feedback!"
        assertEquals("Thank you for your feedback!", driver.findElement(By.id("message")).getText());
//         color of text is white with green on the background
        assertEquals("rgba(76, 175, 80, 1)", driver.findElement(By.xpath("//div[@id='fb_thx']/div")).getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", driver.findElement(By.xpath("//div[@id='fb_thx']/div")).getCssValue("color"));
    }

    @Test
    public void noOnFeedbackPage() throws Exception {
//         fill the whole form
        driver.findElement(By.id("fb_name")).sendKeys("name");
        driver.findElement(By.id("fb_age")).sendKeys("30");
        List<WebElement> checkboxes = driver.findElements(By.xpath("//input[@type='checkbox' and @name='language']"));
        for (WebElement checkbox: checkboxes) {
            checkbox.click();
        }
        driver.findElement(By.xpath("//input[@name='gender' and @value='male']")).click();
        Select dropdown = new Select(driver.findElement(By.id("like_us")));
        dropdown.selectByVisibleText("Why me?");
        driver.findElement(By.xpath("//textarea[@name='comment']")).sendKeys("Some comment, test, test");
//         click "Send"
        driver.findElement(By.tagName("button")).click();
//         click "No"
        driver.findElement(By.xpath("//button[.='No']")).click();
//         check fields are filled correctly
        assertEquals("name", driver.findElement(By.id("fb_name")).getAttribute("value"));
        assertEquals("30", driver.findElement(By.id("fb_age")).getAttribute("value"));
        checkboxes = driver.findElements(By.xpath("//input[@type='checkbox' and @name='language']"));
        for (WebElement checkbox: checkboxes) {
            assertTrue(checkbox.isSelected());
        }
        assertTrue(driver.findElement(By.xpath("//input[@name='gender' and @value='male']")).isSelected());
        dropdown = new Select(driver.findElement(By.id("like_us")));
        assertEquals("Why me?", dropdown.getFirstSelectedOption().getText());
        assertEquals("Some comment, test, test", driver.findElement(By.xpath("//textarea[@name='comment']")).getAttribute("value"));
    }
}
