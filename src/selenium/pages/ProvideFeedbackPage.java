package selenium.pages;

import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;
import selenium.enums.LikeUsSelectOptions;
import selenium.enums.Task2Gender;

import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class ProvideFeedbackPage {
    @FindBy(how = How.ID, using = "fb_name")
    private WebElement nameInput;
    @FindBy(how = How.ID, using = "fb_age")
    private WebElement ageInput;
    @FindBy(how = How.XPATH, using = "//input[@type='checkbox' and @name='language']")
    private List<WebElement> languageCheckboxes;
    @FindBy(how = How.XPATH, using = "//input[@type='radio' and @name='gender']")
    private List<WebElement> genderRadio;
    @FindBy(how = How.ID, using = "like_us")
    private WebElement likeUsSelect;
    @FindBy(how = How.XPATH, using = "//textarea[@name='comment']")
    private WebElement commentArea;
    @FindBy(how = How.TAG_NAME, using = "button")
    private WebElement sendBtn;

    public void checkName(String name){
        assertEquals(name, nameInput.getAttribute("value"));
    }

    public void checkAge(String age){
        assertEquals(age, ageInput.getAttribute("value"));
    }

    public void checkLanguageCheckboxes(boolean english, boolean french, boolean spanish, boolean chinese){
        assertEquals(english, languageCheckboxes.get(0).isSelected());
        assertEquals(french, languageCheckboxes.get(1).isSelected());
        assertEquals(spanish, languageCheckboxes.get(2).isSelected());
        assertEquals(chinese, languageCheckboxes.get(3).isSelected());
    }

    public void checkGenderRadio(Task2Gender gender){
        switch (gender){
            case MALE:{
                assertTrue(genderRadio.get(0).isSelected());
                break;
            }
            case FEMALE:{
                assertTrue(genderRadio.get(1).isSelected());
                break;
            }
            case NONE:{
                assertTrue(genderRadio.get(2).isSelected());
                break;
            }
        }
    }

    public void checkLikeUsSelect(LikeUsSelectOptions option){
        Select select = new Select(likeUsSelect);
        switch (option){
            case NOT_CHOSEN:{
                assertEquals("Choose your option", select.getFirstSelectedOption().getText());
                break;
            }
            case GOOD:{
                assertEquals("Good", select.getFirstSelectedOption().getText());
                break;
            }
            case OK:{
                assertEquals("Ok, i guess", select.getFirstSelectedOption().getText());
                break;
            }
            case BAD:{
                assertEquals("Bad", select.getFirstSelectedOption().getText());
                break;
            }
            case WHY_ME:{
                assertEquals("Why me?", select.getFirstSelectedOption().getText());
                break;
            }
        }
    }

    public void checkComment(String comment){
        assertEquals(comment, commentArea.getAttribute("value"));
    }

    public void checkSendBtnColors(){
        assertEquals("rgba(33, 150, 243, 1)", sendBtn.getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", sendBtn.getCssValue("color"));
    }

    public void clickSendBtn(){
        sendBtn.click();
    }

    public void enterName(String name) {
        this.nameInput.clear();
        this.nameInput.sendKeys(name);
    }

    public void enterAge(String age) {
        this.ageInput.clear();
        this.ageInput.sendKeys(age);
    }

    public void selectLanguages(boolean english, boolean french, boolean spanish, boolean chinese) {
        for (WebElement checkbox: languageCheckboxes) {
            if (checkbox.isSelected()){
                checkbox.click();
            }
        }
        if(english){
            languageCheckboxes.get(0).click();
        }
        if(french){
            languageCheckboxes.get(1).click();
        }
        if(spanish){
            languageCheckboxes.get(2).click();
        }
        if(chinese){
            languageCheckboxes.get(3).click();
        }
    }

    public void selectGender(Task2Gender gender) {
        if (gender == Task2Gender.NONE){
            throw new InvalidArgumentException("\"Don't know\" option is disabled");
        }
        if (gender == Task2Gender.MALE){
            genderRadio.get(0).click();
        } else {
            genderRadio.get(1).click();
        }
    }

    public void selectLikeUsOption(LikeUsSelectOptions option) {
        Select select = new Select(likeUsSelect);
        switch (option){
            case NOT_CHOSEN:{
                throw new InvalidArgumentException("\"Choose your option\" option is disabled");
            }
            case GOOD:{
                select.selectByVisibleText("Good");
                break;
            }
            case OK:{
                select.selectByVisibleText("Ok, i guess");
                break;
            }
            case BAD:{
                select.selectByVisibleText("Bad");
                break;
            }
            case WHY_ME:{
                select.selectByVisibleText("Why me?");
                break;
            }
        }
    }

    public void enterComment(String comment) {
        this.commentArea.clear();
        this.commentArea.sendKeys(comment);
    }
}
