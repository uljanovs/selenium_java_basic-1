package selenium.sample;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class Sample2Task {
    WebDriver driver;

    // method which is being run before each test
    @Before
    public void startingTests() throws Exception {
        // from Sample 1:
        String libWithDriversLocation = System.getProperty("user.dir") + "\\lib\\";
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver.exe");
        // declaration above:
        driver = new ChromeDriver();

        //open page:
        driver.get("https://kristinek.github.io/site/examples/locators");
    }

    // method which is being run after each test
    @After
    public void endingTests() throws Exception {
        driver.quit();
    }

    @Test
    public void findElementByID() throws Exception {
//         get text "Heading 2 text" using id
        System.out.println(driver.findElement(By.id("heading_2")).getText());
    }

    @Test
    public void findElementByName() throws Exception {
//         get attribute "id" and "value" of button "This is also a button" using name
        assertEquals("This is also a button", driver.findElement(By.name("randomButton2")).getAttribute("value"));
        System.out.println(driver.findElement(By.name("randomButton2")).getAttribute("id"));
        System.out.println(driver.findElement(By.name("randomButton2")).getAttribute("value"));
    }

    @Test
    public void findElementByClassFirst() throws Exception {
//         get first text of class "test" (should be "Test Text 1")
        assertEquals("Test Text 1", driver.findElement(By.className("test")).getText());
        System.out.println(driver.findElement(By.className("test")).getText());
    }

    @Test
    public void findElementByClassAll() throws Exception {
//         get size text of class "test" (should be 5)
//         get text of class "test"
//         get third text of class "test" (should be "Test Text 4")
        assertEquals(5, driver.findElements(By.className("test")).size());
        System.out.println(driver.findElements(By.className("test")).size());
        List<WebElement> allElementsWithClass = driver.findElements(By.className("test"));
        for (WebElement elementWithClass : allElementsWithClass) {
            System.out.println(elementWithClass.getText());
        }
        assertEquals("Test Text 4", allElementsWithClass.get(2).getText());
    }
}
