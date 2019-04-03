package selenium.tasks;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import selenium.pages.AgeSamplePage;
import selenium.pages.AgeSubmittedSamplePage;
import selenium.pages.Task2.FirstPage;
import selenium.pages.Task2.SecondPage;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class Task2 {
    static WebDriver driver;
    static FirstPage firstPage;
    static SecondPage secondPage;

    @Before
    public void openPage() {
        String libWithDriversLocation = System.getProperty("user.dir") + "\\lib\\";
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://kristinek.github.io/site/tasks/provide_feedback");
        firstPage = PageFactory.initElements(driver, FirstPage.class);
        secondPage = PageFactory.initElements(driver, SecondPage.class);
    }

    @After
    public void closeBrowser() {
        driver.quit();
    }

    @Test
    public void initialFeedbackPage() throws Exception {


        firstPage.checkNameAgeIsClean();

        firstPage.checkCheckBoxIsClean();

        firstPage.checkRadioButtonsIsClean();
        firstPage.checkDropDownsIsClean();

        firstPage.checkTextareaIsClean();


        firstPage.checkButtonColor();

    }

    @Test
    public void emptyFeedbackPage() throws Exception {


        firstPage.submitButton();


        secondPage.checkEptyFields();


        secondPage.checkColorButtons2();


    }

    @Test
    public void notEmptyFeedbackPage() throws Exception {
//         TODO:
        firstPage.fillFields();
        firstPage.submitButton();
        secondPage.correctFields();
        secondPage.checkColorButtons2();


    }

    @Test
    public void yesOnWithNameFeedbackPage() throws Exception {
//         TODO:
//         enter only name
        firstPage.enterName();

//         click "Send"
        firstPage.submitButton();

//         click "Yes"
        secondPage.yesButton();


//         check message text: "Thank you, NAME, for your feedback!"
        secondPage.checkMessage();
//         color of text is white with green on the background
        secondPage.checkMessageColor();
    }

    @Test
    public void yesOnWithoutNameFeedbackPage() throws Exception {
//         TODO:
//         click "Send" (without entering anything
        firstPage.submitButton();
//         click "Yes"
        secondPage.yesButton();
//         check message text: "Thank you for your feedback!"
        secondPage.checkMessageNoName();
//         color of text is white with green on the background
    }

    @Test
    public void noOnFeedbackPage() throws Exception {
//         TODO:
//         fill the whole form

        firstPage.fillFields();
//         click "Send"
        firstPage.submitButton();
//         click "No"
        secondPage.noButton();
//         check fields are filled correctly
        firstPage.checkFields();

    }
}
