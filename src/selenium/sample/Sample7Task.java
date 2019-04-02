package selenium.sample;

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
        WebElement option1 = driver.findElement(By.cssSelector(".w3-check[value='Option 1'][type='checkbox']"));
        WebElement option2 = driver.findElement(By.cssSelector(".w3-check[value='Option 2'][type='checkbox']"));
        WebElement option3 = driver.findElement(By.cssSelector(".w3-check[value='Option 3'][type='checkbox']"));
        assertFalse(option1.isSelected());
        assertFalse(option2.isSelected());
        assertFalse(option3.isSelected());

//        tick  "Option 2"
        option2.click();

//        check that "Option 1" and "Option 3" are not ticked, but "Option 2" is ticked
        assertFalse(option1.isSelected());
        assertTrue(option2.isSelected());
        assertFalse(option3.isSelected());

//        tick  "Option 3"
        option3.click();

//        click result
        WebElement resultButton = driver.findElement(By.id("result_button_checkbox"));
        resultButton.click();

//        check that text 'You selected value(s): Option 2, Option 3' is being displayed
        WebElement result = driver.findElement(By.cssSelector("#result_checkbox"));
        assertEquals("You selected value(s): Option 2, Option 3", result.getText());

    }


    @Test
    public void selectRadioButton() throws Exception {
//         TODO:
//        check that none of the radio are selected
        WebElement radio1 = driver.findElement(By.cssSelector("#vfb-7-1"));
        WebElement radio2 = driver.findElement(By.cssSelector("#vfb-7-2"));
        WebElement radio3 = driver.findElement(By.cssSelector("#vfb-7-3"));
        assertFalse(radio1.isSelected());
        assertFalse(radio2.isSelected());
        assertFalse(radio3.isSelected());

//        select  "Option 3"
        radio3.click();

//        check that "Option 1" and "Option 2' are not select, but "Option 3" is selected
        assertFalse(radio1.isSelected());
        assertFalse(radio2.isSelected());
        assertTrue(radio3.isSelected());

//        select  "Option 1"
        radio1.click();

//        check that "Option 2" and "Option 3' are not select, but "Option 1" is selected
        assertTrue(radio1.isSelected());
        assertFalse(radio2.isSelected());
        assertFalse(radio3.isSelected());

//        click result
        WebElement resultButton = driver.findElement(By.cssSelector("#result_button_ratio"));
        resultButton.click();

//        check that 'You selected option: Option 1' text is being displayed
        WebElement result = driver.findElement(By.cssSelector("#result_radio"));
        assertEquals("You selected option: Option 1", result.getText());

    }

    @Test
    public void selectOption() throws Exception {
        Select selection = new Select(driver.findElement(By.cssSelector("#vfb-12")));

//        select "Option 3" in Select
        selection.selectByIndex(3);

//        check that selected option is "Option 3"
        assertEquals("Option 3", selection.getFirstSelectedOption().getText());

//        select "Option 2" in Select
        selection.selectByIndex(2);

//        check that selected option is "Option 2"
        assertEquals("Option 2", selection.getFirstSelectedOption().getText());

//        click
        WebElement resultButton = driver.findElement(By.cssSelector("#result_button_select"));
        resultButton.click();

//        check that 'You selected option: Option 2' text is being displayed
        WebElement result = driver.findElement(By.cssSelector("#result_select"));
        assertEquals("You selected option: Option 2", result.getText());

    }

    @Test
    public void chooseDateViaCalendarBonus() throws Exception {
//         TODO:
        Calendar calStart = Calendar.getInstance();
        Calendar calTest = Calendar.getInstance();
        int day = 4, month = 7, year = 2007;
        calTest.set(year, month-1, day);
        String result = new SimpleDateFormat("MM/dd/yyyy").format(calTest.getTime());

//        enter date '4 of July 2007' using calendar widget

        WebElement dateField = driver.findElement(By.cssSelector("#vfb-8"));
        assertEquals("", dateField.getAttribute("value"));
        dateField.click();

        WebElement calendarWidget = driver.findElement(By.cssSelector("#ui-datepicker-div"));
        if (year < calStart.get(Calendar.YEAR)) {
            for(int i = 0; i < calStart.get(Calendar.YEAR) - year; i++) {
                for(int j = 0; j < 12; j++) {
                    calendarWidget.findElement(By.cssSelector(".ui-datepicker-prev")).click();
                }
            }
        } else if (year > calStart.get(Calendar.YEAR)) {
            for(int i = 0; i < year - calStart.get(Calendar.YEAR); i++) {
                for(int j = 0; j < 12; j++) {
                    calendarWidget.findElement(By.cssSelector(".ui-datepicker-next")).click();
                }
            }
        }

        if (month > calStart.get(Calendar.MONTH)-1) {
            for (int i = 0; i < month - calStart.get(Calendar.MONTH)-1; i++) {
                calendarWidget.findElement(By.cssSelector(".ui-datepicker-next")).click();
            }
        } else if (month < calStart.get(Calendar.MONTH)-1) {
            for (int i = 0; i < calStart.get(Calendar.MONTH) - month; i++) {
                calendarWidget.findElement(By.cssSelector(".ui-datepicker-next")).click();
            }
        }

        calendarWidget.findElement(By.xpath("//a[text()='"+ day +"']")).click();

//        check that correct date is added
        assertEquals(result, dateField.getAttribute("value"));

    }

    @Test
    public void chooseDateViaTextBoxBonus() throws Exception {
//         TODO:
        Calendar calStart = Calendar.getInstance();
        Calendar calTest = Calendar.getInstance();
        int day = 2, month = 5, year = 1959;
        calTest.set(year, month-1, day);
        String result = new SimpleDateFormat("MM/dd/yyyy").format(calTest.getTime());

//        enter date '2 of May 1959' using text
        WebElement dateField = driver.findElement(By.cssSelector("#vfb-8"));
        assertEquals("", dateField.getAttribute("value"));
        dateField.click();

        WebElement calendarWidget = driver.findElement(By.cssSelector("#ui-datepicker-div"));
        if (year < calStart.get(Calendar.YEAR)) {
            for(int i = 0; i < calStart.get(Calendar.YEAR) - year; i++) {
                for(int j = 0; j < 12; j++) {
                    calendarWidget.findElement(By.cssSelector(".ui-datepicker-prev")).click();
                }
            }
        } else if (year > calStart.get(Calendar.YEAR)) {
            for(int i = 0; i < year - calStart.get(Calendar.YEAR); i++) {
                for(int j = 0; j < 12; j++) {
                    calendarWidget.findElement(By.cssSelector(".ui-datepicker-next")).click();
                }
            }
        }

        if (month > calStart.get(Calendar.MONTH)-1) {
            for (int i = 0; i < month - calStart.get(Calendar.MONTH)-1; i++) {
                calendarWidget.findElement(By.cssSelector(".ui-datepicker-next")).click();
            }
        } else if (month < calStart.get(Calendar.MONTH)-1) {
            for (int i = 0; i < calStart.get(Calendar.MONTH) - month; i++) {
                calendarWidget.findElement(By.cssSelector(".ui-datepicker-next")).click();
            }
        }

        calendarWidget.findElement(By.xpath("//a[text()='"+ day +"']")).click();

//        check that correct date is added
        assertEquals(result, dateField.getAttribute("value"));
    }
}
