package selenium.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static org.junit.Assert.*;

public class Task10Page { //.w3-check[value='English'][type='checkbox']
    @FindBy(how = How.ID, using = "fb_name")
    private WebElement namebox;
    @FindBy(how = How.ID, using = "fb_age")
    private WebElement agebox;
    @FindBy(how = How.CSS, using = ".w3-check[type='checkbox']")
    private List<WebElement> checkBoxes;
    @FindBy(how = How.CSS, using = ".w3-radio[type='radio']")
    private List<WebElement> radio;
    @FindBy(how = How.XPATH, using = "//*[@type='radio' and @value='']")
    private WebElement dontKnowGenderSelect;
    @FindBy(how = How.ID, using = "like_us")
    private WebElement likeDropdown;
    @FindBy(how = How.CSS, using = "textarea")
    private WebElement commentbox;
    @FindBy(how = How.XPATH, using = "//*[@type='submit']")
    private WebElement sendButton;
    @FindBy(how = How.TAG_NAME, using = "h1")
    private WebElement header;

    public String getPageHeader() {
        return header.getText();
    }

    public void checkPageHeaderText(String aHeaderText) {
        assertEquals(getPageHeader(), aHeaderText);
    }

    public void fillTheWholeForm(String name, int age, int languageIndex, int gender, String option, String comments) {
        namebox.clear();
        namebox.sendKeys(name);
        agebox.clear();
        agebox.sendKeys(String.valueOf(age));
        ((WebElement) checkBoxes.get(languageIndex)).click();
        ((WebElement) radio.get(gender)).click();
        Select dropdown = new Select(likeDropdown);
        dropdown.selectByVisibleText(option);
        commentbox.clear();
        commentbox.sendKeys(comments);}

    public void fillTheWholeFormadnClickSend(){
        enterName("Name");
        enterAge(10);
        selectLanguage(0);
        selectGender(0);
        selectOptionFromDropdown("Good");
        enterComments("comments");
        clickSend();
    }

    public void enterName(String name) {
        namebox.clear();
        namebox.sendKeys(name);}

    public void enterAge(int age) {
        agebox.clear();
        agebox.sendKeys(String.valueOf(age));}

    public void selectLanguage(int languageIndex) {
        (checkBoxes.get(languageIndex)).click();}

    public void selectGender (int gender) {
        (radio.get(gender)).click();}

    public void selectOptionFromDropdown (String option) {
        Select dropdown = new Select(likeDropdown);
        dropdown.selectByVisibleText(option);
    }

    public void enterComments (String comments) {
        commentbox.clear();
        commentbox.sendKeys(comments);}

    public void clickSend() {
        sendButton.click();}

    public void checkFieldsEmptyAndNoticks() {
        checkPageHeaderText("Give us your feedback!");
        assertEquals("",namebox.getAttribute("value"));
        assertEquals("",agebox.getAttribute("value"));
        for (WebElement checkBox : checkBoxes) {
            assertFalse(checkBox.isSelected());}
        assertEquals("", commentbox.getText());
    }

    public void checkDontKnowGenderSelected() {
        assertTrue(dontKnowGenderSelect.isSelected());
    }

    public void checkDropdownDummy() {
        Select dropdown = new Select(likeDropdown);
    assertEquals("Choose your option", dropdown.getFirstSelectedOption().getText());}


    public void checkSendButtonColorandText() {
        assertEquals("rgba(33, 150, 243, 1)",sendButton.getCssValue("background-color"));
        assertEquals("rgb(255, 255, 255)", sendButton.getCssValue("text-decoration-color"));
    }

    public void checkFieldsAreFilledCorrectlyAfterReturnByNo(String name, int age, int languageIndex, int genderIndex, String option, String comments) {
        checkPageHeaderText("Give us your feedback!");
        assertEquals(namebox.getAttribute("value"), name);
        assertEquals(agebox.getAttribute("value"), (String.valueOf(age)));
        assertTrue(checkBoxes.get(languageIndex).isDisplayed());
        assertTrue(checkBoxes.get(genderIndex).isDisplayed());
        assertEquals(likeDropdown.getAttribute("value"), option);
        assertEquals(commentbox.getAttribute("value"), comments);
    }

    public void checkFieldsAreFilledCorrectlyAfterReturnByNo(){
        checkPageHeaderText("Give us your feedback!");
        checkYourName("Name");
        checkYourAge(10);
        checkYourlanguage(0);
        checkYourGender(0);
        checkYourOptionOfUs("Good");
        checkYourComments("comments");
    }

    public void checkYourName(String name) {
        assertEquals(namebox.getAttribute("value"), name);
    }

    public void checkYourAge(int age) {
        assertEquals(agebox.getAttribute("value"), String.valueOf(age));
    }

    public void checkYourlanguage(int languageIndex) {
        assertTrue(checkBoxes.get(languageIndex).isDisplayed());
    }

    public void checkYourGender(int genderIndex) {
        assertTrue(checkBoxes.get(genderIndex).isDisplayed());
    }

    public void checkYourOptionOfUs(String option) {
        assertEquals(likeDropdown.getAttribute("value"), option);
    }

    public void checkYourComments(String comments) {
        assertEquals(commentbox.getAttribute("value"), comments);
    }

}
