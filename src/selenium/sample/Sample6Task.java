package selenium.sample;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static junit.framework.TestCase.assertEquals;

public class Sample6Task {
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
    public void findElementByXPath() throws Exception {
//         TODO:
//        2 ways to find text: "Heading 2 text":
       assertEquals("Heading 2 text", driver.findElement(By.xpath("//*[@id='heading_2']")).getText()) ;
        assertEquals("Heading 2 text", driver.findElement(By.xpath("//h2[@id='heading_2']")).getText());
//        1-2 ways to find text: "Test Text 1"
        assertEquals("Test Text 1", driver.findElement(By.xpath("//div/p[@class='test']")).getText() );
        assertEquals("Test Text 1", driver.findElement(By.xpath("//*[@id='test1']/p")).getText());
//        1-2 ways to find text: "Test Text 2"
        assertEquals("Test Text 2", driver.findElement(By.xpath("//div/p[@class='twoTest']")).getText());
        assertEquals("Test Text 2", driver.findElement(By.xpath("//*[@id='test1']/p[2]")).getText());
//        1-2 ways to find text: "Test Text 3"
        assertEquals("Test Text 3", driver.findElement(By.xpath("//p[.='Test Text 3']")).getText());
        assertEquals("Test Text 3", driver.findElement(By.xpath("//*[@id='test3']/p")).getText());
//        1-2 ways to find text: "Test Text 4"
        assertEquals("Test Text 4", driver.findElement(By.xpath("//div[@id='test3']/p[2]")).getText() );
        assertEquals("Test Text 4", driver.findElement(By.xpath("//*[@id='test3']/p[2]")).getText());
//        1-2 ways to find text: "Test Text 5"
        assertEquals("Test Text 5", driver.findElement(By.xpath("//div[@id='test2']/p")).getText());
        assertEquals("Test Text 5", driver.findElement(By.xpath("//*[@id='test2']/p")).getText());
//        1-2 ways to find text: "This is also a button"
        assertEquals("This is also a button", driver.findElement(By.xpath("//*[@id='buttonId']")).getAttribute("value"));
        assertEquals("This is also a button", driver.findElement(By.xpath("//*[@name='randomButton2']")).getAttribute("value"));
        assertEquals("This is also a button", driver.findElement(By.xpath("//input[2]")).getAttribute("value"));
    }

    @Test
    public void findElementByCssName() throws Exception {
//         TODO:
//        1-2 ways to find text: "Heading 2 text"
        assertEquals("Heading 2 text", driver.findElement(By.cssSelector("#heading_2")).getText());
        assertEquals("Heading 2 text", driver.findElement(By.cssSelector("h2#heading_2")).getText());
//        1-2 ways to find text: "Test Text 1"
        assertEquals("Test Text 1", driver.findElement(By.cssSelector("#test1 p")).getText());
        assertEquals("Test Text 1", driver.findElement(By.cssSelector("#test1>p:first-child")).getText());
//        1-2 ways to find text: "Test Text 2"
        assertEquals("Test Text 2",driver.findElement(By.cssSelector("#test1 > p:nth-child(2)")).getText());
        assertEquals("Test Text 2",driver.findElement(By.cssSelector("#test1 > p:last-child")).getText());
//        1-2 ways to find text: "Test Text 3"
        assertEquals("Test Text 3",driver.findElement(By.cssSelector("div#test3 p")).getText());
        assertEquals("Test Text 3", driver.findElement(By.cssSelector("#test3>p:nth-child(1)")).getText());

//        1-2 ways to find text: "Test Text 4"
        assertEquals("Test Text 4", driver.findElement(By.cssSelector("#test3 > p:nth-child(2)")).getText());
        assertEquals("Test Text 4", driver.findElement(By.cssSelector("#test3 > p:last-child")).getText());
//        1-2 ways to find text: "This is also a button"
        assertEquals("This is also a button", driver.findElement(By.cssSelector("#buttonId")).getAttribute("value"));

    }
}
