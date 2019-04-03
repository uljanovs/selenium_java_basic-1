package selenium.tasks;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import selenium.pages.*;

public class Task2 {
    WebDriver driver;
    static InitialFeedbackPage feedbackPage;
    static EmptyFeedbackPage emptyFeedbackPage;
    static ThankYouForFeedbackPage thankYouForFeedbackPage;
    static NotEmptyFeedbackPage notEmptyFeedbackPage;

    @Before
    public void openPage() {
        String libWithDriversLocation = System.getProperty("user.dir") + "\\lib\\";
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://kristinek.github.io/site/tasks/provide_feedback");
        feedbackPage = PageFactory.initElements(driver, InitialFeedbackPage.class);
        emptyFeedbackPage = PageFactory.initElements(driver, EmptyFeedbackPage.class);
        thankYouForFeedbackPage = PageFactory.initElements(driver, ThankYouForFeedbackPage.class);
        notEmptyFeedbackPage = PageFactory.initElements(driver, NotEmptyFeedbackPage.class);

    }

    @After
    public void closeBrowser() {
        driver.quit();
    }

    @Test
    public void initialFeedbackPage() throws Exception {
//         TODO:done
//         check that all field are empty and no tick are clicked
        feedbackPage.checkThatFormIsClean();

//         "Don't know" is selected in "Genre"
        feedbackPage.checkDontKnowIsSelectedInGenre();

//         "Choose your option" in "How do you like us?"
        feedbackPage.checkWhatOptionSelected("Choose your option");

//         check that the button send is blue with white letters
        feedbackPage.checkButtonStyle();


    }

    @Test
    public void emptyFeedbackPage() throws Exception {
//         TODO:
//         click "Send" without entering any data
        feedbackPage.checkThatFormIsClean();
        feedbackPage.clickSend();
//         check fields are empty or null
        //

//         check button colors
//         (green with white letter and red with white letters)
        emptyFeedbackPage.checkButtonColors();

    }

    @Test
    public void notEmptyFeedbackPage() throws Exception {
//         TODO:
//         fill the whole form, click "Send"
        feedbackPage.fillWholeForm();
        feedbackPage.clickSend();

//         check fields are filled correctly

//         check button colors
//         (green with white letter and red with white letters)
        notEmptyFeedbackPage.checkButtonColors();
    }

    @Test
    public void yesOnWithNameFeedbackPage() throws Exception {
//         TODO://done
//         enter only name
        feedbackPage.enterName("Alina");
//         click "Send"
        feedbackPage.clickSend();
//         click "Yes"
        notEmptyFeedbackPage.clickYes();
//         check message text: "Thank you, NAME, for your feedback!"
//         color of text is white with green on the background
        thankYouForFeedbackPage.checkMessageStyle();
    }

    @Test
    public void yesOnWithoutNameFeedbackPage() throws Exception {
//         TODO://done
//         click "Send" (without entering anything
        feedbackPage.checkThatFormIsClean();
        feedbackPage.clickSend();
//         click "Yes"
        emptyFeedbackPage.clickYes();
//         check message text: "Thank you for your feedback!"
        thankYouForFeedbackPage.checkThankYouForYourFeedbackMessage();
//         color of text is white with green on the background
        thankYouForFeedbackPage.checkMessageStyle();

    }

    @Test
    public void noOnFeedbackPage() throws Exception {
//         TODO:
//         fill the whole form
        feedbackPage.fillWholeForm();
//         click "Send"
        feedbackPage.clickSend();
//         click "No"
        notEmptyFeedbackPage.clickNo();

//         check fields are filled correctly
    }
}
