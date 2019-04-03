package selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static org.junit.Assert.assertEquals;

public class MessageCheck {

    @FindBy(how = How.XPATH, using = "//h2")
    private WebElement thankYouMessage;

    @FindBy(how = How.XPATH, using = "//div//h2[@id='message']")
    private WebElement color1;
    @FindBy(how = How.XPATH, using = "//div[@class='w3-panel w3-green']")
    private WebElement color2;





    public String getMessage() {
        return thankYouMessage.getText();
    }

    public void checkMessage (String aMessageText) {
        assertEquals(getMessage(), aMessageText);
    }

    public void checkColor (){
        assertEquals("rgba(255, 255, 255, 1)",color1.getCssValue("color"));

        assertEquals("rgba(76, 175, 80, 1)",color2.getCssValue("background-color"));
    }

    public void checkSuccessfulMessage (){
        assertEquals("Thank you, Jānis, for your feedback!", thankYouMessage.getText());

    }



}
