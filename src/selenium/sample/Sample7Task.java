package selenium.sample;

import javafx.scene.control.RadioButton;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
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
        for(WebElement chkBox : checkBoxes){
            assertFalse(chkBox.isSelected());
            chkBox.click();
            assertTrue(chkBox.isSelected());
            chkBox.click();
            assertFalse(chkBox.isSelected());
        }
//        tick  "Option 2"
        assertFalse(driver.findElement(By.cssSelector(".w3-check[value='Option 2'][type='checkbox']")).isSelected());
        driver.findElement(By.cssSelector(".w3-check[value='Option 2'][type='checkbox']")).click();
//        check that "Option 1" and "Option 3" are not ticked, but "Option 2" is ticked
        assertFalse(driver.findElement(By.cssSelector(".w3-check[value='Option 1'][type='checkbox']")).isSelected());
        assertTrue(driver.findElement(By.cssSelector(".w3-check[value='Option 2'][type='checkbox']")).isSelected());
        assertFalse(driver.findElement(By.cssSelector(".w3-check[value='Option 3'][type='checkbox']")).isSelected());
//        tick  "Option 3"
        driver.findElement(By.cssSelector(".w3-check[value='Option 3'][type='checkbox']")).click();
//        click result
        driver.findElement(By.cssSelector("#result_button_checkbox")).click();
//        check that text 'You selected value(s): Option 2, Option 3' is being displayed
        assertTrue(driver.findElement(By.id("result_checkbox")).isDisplayed());
        assertEquals("You selected value(s): Option 2, Option 3", driver.findElement(By.id("result_checkbox")).getText());
    }


    @Test
    public void selectRadioButton() throws Exception {
//         TODO:
//        check that none of the radio are selected
        List<WebElement> radioBtns = driver.findElements(By.xpath("//input[id='vfb-7-1']"));
        for(WebElement radBtn : radioBtns){
            assertFalse(radBtn.isSelected());
        }
//        select  "Option 3"
        driver.findElement(By.xpath("//input[@type='radio' and @value='Option 3']")).click();
//        check that "Option 1" and "Option 2' are not select, but "Option 3" is selected
        assertFalse(driver.findElement(By.xpath("//input[@type='radio' and @value='Option 1']")).isSelected());
        assertFalse(driver.findElement(By.xpath("//input[@type='radio' and @value='Option 2']")).isSelected());
        assertTrue(driver.findElement(By.xpath("//input[@type='radio' and @value='Option 3']")).isSelected());
//        select  "Option 1"
        driver.findElement(By.xpath("//input[@type='radio' and @value='Option 1']")).click();
//        check that "Option 2" and "Option 3' are not select, but "Option 1" is selected
        assertTrue(driver.findElement(By.xpath("//input[@type='radio' and @value='Option 1']")).isSelected());
        assertFalse(driver.findElement(By.xpath("//input[@type='radio' and @value='Option 2']")).isSelected());
        assertFalse(driver.findElement(By.xpath("//input[@type='radio' and @value='Option 3']")).isSelected());
//        click result
        driver.findElement(By.id("result_button_ratio")).click();
//        check that 'You selected option: Option 1' text is being displayed
        assertTrue(driver.findElement(By.cssSelector("#result_radio")).isDisplayed());
        assertEquals("You selected option: Option 1", driver.findElement(By.cssSelector("#result_radio")).getText());
    }

    @Test
    public void selectOption() throws Exception {
//        select "Option 3" in Select
        Select drop = new Select(driver.findElement(By.id("vfb-12")));
        drop.selectByValue("value3");
//        check that selected option is "Option 3"
        assertEquals("Option 3", drop.getFirstSelectedOption().getText());
//        select "Option 2" in Select
        drop.selectByValue("value2");
//        check that selected option is "Option 2"
        assertEquals("Option 2", drop.getFirstSelectedOption().getText());
//        click result
        driver.findElement(By.cssSelector("#result_button_select")).click();
//        check that 'You selected option: Option 2' text is being displayed
        assertTrue(driver.findElement(By.xpath("//p[@id='result_select']")).isDisplayed());
        assertEquals("You selected option: Option 2", driver.findElement(By.id("result_select")).getText());
    }

    @Test
    public void chooseDateViaCalendarBonus() throws Exception {
//         TODO:
//        enter date '4 of July 2007' using calendar widget
        Calendar calendar = Calendar.getInstance();
        int currYR = calendar.get(Calendar.YEAR);
        int trgtYR = 2007;
        int currMNTH = calendar.get(Calendar.MONTH);
        int trgtMNTH = 6;
        driver.findElement(By.id("vfb-8")).click();
        WebElement dateWidg = driver.findElement(By.id("ui-datepicker-div"));
        int target = ((currYR - trgtYR)*12) + (currMNTH - trgtMNTH);
        for(int i=0; i<target; i++){
            dateWidg.findElement(By.className("ui-datepicker-prev")).click();
        }
        dateWidg.findElement(By.xpath("//a[text()='4']")).click();
//        check that correct date is added
        assertEquals("07/04/2007", driver.findElement(By.id("vfb-8")).getAttribute("value"));
    }

    @Test
    public void chooseDateViaTextBoxBonus() throws Exception {
//         TODO:
//        enter date '2 of May 1959' using text
        driver.findElement(By.id("vfb-8")).click();
        driver.findElement(By.id("vfb-8")).clear();
        driver.findElement(By.id("vfb-8")).sendKeys("06/02/1959");
        assertEquals("06/02/1959", driver.findElement(By.id("vfb-8")).getAttribute("value"));
//        check that correct date is added

    }
}
