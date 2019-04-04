package selenium.pages;

import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FeedbackPage extends GenericPage {

    @FindBy(how = How.ID, using = "fb_name")
    private WebElement nameInput;

    @FindBy(how = How.ID, using = "fb_age")
    private WebElement ageInput;

    @FindBy(how = How.CLASS_NAME, using = "w3-check")
    private List<WebElement> languagesCheckBoxes;

    @FindBy(how = How.CLASS_NAME, using = "w3-radio")
    private List<WebElement> selectGenre;

    @FindBy(how = How.CSS, using = "#like_us")
    private WebElement howDoYouLikeUs;

    @FindBy(how = How.XPATH, using = "//textarea[@name='comment']")
    private WebElement comment;

    @FindBy(how = How.XPATH, using = "//button[@type='submit']")
    private WebElement sendButton;

    public void enterName(String name) {
        nameInput.clear();
        nameInput.sendKeys(name);
    }

    public void enterAge(String age) {
        ageInput.clear();
        ageInput.sendKeys(age);
    }

    public void enterAge(int age) {
        enterAge(String.valueOf(age));
    }

    public void languageCheckBoxesAreUnticked() {
        for (WebElement language : languagesCheckBoxes) {
            assertFalse(language.isSelected());
        }
    }

    public void chooseEnglishLanguage() {
        languagesCheckBoxes.get(0).click();
    }

    public void chooseFrenchLanguage() {
        languagesCheckBoxes.get(1).click();
    }

    public void chooseSpanishLanguage() {
        languagesCheckBoxes.get(2).click();
    }

    public void chooseChineseLanguage() {
        languagesCheckBoxes.get(3).click();
    }

    public void selectYourGenreDontKnowSelected() {
        for (WebElement genre : selectGenre) {
            assertTrue(selectGenre.get(2).isSelected());
        }
    }

    public void selectMale() {
        selectGenre.get(0).click();
    }

    public void selectFemale() {
        selectGenre.get(1).click();
    }

    public void setHowDoYouLikeUS() {
        Select dropdown = new Select(howDoYouLikeUs);
        assertEquals("Choose your option", dropdown.getFirstSelectedOption().getText());
    }

    public void chooseGood() {
        Select dropdown = new Select(howDoYouLikeUs);
        dropdown.selectByIndex(1);
    }

    public void chooseOkIGuess() {
        Select dropdown = new Select(howDoYouLikeUs);
        dropdown.selectByIndex(2);
    }

    public void chooseBad() {
        Select dropdown = new Select(howDoYouLikeUs);
        dropdown.selectByIndex(3);
    }

    public void chooseWhyMe() {
        Select dropdown = new Select(howDoYouLikeUs);
        dropdown.selectByIndex(4);
    }

    public void writeComment(String comments) {
        comment.clear();
        comment.sendKeys(comments);
    }

    public void clickSend() {
        sendButton.click();
    }

    public void checkAllFieldsAreClean() {
        checkHeader("Give us your feedback!");
        assertEquals("Name", nameInput.getAttribute("placeholder"));
        assertEquals("Age", ageInput.getAttribute("placeholder"));
        languageCheckBoxesAreUnticked();
        selectYourGenreDontKnowSelected();
        setHowDoYouLikeUS();
        assertEquals("", comment.getText());
    }

    public void buttonIsBlueAndLettersWhite() {
        assertEquals("rgba(33, 150, 243, 1)", sendButton.getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", sendButton.getCssValue("color"));
    }

    public void checkAllFieldsAreFilledCorrectly(String name, String age, List<String> languages, String comment) {
        checkHeader("Give us your feedback!");
        assertEquals(name, nameInput.getAttribute("value"));
        assertEquals(age, ageInput.getAttribute("value"));
        assertTrue(languagesCheckBoxes.get(0).isSelected());
        assertTrue(languagesCheckBoxes.get(3).isSelected());
        assertTrue(selectGenre.get(1).isSelected());
        Select dropdown = new Select(howDoYouLikeUs);
        assertTrue(dropdown.getFirstSelectedOption().isSelected());
        assertEquals(comment, this.comment.getAttribute("value"));

    }
}
