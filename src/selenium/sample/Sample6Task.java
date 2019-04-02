package selenium.sample;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

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
        System.out.println("finding 'Heading 2 text' : " +
            driver.findElement(By.xpath("//*[@id='heading_2']")).getText() + "' " +
            driver.findElement(By.xpath("//h2[2]")).getText() + "'");

//        1-2 ways to find text: "Test Text 1"
        System.out.println("finding 'Test Text 1' : " +
                driver.findElement(By.xpath("//*[@id='test1']//p[1]")).getText() + "' " +
                driver.findElement(By.xpath("//*[@class='test']")).getText() + "'");

//        1-2 ways to find text: "Test Text 2"
        System.out.println("finding 'Test Text 2' : " +
                driver.findElement(By.xpath("//*[@id='test1']//p[2]")).getText() + "' " +
                driver.findElement(By.xpath("//*[@class='twoTest']")).getText() + "'");

//        1-2 ways to find text: "Test Text 3"
        System.out.println("finding 'Test Text 3' : " +
                driver.findElement(By.xpath("//*[@id='test3']//p[1]")).getText() + "' " +
                driver.findElement(By.xpath("//*[@id='test3']//*[@class='test']")).getText() + "'");

//        1-2 ways to find text: "Test Text 4"
        System.out.println("finding 'Test Text 4' : " +
                driver.findElement(By.xpath("//*[@id='test3']//p[2]")).getText() + "' " +
                driver.findElement(By.xpath("//*[@id='test3']//*[@class='test'][2]")).getText() + "'");

//        1-2 ways to find text: "Test Text 5"
        System.out.println("finding 'Test Text 5' : " +
                driver.findElement(By.xpath("//*[@id='test2']//p[1]")).getText() + "' " +
                driver.findElement(By.xpath("//*[@class='Test']")).getText() + "'");

//        1-2 ways to find text: "This is also a button"
        System.out.println("finding 'This is also a button' : " +
                driver.findElement(By.xpath("//*[@id='buttonId']")).getAttribute("value") + "' " +
                driver.findElement(By.xpath("//input[2]")).getAttribute("value") + "'");
    }

    @Test
    public void findElementByCssName() throws Exception {
//         TODO:

//        1-2 ways to find text: "Heading 2 text"
        System.out.println("finding 'Heading 2 text' : " +
                driver.findElement(By.cssSelector("#heading_2")).getText() + "' " +
                driver.findElement(By.cssSelector("h2:nth-of-type(2)")).getText() + "'");

//        1-2 ways to find text: "Test Text 1"
        System.out.println("finding 'Test Text 1' : " +
                driver.findElement(By.cssSelector("#test1 > p:nth-child(1)")).getText() + "' " +
                driver.findElement(By.cssSelector(".test")).getText() + "'");

//        1-2 ways to find text: "Test Text 2"
        System.out.println("finding 'Test Text 2' : " +
                driver.findElement(By.cssSelector("#test1 > p:nth-child(2)")).getText() + "' " +
                driver.findElement(By.cssSelector(".twoTest")).getText() + "'");

//        1-2 ways to find text: "Test Text 3"
        System.out.println("finding 'Test Text 3' : " +
                driver.findElement(By.cssSelector("#test3 > p:nth-child(1)")).getText() + "' " +
                driver.findElement(By.cssSelector("#test3 .test")).getText() + "'");

//        1-2 ways to find text: "This is also a button"

        System.out.println("finding 'This is also a button' : " +
                driver.findElement(By.cssSelector("#buttonId")).getAttribute("value") + "' " +
                driver.findElement(By.cssSelector("input:nth-of-type(2)")).getAttribute("value") + "' ");
    }
}
