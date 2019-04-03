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
       List <WebElement> checkBox =  driver.findElements(By.cssSelector(".w3-check[type='checkbox']"));
        for (WebElement checkBoxs : checkBox) {
            assertFalse(checkBoxs.isSelected()); // checkboxes are NOT selected

        }
        WebElement option1 = driver.findElement(By.cssSelector(".w3-check[value='Option 1'][type='checkbox']"));
        WebElement option2 = driver.findElement(By.cssSelector(".w3-check[value='Option 2'][type='checkbox']"));
        WebElement option3 = driver.findElement(By.cssSelector(".w3-check[value='Option 3'][type='checkbox']"));
        WebElement Checkboxbutton = driver.findElement(By.id("result_button_checkbox"));
        WebElement text = driver.findElement(By.id("result_checkbox"));

//        tick  "Option 2"
          option2.click();
//        check that "Option 1" and "Option 3" are not ticked, but "Option 2" is ticked
        assertFalse(option1.isSelected());
        assertFalse(option3.isSelected());
//        tick  "Option 3"
        option3.click();
//        click result
        Checkboxbutton.click();
//        check that text 'You selected value(s): Option 2, Option 3' is being displayed
        assertEquals("You selected value(s): Option 2, Option 3",text.getText());
    }


    @Test
    public void selectRadioButton() throws Exception {
//         TODO:
//        check that none of the radio are selected
        List<WebElement> radioButtons = driver.findElements(By.cssSelector(".w3-check[type='radio']"));
        for (WebElement radioButton : radioButtons) {
            assertFalse(radioButton.isSelected()); // radio are NOT selected
        }
        WebElement option3 = driver.findElement(By.cssSelector(".w3-check[value='Option 3'][type='radio'"));
        WebElement option2 = driver.findElement(By.cssSelector(".w3-check[value='Option 2'][type='radio'"));
        WebElement option1 = driver.findElement(By.cssSelector(".w3-check[value='Option 1'][type='radio'"));
        WebElement radiobutton = driver.findElement(By.id("result_button_ratio"));
        WebElement radiotext = driver.findElement(By.id("result_radio"));
//        select  "Option 3"

      //  radioButtons.get(2).click();------- Another method XXXXXXX

        option3.click();
//        check that "Option 1" and "Option 2' are not select, but "Option 3" is selected
        assertFalse(option1.isSelected());
        assertFalse(option2.isSelected());
        assertTrue(option3.isSelected());
//        select  "Option 1"
        option1.click();
//        check that "Option 2" and "Option 3' are not select, but "Option 1" is selected
        assertFalse(option2.isSelected());
        assertFalse(option3.isSelected());
        assertTrue(option1.isSelected());
//        click result
        radiobutton.click();
//        check that 'You selected option: Option 1' text is being displayed
        assertEquals("You selected option: Option 1",radiotext.getText());

    }

    @Test
    public void selectOption() throws Exception {
//        select "Option 3" in Select
        Select dropdown = new Select(driver.findElement(By.id("vfb-12")));
        WebElement selectbutton = driver.findElement(By.id("result_button_select"));
        WebElement selecttext = driver.findElement(By.id("result_select"));
        dropdown.selectByVisibleText("Option 3");
//        check that selected option is "Option 3"
        assertEquals("Option 3", dropdown.getFirstSelectedOption().getText());
//        select "Option 2" in Select
        dropdown.selectByVisibleText("Option 2");
//        check that selected option is "Option 2"
        assertEquals("Option 2", dropdown.getFirstSelectedOption().getText());
//        click result
        selectbutton.click();
//        check that 'You selected option: Option 2' text is being displayed
        assertEquals("You selected option: Option 2",selecttext.getText());
    }

    @Test
    public void chooseDateViaCalendarBonus() throws Exception {
//         TODO:
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -141);
        String result = new SimpleDateFormat("MM/04/yyyy").format(cal.getTime());

//        enter date '4 of July 2007' using calendar widget
        WebElement dateBox = driver.findElement(By.id("vfb-8"));
        dateBox.click();
        //dateBox.sendKeys("04/07/2007");
        WebElement dateWidget = driver.findElement(By.id("ui-datepicker-div"));
        for (int i = 0; i < 141; i++) {
            dateWidget.findElement(By.className("ui-datepicker-prev")).click();
        }
//        check that correct date is added
        dateWidget.findElement(By.xpath("//a[text()='4']")).click();
       assertEquals("07/04/2007",dateBox.getAttribute("value"));
    }

    @Test
    public void chooseDateViaTextBoxBonus() throws Exception {
//         TODO:
        WebElement dateBox = driver.findElement(By.id("vfb-8"));
//        enter date '2 of May 1959' using text
       dateBox.sendKeys("02/05/1959");
//        check that correct date is added
        assertEquals("02/05/1959",dateBox.getAttribute("value"));
        WebElement clickdaebutton = driver.findElement(By.id("result_button_date"));
        WebElement clickheader = driver.findElement(By.cssSelector("body > div:nth-child(4) > div:nth-child(3) > div > h2"));
        clickheader.click();
        Thread.sleep(1000);
        clickdaebutton.click();
    }
}
