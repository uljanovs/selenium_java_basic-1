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
//        2 ways to find text: "Heading 2 text": Heading 2 text
        System.out.println(driver.findElement(By.xpath("//*[@id='heading_2']")).getText());
        System.out.println(driver.findElement(By.xpath("//*[text()='Heading 2 text']")).getText());
        System.out.println(driver.findElement(By.xpath("//h2[@id='heading_2']")).getText());
//        1-2 ways to find text: "Test Text 1" id = test1 class = test
        System.out.println(driver.findElement(By.xpath("//*[text()='Test Text 1']")).getText());
        System.out.println(driver.findElement(By.xpath("//*[@id='test1']/p[1]")).getText());
//        1-2 ways to find text: "Test Text 2" class = twoTest
        System.out.println(driver.findElement(By.xpath("//*[text()='Test Text 2']")).getText());
        System.out.println(driver.findElement(By.xpath("//*[@id='test1']/p[2]")).getText());
        System.out.println(driver.findElement(By.xpath("//*[@class='twoTest']")).getText());
//        1-2 ways to find text: "Test Text 3"
        System.out.println(driver.findElement(By.xpath("//*[text()='Test Text 3']")).getText());
        System.out.println(driver.findElement(By.xpath("//*[@id='test3']/p[1]")).getText());
//        1-2 ways to find text: "Test Text 4"
        System.out.println(driver.findElement(By.xpath("//*[text()='Test Text 4']")).getText());
        System.out.println(driver.findElement(By.xpath("//*[@id='test3']/p[2]")).getText());

        //System.out.println(driver.findElement(By.xpath("//*[@id='nonStandartText']/p[contains(@class, 'amazing')]")).getText());

//        1-2 ways to find text: "Test Text 5"
        System.out.println(driver.findElement(By.xpath("//*[text()='Test Text 5']")).getText());
        System.out.println(driver.findElement(By.xpath("//*[@id='test2']/p[1]")).getText());
//        1-2 ways to find text: "This is also a button" buttonId type button
        System.out.println(driver.findElement(By.xpath("//*[@name='randomButton2']")).getAttribute("value"));
        System.out.println(driver.findElement(By.xpath("//*[@id='buttonId']")).getAttribute("value"));
        System.out.println(driver.findElement(By.xpath("//*[@type='button' and @id='buttonId']")).getAttribute("value"));

    }

    @Test
    public void findElementByCssName() throws Exception {
//         TODO:
//        1-2 ways to find text: "Heading 2 text"
        System.out.println(driver.findElement(By.cssSelector("#heading_2")).getText());
//        1-2 ways to find text: "Test Text 1" id = test1 class = test
        System.out.println(driver.findElement(By.cssSelector("#test1 .test")).getText());
        System.out.println(driver.findElement(By.cssSelector(".test")).getText()); // bc 1st of class 'test'
        //System.out.println(driver.findElement(By.cssSelector("#test>p:nth-child(1)")).getText());
//        1-2 ways to find text: "Test Text 2"
        System.out.println(driver.findElement(By.cssSelector(".twoTest")).getText());
        System.out.println(driver.findElement(By.cssSelector("#test1 .twoTest")).getText());
//        1-2 ways to find text: "Test Text 3"
        System.out.println(driver.findElement(By.cssSelector("#test3 .test")).getText()); // with space in case of child element, w/o space if element has both attributes
//  1-2 ways to find text: "This is also a button" buttonId name=randomButton2
        System.out.println(driver.findElement(By.cssSelector("#buttonId")).getAttribute("value"));
        System.out.println(driver.findElement(By.cssSelector("input[name='randomButton2'")).getAttribute("value"));
        System.out.println(driver.findElement(By.cssSelector(".randomButton2")).getAttribute("value"));
        System.out.println(driver.findElement(By.cssSelector("input[name='randomButton2'")).getAttribute("value"));
    }
}
