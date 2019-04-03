package selenium.Task2_Page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class Task2_provide_feedback {

    @FindBy(how = How.ID, using = "fb_name")// By.id("fb_name")
    private WebElement nameInput;           // WebElement nameInput = driver.findElement(By.id("name"));
    @FindBy(how=How.ID,using="fb_age")      // By.id("fb_age")
    private WebElement ageInput;

    //Language
    @FindBy(xpath = "//input[@type='checkbox']")
    private WebElement chkBx;
    @FindBy(xpath = "//input[@type='checkbox' and @value='English']")
    private WebElement engLangCheck;
    @FindBy(xpath = "//input[@type='checkbox' and @value='French']")
    private WebElement frLangCheck;
    @FindBy(xpath = "//input[@type='checkbox' and @value='Spanish']")
    private WebElement spaLangCheck;
    @FindBy(xpath = "//input[@type='checkbox' and @value='Chinese']")
    private WebElement chiLangCheck;

    //Genre
    @FindBy(xpath = "//input[@type='radio' and @value='male']")
    private WebElement genreMale;
    @FindBy(xpath = "//input[@type='radio' and @value='female']")
    private WebElement genreFemale;
    @FindBy(xpath = "//input[@type='radio' and @checked and @disabled]")
    private WebElement genreX;

    //Dropdown
    @FindBy(tagName = "option")
    private List<WebElement> dropdownLikeUs;

    //Comment text area
    @FindBy(xpath = "//textarea[@name='comment']")
    private WebElement commentary;

    //Button "Send"
    @FindBy(xpath = "//button[@type='submit']")
    private WebElement btnSend;

    public void checkFormIsClean() {
        assertEquals(nameInput.getAttribute("value"), "");
        assertEquals(ageInput.getAttribute("value"), "");
        //Check boxes
        for(int i = 0; i < 4; i++) { assertFalse(chkBx.isSelected()); }
        //Genre
        assertFalse(genreMale.isSelected());
        assertFalse(genreFemale.isSelected());
        assertTrue(genreX.isSelected());
        //Dropdown = default option
        assertTrue(dropdownLikeUs.get(0).getText().equals("Choose your option"));
        //
        assertEquals("rgba(33, 150, 243, 1)", btnSend.getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", btnSend.getCssValue("color"));
    }

    public void clickSendForm(){
        btnSend.click();
    }
    public void fillFeedback(String name, String age, int option, String comment){
        nameInput.clear();
        nameInput.sendKeys(name);
        ageInput.clear();
        ageInput.sendKeys(age);
        engLangCheck.click();
        genreMale.click();
        dropdownLikeUs.get(option).click();
        commentary.clear();
        commentary.sendKeys(comment);
    }


}
