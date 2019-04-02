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
        List<WebElement> checkBoxes = driver.findElements(By.cssSelector(".w3-check[type='checkbox']"));

        for (WebElement checkBox : checkBoxes) {
            assertFalse(checkBox.isSelected()); // checkboxes are NOT selected
           }
        //tick  "Option 2"
        WebElement option2 = driver.findElement(By.cssSelector(".w3-check[value='Option 2'][type='checkbox']"));
        option2.click();
        assertTrue(option2.isSelected());

        //check that "Option 1" and "Option 3" are not ticked, but "Option 2" is ticked
        WebElement option1 = driver.findElement(By.cssSelector(".w3-check[value='Option 1'][type='checkbox']"));
        assertFalse(option1.isSelected());

        WebElement option3 = driver.findElement(By.cssSelector(".w3-check[value='Option 3'][type='checkbox']"));
        assertFalse(option3.isSelected());

        WebElement rightoptionTwoTicked = driver.findElement(By.cssSelector(".w3-check[value='Option 2'][type='checkbox']"));
        assertTrue(rightoptionTwoTicked.isSelected());

        /*checkboxes.get(1).click();
        assertTrue(checkboxes.get(0).isSelected); .....
        checkboxes.get(2).click();*/

//        tick  "Option 3"
        WebElement clickOptionThree = driver.findElement(By.cssSelector(".w3-check[value='Option 3'][type='checkbox']"));
       clickOptionThree.click();
        assertTrue(clickOptionThree.isSelected());

//        click result
        WebElement resultClick = driver.findElement(By.id("result_button_checkbox"));
        resultClick.click();

//        check that text 'You selected value(s): Option 2, Option 3' is being displayed
        assertEquals("You selected value(s): Option 2, Option 3", driver.findElement(By.id("result_checkbox")).getText());
    }
    @Test
    public void selectRadioButton() throws Exception {
//         TODO:
//        check that none of the radio are selected
        List<WebElement> radioButtons = driver.findElements(By.cssSelector(".w3-check[type='radio']"));

        for (WebElement radioButton : radioButtons) {
            assertFalse(radioButton.isSelected()); // checkboxes are NOT selected
   }
        //select  "Option 3"
        /*radioButtons.get(2).click();
        assertFalse(radioButtons.get(2).isSelected());
        radioButtons.get(0).click();*/

        WebElement option3 = driver.findElement(By.cssSelector(".w3-check[value='Option 3'][type='radio']"));
        option3.click();
        assertTrue(option3.isSelected());
//        check that "Option 1" and "Option 2' are not select, but "Option 3" is selected
        WebElement option1 = driver.findElement(By.cssSelector(".w3-check[value='Option 1'][type='radio']"));
        assertFalse(option1.isSelected());

        WebElement option2 = driver.findElement(By.cssSelector(".w3-check[value='Option 2'][type='radio']"));
        assertFalse(option2.isSelected());

        WebElement checkOption3 = driver.findElement(By.cssSelector(".w3-check[value='Option 3'][type='radio']"));
        assertTrue(checkOption3.isSelected());

//        select  "Option 1"
        WebElement checkOption1 = driver.findElement(By.cssSelector(".w3-check[value='Option 1'][type='radio']"));
        checkOption1.click();
        assertTrue(checkOption1.isSelected());
//        check that "Option 2" and "Option 3' are not select, but "Option 1" is selected
        assertFalse(option2.isSelected());
        assertFalse(checkOption3.isSelected());

//        click result
        WebElement resultClick = driver.findElement(By.id("result_button_ratio"));
        resultClick.click();
//        check that 'You selected option: Option 1' text is being displayed
        assertEquals("You selected option: Option 1", driver.findElement(By.id("result_radio")).getText());
    }

    @Test
    public void selectOption() throws Exception {

//        select "Option 3" in Select
        Select dropdown = new Select(driver.findElement(By.id("vfb-12"))); //by.css.Delector("select@vfb-12")
        //Option3.click();
        dropdown.selectByVisibleText("Option 3");
        // check that selected option is "Option 3"
        assertEquals("Option 3", dropdown.getFirstSelectedOption().getText());
//        select "Option 2" in Select
             Select dropdown2 = new Select(driver.findElement(By.id("vfb-12")));
        dropdown.selectByVisibleText("Option 2");
        //check that selected option is "Option 2"
        assertEquals("Option 2", dropdown.getFirstSelectedOption().getText());
//        // click result
        WebElement resultText = driver.findElement(By.id("result_button_select"));
        resultText.click();
//        check that 'You selected option: Option 2' text is being displayed
        assertEquals("You selected option: Option 2", driver.findElement(By.id("result_select")).getText());
    }


    @Test
    public void chooseDateViaCalendarBonus() throws Exception {
//         TODO:
//        enter date '4 of July 2007' using calendar widget
        //get today date
        Calendar cal = Calendar.getInstance();
//    go back 141 month
        cal.add(Calendar.MONTH, -141);
        String result = new SimpleDateFormat("MM/04/yyyy").format(cal.getTime());

        WebElement dateBox = driver.findElement(By.id("vfb-8"));
        assertEquals("", dateBox.getAttribute("value"));

        dateBox.click();
        WebElement dateWidget = driver.findElement(By.id("ui-datepicker-div"));
//    go back 141 month in calendar on page
        for (int i = 0; i < 141; i++) {
            dateWidget.findElement(By.className("ui-datepicker-prev")).click();
        }
//    select date 4
        dateWidget.findElement(By.xpath("//a[text()='4'l]")).click();

//        check that correct date is added
        assertEquals(result, dateBox.getAttribute("value"));
        dateBox.clear();
    }
    @Test
    public void chooseDateViaTextBoxBonus() throws Exception {
//         TODO:
//        enter date '2 of May 1959' using text
//        check that correct date is added
        String dateToEnter = "05/02/1959";
        WebElement dateBox = driver.findElement(By.id("vfb-8"));
        assertEquals("", dateBox.getAttribute("value"));
        //dateBox.clear();

        dateBox.sendKeys(dateToEnter);
        //WebElement clickSomewhere = driver.findElement(By.id("result_date"));
        //clickSomewhere.click();
        //WebElement clickSomewhere = driver.findElement(By.id("vfb-8"));
        WebElement clickSomewhere = driver.findElement(By.className("w3-navbar w3-black w3-large"));
        clickSomewhere.click();
        Thread.sleep(1000);

        WebElement clickResult = driver.findElement(By.id("result_button_date"));
        clickResult.click();
        assertEquals(dateToEnter, dateBox.getAttribute("value"));
        String resultText = driver.findElement(By.cssSelector("@result date")).getText();
        assertEquals("You entered date: 05/02/1959", resultText);

    }
}
