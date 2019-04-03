package selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;
import sun.awt.SunHints;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;


public class Feedback_Task2 {
    //FIRST PAGE
    @FindBy(how = How.CSS, using = "#fb_name") // By.id("name")
    private WebElement nameInput; // WebElement nameInput = driver.findElement(By.id("name"));
    @FindBy(how = How.CSS, using = "#fb_age") // By.name("age")
    private WebElement ageInput;
    @FindBy(how = How.CSS, using = ".w3-check[value='English']")
    private WebElement selectEnglish;
    @FindBy(how = How.CSS, using = ".w3-check[value='French']")
    private WebElement selectFrench;
    @FindBy(how = How.CSS, using = ".w3-check[value='Spanish']")
    private WebElement selectSpanish;
    @FindBy(how = How.CSS, using = ".w3-check[value='Chinese']")
    private WebElement selectChinese;
    @FindBy(how = How.CSS, using = ".w3-radio[value='male']")
    private WebElement selectMale;
    @FindBy(how = How.CSS, using = ".w3-radio[value='female']")
    private WebElement selectFemale;
    @FindBy(how = How.CSS, using = ".w3-check[id='like_us']")
    private WebElement selectOption;
    @FindBy(how = How.CSS, using = ".w3-check[value='disabled']")
    private WebElement selectDontKnow;
    @FindBy(how = How.CSS, using = "[name='comment']")
    private WebElement writeComment;
    @FindBy(how = How.CSS, using = "#like_us")
    private WebElement howDoYouLikeList;
    @FindBy(how = How.XPATH, using = "//*[@class=\"w3-btn-block w3-blue w3-section\"]")
    private WebElement buttonSend;
    @FindBy(how = How.XPATH, using = "//div[4]/input[3]")
    private WebElement buttonDontKnow;

    //SECOND PAGE AFTER CLICKING "SEND"
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
    @FindBy(how = How.CSS, using = "h2")
    private WebElement greetingMessage;
    @FindBy(how = How.XPATH, using = "//*[@class=\"w3-panel w3-green\"]")
    private WebElement greetingMessageColor;

    private WebDriver driver;

    public void checkAlltheFieldsAreEmpty() {
       assertEquals("",nameInput.getAttribute("value"));
       assertEquals("",ageInput.getAttribute("value"));
        assertFalse(selectEnglish.isSelected());
        assertFalse(selectFrench.isSelected());
        assertFalse(selectSpanish.isSelected());
        assertFalse(selectChinese.isSelected());
        assertFalse(selectMale.isSelected());
        assertFalse(selectFemale.isSelected());
        assertEquals("",writeComment.getText());
        Select dropdown = new Select(howDoYouLikeList);
        assertEquals("Choose your option",dropdown.getFirstSelectedOption().getText());

       }
           public void dontKnowButtonEnabled() {
        assertTrue(buttonDontKnow.isSelected());

           }

    public void chooseHowDoYouLikeUs() {
        Select dropdown = new Select(howDoYouLikeList);
        assertEquals("Choose your option",dropdown.getFirstSelectedOption().getText());
        dropdown.selectByVisibleText("Good");
        assertEquals("Good", dropdown.getFirstSelectedOption().getAttribute("value"));

    }

    public void  buttonBlueWithWhiteLetters() {
        assertEquals("rgba(255, 255, 255, 1)", buttonSend.getCssValue("color"));
        assertEquals("rgba(33, 150, 243, 1)", buttonSend.getCssValue("background-color"));
    }

    public void clickSendWithoutData() {
        buttonSend.click();
    }

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

public void fillWholeFormAndSend() {
        nameInput.sendKeys("Kristine");
        ageInput.sendKeys("16");
        selectFrench.click();
        selectFemale.click();
        Select dropdown = new Select(howDoYouLikeList);
        dropdown.selectByVisibleText("Good");
        writeComment.sendKeys("Very long task");
        buttonSend.click();
}

public void checkThatFormIsCorrect () {
    assertEquals("Kristine",yourName.getText());
    assertEquals("16",yourAge.getText());
    assertEquals("French",yourLanguage.getText());
    assertEquals("female",yourGenre.getText());
    assertEquals("Good",yourOption.getText());
    assertEquals("Very long task",yourComment.getText());
    }

public void writeNameAndSendIt () {
    nameInput.sendKeys("Kristine");
    buttonSend.click();
    buttonYesGreen.click();
    assertEquals("Thank you, Kristine, for your feedback!", greetingMessage.getText());
}

public void checkGreetingMessageColor() {
//check color and backround color of greeting message is right
    assertEquals("rgba(255, 255, 255, 1)", greetingMessage.getCssValue("color"));
    assertEquals("rgba(76, 175, 80, 1)", greetingMessageColor.getCssValue("background-color"));
}

public void sendBlankForm() {
    buttonSend.click();
    buttonYesGreen.click();
}

public void checkGreetingMessageAndColors(){
    assertEquals("Thank you for your feedback!", greetingMessage.getText());

    assertEquals("rgba(255, 255, 255, 1)", greetingMessage.getCssValue("color"));
    assertEquals("rgba(76, 175, 80, 1)", greetingMessageColor.getCssValue("background-color"));
}
public void sendWholeFormClickNo() {
    nameInput.sendKeys("Kristine");
    ageInput.sendKeys("16");
    selectFrench.click();
    selectFemale.click();
    Select dropdown = new Select(howDoYouLikeList);
    dropdown.selectByVisibleText("Good");
    writeComment.sendKeys("Very long task");

    buttonSend.click();
    buttonNoRed.click();
}

public void checkAfterClickingNoFormCorrect()
{
    assertEquals("Kristine",nameInput.getAttribute("value"));
    assertEquals("16",ageInput.getAttribute("value"));
    assertEquals("French",selectFrench.getAttribute("value"));
    assertEquals("female",selectFemale.getAttribute("value"));
    assertEquals("Good",howDoYouLikeList.getAttribute("value"));
    assertEquals("Very long task",writeComment.getAttribute("value"));
}

}
