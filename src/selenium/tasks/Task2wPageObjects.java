package selenium.tasks;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import selenium.enums.LikeUsSelectOptions;
import selenium.enums.Task2Gender;
import selenium.pages.*;

public class Task2wPageObjects {
    private WebDriver driver;
    private ProvideFeedbackPage provideFeedbackPage;
    private CheckFeedbackPage checkFeedbackPage;
    private ThankYouFeedbackPage thankYouFeedbackPage;

    @Before
    public void openPage() {
        String libWithDriversLocation = System.getProperty("user.dir") + "\\lib\\";
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://kristinek.github.io/site/tasks/provide_feedback");
        provideFeedbackPage = PageFactory.initElements(driver, ProvideFeedbackPage.class);
        checkFeedbackPage = PageFactory.initElements(driver, CheckFeedbackPage.class);
        thankYouFeedbackPage = PageFactory.initElements(driver, ThankYouFeedbackPage.class);
    }

    @After
    public void closeBrowser() {
        driver.quit();
    }

    @Test
    public void initialFeedbackPage() throws Exception {
//         check that all field are empty and no tick are clicked
        provideFeedbackPage.checkName("");
        provideFeedbackPage.checkAge("");
        provideFeedbackPage.checkLanguageCheckboxes(false, false, false, false);
        provideFeedbackPage.checkComment("");
//         "Don't know" is selected in "Genre"
        provideFeedbackPage.checkGenderRadio(Task2Gender.NONE);
//         "Choose your option" in "How do you like us?"
        provideFeedbackPage.checkLikeUsSelect(LikeUsSelectOptions.NOT_CHOSEN);
//         check that the button send is blue with white letters
        provideFeedbackPage.checkSendBtnColors();
    }

    @Test
    public void emptyFeedbackPage() throws Exception {
//         click "Send" without entering any data
        provideFeedbackPage.clickSendBtn();
//         check fields are empty or null
        checkFeedbackPage.checkName("");
        checkFeedbackPage.checkAge("");
        checkFeedbackPage.checkLanguage("");
        checkFeedbackPage.checkGender(Task2Gender.NONE);
        checkFeedbackPage.checkOption(LikeUsSelectOptions.NOT_CHOSEN);
        checkFeedbackPage.checkComment("");
//         check button colors
//         (green with white letter and red with white letters)
        checkFeedbackPage.checkYesBtnColors();
        checkFeedbackPage.checkNoBtnColors();
    }

    @Test
    public void notEmptyFeedbackPage() throws Exception {
//         fill the whole form, click "Send"
        provideFeedbackPage.enterName("name");
        provideFeedbackPage.enterAge("30");
        provideFeedbackPage.selectLanguages(false, true, false, true);
        provideFeedbackPage.selectGender(Task2Gender.FEMALE);
        provideFeedbackPage.selectLikeUsOption(LikeUsSelectOptions.WHY_ME);
        provideFeedbackPage.enterComment("Some comment\ntest\ntest");
        provideFeedbackPage.clickSendBtn();
//         check fields are filled correctly
        checkFeedbackPage.checkName("name");
        checkFeedbackPage.checkAge("30");
        checkFeedbackPage.checkLanguage(false, true, false, true);
        checkFeedbackPage.checkGender(Task2Gender.FEMALE);
        checkFeedbackPage.checkOption(LikeUsSelectOptions.WHY_ME);
        checkFeedbackPage.checkComment("Some comment\ntest\ntest");
//         check button colors
//         (green with white letter and red with white letters)
        checkFeedbackPage.checkYesBtnColors();
        checkFeedbackPage.checkNoBtnColors();
    }

    @Test
    public void yesOnWithNameFeedbackPage() throws Exception {
//         enter only name
        provideFeedbackPage.enterName("name");
//         click "Send"
        provideFeedbackPage.clickSendBtn();
//         click "Yes"
        checkFeedbackPage.clickYesBtn();
//         check message text: "Thank you, NAME, for your feedback!"
        thankYouFeedbackPage.checkMessage("name");
//         color of text is white with green on the background
        thankYouFeedbackPage.checkMsgContainerColors();
    }

    @Test
    public void yesOnWithoutNameFeedbackPage() throws Exception {
//         click "Send" (without entering anything
        provideFeedbackPage.clickSendBtn();
//         click "Yes"
        checkFeedbackPage.clickYesBtn();
//         check message text: "Thank you for your feedback!"
        thankYouFeedbackPage.checkMessage("");
//         color of text is white with green on the background
        thankYouFeedbackPage.checkMsgContainerColors();
    }

    @Test
    public void noOnFeedbackPage() throws Exception {
//         fill the whole form
        provideFeedbackPage.enterName("name");
        provideFeedbackPage.enterAge("30");
        provideFeedbackPage.selectLanguages(true, true, true, true);
        provideFeedbackPage.selectGender(Task2Gender.MALE);
        provideFeedbackPage.selectLikeUsOption(LikeUsSelectOptions.OK);
        provideFeedbackPage.enterComment("Some comment\ntest\ntest");
//         click "Send"
        provideFeedbackPage.clickSendBtn();
//         click "No"
        checkFeedbackPage.clickNoBtn();
//         check fields are filled correctly
        provideFeedbackPage.checkName("name");
        provideFeedbackPage.checkAge("30");
        provideFeedbackPage.checkLanguageCheckboxes(true, true, true, true);
        provideFeedbackPage.checkGenderRadio(Task2Gender.MALE);
        provideFeedbackPage.checkLikeUsSelect(LikeUsSelectOptions.OK);
        provideFeedbackPage.checkComment("Some comment\ntest\ntest");
    }
}
