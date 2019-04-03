package selenium.tasks;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import selenium.pages.AgeSamplePage;
import selenium.pages.FeedbackPage;

import java.util.concurrent.TimeUnit;

public class Task2 {
    WebDriver driver;
    static FeedbackPage feedbackPage;

    @Before
    public void openPage() {
        String libWithDriversLocation = System.getProperty("user.dir") + "\\lib\\";
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://kristinek.github.io/site/tasks/provide_feedback");
        feedbackPage = PageFactory.initElements(driver, FeedbackPage.class);
    }

    @After
    public void closeBrowser() {
        driver.quit();
    }

    @Test
    public void initialFeedbackPage() throws Exception {
//         TODO:
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//         check that all field are empty and no tick are clicked
        feedbackPage.checkfieldsareempty();
//         "Don't know" is selected in "Genre"
        //it's unclickable
        feedbackPage.checkdisabledradiobutton();
//         "Choose your option" in "How do you like us?"
        feedbackPage.selectfromdropdown();
//         check that the button send is blue with white letters
        feedbackPage.checksubmitbutton();
    }

    @Test
    public void emptyFeedbackPage() throws Exception {
//         TODO:
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//         click "Send" without entering any data
        feedbackPage.clickformbuttonsubmit();
//         check fields are empty or null
        feedbackPage.checkformisemptyafterclick();
//         check button colors
//         (green with white letter and red with white letters)
        feedbackPage.checkbuttoncoloursyesorno();
    }

    @Test
    public void notEmptyFeedbackPage() throws Exception {
//         TODO:
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//         fill the whole form, click "Send"
        feedbackPage.fillfeedbackform();
        feedbackPage.clickformbuttonsubmit();
        Thread.sleep(10000);
//         check fields are filled correctly
        feedbackPage.checkfilledform();
//         check button colors
//         (green with white letter and red with white letters)
        feedbackPage.checkbuttoncoloursyesorno();
    }

    @Test
    public void yesOnWithNameFeedbackPage() throws Exception {
//         TODO:
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//         enter only name
        feedbackPage.entername();
//         click "Send"
        feedbackPage.clickformbuttonsubmit();
//         click "Yes"
        feedbackPage.clickyes();

//         check message text: "Thank you, NAME, for your feedback!"
        feedbackPage.checkmessage("Thank you, Muthu, for your feedback!");
//         color of text is white with green on the background
        feedbackPage.checkmessagetextcolour();

    }

    @Test
    public void yesOnWithoutNameFeedbackPage() throws Exception {
//         TODO:
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//         click "Send" (without entering anything
        feedbackPage.clickformbuttonsubmit();
//         click "Yes"
        feedbackPage.clickyes();
//         check message text: "Thank you for your feedback!"
        feedbackPage.checkmessage("Thank you for your feedback!");
//         color of text is white with green on the background
        feedbackPage.checkmessagetextcolour();
    }

    @Test
    public void noOnFeedbackPage() throws Exception {
//         TODO:
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//         fill the whole form
        feedbackPage.fillfeedbackform();
//         click "Send"
        feedbackPage.clickformbuttonsubmit();
//         click "No"
        feedbackPage.clickno();
//         check fields are filled correctly
        feedbackPage.checkfeedbackformafterentry();
    }
}
