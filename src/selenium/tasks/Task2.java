package selenium.tasks;

import javafx.scene.paint.Stop;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.Task2_Page.Task2_provide_feedback;
import selenium.Task2_Page.Task2_check_feedback;

import java.util.concurrent.TimeUnit;



public class Task2 {
    WebDriver driver;
    private static WebDriverWait wait;
    static long startTime;
    static Task2_provide_feedback fbPage;
    static Task2_check_feedback fbSubmittedPage;

    @Before
    public void openPage() {
        String libWithDriversLocation = System.getProperty("user.dir") + "\\lib\\";
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.get("https://kristinek.github.io/site/tasks/provide_feedback");
        fbPage = PageFactory.initElements(driver, Task2_provide_feedback.class);
        fbSubmittedPage = PageFactory.initElements(driver, Task2_check_feedback.class);
        wait = (WebDriverWait) new WebDriverWait(driver, 10).ignoring(StaleElementReferenceException.class);

        startTime = System.currentTimeMillis();
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
//         check that the button send is blue with white letters
        fbPage.checkFormIsClean();

    }

    @Test
    public void emptyFeedbackPage() throws Exception {
//         TODO:
//         click "Send" without entering any data
        fbPage.clickSendForm();
//         check fields are empty or null
        fbSubmittedPage.checkResultFeedbackEmpty();
//         check button colors
//         (green with white letter and red with white letters)
        fbSubmittedPage.chckButtonYesNoColors();
    }

    @Test
    public void notEmptyFeedbackPage() throws Exception {
//         TODO:
//         fill the whole form, click "Send"
        String name = "TestName";
        String age = "33";
        String comment = "Some comment here";
        int option = 1;
        fbPage.fillFeedback(name, age, comment, option);
        fbPage.clickSendForm();
//         check fields are filled correctly
        fbSubmittedPage.chckFieldsRok();
//         check button colors
//         (green with white letter and red with white letters)
        fbSubmittedPage.chckButtonYesNoColors();
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
