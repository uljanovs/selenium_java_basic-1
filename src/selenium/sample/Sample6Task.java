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
        System.out.println("\t text of element with id 'Heading 2' is '" +
                driver.findElement(By.xpath("//*[@id='heading_2']")).getText() + "'");

        assertEquals("Heading 2 text", driver.findElement(By.xpath("//*[@id='heading_2']")).getText());

        assertEquals("Heading 2 text", driver.findElement(By.xpath("//h2[@id='heading_2']")).getText());

        //        1-2 ways to find text: "Test Text 1"
       /* System.out.println("\t class of element with text 'Test Text 1' is '" +
                driver.findElement(By.xpath("//*[text()='Test Text 1']")).getAttribute("class") + "'");*/

        assertEquals("Test Text 1", driver.findElement(By.xpath("//*[@id='test1']/p[1]")).getText());


 //        1-2 ways to find text: "Test Text 2"
               /*System.out.println("\t text of element with class 'twoTest' is '" +
        driver.findElement(By.xpath("//*[@class='twoTest']")).getText() + "'");*/

        assertEquals("Test Text 2", driver.findElement(By.xpath("//*[@id='test1']/p[2]")).getText());

//        1-2 ways to find text: "Test Text 3"
        /*System.out.println("\t text of element which contains text 'Test Text 3' is '" +
                driver.findElement(By.xpath("//*[contains(text(), 'Test Text 3')]")).getText() + "'");

        System.out.println("\t class of element with text 'Test Text 3' is '" +
                driver.findElement(By.xpath("//*[text()='Test Text 3']")).getText() + "'");*/

        assertEquals("Test Text 3", driver.findElement(By.xpath("//*[@id='test3']/p[1]")).getText());

//        1-2 ways to find text: "Test Text 4"
        /*System.out.println("\t class of element with text 'Test Text 4' is '" +
                driver.findElement(By.xpath("//*[text()='Test Text 4']")).getAttribute("class") + "'");
        System.out.println("\t text of element which contains text 'Test Text 4' is '" +
                driver.findElement(By.xpath("//*[contains(text(), 'Test Text 4')]")).getText() + "'");*/

        assertEquals("Test Text 4", driver.findElement(By.xpath("//*[@id='test3']/p[2]")).getText());

//        1-2 ways to find text: "Test Text 5"
        /*System.out.println("\t class of element with text 'Test Text 5' is '" +
                driver.findElement(By.xpath("//*[text()='Test Text 5']")).getAttribute("class") + "'");

        System.out.println("\t text of element which contains text 'Test Text 5' is '" +
                driver.findElement(By.xpath("//*[contains(text(), 'Test Text 5')]")).getText() + "'");*/

        assertEquals("Test Text 5", driver.findElement(By.xpath("//*[@id='test2']/p[1]")).getText());

//        1-2 ways to find text: "This is also a button"
       /* System.out.println("\t text of element with id 'buttonId' is '" +
                driver.findElement(By.xpath("//*[@id='buttonId']")).getAttribute("value") + "'");*/

        assertEquals("This is also a button", driver.findElement(By.xpath("//input[@name='randomButton2']")).getAttribute("value"));
    }

    private void assertEquals(String s, String text) {
    }

    @Test
    public void findElementByCssName() throws Exception {
//         TODO:
//        1-2 ways to find text: "Heading 2 text"
        System.out.println("\t text of element with id 'heading_2' is '" +
                driver.findElement(By.cssSelector("#heading_2")).getText() + "'");

//        1-2 ways to find text: "Test Text 1"
        System.out.println("\t text of element with id 'test1' is '" +
                driver.findElement(By.cssSelector("#test1 .test")).getText() + "'");

//        1-2 ways to find text: "Test Text 2"
        System.out.println("\t text of element with id 'test1' is '" +
                driver.findElement(By.cssSelector("#test1 .twoTest")).getText() + "'");

//        1-2 ways to find text: "Test Text 3"
        System.out.println("\t text of element with id 'test3' is '" +
                driver.findElement(By.cssSelector("#test3 .test")).getText() + "'");

//        1-2 ways to find text: "This is also a button"
        System.out.println("\t text of element with id 'buttonId' is '" +
                driver.findElement(By.cssSelector("#buttonId")).getAttribute("value") + "'");

    }
}
