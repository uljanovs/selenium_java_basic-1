package selenium.pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;


import static org.junit.Assert.assertEquals;

public class Feedback_Task2_Page3 {
    @FindBy(how = How.CSS, using = "h2")
    private WebElement greetingMessage;
    @FindBy(how = How.XPATH, using = "//*[@class=\"w3-panel w3-green\"]")
    private WebElement greetingMessageColor;

    private WebDriver driver;

    public void checkGreetingMessageTextWithName() {
        assertEquals("Thank you, Kristine, for your feedback!", greetingMessage.getText());
    }

    public void checkGreetingMessageColor() {
      //check color and backround color of greeting message is right
        assertEquals("rgba(255, 255, 255, 1)", greetingMessage.getCssValue("color"));
        assertEquals("rgba(76, 175, 80, 1)", greetingMessageColor.getCssValue("background-color"));
    }

    public void checkGreetingMessageTextWithoutName() {
        assertEquals("Thank you for your feedback!", greetingMessage.getText());
           }
}


