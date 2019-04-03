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
        WebElement greenButton = driver.findElement(By.cssSelector("#start_green"));

        greenButton.click();
//         * 2) check that button does not appear,
//         * but loading text is seen instead   "Loading green..."
        TimeUnit.SECONDS.sleep(3);
        assertFalse(greenButton.isDisplayed());

        WebElement loadingGreenText = driver.findElement(By.cssSelector("#loading_green"));

        assertTrue(loadingGreenText.isDisplayed());
//         * 3) check that both button
//         * and loading text is not seen,
//         * success is seen instead "Green Loaded"
        TimeUnit.SECONDS.sleep(3);

        WebElement greenLoadedText = driver.findElement(By.cssSelector("#finish_green"));

        assertFalse(loadingGreenText.isDisplayed());

        assertTrue(greenLoadedText.isDisplayed());
    }

    @Test
    public void loadGreenImplicit() throws Exception {
//         TODO:
//         * 1) click on start loading green button
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        WebElement greenButton = driver.findElement(By.cssSelector("#start_green"));

        greenButton.click();
//         * 2) check that button does not appear,
//         * but loading text is seen instead   "Loading green..."


        assertFalse(greenButton.isDisplayed());

        WebElement loadingGreenText = driver.findElement(By.cssSelector("#loading_green"));

        assertTrue(loadingGreenText.isDisplayed());
//         * 3) check that both button
//         * and loading text is not seen,
//         * success is seen instead "Green Loaded"
        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        assertFalse(greenButton.isDisplayed());

        WebElement greenLoadedText = driver.findElement(By.cssSelector("#finish_green"));

        assertFalse(loadingGreenText.isDisplayed());


        assertTrue(greenLoadedText.isDisplayed());
    }

    @Test
    public void loadGreenExplicitWait() throws Exception {
//         TODO:
//         * 1) click on start loading green button
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#start_green")));
        WebElement greenButton = driver.findElement(By.cssSelector("#start_green"));

        greenButton.click();
//         * 2) check that button does not appear,
//         * but loading text is seen instead   "Loading green..."
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#loading_green")));
        WebElement loadingGreenText = driver.findElement(By.cssSelector("#loading_green"));

        assertFalse(greenButton.isDisplayed());
        assertTrue(loadingGreenText.isDisplayed());
//         * 3) check that both button
//         * and loading text is not seen,
//         * success is seen instead "Green Loaded"
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#finish_green")));
        WebElement greenLoadedText = driver.findElement(By.cssSelector("#finish_green"));

        assertFalse(greenButton.isDisplayed());
        assertFalse(loadingGreenText.isDisplayed());
        assertTrue(greenLoadedText.isDisplayed());
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
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#start_green_and_blue")));

        WebElement greenAndBlueButton = driver.findElement(By.cssSelector("#start_green_and_blue"));

        greenAndBlueButton.click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#loading_green_without_blue")));
        WebElement loadingGreenWithoutBlueText = driver.findElement(By.cssSelector("#loading_green_without_blue"));

        assertFalse(greenAndBlueButton.isDisplayed());
        assertTrue(loadingGreenWithoutBlueText.isDisplayed());

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#loading_green_with_blue")));
        WebElement loadingGreenWithBlueText = driver.findElement(By.cssSelector("#loading_green_with_blue"));

        assertFalse(greenAndBlueButton.isDisplayed());
        assertTrue(loadingGreenWithBlueText.isDisplayed());

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#loading_blue_without_green")));
        WebElement loadingBlueWithoutGreenText = driver.findElement(By.cssSelector("#loading_blue_without_green"));

        assertFalse(greenAndBlueButton.isDisplayed());
        assertFalse(loadingGreenWithoutBlueText.isDisplayed());
        assertTrue(loadingGreenWithBlueText.isDisplayed()); //  ???
        assertTrue(loadingBlueWithoutGreenText.isDisplayed());

        /*wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#finish_green_and_blue")));
        WebElement greenAndBlueLoadedText = driver.findElement(By.cssSelector("#finish_green_and_blue"));*/

    }

}