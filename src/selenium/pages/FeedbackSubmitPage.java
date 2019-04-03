package selenium.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FeedbackSubmitPage {

    @FindBy(how = How.ID, using = "name")
    private WebElement nameInput;

    @FindBy(how = How.ID, using = "age")
    private WebElement ageInput;

    @FindBy(how = How.ID, using = "language")
    private WebElement yourLanguage;

    @FindBy(how = How.ID, using = "gender")
    private WebElement yourGenre;

    @FindBy(how = How.ID, using = "option")
    private WebElement yourOptionsOfUs;

    @FindBy(how = How.ID, using = "comment")
    private WebElement yourComment;

    @FindBy(how = How.CLASS_NAME, using = "w3-green")
    private WebElement buttonYes;

    @FindBy(how = How.CLASS_NAME, using = "w3-red")
    private WebElement buttonNo;


    public void clickButtonYes() {
        buttonYes.click();
    }

    public void clickButtonNo() {
        buttonNo.click();
    }

    public void checkAllFieldsAreClean() {
        assertEquals("", nameInput.getText());
        assertEquals("", ageInput.getText());
        assertEquals("", yourLanguage.getText());
        assertEquals("null", yourGenre.getText());
        assertEquals("null", yourOptionsOfUs.getText());
        assertEquals("", yourComment.getText());
    }

    public void buttonYesGreenAndLettersWhite() {
        assertEquals("rgba(76, 175, 80, 1)", buttonYes.getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", buttonYes.getCssValue("color"));
    }

    public void buttonNoRedAndLettersWhite() {
        assertEquals("rgba(244, 67, 54, 1)", buttonNo.getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", buttonNo.getCssValue("color"));
    }

    public void checkAllFieldsAreFilledCorrectly(String name, String age, List<String> languages, String comment) {
        assertEquals(name, nameInput.getText());
        assertEquals(age, ageInput.getText());
        assertTrue(yourLanguage.getText().contains(languages.get(0)));
        assertTrue(yourLanguage.getText().contains(languages.get(1)));
        assertEquals("female", yourGenre.getText());
        assertEquals("Good", yourOptionsOfUs.getText());
        assertEquals(comment, yourComment.getText());
    }



}
