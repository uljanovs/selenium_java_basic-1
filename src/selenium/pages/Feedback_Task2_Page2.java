package selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static org.junit.Assert.assertEquals;

public class Feedback_Task2_Page2 {
    @FindBy(how = How.CSS, using = "#name")
    private WebElement yourName;
    @FindBy(how = How.CSS, using = "#age")
    private WebElement yourAge;
    @FindBy(how = How.CSS, using = "#language")
    private WebElement yourLanguage;
    @FindBy(how = How.CSS, using = "#gender")
    private WebElement yourGenre;
    @FindBy(how = How.CSS, using = "#option")
    private WebElement yourOption;
    @FindBy(how = How.CSS, using = "#comment")
    private WebElement yourComment;
    @FindBy(how = How.XPATH, using = "//*[@class=\"w3-btn w3-green w3-xlarge\"]")
    private WebElement buttonYesGreen;
    @FindBy(how = How.XPATH, using = "//*[@class=\"w3-btn w3-red w3-xlarge\"]")
    private WebElement buttonNoRed;

    private WebDriver driver;

    public void checkFieldsAreEmptyAfterSend() {
        assertEquals("",yourName.getText());
        assertEquals("",yourAge.getText());
        assertEquals("",yourLanguage.getText());
        assertEquals("null",yourGenre.getText());
        assertEquals("null",yourOption.getText());
        assertEquals("",yourComment.getText());

    }

    public void checkTheGreenAndRedButtonColor() {
        //check the green button
        assertEquals("rgba(255, 255, 255, 1)", buttonYesGreen.getCssValue("color"));
        assertEquals("rgba(76, 175, 80, 1)", buttonYesGreen.getCssValue("background-color"));

        //check the red button
        assertEquals("rgba(255, 255, 255, 1)", buttonNoRed.getCssValue("color"));
        assertEquals("rgba(244, 67, 54, 1)", buttonNoRed.getCssValue("background-color"));

    }
    public void checkThatFormIsCorrect () {
        assertEquals("Kristine",yourName.getText());
        assertEquals("16",yourAge.getText());
        assertEquals("French",yourLanguage.getText());
        assertEquals("female",yourGenre.getText());
        assertEquals("Good",yourOption.getText());
        assertEquals("Very long task",yourComment.getText());
    }

    }

