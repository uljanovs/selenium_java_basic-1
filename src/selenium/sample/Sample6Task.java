package selenium.sample;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

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
        System.out.println("Find element by id using xPath:");
//        2 ways to find text: "Heading 2 text":
        System.out.println(driver.findElement(By.xpath("//*[@id='heading_2']")).getText());
//        1-2 ways to find text: "Test Text 1"
        System.out.println(driver.findElements(By.xpath("//*[@class='test']")).get(0).getText());
//        1-2 ways to find text: "Test Text 2"
        System.out.println(driver.findElement(By.xpath("//*[@class='twoTest']")).getText());
//        1-2 ways to find text: "Test Text 3"
        System.out.println(driver.findElements(By.xpath("//*[@class='test']")).get(1).getText());
//        1-2 ways to find text: "Test Text 4"
        System.out.println(driver.findElements(By.xpath("//*[@class='test']")).get(2).getText());
//        1-2 ways to find text: "Test Text 5"
        System.out.println(driver.findElement(By.xpath("//*[@class='Test']")).getText());
//        1-2 ways to find text: "This is also a button"
        System.out.println(driver.findElement(By.xpath("//*[@name='randomButton2']")).getAttribute("value"));
    }

    @Test
    public void findElementByCssName() throws Exception {
//         TODO:
        System.out.println("Find element by id using CSS:");
//        1-2 ways to find text: "Heading 2 text"
        System.out.println(driver.findElement(By.cssSelector("h2#heading_2")).getText());
//        1-2 ways to find text: "Test Text 1"
        System.out.println(driver.findElements(By.className("test")).get(0).getText());
//        1-2 ways to find text: "Test Text 2"
        System.out.println(driver.findElement(By.className("twoTest")).getText());
//        1-2 ways to find text: "Test Text 3"
        System.out.println(driver.findElements(By.className("test")).get(1).getText());
//        1-2 ways to find text: "This is also a button"
        System.out.println(driver.findElement(By.id("buttonId")).getAttribute("value"));
    }
}
