package selenium.sample.extra;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;

public class extra1Task {
    private WebDriver driver;
    private String base_url = "https://kristinek.github.io/site/examples/act";
    private static WebDriverWait wait;

    // method which is being run before each test
    @Before
    public void startingTests() throws Exception {
        // from Sample 1:
        String libWithDriversLocation = System.getProperty("user.dir") + "\\lib\\";
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver.exe");
        // declaration above:
        driver = new ChromeDriver();
        wait = (WebDriverWait) new WebDriverWait(driver, 10).ignoring(StaleElementReferenceException.class);

        //open page:
        driver.get(base_url);
    }

    // method which is being run after each test
    @After
    public void endingTests() throws Exception {
        driver.quit();
    }

    @Test
    public void navigateBack() throws Exception {
//        open page with url "https://kristinek.github.io/site/examples/po"
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//ul/li[2]")));
        driver.findElement(By.xpath("//ul/li[2]")).click();
        driver.findElement(By.linkText("Page with Links")).click();
//        click "More > " for the top left element
        driver.findElement(By.xpath("//div[contains(@class, w3-pale-red)]//a[.='More >> ']")).click();
//        check that the url now "https://kristinek.github.io/site/examples/po1"
        assertEquals("https://kristinek.github.io/site/examples/po1", driver.getCurrentUrl());
//        using driver navigation go back to "https://kristinek.github.io/site/examples/po"
        driver.navigate().back();
//        check that the page now is "https://kristinek.github.io/site/examples/po"
        assertEquals("https://kristinek.github.io/site/examples/po", driver.getCurrentUrl());
    }

    @Test
    public void navigateForward() throws Exception {
//        open page with url "https://kristinek.github.io/site/examples/po"
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//ul/li[2]")));
        driver.findElement(By.xpath("//ul/li[2]")).click();
        driver.findElement(By.linkText("Page with Links")).click();
//        click "More > " for the top left element
        driver.findElement(By.xpath("//div[contains(@class, w3-pale-red)]//a[.='More >> ']")).click();
//        using driver navigation go back to "https://kristinek.github.io/site/examples/po"
        driver.navigate().back();
//        using driver navigation go forward to "https://kristinek.github.io/site/examples/po1"
        driver.navigate().forward();
//        check that the page now is "https://kristinek.github.io/site/examples/po1"
        assertEquals("https://kristinek.github.io/site/examples/po1", driver.getCurrentUrl());
    }

    @Test
    public void refresh() throws Exception {
//        open page "https://kristinek.github.io/site/examples/act"
//        click on "Show" button in 'Button' section
        driver.findElement(By.id("show_text")).click();
//        check that text "I am here!" is seen
        assertEquals("I am here!", driver.findElement(By.id("show_me")).getText());
//        refresh page
        driver.navigate().refresh();
//        check that text "I am here!" is not seen
        assertFalse(driver.findElement(By.id("show_me")).isDisplayed());
    }
}
