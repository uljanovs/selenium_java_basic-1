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
    }

    @After
    public void closeBrowser() {
        driver.quit();
    }

    @Test
    public void loadGreenSleep() throws Exception {
//         TODO:
//         * 1) click on start loading green button
        WebElement start_green = driver.findElement(By.id("start_green"));
        start_green.click();


//         * 2) check that button does not appear,
        assertFalse(start_green.isSelected());

//         * but loading text is seen instead   "Loading green..."
        WebElement loading_green = driver.findElement(By.id("loading_green"));
        assertTrue(loading_green.isDisplayed());
        TimeUnit.SECONDS.sleep(5);
//         * 3) check that both button
        assertFalse(start_green.isSelected());
//         * and loading text is not seen,
        assertFalse(loading_green.isDisplayed());
//         * success is seen instead "Green Loaded"
        assertTrue(driver.findElement(By.id("finish_green")).isEnabled());
    }

    @Test
    public void loadGreenImplicit() throws Exception {
//         TODO:
//         * 1) click on start loading green button
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        WebElement startGreen = driver.findElement(By.id("start_green"));
        startGreen.click();
//         * 2) check that button does not appear,
        assertFalse(startGreen.isSelected());
//         * but loading text is seen instead   "Loading green..."
        WebElement loadingGreen = driver.findElement(By.id("loading_green"));
        assertTrue(loadingGreen.isDisplayed());
//         * 3) check that both button
        assertFalse(startGreen.isSelected());
//         * and loading text is not seen,
        WebElement finishGreen = driver.findElement(By.id("finish_green"));

        assertFalse(loadingGreen.isDisplayed());
//         * success is seen instead "Green Loaded"
       assertTrue(finishGreen.isDisplayed());
    }

    @Test
    public void loadGreenExplicitWait() throws Exception {
//         TODO:
//         * 1) click on start loading green button
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        WebElement startGreen = driver.findElement(By.id("start_green"));
        startGreen.click();
//         * 2) check that button does not appear,
        assertFalse(startGreen.isSelected());
//         * but loading text is seen instead   "Loading green..."
        WebElement loadingGreen = driver.findElement(By.id("loading_green"));
        assertTrue(loadingGreen.isDisplayed());
//         * 3) check that both button
        assertFalse(startGreen.isSelected());
//         * and loading text is not seen,
        WebElement finishGreen = driver.findElement(By.id("finish_green"));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("finish_green"), "Loaded"));


        assertFalse(loadingGreen.isDisplayed());
//         * success is seen instead "Green Loaded"
        assertTrue(finishGreen.isDisplayed());
    }

    @Test
    public void loadGreenAndBlueBonus() {
//        /* TODO:
//         * 0) wait until button to load green and blue appears
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("start_green_and_blue"), "Start loading green and blue"));

//         * 1) click on start loading green and blue button
        WebElement startGreenAndBlue = driver.findElement(By.id("start_green_and_blue"));
        startGreenAndBlue.click();
//         * 2) check that button does not appear, but loading text is seen instead for green
        assertFalse(startGreenAndBlue.isDisplayed());
        WebElement loadingGreen = driver.findElement(By.id("loading_green_without_blue"));
        assertTrue(loadingGreen.isDisplayed());

//         * 3) check that button does not appear, but loading text is seen instead for green and blue
        assertFalse(startGreenAndBlue.isDisplayed());
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("loading_green_with_blue"), "Loading"));
        WebElement loadingBlue = driver.findElement(By.id("loading_green_with_blue"));
        assertTrue(loadingBlue.isDisplayed());

//         * 4) check that button and loading green does not appear,
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("loading_blue_without_green"), "waiting"));
        WebElement loadingBlueWithout = driver.findElement(By.id("loading_blue_without_green"));
        assertFalse(startGreenAndBlue.isDisplayed());
        assertFalse(loadingGreen.isDisplayed());
        assertTrue(loadingBlueWithout.isDisplayed());



//         * 		but loading text is seen instead for blue and success for green is seen
//         * 5) check that both button and loading text is not seen, success is seen instead
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("finish_green_and_blue"), "Loaded"));
        WebElement finishGreenAndBlue = driver.findElement(By.id("finish_green_and_blue"));
        assertFalse(loadingGreen.isDisplayed());
        assertFalse(loadingBlue.isDisplayed());
        assertTrue(finishGreenAndBlue.isDisplayed());

//         */


      //  wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//p"), "dev"));

    }

}