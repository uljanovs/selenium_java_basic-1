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
    @FindBy(how = How.XPATH, using = "//input[@type='radio' and @name='gender']")
    private List<WebElement> radioButtons;
    @FindBy(how = How.XPATH, using = "//input[@name='gender' and @value='']")
    private WebElement dontKnowRadioButton;
    @FindBy(how = How.XPATH, using = "//*[@id=\"like_us\"]")
    private WebElement howDoYouLikeUs;
    @FindBy(how = How.XPATH, using = "//*[@id=\"fb_form\"]/form/button")
    private WebElement sendButton;
    @FindBy(how = How.XPATH, using = "//*[@id=\"fb_name\"]")
    private WebElement nameField;
    @FindBy(how = How.ID, using = "fb_age")
    private WebElement ageField;
    @FindBy(how = How.XPATH, using = "//*[@id=\"fb_form\"]/form/div[6]/textarea")
    private WebElement commentField;


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

    public void fillAllFields(String name, String age, String comment) {
        nameField.sendKeys(name);
        ageField.sendKeys(age);
        commentField.sendKeys(comment);
        checkBoxes.get(0).click();
        radioButtons.get(0).click();

        Select dropdown = new Select(howDoYouLikeUs);
        dropdown.selectByIndex(1);
    }

}
