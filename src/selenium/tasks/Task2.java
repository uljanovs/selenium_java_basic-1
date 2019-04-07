package selenium.tasks;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import selenium.pages.TaskFeedbackPage;
import selenium.pages.TaskMainPage;

import java.util.concurrent.TimeUnit;

public class Task2 {
    private WebDriver driver;
    private static TaskMainPage mainPage;
    private static TaskFeedbackPage feedbackPage;

    @Before
    public void openPage() {
        String libWithDriversLocation = System.getProperty("user.dir") + "\\lib\\";
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://kristinek.github.io/site/tasks/provide_feedback");

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        mainPage = PageFactory.initElements(driver, TaskMainPage.class);
        feedbackPage = PageFactory.initElements(driver, TaskFeedbackPage.class);
    }

    @After
    public void closeBrowser() {
        driver.quit();
    }

    @Test
    public void initialFeedbackPage() {
//         TODO:
//         check that all field are empty and no tick are clicked
//         "Don't know" is selected in "Genre"
//         "Choose your option" in "How do you like us?"
        mainPage.noDataEntered();
//         check that the button send is blue with white letters
        mainPage.checkButtonSendColors();
    }

    @Test
    public void emptyFeedbackPage() {
//         TODO:
//         click "Send" without entering any data
        mainPage.clickSendButton();
//         check fields are empty or null
        feedbackPage.checkFieldsAreEmpty();
//         check button colors
//         (green with white letter and red with white letters)
        feedbackPage.checkButtonColors();
    }

    @Test
    public void notEmptyFeedbackPage() throws Exception {
        String name = "Maksims";
        String age = "22";
        String comment = "Feedback example.";

//         TODO:
//      fill the whole form, click "Send"
        mainPage.fillAllFields(name, age, comment);
        mainPage.clickSendButton();
//         check fields are filled correctly
        feedbackPage.checkFeedbackFieldsFilledCorrectly(name, age, comment);
//         check button colors
//         (green with white letter and red with white letters)
        feedbackPage.checkButtonColors();
    }

    @Test
    public void yesOnFeedbackPageWithName() {
        String name = "Aija";
//         TODO:
//         enter only name
        mainPage.fillNameField(name);
//         click "Send"
        mainPage.clickSendButton();
//         click "Yes"
        feedbackPage.clickYes();
//         check message text: "Thank you, NAME, for your feedback!"
        feedbackPage.checkFinalMessageWithName(name);
//         color of text is white with green on the background
        feedbackPage.checkFinalMessageColors();
    }

    @Test
    public void yesOnFeedbackPageWithoutName() {
//         TODO:
//         click "Send" (without entering anything
        mainPage.clickSendButton();
//         click "Yes"
        feedbackPage.clickYes();
//         check message text: "Thank you for your feedback!"
        feedbackPage.checkFinalMessageWithoutName();
//         color of text is white with green on the background
        feedbackPage.checkFinalMessageColors();
    }

    @Test
    public void noOnFeedbackPage() throws Exception {
//         TODO:
//         fill the whole form
        String name = "Aiga";
        String age = "19";
        String comment = "Very useful feedback!";

        mainPage.fillAllFields(name, age, comment);
//         click "Send"
        mainPage.clickSendButton();
//         click "No"
        feedbackPage.clickNo();
//         check fields are filled correctly
        mainPage.checkFieldsFilledCorrectly(name, age, comment);
    }
}
