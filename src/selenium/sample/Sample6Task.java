package selenium.sample;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.*;

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
        System.out.println("Find element by id using xPath:");
        System.out.println("\t text of element with id 'heading_2' is '" +
                driver.findElement(By.xpath("//*[@id='heading_2']")).getText() + "'");
        System.out.println("\t text of element with id 'heading_2' is '" +
                driver.findElement(By.xpath("//h2[@id='heading_2']")).getText() + "'");
        assertEquals("Heading 2 text", driver.findElement(By.xpath("//*[@id='heading_2']")).getText());

//        1-2 ways to find text: "Test Text 1"
        System.out.println("\t text of element with id 'Test Text 1' is '" +
                driver.findElement(By.xpath("//*[@class='test']")).getText() + "'");
        System.out.println("\t text of element with id 'Test Text 1' is '" +
                driver.findElement(By.xpath("//*[@id='test1']/p[1]")).getText() + "'");
        assertEquals("Test Text 1", driver.findElement(By.xpath("//*[@class='test']")).getText());
        assertEquals("Test Text 1", driver.findElement(By.xpath("//*[@id='test1']/p[1]")).getText());

//        1-2 ways to find text: "Test Text 2"
        System.out.println("\t text of element with id 'Test Text 2' is '" +
                driver.findElement(By.xpath("//*[@class='twoTest']")).getText() + "'");
        assertEquals("Test Text 2", driver.findElement(By.xpath("//*[@class='twoTest']")).getText());

//        1-2 ways to find text: "Test Text 3"
        System.out.println("\t text of element with id 'Test Text 3' is '" +
                driver.findElement(By.xpath("//div[@id='test3']//p[1]")).getText() + "'");
        assertEquals("Test Text 3", driver.findElement(By.xpath("//div[@id='test3']//p[1]")).getText());

//        1-2 ways to find text: "Test Text 4"
        System.out.println("\t text of element with id 'Test Text 4' is '" +
                driver.findElement(By.xpath("//div[@id='test3']//p[2]")).getText() + "'");
        assertEquals("Test Text 4", driver.findElement(By.xpath("//div[@id='test3']//p[2]")).getText());

//        1-2 ways to find text: "Test Text 5"
        System.out.println("\t text of element with id 'Test Text 5' is '" +
                driver.findElement(By.xpath("//div[@id='test2']//p[1]")).getText() + "'");
        assertEquals("Test Text 5", driver.findElement(By.xpath("//div[@id='test2']//p[1]")).getText());

        System.out.println();

//        1-2 ways to find text: "This is also a button"
        System.out.println("Find element by other attributes using xPath:");
        System.out.println("\t name of element with value 'This is also a button' is '" +
                driver.findElement(By.xpath("//input[@id='buttonId']")).getAttribute("value") + "'");
        System.out.println("\t name of element with value 'This is also a button' is '" +
                driver.findElement(By.xpath("//input[@name='randomButton2']")).getAttribute("value") + "'");
        assertEquals("This is also a button", driver.findElement(By.xpath("//input[@id='buttonId']")).getAttribute("value"));
        assertEquals("This is also a button", driver.findElement(By.xpath("//input[@name='randomButton2']")).getAttribute("value"));

        System.out.println();
    }

    @Test
    public void findElementByCssName() throws Exception {
//         TODO:
//        1-2 ways to find text: "Heading 2 text"
        System.out.println("Find element by id using CSS:");
        System.out.println("\t text of element with id 'heading_2' is '" +
                driver.findElement(By.cssSelector("#heading_2")).getText() + "'");
        System.out.println("\t text of element with id 'heading_2' is '" +
                driver.findElement(By.cssSelector("h2#heading_2")).getText() + "'");
        assertEquals("Heading 2 text", driver.findElement(By.cssSelector("#heading_2")).getText());
        assertEquals("Heading 2 text", driver.findElement(By.cssSelector("h2#heading_2")).getText());

//        1-2 ways to find text: "Test Text 1"
        System.out.println("\t text of element with class 'test' is '" +
                driver.findElement(By.cssSelector(".test")).getText() + "'");
        System.out.println("\t text of element with class 'test' is '" +
                driver.findElement(By.cssSelector("#test1 > p.test")).getText() + "'");
        assertEquals("Test Text 1", driver.findElement(By.cssSelector(".test")).getText());
        assertEquals("Test Text 1", driver.findElement(By.cssSelector("#test1 > p.test")).getText());

//        1-2 ways to find text: "Test Text 2"
        System.out.println("\t text of element with class 'test' is '" +
                driver.findElement(By.cssSelector("p.twoTest")).getText() + "'");
        System.out.println("\t text of element with class 'test' is '" +
                driver.findElement(By.cssSelector("#test1 > p.twoTest")).getText() + "'");
        assertEquals("Test Text 2", driver.findElement(By.cssSelector("p.twoTest")).getText());
        assertEquals("Test Text 2", driver.findElement(By.cssSelector("#test1 > p.twoTest")).getText());

//        1-2 ways to find text: "Test Text 3"
        System.out.println("\t text of element with class 'test' is '" +
                driver.findElement(By.cssSelector("#test3 > p:nth-child(1)")).getText() + "'");
        assertEquals("Test Text 3", driver.findElement(By.cssSelector("#test3 > p:nth-child(1)")).getText());

//        1-2 ways to find text: "This is also a button"
        System.out.println("\t value of element with id 'buttonId' is '" +
                driver.findElement(By.cssSelector("#buttonId")).getAttribute("value") + "'");
        assertEquals("This is also a button", driver.findElement(By.cssSelector("#buttonId")).getAttribute("value"));

    }
}
