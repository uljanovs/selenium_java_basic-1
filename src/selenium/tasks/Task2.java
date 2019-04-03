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
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
//         TODO:
//         check that all field are empty and no tick are clicked - sample 7task
        WebElement namebox = driver.findElement(By.id("fb_name"));
        WebElement agebox = driver.findElement(By.id("fb_age"));

        assertEquals("", namebox.getText());
        assertEquals("", agebox.getText());

        List<WebElement> checkBoxes = driver.findElements(By.cssSelector(".w3-check[type='checkbox']"));
        for (WebElement checkBox : checkBoxes) {
            assertFalse(checkBox.isSelected());}

        WebElement genderMale = driver.findElement(By.xpath("//*[@type='radio' and @value='male']"));
        assertFalse(genderMale.isSelected());

        WebElement genderFemale = driver.findElement(By.xpath("//*[@type='radio' and @value='female']"));
        assertFalse(genderFemale.isSelected());

        WebElement textbox = driver.findElement(By.cssSelector("textarea"));
        assertEquals("", textbox.getText());

//         "Don't know" is selected in "Genre"
        WebElement dontknow = driver.findElement(By.xpath("//*[@type='radio' and @value='']"));
        assertTrue(dontknow.isSelected());

//         "Choose your option" in "How do you like us?"
        Select dropdown = new Select(driver.findElement(By.id("like_us")));
        assertEquals("Choose your option", dropdown.getFirstSelectedOption().getText());

