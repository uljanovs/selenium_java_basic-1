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

import static org.junit.Assert.*;

public class Sample9Task {
    WebDriver driver;
    private static WebDriverWait wait;
    static long startTime;

    @Before
    public void openPage() {
        String libWithDriversLocation = System.getProperty("user.dir") + "\\lib\\";
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://kristinek.github.io/site/examples/loading_color");

        wait = (WebDriverWait) new WebDriverWait(driver, 10).ignoring(StaleElementReferenceException.class);

        startTime = System.currentTimeMillis();
    }

    @After
    public void closeBrowser() {
        driver.quit();
    }

    @Test
    public void loadGreenSleep() throws Exception {
//         TODO:
//         * 1) click on start loading green button
        driver.findElement(By.id("start_green")).click();
//         * 2) check that button does not appear,
        assertFalse(driver.findElement(By.id("start_green")).isDisplayed());
//         * but loading text is seen instead   "Loading green..."
        assertEquals("Loading green...", driver.findElement(By.id("loading_green")).getText());
//         * 3) check that both button
        Thread.sleep(5000);
        assertFalse(driver.findElement(By.id("start_green")).isDisplayed());
        assertFalse(driver.findElement(By.id("loading_green")).isDisplayed());
//         * and loading text is not seen,
//         * success is seen instead "Green Loaded"
        Thread.sleep(5000);
        assertEquals("Green Loaded", driver.findElement(By.id("finish_green")).getText());
    }

    @Test
    public void loadGreenImplicit() throws Exception {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//         TODO:
//         * 1) click on start loading green button
        driver.findElement(By.id("start_green")).click();
//         * 2) check that button does not appear,
        assertFalse(driver.findElement(By.id("start_green")).isDisplayed());
//         * but loading text is seen instead   "Loading green..."
        assertTrue(driver.findElement(By.id("loading_green")).isDisplayed());
//         * 3) check that both button
        WebElement finishGreen = driver.findElement(By.id("finish_green"));
        assertFalse(driver.findElement(By.id("start_green")).isDisplayed());
        assertFalse(driver.findElement(By.id("loading_green")).isDisplayed());
//         * and loading text is not seen,
//         * success is seen instead "Green Loaded"
        assertEquals("Green Loaded", finishGreen.getText());
    }

    @Test
    public void loadGreenExplicitWait() throws Exception {
//         TODO:
//         * 1) click on start loading green button
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#start_green")));
        WebElement startGreen = driver.findElement(By.id("start_green"));
        startGreen.click();
//         * 2) check that button does not appear,
        assertFalse(startGreen.isDisplayed());
//         * but loading text is seen instead   "Loading green..."
        assertEquals("Loading green...", driver.findElement(By.id("loading_green")).getText());
//         * 3) check that both button
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("finish_green"), "Loaded"));
//         * and loading text is not seen,
        assertFalse(startGreen.isDisplayed());
        assertFalse(driver.findElement(By.id("loading_green")).isDisplayed());
//         * success is seen instead "Green Loaded"
        assertEquals("Green Loaded", driver.findElement(By.id("finish_green")).getText());
    }

    @Test
    public void loadGreenAndBlueBonus() {
        TODO:

//         * 0) wait until button to load green and blue appears
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("start_green_and_blue")));

//         * 1) click on start loading green and blue button
        WebElement greenAndBlueStart = driver.findElement(By.id("start_green_and_blue"));
        greenAndBlueStart.click();

//         * 2) check that button does not appear, but loading text is seen instead for green
        WebElement greenNoBlue = driver.findElement(By.id("loading_green_without_blue"));

        assertFalse(greenAndBlueStart.isDisplayed());
        assertTrue(greenNoBlue.isDisplayed());

//         * 3) check that button does not appear, but loading text is seen instead for green and blue
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("loading_green_with_blue")));
        WebElement greenWithBlue = driver.findElement(By.id("loading_green_with_blue"));

        assertFalse(greenAndBlueStart.isDisplayed());
        assertTrue(greenNoBlue.isDisplayed());
        assertTrue(greenWithBlue.isDisplayed());
//         * 4) check that button and loading green does not appear,
//         * 		but loading text is seen instead for blue and success for green is seen
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("loading_blue_without_green"), "waiting"));
        WebElement waitingBlue = driver.findElement(By.id("loading_blue_without_green"));
        assertFalse(greenAndBlueStart.isDisplayed());
        assertFalse(greenNoBlue.isDisplayed());
        assertTrue(waitingBlue.isDisplayed());

//         * 5) check that both button and loading text is not seen, success is seen instead
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("finish_green_and_blue"), "Loaded"));
        WebElement finishGreenAndBlue = driver.findElement(By.id("finish_green_and_blue"));

        assertFalse(greenAndBlueStart.isDisplayed());
        assertFalse(greenNoBlue.isDisplayed());
        assertFalse(greenAndBlueStart.isDisplayed());
        assertFalse(waitingBlue.isDisplayed());

        assertEquals("Green and Blue Loaded", finishGreenAndBlue.getText());

    }

}