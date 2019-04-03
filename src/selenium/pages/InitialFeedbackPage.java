package selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static org.junit.Assert.*;


public class InitialFeedbackPage extends GenericSamplePage {
    @FindBy(how = How.ID, using = "fb_name")
    private WebElement nameInput;
    @FindBy(how = How.ID, using = "fb_age")
    private WebElement ageInput;
    @FindBy(how = How.XPATH, using = "//input[@value='English'and @type='checkbox']")

    private WebElement selectEnglish;
    @FindBy(how = How.XPATH, using = "//input[@value='French'and @type='checkbox']")
    private WebElement selectFrench;
    @FindBy(how = How.XPATH, using = "//input[@value='Spanish'and @type='checkbox']")
    private WebElement selectSpanish;
    @FindBy(how = How.XPATH, using = "//input[@value='Chinese'and @type='checkbox']")
    private WebElement selectChinese;
    @FindBy(how = How.XPATH, using = "//input[@value='male'and @type='radio']")
    private WebElement selectGenderMale;
    @FindBy(how = How.XPATH, using = "//input[@value='female'and @type='radio']")
    private WebElement selectGenderFemale;
    @FindBy(how = How.XPATH, using = "//label[contains(.,'know')]")
    private WebElement genderDontKnow;

    @FindBy(how = How.CSS, using = "#like_us")
    private WebElement fieldLikeUs;
    @FindBy(how = How.XPATH, using = " //option[@value='Good']")
    private WebElement optionGood;

    @FindBy(how = How.CSS, using = "#like_us") //not working help
    private WebElement likeUsOptionDisabled;
    @FindBy(how = How.XPATH, using = "//select[@id='like_us']")
    private WebElement selectOption;

    @FindBy(how = How.XPATH, using = "//textarea[@class]")
    private WebElement commentField;

    @FindBy(how = How.XPATH, using = "//button[@class='w3-btn-block w3-blue w3-section']")
    private WebElement buttonSend;

    @FindBy(how = How.TAG_NAME, using = "span")
    private List <WebElement> formElements;


    //   check that all field are empty and no tick are clicked
    //   "Don't know" is selected in "Genre"
    //  "Choose your option" in "How do you like us?"
    //   check that the button send is blue with white letters

    public void checkThatFormIsClean() {
        checkPageHeaderText("Give us your feedback!");
        assertEquals(nameInput.getAttribute("value"), "");
        assertEquals(ageInput.getAttribute("value"), "");
        assertFalse(selectEnglish.isSelected());
        assertFalse(selectFrench.isSelected());
        assertFalse(selectSpanish.isSelected());
        assertFalse(selectChinese.isSelected());
        assertEquals(commentField.getAttribute("value"), "");

    }

    public void checkWhatOptionSelected(String option) throws Exception {
        Select dropdown = new Select(selectOption);
        assertEquals(option, dropdown.getFirstSelectedOption().getText());
    }

    public void checkButtonStyle() throws Exception {
        assertEquals("rgba(33, 150, 243, 1)", buttonSend.getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", buttonSend.getCssValue("color"));
    }

    public void checkDontKnowIsSelectedInGenre() throws Exception {
        assertTrue(genderDontKnow.isSelected());

    }

    public void clickSend() {
        buttonSend.click();
    }
    public void selectLikeUsOptionGood(String option) throws Exception {
        Select dropdown = new Select(selectOption);
        assertEquals(option, dropdown.getFirstSelectedOption().getText());
    }

    public void enterName(String name) {
        ageInput.clear();
        ageInput.sendKeys(name);
    }
    public void enterAge(String age) {
        nameInput.clear();
        ageInput.sendKeys(age);
    }
    public void inputComment(String comment){
        commentField.clear();
        commentField.sendKeys(comment);
    }


    public void enterAge(int age) {
        enterAge(String.valueOf(age));


    }

    public void fillWholeForm(){
        enterName("Alina");
        enterAge(21);
        selectEnglish.click();
        selectGenderFemale.click();
        optionGood.click();
        inputComment("Hi , i want to leave feedback");

    }
    public void checkFieldsAreFilledCorrect(){
        fillWholeForm();



    }

}