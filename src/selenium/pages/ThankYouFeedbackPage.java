package selenium.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static junit.framework.TestCase.assertEquals;

public class ThankYouFeedbackPage {
    @FindBy(how = How.ID, using = "message")
    private WebElement message;
    @FindBy(how = How.XPATH, using = "//div[@id='fb_thx']/div")
    private WebElement msgContainer;

    public void checkMessage(String name){
        if (!name.equals("")){
            assertEquals("Thank you, " + name + ", for your feedback!", this.message.getText());
        } else {
            assertEquals("Thank you for your feedback!", this.message.getText());
        }
    }

    public void checkMsgContainerColors(){
        assertEquals("rgba(76, 175, 80, 1)", msgContainer.getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", msgContainer.getCssValue("color"));
    }
}
