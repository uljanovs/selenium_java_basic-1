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

        System.out.println(driver.findElement(By.xpath("//*[@id='heading_2']")).getText());
        System.out.println(driver.findElement(By.xpath("//*[contains(text(), 'Heading 2 text')]")).getText() + "\n");


//        1-2 ways to find text: "Test Text 1"

        System.out.println(driver.findElement(By.xpath("//*[@id='test1']/p[1]")).getText());
        System.out.println(driver.findElement(By.xpath("//*[contains(text(), 'Test Text 1')]")).getText() + "\n");


//        1-2 ways to find text: "Test Text 2"
        System.out.println(driver.findElement(By.xpath("//*[@id='test1']/p[2]")).getText());
        System.out.println(driver.findElement(By.xpath("//*[contains(text(), 'Test Text 2')]")).getText() + "\n");


//        1-2 ways to find text: "Test Text 3"
        System.out.println(driver.findElement(By.xpath("//*[@id='test3']/p[1]")).getText());
        System.out.println(driver.findElement(By.xpath("//*[contains(text(), 'Test Text 3')]")).getText() + "\n");


//        1-2 ways to find text: "Test Text 4"

        System.out.println(driver.findElement(By.xpath("//*[@id='test3']/p[2]")).getText());
        System.out.println(driver.findElement(By.xpath("//*[contains(text(), 'Test Text 4')]")).getText() + "\n");


//        1-2 ways to find text: "Test Text 5"
        System.out.println(driver.findElement(By.xpath("//*[@id='test2']/p[1]")).getText());
        System.out.println(driver.findElement(By.xpath("//*[contains(text(), 'Test Text 5')]")).getText() + "\n");


        //        1-2 ways to find text: "This is also a button"
        System.out.println(driver.findElement(By.xpath(" //*[@name='randomButton1']")).getAttribute("value"));
        System.out.println(driver.findElement(By.xpath(" //*[@value='This is a button']")).getAttribute("value") + "\n");


    }

    @Test
    public void findElementByCssName() throws Exception {
//         TODO:
//        1-2 ways to find text: "Heading 2 text"

        System.out.println(driver.findElement(By.cssSelector("#heading_2")).getText() + "\n");


//        1-2 ways to find text: "Test Text 1"
        System.out.println(driver.findElement(By.cssSelector("#test1 > p.test")).getText() + "\n");


//        1-2 ways to find text: "Test Text 2"
        System.out.println(driver.findElement(By.cssSelector("#test1 > p.twoTest")).getText() + "\n");


//        1-2 ways to find text: "Test Text 3"

        System.out.println(driver.findElement(By.cssSelector("#test3 > p:nth-child(1)")).getText() + "\n");

//        1-2 ways to find text: "This is also a button"
        System.out.println(driver.findElement(By.cssSelector("body > input[type=\"button\"]:nth-child(9)")).getAttribute("value") + "\n");


    }
}
