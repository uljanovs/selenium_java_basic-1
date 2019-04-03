package selenium.pages;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static org.junit.Assert.*;

public class Task10Page2 {

    @FindBy(how = How.XPATH, using = "//*[@id='name']")
    private WebElement yourname;
    @FindBy(how = How.XPATH, using = "//*[@id='age']")
    private WebElement yourage;
    @FindBy(how = How.XPATH, using = "//*[@id='language']")
    private WebElement yourlanguage;
    @FindBy(how = How.XPATH, using = "//*[@id='gender']")
    private WebElement yourgender;
    @FindBy(how = How.XPATH, using = "//*[@id='option']")
    private WebElement youroptionofus;
    @FindBy(how = How.XPATH, using = "//*[@id='comment']")
    private WebElement yourcomments;
    @FindBy(how = How.XPATH, using = "//*[text()='Yes']")
    private WebElement yesButton;
    @FindBy(how = How.XPATH, using = "//*[text()='No']")
    private WebElement noButton;
    @FindBy(how = How.TAG_NAME, using = "h2")
    private WebElement header;

    public String getPageHeader() {
        return header.getText();
    }

    public void checkPageHeaderText(String aHeaderText) {
        assertEquals(getPageHeader(), aHeaderText);
    }

    public void checkFieldsEmptyOrNull() {
        checkPageHeaderText("Is this the feedback you want to give us?");
        assertEquals("",yourname.getText());
        assertEquals("",yourage.getText());
        assertEquals("",yourlanguage.getText());
        assertEquals("null",yourgender.getText());
        assertEquals("null",youroptionofus.getText());
        assertEquals("",yourcomments.getText());
    }

    public void checkButtonsColorandText() {
        assertEquals("rgba(76, 175, 80, 1)",yesButton.getCssValue("background-color"));
        assertEquals("rgb(255, 255, 255)", yesButton.getCssValue("text-decoration-color"));
        assertEquals("rgba(244, 67, 54, 1)",noButton.getCssValue("background-color"));
        assertEquals("rgb(255, 255, 255)", noButton.getCssValue("text-decoration-color"));
    }

    public void checkFieldsFilledCorrectlyOnValidationPage(String name, int age, String language, String gender, String optionofus, String comments) {
        checkPageHeaderText("Is this the feedback you want to give us?");
        assertEquals(yourname.getText(), name);
        assertEquals(yourage.getText(), String.valueOf(age));
        assertEquals(yourlanguage.getText(), language);
        assertEquals(yourgender.getText(), gender);
        assertEquals(youroptionofus.getText(), optionofus);
        assertEquals(yourcomments.getText(), comments); }

        public void checkFieldsFilledCorrectlyOnValidationPage(){
        checkPageHeaderText("Is this the feedback you want to give us?");
        checkYourName("Name");
        checkYourAge(10);
        checkYourGender("male");
        checkYourComments("comments");
        checkYourOptionOfUs("Good");
        checkYourlanguage("English");
        }

    public void checkYourName(String name) {
        assertEquals(yourname.getText(), name);
    }

    public void checkYourAge(int age) {
        assertEquals(yourage.getText(), String.valueOf(age));
    }

    public void checkYourlanguage(String language) {
        assertEquals(yourlanguage.getText(), language);
    }

    public void checkYourGender(String gender) {
        assertEquals(yourgender.getText(), gender);
    }

    public void checkYourOptionOfUs(String optionofus) {
        assertEquals(youroptionofus.getText(), optionofus);
    }

    public void checkYourComments(String comments) {
        assertEquals(yourcomments.getText(), comments);
    }

    public void clickYes() {
        yesButton.click();}

    public void clickNo() {
        noButton.click();}

}


