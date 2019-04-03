package selenium.sample;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class Sample9Task {
    WebDriver driver;
    private static WebDriverWait wait;
    static long startTime;

    @Before
    public void openPage() {
        String libWithDriversLocation = System.getProperty("user.dir") + "\\lib\\";
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver.exe");
        driver = new ChromeDriver();
        wait = (WebDriverWait) new WebDriverWait(driver, 10).ignoring(StaleElementReferenceException.class);


        driver.get("https://kristinek.github.io/site/examples/loading_color");

        startTime = System.currentTimeMillis();

    }

    @After
    public void closeBrowser() {
        driver.quit();

    }

    @Test
    public void loadGreenSleep() throws Exception {
//         TODO:
       //Thread.sleep(10000);
        TimeUnit.SECONDS.sleep(3);
        WebElement button = driver.findElement(By.id("start_green"));

//         * 1) click on start loading green button
        button.click();
//         * 2) check that button does not appear,
        TimeUnit.SECONDS.sleep(3);
        assertFalse(button.isDisplayed());
//         * but loading text is seen instead   "Loading green..."
        WebElement loading = driver.findElement(By.cssSelector("#loading_green"));
        assertTrue(loading.isDisplayed());

//         * 3) check that both button
//         * and loading text is not seen,
        TimeUnit.SECONDS.sleep(8);
        WebElement loadinggreen = driver.findElement(By.cssSelector("#finish_green"));
        assertEquals("Green Loaded",loadinggreen.getText());

//         * success is seen instead "Green Loaded"
        TimeUnit.SECONDS.sleep(1);
        assertFalse(button.isDisplayed());

    }

    @Test
    public void loadGreenImplicit() throws Exception {
//         TODO:
//         * 1) click on start loading green button
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement button = driver.findElement(By.id("start_green"));
        button.click();
//         * 2) check that button does not appear,
        assertFalse(button.isDisplayed());
//         * but loading text is seen instead   "Loading green..."
        WebElement loading = driver.findElement(By.cssSelector("#loading_green"));
        assertTrue(loading.isDisplayed());
//         * 3) check that both button
//         * and loading text is not seen,
        WebElement loadinggreen = driver.findElement(By.cssSelector("#finish_green"));
        assertEquals("Green Loaded",loadinggreen.getText());
//         * success is seen instead "Green Loaded"
        assertFalse(button.isDisplayed());
    }

    @Test
    public void loadGreenExplicitWait() throws Exception {
//         TODO:
//         * 1) click on start loading green button
        WebElement button = driver.findElement(By.id("start_green"));
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@id='start_green']")));
        button.click();
//         * 2) check that button does not appear,

        assertFalse(button.isDisplayed());

//         * but loading text is seen instead   "Loading green..."
        WebElement loading = driver.findElement(By.cssSelector("#loading_green"));
        assertTrue(loading.isDisplayed());

//         * 3) check that both button
//         * and loading text is not seen,
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@id='finish_green']")));
        WebElement loadinggreen = driver.findElement(By.cssSelector("#finish_green"));
        assertEquals("Green Loaded",loadinggreen.getText());
//         * success is seen instead "Green Loaded"
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='start_green']")));
        assertFalse(button.isDisplayed());
    }

    @Test
    public void loadGreenAndBlueBonus() {
        /* TODO:
         * 0) wait until button to load green and blue appears
         * 1) click on start loading green and blue button
         * 2) check that button does not appear, but loading text is seen instead for green
         * 3) check that button does not appear, but loading text is seen instead for green and blue
         * 4) check that button and loading green does not appear,
         * 		but loading text is seen instead for blue and success for green is seen
         * 5) check that both button and loading text is not seen, success is seen instead
         */
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@id='start_green_and_blue']")));
        WebElement buttonloadinggb = driver.findElement(By.id("start_green_and_blue"));
        buttonloadinggb.click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='start_green_and_blue']")));
        assertFalse(buttonloadinggb.isDisplayed());
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@id='finish_green_and_blue']")));
        WebElement text = driver.findElement(By.id("finish_green_and_blue"));
        assertEquals("Green and Blue Loaded",text.getText());

    }

}