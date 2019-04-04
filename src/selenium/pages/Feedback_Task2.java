package selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;


public class Feedback_Task2 {
    @FindBy(how = How.CSS, using = "#fb_name")
    private WebElement nameInput;
    @FindBy(how = How.CSS, using = "#fb_age")
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
    @FindBy(how = How.XPATH, using = "//*[@class=\"w3-btn w3-red w3-xlarge\"]")
    private WebElement buttonNoRed;
    @FindBy(how = How.XPATH, using = "//*[@class=\"w3-btn w3-green w3-xlarge\"]")
    private WebElement buttonYesGreen;

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
    public void writeNameAndSendIt () {
    nameInput.sendKeys("Kristine");
    buttonSend.click();
    buttonYesGreen.click();
    }
public void sendBlankForm() {
    buttonSend.click();
    buttonYesGreen.click();
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