//         check that the button send is blue with white letters className = w3-btn type=submit
        WebElement sendbutton = driver.findElement(By.xpath("//*[@type='submit']"));
        assertEquals("rgba(33, 150, 243, 1)",sendbutton.getCssValue("background-color"));
        assertEquals("rgb(255, 255, 255)", sendbutton.getCssValue("text-decoration-color"));


    }

    @Test
    public void emptyFeedbackPage() throws Exception {
//         TODO:
//         click "Send" without entering any data
        WebElement sendbutton = driver.findElement(By.xpath("//*[@type='submit']"));
        sendbutton.click();
//         check fields are empty or null - all span id = ''
        assertEquals("", driver.findElement(By.xpath("//*[@id='name']")).getText());
        assertEquals("", driver.findElement(By.xpath("//*[@id='age']")).getText());
        assertEquals("", driver.findElement(By.xpath("//*[@id='language']")).getText());
        assertEquals("null", driver.findElement(By.xpath("//*[@id='gender']")).getText());
        assertEquals("null", driver.findElement(By.xpath("//*[@id='option']")).getText());
        assertEquals("", driver.findElement(By.xpath("//*[@id='comment']")).getText());

//         check button colors
//         (green with white letter and red with white letters)
        WebElement yesbutton = driver.findElement(By.xpath("//*[text()='Yes']"));
        WebElement nobutton = driver.findElement(By.xpath("//*[text()='No']"));

        assertEquals("rgba(76, 175, 80, 1)",yesbutton.getCssValue("background-color"));
        assertEquals("rgb(255, 255, 255)", yesbutton.getCssValue("text-decoration-color"));

        assertEquals("rgba(244, 67, 54, 1)",nobutton.getCssValue("background-color"));
        assertEquals("rgb(255, 255, 255)", nobutton.getCssValue("text-decoration-color"));
    }

    @Test
    public void notEmptyFeedbackPage() throws Exception {
//         TODO:
//         fill the whole form, click "Send"
        driver.findElement(By.id("fb_name")).sendKeys("Name");
        driver.findElement(By.id("fb_age")).sendKeys("10");
        driver.findElement(By.cssSelector(".w3-check[value='English'][type='checkbox']")).click();
        driver.findElement(By.xpath("//*[@type='radio' and @value='female']")).click();
        Select dropdown = new Select(driver.findElement(By.id("like_us")));
        dropdown.selectByVisibleText("Good");
        driver.findElement(By.cssSelector("textarea")).sendKeys("comment");

        driver.findElement(By.xpath("//*[@type='submit']")).click();
//         check fields are filled correctly
        assertEquals("Name", driver.findElement(By.xpath("//*[@id='name']")).getText());
        assertEquals("10", driver.findElement(By.xpath("//*[@id='age']")).getText());
        assertEquals("English", driver.findElement(By.xpath("//*[@id='language']")).getText());
        assertEquals("female", driver.findElement(By.xpath("//*[@id='gender']")).getText());
        assertEquals("Good", driver.findElement(By.xpath("//*[@id='option']")).getText());
        assertEquals("comment", driver.findElement(By.xpath("//*[@id='comment']")).getText());

//         check button colors
//         (green with white letter and red with white letters)
        WebElement yesbutton = driver.findElement(By.xpath("//*[text()='Yes']"));
        WebElement nobutton = driver.findElement(By.xpath("//*[text()='No']"));

        assertEquals("rgba(76, 175, 80, 1)",yesbutton.getCssValue("background-color"));
        assertEquals("rgb(255, 255, 255)", yesbutton.getCssValue("text-decoration-color"));

        assertEquals("rgba(244, 67, 54, 1)",nobutton.getCssValue("background-color"));
        assertEquals("rgb(255, 255, 255)", nobutton.getCssValue("text-decoration-color"));
    }

    @Test
    public void yesOnWithNameFeedbackPage() throws Exception {
//         TODO:
//         enter only name
        String inputname = "Name";
        driver.findElement(By.id("fb_name")).sendKeys(inputname);

//         click "Send"
        driver.findElement(By.xpath("//*[@type='submit']")).click();

//         click "Yes"
        driver.findElement(By.xpath("//*[text()='Yes']")).click();

//         check message text: "Thank you, NAME, for your feedback!"
        assertEquals("Thank you, " + inputname + ", for your feedback!", driver.findElement(By.id("message")).getText());

//         color of text is white with green on the background
        assertEquals("rgba(76, 175, 80, 1)",driver.findElement(By.className("w3-green")).getCssValue("background-color"));
        assertEquals("rgb(255, 255, 255)", driver.findElement(By.id("message")).getCssValue("text-decoration-color"));
    }

    @Test
    public void yesOnWithoutNameFeedbackPage() throws Exception {
//         TODO:
//         click "Send" (without entering anything
        driver.findElement(By.xpath("//*[@type='submit']")).click();

//         click "Yes"
        driver.findElement(By.xpath("//*[text()='Yes']")).click();

//         check message text: "Thank you for your feedback!"
        assertEquals("Thank you for your feedback!", driver.findElement(By.id("message")).getText());

//         color of text is white with green on the background
        assertEquals("rgba(76, 175, 80, 1)",driver.findElement(By.className("w3-green")).getCssValue("background-color"));
        assertEquals("rgb(255, 255, 255)", driver.findElement(By.id("message")).getCssValue("text-decoration-color"));
    }

    @Test
    public void noOnFeedbackPage() throws Exception {
//         TODO:
//         fill the whole form
        driver.findElement(By.id("fb_name")).sendKeys("Name");
        driver.findElement(By.id("fb_age")).sendKeys("10");
        driver.findElement(By.cssSelector(".w3-check[value='English'][type='checkbox']")).click();
        driver.findElement(By.xpath("//*[@type='radio' and @value='female']")).click();
        Select dropdown = new Select(driver.findElement(By.id("like_us")));
        dropdown.selectByVisibleText("Good");
        driver.findElement(By.cssSelector("textarea")).sendKeys("comment");

//         click "Send"
        driver.findElement(By.xpath("//*[@type='submit']")).click();

//         click "No"
        driver.findElement(By.xpath("//*[text()='No']")).click();

//         check fields are filled correctly
        assertEquals("Name", driver.findElement(By.id("fb_name")).getAttribute("value"));
        assertEquals("10", driver.findElement(By.id("fb_age")).getAttribute("value"));
        assertTrue(driver.findElement(By.cssSelector(".w3-check[value='English'][type='checkbox']")).isSelected());
        assertTrue(driver.findElement(By.xpath("//*[@type='radio' and @value='female']")).isSelected());
        assertEquals("Good", driver.findElement(By.id("like_us")).getAttribute("value"));
        assertEquals("comment", driver.findElement(By.cssSelector("textarea")).getAttribute("value"));
    }
}
