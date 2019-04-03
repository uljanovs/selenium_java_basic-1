package selenium.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Task2Page_yesno {


    @FindBy(how = How.XPATH, using = "//div[@class='w3-btn-group']//button[1]")
    private WebElement yesButton;

    @FindBy(how = How.XPATH, using = "//div[@class='w3-btn-group']//button[2]")
    private WebElement noButton;

    @FindBy (how = How.XPATH, using = "//*[@class='description'][1]")
    private WebElement nameCheck;

    @FindBy (how = How.XPATH, using = "//*[@id='age']")
    private WebElement ageCheck;

    @FindBy (how = How.XPATH, using = "//*[@id='language']")
    private WebElement languageCheck;

    @FindBy (how = How.XPATH, using = "#gender")
    private WebElement genderCheck;

    @FindBy (how = How.XPATH, using = "#option")
    private WebElement optionCheck;

    @FindBy (how = How.XPATH, using = "#comment")
    private WebElement commentCheck;

    @FindBy (how = How.XPATH, using = " //div[@class='w3-btn-group']//button[1]")
    private WebElement greenButton;

    @FindBy (how = How.XPATH, using = " //div[@class='w3-btn-group']//button[2]")
    private WebElement redButton;










    public void clickYes() {
        yesButton.click();
    }

    public void clickNo() { noButton.click();}


   // public void checkApplication (){                                           //šis nestrādā
     //   assertEquals("Your name: Jānis", nameCheck.getText());
       // assertEquals("24", ageCheck.getText());
//        assertEquals("Hello", commentCheck.getText());



    public void checkColorOfButtons (){
        assertEquals("rgba(255, 255, 255, 1)",greenButton.getCssValue("color"));
        assertEquals("rgba(255, 255, 255, 1)",redButton.getCssValue("color"));

        assertEquals("rgba(76, 175, 80, 1)",greenButton.getCssValue("background-color"));
        assertEquals("rgba(244, 67, 54, 1)",redButton.getCssValue("background-color"));
    }
    }










