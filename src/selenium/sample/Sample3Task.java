package selenium.sample;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

import static org.junit.Assert.*;

public class Sample3Task {
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
    public void assertEqualsTask() throws Exception {
//         TODO:
//         check how many element with class "test" there are on page (5)
//         check that value of second button is "This is also a button"
        System.out.println(driver.findElements(By.className("test")).size());
        assertEquals("5", driver.findElements(By.className("test")).size());

        String expected = "This is also a button";
        String actual = driver.findElements(By.xpath("//input")).get(1).getAttribute("value");
        assertEquals(expected, actual);
    }

    @Test
    public void assertTrueTask() throws Exception {
//         TODO:
//         check that it is True that value of second button is
//         "this is Also a Button" if you ignore Caps Locks
//         fail with custom error message:

        assertTrue("Custom message", driver.findElement(By.name("randomButton2")).getAttribute("value").equalsIgnoreCase("this is Also a Button"));

        String expected = "This is Also a button";
        String actual = driver.findElements(By.xpath("//input")).get(1).getAttribute("value");

        assertTrue("custom error message", actual.equalsIgnoreCase(expected));

        // ----------------------------

        // ----------------------------

        try {
            assertEquals(expected, actual);
        } catch (AssertionError e) {
            System.err.println("We failed ");
            e.printStackTrace();
//            We failed
//            java.lang.AssertionError: expected:<3> but was:<4>
        }


        try {
            assertEquals("custom message", actual, expected);
        } catch (AssertionError e) {
            System.err.println("We failed with custom message”");
            e.printStackTrace();
        }

    }

    @Test
    public void assertFalseTask() throws Exception {
//         TODO:
//        check that it is False that value of second button is "This is a button"
        String expected = "This is a button";
        String actual = driver.findElements(By.xpath("//input")).get(1).getAttribute("value");

        assertFalse(expected.equals(actual));
    }

    @Test
    public void failTask() throws Exception {
//        TODO:
//        check that none of items with class "test"
//        contain number 190

/*
    List<WebElement> elementsWithClass = driver.findElements(By.cssSelector(".test"));

    for (WebElement element : elementsWithClass) {
        assertTrue(element.getText().contains("5"));
    }
*/

    List<WebElement> someElements = driver.findElements(By.className("test"));
    for (WebElement someElement : someElements) {
        assertTrue(!someElement.getText().contains("190"));
        assertFalse(someElement.getText().contains("190"));
    }

    }
}
