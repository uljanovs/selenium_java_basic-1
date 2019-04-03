package selenium.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ThankYouForFeedbackPage {
    @FindBy(how = How.XPATH, using = "//div[@class='w3-panel w3-green']")
    private WebElement thankYouForYourFeedbackMessage;


    public void checkThankYouForYourFeedbackMessage() throws Exception {
        assertTrue(thankYouForYourFeedbackMessage.isDisplayed());

    }

    public void checkMessageStyle() throws Exception {
        assertEquals("rgba(76, 175, 80, 1)", thankYouForYourFeedbackMessage.getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", thankYouForYourFeedbackMessage.getCssValue("color"));
    }
}