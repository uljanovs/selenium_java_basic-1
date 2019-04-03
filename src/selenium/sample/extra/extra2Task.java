package selenium.sample.extra;

import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import static org.junit.Assert.assertEquals;

public class extra2Task {
    private WebDriver driver;
    private String libWithDriversLocation = System.getProperty("user.dir") + "\\lib\\";

    @After
    public void endingTests() throws Exception {
        driver.quit();
    }

    @Test
    public void runningOnFirefox() throws Exception {
        System.setProperty("webdriver.gecko.driver", libWithDriversLocation + "geckodriver.exe");
        driver = new FirefoxDriver();
//        go to page https://kristinek.github.io/site/examples/po
        driver.get("https://kristinek.github.io/site/examples/po");
//        check the background color of h1 element
        assertEquals("rgba(0, 0, 0, 0)", driver.findElement(By.xpath("//h1")).getCssValue("background-color"));
    }

    @Test
    public void runningOnChrome() throws Exception {
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver.exe");
        driver = new ChromeDriver();
//        go to page https://kristinek.github.io/site/examples/po
        driver.get("https://kristinek.github.io/site/examples/po");
//        check the background color of h1 element
        assertEquals("rgba(0, 0, 0, 0)", driver.findElement(By.xpath("//h1")).getCssValue("background-color"));
    }

    @Test
    public void runningOnIE() throws Exception {
        System.setProperty("webdriver.ie.driver", libWithDriversLocation + "IEDriverServer.exe");
        driver = new InternetExplorerDriver();
//        go to page https://kristinek.github.io/site/examples/po
        driver.get("https://kristinek.github.io/site/examples/po");
//        check the background color of h1 element
//        the driver can't find h1 element and the web page is broken on IE
        assertEquals("rgba(0, 0, 0, 0)", driver.findElement(By.xpath("//h1")).getCssValue("background-color"));
    }
}
