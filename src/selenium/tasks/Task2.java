package selenium.tasks;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.pages.AgeSamplePage;
import selenium.pages.Feedback_Task2;
import selenium.pages.Feedback_Task2_Page2;
import selenium.pages.Feedback_Task2_Page3;

import java.util.concurrent.TimeUnit;

public class Task2 {
    WebDriver driver;
    static Feedback_Task2 feedbackTask2;
    static Feedback_Task2_Page2 feedbackTask2Page2;
    static Feedback_Task2_Page3 feedbackTask2Page3;

    @Before
    public void openPage() {
        String libWithDriversLocation = System.getProperty("user.dir") + "\\lib\\";
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://kristinek.github.io/site/tasks/provide_feedback");
        feedbackTask2 = PageFactory.initElements(driver, Feedback_Task2.class);
        feedbackTask2Page2 = PageFactory.initElements(driver, Feedback_Task2_Page2.class);
        feedbackTask2Page3 = PageFactory.initElements(driver, Feedback_Task2_Page3.class);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @After
    public void closeBrowser()
    {
               driver.quit();
    }

    @Test
    public void initialFeedbackPage() throws Exception {
//         TODO:
       //         check that all field are empty and no tick are clicked
        feedbackTask2.checkAlltheFieldsAreEmpty();
//         "Don't know" is selected in "Genre" ---- IT IS UNCLICKABLE !!!
        feedbackTask2.dontKnowButtonEnabled();
//         "Choose your option" in "How do you like us?"
        feedbackTask2.chooseHowDoYouLikeUs();
//         check that the button send is blue with white letters
        feedbackTask2.buttonBlueWithWhiteLetters();
        }

    @Test
    public void emptyFeedbackPage() throws Exception {
//         TODO:
        //         click "Send" without entering any data
        feedbackTask2.clickSendWithoutData();
//         check fields are empty or null
        feedbackTask2Page2.checkFieldsAreEmptyAfterSend();
//         check button colors
        // (green with white letter and red with white letters)
        feedbackTask2Page2.checkTheGreenAndRedButtonColor();
    }

    @Test
    public void notEmptyFeedbackPage() throws Exception {
//         TODO:
//         fill the whole form, click "Send"
       feedbackTask2.fillWholeFormAndSend();
//         check fields are filled correctly
       feedbackTask2Page2.checkThatFormIsCorrect();
//         check button colors
// (green with white letter and red with white letters)
       feedbackTask2Page2.checkTheGreenAndRedButtonColor();
    }

    @Test
    public void yesOnWithNameFeedbackPage() throws Exception {
//         TODO:
//         enter only name
//         click "Send"
//         click "Yes"
        feedbackTask2.writeNameAndSendIt();
//         check message text: "Thank you, NAME, for your feedback!"
        feedbackTask2Page3.checkGreetingMessageTextWithName();
//         color of text is white with green on the background
        feedbackTask2Page3.checkGreetingMessageColor();
    }

    @Test
    public void yesOnWithoutNameFeedbackPage() throws Exception {
//         TODO:
//         click "Send" (without entering anything
//         click "Yes"
        feedbackTask2.sendBlankForm();
//         check message text: "Thank you for your feedback!"
        feedbackTask2Page3.checkGreetingMessageTextWithoutName();
//         color of text is white with green on the background
        feedbackTask2Page3.checkGreetingMessageColor();
    }

    @Test
    public void noOnFeedbackPage() throws Exception {
//         TODO:
//         fill the whole form
//         click "Send"
//         click "No"
        feedbackTask2.sendWholeFormClickNo();
//         check fields are filled correctly
        feedbackTask2.checkAfterClickingNoFormCorrect();
    }
}
