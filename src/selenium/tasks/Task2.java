package selenium.tasks;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.Task2_Page.Task2_fbProvide;
import selenium.Task2_Page.Task2_fbCheck;
import selenium.Task2_Page.Task2_fbThank;

import java.util.concurrent.TimeUnit;



public class Task2 {
    WebDriver driver;
    private static WebDriverWait wait;
    static long startTime;
    static Task2_fbProvide fbProvide;
    static Task2_fbCheck fbCheck;
    static Task2_fbThank fbThank;

    @Before
    public void openPage() {
        String libWithDriversLocation = System.getProperty("user.dir") + "\\lib\\";
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.get("https://kristinek.github.io/site/tasks/provide_feedback");
        fbProvide = PageFactory.initElements(driver, Task2_fbProvide.class);
        fbCheck = PageFactory.initElements(driver, Task2_fbCheck.class);
        fbThank = PageFactory.initElements(driver, Task2_fbThank.class);
        wait = (WebDriverWait) new WebDriverWait(driver, 10).ignoring(StaleElementReferenceException.class);

        startTime = System.currentTimeMillis();
    }

    @After
    public void closeBrowser() {
        driver.quit();
    }

    @Test
    public void initialFeedbackPage() throws Exception {
//         check that all field are empty and no tick are clicked
//         "Don't know" is selected in "Genre"
//         "Choose your option" in "How do you like us?"
//         check that the button send is blue with white letters
        fbProvide.checkFBPFormIsClean();

    }

    @Test
    public void emptyFeedbackPage() throws Exception {
//         click "Send" without entering any data
        fbProvide.clickFBPSendForm();
//         check fields are empty or null
        fbCheck.checkCFBEmpty();
//         check button colors
//         (green with white letter and red with white letters)
        fbCheck.checkCFBButtonYesNoColors();
    }

    @Test
    public void notEmptyFeedbackPage() throws Exception {
//         fill the whole form, click "Send"
        String name = "TestName";
        String age = "33";
        String lang = "French"; //Language(can be only one): English; French; Spanish; Chinese
        String gen = "male";    //Genre: male; female; default(Disabled)
        String like = "Good";   //LikeUs (0-3): 0=Choose your option(Disabled) ;1=Good; 2=Ok, i guess; 3=Bad; Why me?
        String comment = "Some comment here";

        fbProvide.fillFBPForm(name, age, lang, gen, like, comment);
        fbProvide.clickFBPSendForm();
//         check fields are filled correctly
        fbCheck.checkCFBFieldsFilledOk(name, age, lang, gen, like, comment);
//         check button colors
//         (green with white letter and red with white letters)
        fbCheck.checkCFBButtonYesNoColors();
    }

    @Test
    public void yesOnWithNameFeedbackPage() throws Exception {
//         enter only name
        String name = "Some Test Name";
        fbProvide.fillFBPForm(name, "", "", "", "", "");
//         click "Send"
        fbProvide.clickFBPSendForm();
//         click "Yes"
        fbCheck.clickCFBYes();
//         check message text: "Thank you, NAME, for your feedback!"
        fbThank.fbThank_CheckMessage(name);
//         color of text is white with green on the background
        fbThank.fbThank_CheckMessageTextColors();
        fbThank.fbThank_CheckMessageBackgroundColors();
    }

    @Test
    public void yesOnWithoutNameFeedbackPage() throws Exception {
//         click "Send" (without entering anything)
        fbProvide.clickFBPSendForm();
//         click "Yes"
        fbCheck.clickCFBYes();
//         check message text: "Thank you for your feedback!"
        fbThank.fbThank_CheckMessage("");
//         color of text is white with green on the background
        fbThank.fbThank_CheckMessageTextColors();
        fbThank.fbThank_CheckMessageBackgroundColors();
    }

    @Test
    public void noOnFeedbackPage() throws Exception {
//         TODO:
//         fill the whole form
        String name = "TestName";
        String age = "33";
        String lang = "French"; //Language(can be only one): English; French; Spanish; Chinese
        String gen = "male";    //Genre: male; female; default(Disabled)
        String like = "Good";   //LikeUs (0-3): 0=Choose your option(Disabled) ;1=Good; 2=Ok, i guess; 3=Bad; Why me?
        String comment = "Some comment here";

        fbProvide.fillFBPForm(name, age, lang, gen, like, comment);
//         click "Send"
        fbProvide.clickFBPSendForm();
//         click "No"
        fbCheck.clickCFBNo();
//         check fields are filled correctly
        fbProvide.fbProvide_CheckFilledCorrect(name, age, lang, gen, like, comment);
    }
}
