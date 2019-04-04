package selenium.Task2_Page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static junit.framework.TestCase.assertTrue;

public class Task2_fbThank {

    @FindBy(xpath = "//h2[@id='message']")
    private WebElement message_text;
    @FindBy(xpath = "//div[@class='w3-panel w3-green']")
    private WebElement message_background;

    public void fbThank_CheckMessage(String name) {
        assertTrue(message_text.getText().equals("Thank you, " + name + ", for your feedback!") || message_text.getText().equals("Thank you for your feedback!"));
    }
    public void fbThank_CheckMessageTextColors(){
        assertTrue(message_text.getCssValue("color").equals("rgba(255, 255, 255, 1)"));
        assertTrue(message_text.getCssValue("background-color").equals("rgba(0, 0, 0, 0)"));
    }
    public void fbThank_CheckMessageBackgroundColors(){
        assertTrue(message_background.getCssValue("color").equals("rgba(255, 255, 255, 1)"));
        assertTrue(message_background.getCssValue("background-color").equals("rgba(76, 175, 80, 1)"));
    }
}
