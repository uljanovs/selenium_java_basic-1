package selenium.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static org.junit.Assert.*;

public class Task10Page3 {
    @FindBy(how = How.ID, using = "message")
    private WebElement thankYouMessage;
    @FindBy(how = How.CLASS_NAME, using = "w3-panel")
    private WebElement background;

    public void checkMessageText(String messageText) {
        assertTrue(thankYouMessage.isDisplayed());
        assertEquals(messageText, thankYouMessage.getText());
    }

    public void checkMessageColor() {
        assertEquals("rgba(76, 175, 80, 1)",background.getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", thankYouMessage.getCssValue("color"));
    }

}
