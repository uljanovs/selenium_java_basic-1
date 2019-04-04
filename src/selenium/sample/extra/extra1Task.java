package selenium.sample.extra;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.*;

public class extra1Task {
    WebDriver driver;
    String base_url = "https://kristinek.github.io/site/examples/po";
    String new_url = "https://kristinek.github.io/site/examples/po1";

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
    public void testNavigateBack() throws Exception {
//        TODO
        assertEquals(base_url, driver.getCurrentUrl());
//        open page with url "https://kristinek.github.io/site/examples/po"
        //driver.get(base_url);
//        click "More > " for the top left element
        driver.findElement(By.cssSelector("a[href='po1']")).click();
//        check that the url now "https://kristinek.github.io/site/examples/po1"
        assertEquals(new_url, driver.getCurrentUrl());
//        using driver navigation go back to "https://kristinek.github.io/site/examples/po"
        driver.navigate().back();
//        check that the page now is "https://kristinek.github.io/site/examples/po"
        assertEquals(base_url, driver.getCurrentUrl());
    }

    @Test
    public void navigateForward() throws Exception {
//        TODO
//        open page with url "https://kristinek.github.io/site/examples/po"
        assertEquals(base_url, driver.getCurrentUrl());
//        click "More > " for the top left element
        driver.findElement(By.cssSelector("a[href='po1']")).click();
//        using driver navigation go back to "https://kristinek.github.io/site/examples/po"
        driver.navigate().back();
//        using driver navigation go forward to "https://kristinek.github.io/site/examples/po1"
        driver.navigate().forward();
//        check that the page now is "https://kristinek.github.io/site/examples/po1"
        assertEquals(new_url, driver.getCurrentUrl());
    }

    @Test
    public void refresh() throws Exception {

//        TODO
//        open page "https://kristinek.github.io/site/examples/act"
        driver.get("https://kristinek.github.io/site/examples/actions");
        WebElement text = driver.findElement(By.id("show_me"));
        WebElement showButton = driver.findElement(By.id("show_text"));


//        click on "Show" button in 'Button' section
        showButton.click();
//        check that text "I am here!" is seen
        assertTrue("I am here!".equals(text.getText()));
//        refresh page
        driver.navigate().refresh();
        text = driver.findElement(By.id("show_me"));
        //        check that text "I am here!" is not seen
        //assertEquals("", text.getText());
        assertFalse(text.isDisplayed());
    }
}
