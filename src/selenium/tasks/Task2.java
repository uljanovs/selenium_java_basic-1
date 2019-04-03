package selenium.tasks;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import selenium.pages.Task10Page;
import selenium.pages.Task10Page2;
import selenium.pages.Task10Page3;

public class Task2 {
    static WebDriver driver;
    static Task10Page taskPage1;
    static Task10Page2 taskPage2;
    static Task10Page3 taskPage3;

    @Before
    public void openPage() {
        String libWithDriversLocation = System.getProperty("user.dir") + "\\lib\\";
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://kristinek.github.io/site/tasks/provide_feedback");
        taskPage1 = PageFactory.initElements(driver,Task10Page.class);
        taskPage2 =  PageFactory.initElements(driver,Task10Page2.class);
        taskPage3 =  PageFactory.initElements(driver,Task10Page3.class);
    }

    @After
    public void closeBrowser() {
        driver.quit();
    }

    @Test
    public void initialFeedbackPage() throws Exception {
//         TODO:
//         check that all field are empty and no tick are clicked
        taskPage1.checkFieldsEmptyAndNoticks();

//         "Don't know" is selected in "Genre"
        taskPage1.checkDontKnowGenderSelected();

//         "Choose your option" in "How do you like us?"
        taskPage1.checkDropdownDummy();

//         check that the button send is blue with white letters className = w3-btn type=submit
        taskPage1.checkSendButtonColorandText();
    }

    @Test
    public void emptyFeedbackPage() throws Exception {
//         TODO:
//         click "Send" without entering any data
        taskPage1.clickSend();

//         check fields are empty or null
        taskPage2.checkFieldsEmptyOrNull();

//         check button colors
//         (green with white letter and red with white letters)
        taskPage2.checkButtonsColorandText();
    }

    @Test
    public void notEmptyFeedbackPage() throws Exception {
//         TODO:
//         fill the whole form, click "Send"
        taskPage1.fillTheWholeFormadnClickSend();

//         check fields are filled correctly
        taskPage2.checkFieldsFilledCorrectlyOnValidationPage();

//         check button colors
//         (green with white letter and red with white letters)
        taskPage2.checkButtonsColorandText();
    }

    @Test
    public void yesOnWithNameFeedbackPage() throws Exception {
//         TODO:
//         enter only name
        taskPage1.enterName("Name");

//         click "Send"
        taskPage1.clickSend();

//         click "Yes"
        taskPage2.clickYes();

//         check message text: "Thank you, NAME, for your feedback!"
        taskPage3.checkMessageText("Thank you, Name, for your feedback!");

//         color of text is white with green on the background
        taskPage3.checkMessageColor();
    }

    @Test
    public void yesOnWithoutNameFeedbackPage() throws Exception {
//         TODO:
//         click "Send" (without entering anything
        taskPage1.clickSend();

//         click "Yes"
        taskPage2.clickYes();

//         check message text: "Thank you for your feedback!"
        taskPage3.checkMessageText("Thank you for your feedback!");

//         color of text is white with green on the background
        taskPage3.checkMessageColor();
    }

    @Test
    public void noOnFeedbackPage() throws Exception {
//         TODO:
//         fill the whole form, click "Send"
        taskPage1.fillTheWholeFormadnClickSend();

//         click "No"
        taskPage2.clickNo();

//         check fields are filled correctly
       taskPage1.checkFieldsAreFilledCorrectlyAfterReturnByNo();


    }
}
