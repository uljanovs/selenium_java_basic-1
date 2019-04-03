package selenium.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.openqa.selenium.support.ui.Select;

public class FeedbackFormPage extends  GenericSamplePage{

    @FindBy(how = How.XPATH, using = "//input[@type='checkbox' and @name='language']")
    private List<WebElement> checkBoxes;
    @FindBy(how = How.XPATH, using = "//input[@name='gender' and @value='']")
    private WebElement dontKnowRadioButton;
    @FindBy(how = How.XPATH, using = "//*[@id=\"like_us\"]")
    private WebElement howDoYouLikeUs;
    @FindBy(how = How.XPATH, using = "//*[@id=\"fb_form\"]/form/button")
    private WebElement sendButton;

    public void checkCheckBoxesAreEmpty(){
        for (WebElement checkbox : checkBoxes) {
            assertFalse(checkbox.isSelected());
        }
    }

    public void checkDontInGenreIsSelected(){
        assertTrue(dontKnowRadioButton.isSelected());
    }

    public void checkHowDoYouLikeUsDefault(){
        Select dropdown = new Select(howDoYouLikeUs);
        assertEquals("Choose your option", dropdown.getFirstSelectedOption().getText());
    }

    public void checkButtonSendBlueWhiteLetters(){
        assertEquals("rgba(33, 150, 243, 1)", sendButton.getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", sendButton.getCssValue("color"));
    }

    public void clickSendButton(){
        sendButton.click();
    }



}
