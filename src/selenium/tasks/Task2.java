package selenium.tasks;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import selenium.pages.FeedbackFormPage;
import selenium.pages.FeedbackPage;

import java.util.concurrent.TimeUnit;

public class Task2 {
    WebDriver driver;
    static FeedbackFormPage formPage;
    static FeedbackPage feedbackPage;

    @Before
    public void openPage() {
        String libWithDriversLocation = System.getProperty("user.dir") + "\\lib\\";
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://kristinek.github.io/site/tasks/provide_feedback");
        formPage = PageFactory.initElements(driver, FeedbackFormPage.class);
        feedbackPage = PageFactory.initElements(driver, FeedbackPage.class);
    }

    @After
    public void closeBrowser() {
        driver.quit();
    }

    @Test
    public void initialFeedbackPage() throws Exception {
//         TODO:
//         check that all field are empty and no tick are clicked
        formPage.checkCheckBoxesAreEmpty();
//         "Don't know" is selected in "Genre"
        formPage.checkDontInGenreIsSelected();
//         "Choose your option" in "How do you like us?"
        formPage.checkHowDoYouLikeUsDefault();
//         check that the button send is blue with white letters
        formPage.checkButtonSendBlueWhiteLetters();
    }

    @Test
    public void emptyFeedbackPage() throws Exception {
//         TODO:
//         click "Send" without entering any data
        formPage.clickSendButton();
//         check fields are empty or null
        feedbackPage.checkFieldsEmpty();
//         check button colors
//         (green with white letter and red with white letters)
        feedbackPage.checkYesAndNoButtonColor();
    }

    @Test
    public void notEmptyFeedbackPage() throws Exception {
//         TODO:
        String name = "test";
        String age = "30";
        String comment = "comment check";
//         fill the whole form, click "Send"
        formPage.fillAllFields(name, age, comment);
        formPage.clickSendButton();
//         check fields are filled correctly
        feedbackPage.checkFieldsFilledCorrectly(name, age, comment);
//         check button colors
//         (green with white letter and red with white letters)
    }

    @Test
    public void yesOnWithNameFeedbackPage() throws Exception {
//         TODO:
//         enter only name
//         click "Send"
//         click "Yes"
//         check message text: "Thank you, NAME, for your feedback!"
//         color of text is white with green on the background
    }

    @Test
    public void yesOnWithoutNameFeedbackPage() throws Exception {
//         TODO:
//         click "Send" (without entering anything
//         click "Yes"
//         check message text: "Thank you for your feedback!"
//         color of text is white with green on the background
    }

    @Test
    public void noOnFeedbackPage() throws Exception {
//         TODO:
//         fill the whole form
//         click "Send"
//         click "No"
//         check fields are filled correctly
    }
}
