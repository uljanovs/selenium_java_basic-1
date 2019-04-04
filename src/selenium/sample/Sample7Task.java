package selenium.sample;

import com.sun.xml.internal.ws.org.objectweb.asm.ByteVector;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.*;

public class Sample7Task {
    WebDriver driver;
    String base_url = "https://kristinek.github.io/site/examples/actions";

    // method which is being run before each test
    @Before
    public void startingTests() throws Exception {
        // from Sample 1:
        String libWithDriversLocation = System.getProperty("user.dir") + "\\lib\\";
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver.exe");
        // declaration above:
        driver = new ChromeDriver();
        //open page:
        driver.get(base_url);
    }

    // method which is being run after each test
    @After
    public void endingTests() throws Exception {
        driver.quit();
    }

    @Test
    public void selectCheckBox() throws Exception {
//         TODO:
//        check that none of the checkboxes are ticked
        List<WebElement> checkBoxes = driver.findElements(By.cssSelector(".w3-check[type='checkbox']"));

        for (WebElement checkBox : checkBoxes) {
            assertFalse(checkBox.isSelected());
        }

//        tick  "Option 2"

        // WebElement option2 = driver.findElement(By.cssSelector(".w3-check[value='Option 2'][type='checkbox']"));
        assertFalse(checkBoxes.get(1).isSelected());
        // option2.click();
        checkBoxes.get(1).click();
        assertTrue(checkBoxes.get(1).isSelected());
//        check that "Option 1" and "Option 3" are not ticked, but "Option 2" is ticked
        WebElement option1 = checkBoxes.get(0);
        WebElement option3 = checkBoxes.get(2);
        assertFalse(option1.isSelected());
        assertFalse(option3.isSelected());
//        tick  "Option 3"
        option3.click();
//        click result
        WebElement result = driver.findElement(By.id("result_button_checkbox"));
        result.click();
//        check that text 'You selected value(s): Option 2, Option 3' is being displayed
        assertEquals("You selected value(s): Option 2, Option 3", driver.findElement(By.id("result_checkbox")).getText());
    }


    @Test
    public void selectRadioButton() throws Exception {
//         TODO:
//        check that none of the radio are selected
        List<WebElement> radioButtons = driver.findElements(By.cssSelector(".w3-check[type='radio']"));

        for (WebElement radioButton : radioButtons) {
            assertFalse(radioButton.isSelected());
        }
//        select  "Option 3"
        WebElement option3 = radioButtons.get(2);
        assertFalse(option3.isSelected());
        option3.click();
        assertTrue(option3.isSelected());
//        check that "Option 1" and "Option 2' are not select, but "Option 3" is selected
        WebElement option1 = radioButtons.get(0);
        WebElement option2 = radioButtons.get(1);
        assertFalse(option1.isSelected());
        assertFalse(option2.isSelected());
//        select  "Option 1"
        option1.click();
//        check that "Option 2" and "Option 3' are not select, but "Option 1" is selected
        assertFalse(option2.isSelected());
        assertFalse(option3.isSelected());
        assertTrue(option1.isSelected());

//        click result
        WebElement result = driver.findElement(By.id("result_button_ratio"));
        result.click();
//        check that 'You selected option: Option 1' text is being displayed
        assertEquals("You selected option: Option 1", driver.findElement(By.id("result_radio")).getText());
    }

    @Test
    public void selectOption() throws Exception {
//        select "Option 3" in Select
        Select dropdown = new Select(driver.findElement(By.id("vfb-12")));
        assertEquals("Choose your option", dropdown.getFirstSelectedOption().getText());
        dropdown.selectByIndex(3);
        //        check that selected option is "Option 3"
        assertEquals("Option 3", dropdown.getFirstSelectedOption().getText());
//        select "Option 2" in Select
        dropdown.selectByVisibleText("Option 2");
//        check that selected option is "Option 2"
        assertEquals("Option 2", dropdown.getFirstSelectedOption().getText());
//        click result
        driver.findElement(By.id("result_button_select")).click();
//        check that 'You selected option: Option 2' text is being displayed
        assertEquals("You selected option: Option 2", driver.findElement(By.id("result_select")).getText());
    }

    @Test
    public void chooseDateViaCalendarBonus() throws Exception {
//         TODO:
        Calendar cal = Calendar.getInstance();
        WebElement dateWidget = driver.findElement(By.id("ui-datepicker-div"));
//        enter date '4 of July 2007' using calendar widget
        WebElement dateBox = driver.findElement(By.id("vfb-8"));
        assertEquals("", dateBox.getAttribute("value"));
        dateBox.click();

        for (int i = 0; i < 141; i++) {
            dateWidget.findElement(By.className("ui-datepicker-prev")).click();
        }
        dateWidget.findElement(By.xpath("//a[text()='4']")).click();

//        check that correct date is added
        driver.findElement(By.id("result_button_date")).click();
        assertEquals("You entered date: 07/04/2007", driver.findElement(By.id("result_date")).getText());
    }

    @Test
    public void chooseDateViaTextBoxBonus() throws Exception {
//         TODO:
        String dateToEnter = "05/02/1959";
        Calendar.getInstance();

        WebElement dateBox = driver.findElement(By.id("vfb-8"));
        dateBox.clear();
//        enter date '2 of May 1959' using text
        dateBox.sendKeys(dateToEnter);
        dateBox.sendKeys(Keys.ENTER);
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("#result_button_date")).click();
//        check that correct date is added
        assertEquals(dateToEnter, dateBox.getAttribute("value"));
        assertEquals("You entered date: 05/02/1959", driver.findElement(By.cssSelector("#result_date")).getText());
    }
}
