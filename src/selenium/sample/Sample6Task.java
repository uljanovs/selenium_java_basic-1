package selenium.sample;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static junit.framework.TestCase.assertEquals;

public class Sample6Task {
    private WebDriver driver;

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
    public void findElementByXPath() throws Exception {
//        2 ways to find text: "Heading 2 text":
        assertEquals("Heading 2 text", driver.findElement(By.xpath("//*[@id='heading_2']")).getText());
        assertEquals("heading_2", driver.findElement(By.xpath("//*[text()='Heading 2 text']")).getAttribute("id"));
//        1-2 ways to find text: "Test Text 1"
        assertEquals("Test Text 1", driver.findElement(By.xpath("//div[@id='test1']/p[@class='test']")).getText());
        assertEquals("test", driver.findElement(By.xpath("//*[text()='Test Text 1']")).getAttribute("class"));
//        1-2 ways to find text: "Test Text 2"
        assertEquals("Test Text 2", driver.findElement(By.xpath("//*[@class='twoTest']")).getText());
        assertEquals("twoTest", driver.findElement(By.xpath("//*[text()='Test Text 2']")).getAttribute("class"));
//        1-2 ways to find text: "Test Text 3"
        assertEquals("Test Text 3", driver.findElement(By.xpath("//div[@id='test3']/p[@class='test'][1]")).getText());
        assertEquals("test", driver.findElement(By.xpath("//*[text()='Test Text 3']")).getAttribute("class"));
//        1-2 ways to find text: "Test Text 4"
        assertEquals("Test Text 4", driver.findElement(By.xpath("//div[@id='test3']/p[@class='test'][2]")).getText());
        assertEquals("test", driver.findElement(By.xpath("//*[text()='Test Text 4']")).getAttribute("class"));
//        1-2 ways to find text: "Test Text 5"
        assertEquals("Test Text 5", driver.findElement(By.xpath("//*[@class='Test']")).getText());
        assertEquals("Test", driver.findElement(By.xpath("//*[text()='Test Text 5']")).getAttribute("class"));
//        1-2 ways to find text: "This is also a button"
        assertEquals("This is also a button", driver.findElement(By.xpath("//*[@id='buttonId']")).getAttribute("value"));
        assertEquals("This is also a button", driver.findElement(By.xpath("//*[@name='randomButton2']")).getAttribute("value"));
        assertEquals("randomButton2", driver.findElement(By.xpath("//*[@value='This is also a button']")).getAttribute("name"));
    }

    @Test
    public void findElementByCssName() throws Exception {
//        1-2 ways to find text: "Heading 2 text"
        assertEquals("Heading 2 text", driver.findElement(By.cssSelector("#heading_2")).getText());
        assertEquals("heading_2", driver.findElement(By.cssSelector("h2:nth-of-type(2)")).getAttribute("id"));
//        1-2 ways to find text: "Test Text 1"
        assertEquals("Test Text 1", driver.findElement(By.cssSelector("#test1 > .test")).getText());
        assertEquals("test", driver.findElement(By.cssSelector("#test1 *:nth-child(1)")).getAttribute("class"));
//        1-2 ways to find text: "Test Text 2"
        assertEquals("Test Text 2", driver.findElement(By.cssSelector(".twoTest")).getText());
        assertEquals("twoTest", driver.findElement(By.cssSelector("#test1 *:nth-child(2)")).getAttribute("class"));
//        1-2 ways to find text: "Test Text 3"
        assertEquals("Test Text 3", driver.findElement(By.cssSelector("#test3 *:nth-child(1)")).getText());
//        1-2 ways to find text: "This is also a button"
        assertEquals("This is also a button", driver.findElement(By.cssSelector("#buttonId")).getAttribute("value"));
        assertEquals("This is also a button", driver.findElement(By.cssSelector("*[name='randomButton2']")).getAttribute("value"));
    }
}
