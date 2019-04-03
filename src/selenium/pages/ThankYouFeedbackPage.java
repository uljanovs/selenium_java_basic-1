package selenium.pages;

import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static org.junit.Assert.assertEquals;

public class ThankYouFeedbackPage {

    @FindBy(how = How.ID, using = "message")
    private WebElement message;

    public void checkMessage(String messageText){
        assertEquals(messageText, message.getText());
    }

    public void checkMessageAndLettersColor(){
        assertEquals("rgba(76, 175, 80, 1)", message.getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", message.getCssValue("color"));
    }

}
