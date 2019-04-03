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

    @Before
    public void openPage() {
        String libWithDriversLocation = System.getProperty("user.dir") + "\\lib\\";
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://kristinek.github.io/site/examples/loading_color");
        wait = (WebDriverWait) new WebDriverWait(driver, 10).ignoring(StaleElementReferenceException.class);
    }

    @After
    public void closeBrowser() {
        driver.quit();
    }

    @Test
    public void loadGreenSleep() throws Exception {
//         TODO:
//         * 1) click on start loading green button
        WebElement greenbutton = driver.findElement(By.id("start_green"));
        greenbutton.click();
        TimeUnit.MILLISECONDS.sleep(500);

//         * 2) check that button does not appear,
//         * but loading text is seen instead   "Loading green..."
        assertFalse(greenbutton.isDisplayed());
        assertTrue(driver.findElement(By.id("loading_green")).isDisplayed());
        assertEquals("Loading green...", driver.findElement(By.id("loading_green")).getText());
        TimeUnit.SECONDS.sleep(7);


//         * 3) check that both button
//         * and loading text is not seen,
//         * success is seen instead "Green Loaded"
        assertFalse(driver.findElement(By.id("loading_green")).isDisplayed());
        assertFalse(greenbutton.isDisplayed());
        assertTrue(driver.findElement(By.id("finish_green")).isDisplayed());
        assertEquals("Green Loaded", driver.findElement(By.id("finish_green")).getText());
    }

    @Test
    public void loadGreenImplicit() throws Exception { // waits for the element to be displayed, so the order of actions is
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//         TODO:
//         * 1) click on start loading green button
        WebElement greenbutton = driver.findElement(By.id("start_green"));
        greenbutton.click();

//         * 2) check that button does not appear,
//         * but loading text is seen instead   "Loading green..."
        assertTrue(driver.findElement(By.id("loading_green")).isDisplayed());
        assertFalse(greenbutton.isDisplayed());
        assertEquals("Loading green...", driver.findElement(By.id("loading_green")).getText());

//         * 3) check that both button
//         * and loading text is not seen,
//         * success is seen instead "Green Loaded"
        assertTrue(driver.findElement(By.id("finish_green")).isDisplayed());
        assertFalse(greenbutton.isDisplayed());
        assertFalse(driver.findElement(By.id("loading_green")).isDisplayed());
    }

    @Test
    public void loadGreenExplicitWait() throws Exception {
//         TODO:
//         * 1) click on start loading green button
        WebElement greenbutton = driver.findElement(By.id("start_green"));
        wait.until(ExpectedConditions.elementToBeClickable(greenbutton));
        greenbutton.click();

//         * 2) check that button does not appear,
//         * but loading text is seen instead   "Loading green..."
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loading_green")));
        assertTrue(driver.findElement(By.id("loading_green")).isDisplayed());
        assertEquals("Loading green...", driver.findElement(By.id("loading_green")).getText());
        assertFalse(greenbutton.isDisplayed());

//         * 3) check that both button
//         * and loading text is not seen,
//         * success is seen instead "Green Loaded"
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("finish_green")));
        assertFalse(driver.findElement(By.id("loading_green")).isDisplayed());
        assertFalse(greenbutton.isDisplayed());
        assertEquals("Green Loaded", driver.findElement(By.id("finish_green")).getText());



    }

    @Test
    public void loadGreenAndBlueBonus() {
        //* TODO:
        // * 0) wait until button to load green and blue appears
         wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("start_green_and_blue")));

        // * 1) click on start loading green and blue button
        driver.findElement(By.id("start_green_and_blue")).click();

         //* 2) check that button does not appear, but loading text is seen instead for green
        assertFalse(driver.findElement(By.id("start_green_and_blue")).isDisplayed());
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("loading_green_without_blue")));
        assertTrue(driver.findElement(By.id("loading_green_without_blue")).isDisplayed());

         //* 3) check that button does not appear, but loading text is seen instead for green and blue
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("loading_green_with_blue")));
        assertTrue(driver.findElement(By.id("loading_green_with_blue")).isDisplayed());
        assertFalse(driver.findElement(By.id("start_green_and_blue")).isDisplayed());

         //* 4) check that button and loading green does not appear,
         //* 		but loading text is seen instead for blue and success for green is seen
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("loading_blue_without_green")));
        assertTrue(driver.findElement(By.id("loading_blue_without_green")).isDisplayed());
        assertFalse(driver.findElement(By.id("loading_green_without_blue")).isDisplayed());
        assertFalse(driver.findElement(By.id("start_green_and_blue")).isDisplayed());

        // * 5) check that both button and loading text is not seen, success is seen instead
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("finish_green_and_blue")));
        assertTrue(driver.findElement(By.id("finish_green_and_blue")).isDisplayed());
        assertFalse(driver.findElement(By.id("start_green_and_blue")).isDisplayed());
        assertFalse(driver.findElement(By.id("loading_blue_without_green")).isDisplayed());
         //*/
    }

}