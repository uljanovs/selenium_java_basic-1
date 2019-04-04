package selenium.tasks;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import selenium.pages.FeedbackPage;
import selenium.pages.FeedbackSubmitPage;
import selenium.pages.ThankYouFeedbackPage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Task2 {
    // WebDriver driver;
    static WebDriver driver;
    static FeedbackPage feedbackPage;
    static FeedbackSubmitPage feedbackSubmitPage;
    static ThankYouFeedbackPage thankYouFeedbackPage;
    private static final String NAME = "Valentina";
    private static final String AGE = "30";
    private static final List<String> LANGUAGES = Arrays.asList("English", "Chinese");
    private static final String COMMENT = "Accenture Bootcamp";


    @Before
    public void openPage() {
        String libWithDriversLocation = System.getProperty("user.dir") + "\\lib\\";
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://kristinek.github.io/site/tasks/provide_feedback");
        feedbackPage = PageFactory.initElements(driver, FeedbackPage.class);
        feedbackSubmitPage = PageFactory.initElements(driver, FeedbackSubmitPage.class);
        thankYouFeedbackPage = PageFactory.initElements(driver, ThankYouFeedbackPage.class);
    }

    @After
    public void closeBrowser() {
        driver.quit();
    }

    @Test
    public void initialFeedbackPage() throws Exception {
//         TODO:
//         check that all field are empty and no tick are clicked

//         "Don't know" is selected in "Genre"
//         "Choose your option" in "How do you like us?"
        feedbackPage.checkAllFieldsAreClean();
//         check that the button send is blue with white letters
        feedbackPage.buttonIsBlueAndLettersWhite();
    }

    @Test
    public void emptyFeedbackPage() throws Exception {
//         TODO:
//         click "Send" without entering any data
        feedbackPage.clickSend();
//         check fields are empty or null
        feedbackSubmitPage.checkAllFieldsAreClean();
//         check button colors
//         (green with white letter and red with white letters)
        feedbackSubmitPage.buttonYesGreenAndLettersWhite();
        feedbackSubmitPage.buttonNoRedAndLettersWhite();
    }

    @Test
    public void notEmptyFeedbackPage() throws Exception {
//         TODO:
//         fill the whole form, click "Send"
        feedbackPage.enterName(NAME);
        feedbackPage.enterAge(AGE);
        feedbackPage.chooseEnglishLanguage();
        feedbackPage.chooseChineseLanguage();
        feedbackPage.selectFemale();
        feedbackPage.chooseGood();
        feedbackPage.writeComment(COMMENT);
        feedbackPage.clickSend();

//         check fields are filled correctly
        feedbackSubmitPage.checkAllFieldsAreFilledCorrectly(NAME, AGE, LANGUAGES, COMMENT);
//         check button colors
//         (green with white letter and red with white letters)
        feedbackSubmitPage.buttonYesGreenAndLettersWhite();
        feedbackSubmitPage.buttonNoRedAndLettersWhite();
    }

    @Test
    public void yesOnWithNameFeedbackPage() throws Exception {
//         TODO:
//         enter only NAME
        feedbackPage.enterName(NAME);
//         click "Send"
        feedbackPage.clickSend();
//         click "Yes"
        feedbackSubmitPage.clickButtonYes();
//         check message text: "Thank you, NAME, for your feedback!"
        thankYouFeedbackPage.checkMessage("Thank you, " + NAME + ", for your feedback!");
//         color of text is white with green on the background
    }

    @Test
    public void yesOnWithoutNameFeedbackPage() throws Exception {
//         TODO:
//         click "Send" (without entering anything
        feedbackPage.clickSend();
//         click "Yes"
        feedbackSubmitPage.clickButtonYes();
//         check message text: "Thank you for your feedback!"
        thankYouFeedbackPage.checkMessage("Thank you for your feedback!");
//         color of text is white with green on the background
        thankYouFeedbackPage.checkMessageAndLettersColor();

    }

    @Test
    public void noOnFeedbackPage() throws Exception {
//         TODO:
//         fill the whole form
        feedbackPage.enterName(NAME);
        feedbackPage.enterAge(AGE);
        feedbackPage.chooseEnglishLanguage();
        feedbackPage.chooseChineseLanguage();
        feedbackPage.selectFemale();
        feedbackPage.chooseGood();
        feedbackPage.writeComment(COMMENT);

//         click "Send"
        feedbackPage.clickSend();
//         click "No"
        feedbackSubmitPage.clickButtonNo();
//         check fields are filled correctly
        feedbackPage.checkAllFieldsAreFilledCorrectly(NAME, AGE, LANGUAGES, COMMENT);

    }
}
