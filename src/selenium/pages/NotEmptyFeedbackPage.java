package selenium.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import static org.junit.Assert.*;

public class NotEmptyFeedbackPage extends InitialFeedbackPage {
    @FindBy(how = How.XPATH, using = "//button[@class='w3-btn w3-green w3-xlarge']")
    private WebElement buttonYes;

    @FindBy(how = How.XPATH, using = "//button[@class='w3-btn w3-red w3-xlarge']")
    private WebElement buttonNo;

    @Override
    public void checkButtonStyle() throws Exception {
        super.checkButtonStyle();
    }

    @Override
    public void checkThatFormIsClean() {
        super.checkThatFormIsClean();
    }

    public void checkButtonColors() {
        assertEquals("rgba(76, 175, 80, 1)", buttonYes.getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", buttonYes.getCssValue("color"));
        assertEquals("rgba(244, 67, 54, 1)", buttonNo.getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", buttonNo.getCssValue("color"));
    }
    public void clickYes() {
        buttonYes.click();
    }
    public void clickNo() {
        buttonNo.click();
    }
}
