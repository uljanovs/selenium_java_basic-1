package selenium.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;

import static org.junit.Assert.*;

public class FeedbackPage {
    //page 1
    @FindBy(how = How.CSS, using = "#fb_name")
    private WebElement fbname;
    @FindBy(how = How.ID, using = "fb_age")
    private WebElement fbage;
    @FindBy(how = How.CSS, using = ".w3-check[value='French']")
    private WebElement languagecheckboxfrench;
    @FindBy(how = How.CSS, using = ".w3-check[value='Chinese']")
    private WebElement languagecheckboxchinese;
    @FindBy(how = How.CSS, using = ".w3-check[value='English']")
    private WebElement languagecheckboxenglish;
    @FindBy(how = How.CSS, using = ".w3-check[value='Spanish']")
    private WebElement languagecheckboxspanish;
    @FindBy(how = How.CSS, using = ".w3-radio[value='male'][type='radio']")
    private WebElement radiobuttonmale;
    @FindBy(how = How.CSS, using = ".w3-radio[value='female'][type='radio']")
    private WebElement radiobuttonfemale;
    @FindBy(how = How.XPATH, using = "//div[4]/input[3]")
    private WebElement radiobuttondisabled;
    @FindBy(how = How.CSS, using = "[name='comment']")
    private WebElement comment;
    @FindBy(how = How.ID, using = "like_us")
    private WebElement dropdown;
    @FindBy(how = How.XPATH, using = "//*[@class=\"w3-btn-block w3-blue w3-section\"]")
    private WebElement buttonsubmit;

    //page 2
    @FindBy(how = How.XPATH, using = "//*[@id='name']")
    private WebElement namedescription;
    @FindBy(how = How.XPATH, using = "//*[@id='age']")
    private WebElement agedescription;
    @FindBy(how = How.XPATH, using = "//*[@id='language']")
    private WebElement languagedescription;
    @FindBy(how = How.XPATH, using = "//*[@id='gender']")
    private WebElement genderescription;
    @FindBy(how = How.XPATH, using = "//*[@id='option']")
    private WebElement optiondescription;
    @FindBy(how = How.XPATH, using = "//*[@id='comment']")
    private WebElement commentdescription;
    @FindBy(how = How.XPATH, using = "//*[@class=\"w3-btn w3-green w3-xlarge\"]")
    private WebElement buttonyes;
    @FindBy(how = How.XPATH, using = "//*[@class=\"w3-btn w3-red w3-xlarge\"]")
    private WebElement buttonno;

    //page 3
    @FindBy(how = How.ID,using = "message")
    private WebElement message;
    @FindBy(how = How.CSS, using = ".w3-panel.w3-green")
    private WebElement messagecolour;

    public void checkfieldsareempty() {
        assertEquals("", fbname.getAttribute("value"));
        assertEquals("", fbage.getAttribute("value"));
        assertFalse(languagecheckboxchinese.isSelected());
        assertFalse(languagecheckboxenglish.isSelected());
        assertFalse(languagecheckboxfrench.isSelected());
        assertFalse(languagecheckboxspanish.isSelected());
        assertFalse(radiobuttonmale.isSelected());
        assertFalse(radiobuttonfemale.isSelected());
        assertTrue(radiobuttondisabled.isSelected());
        Select selectlist = new Select(dropdown);
        assertEquals("Choose your option",selectlist.getFirstSelectedOption().getText());
        assertEquals("",comment.getText());
    }
    public void checkdisabledradiobutton(){
        assertTrue(radiobuttondisabled.isSelected());
    }
    public void selectfromdropdown(){
        Select selectlist = new Select(dropdown);
        selectlist.selectByVisibleText("Why me?");
        assertEquals("Why me?", selectlist.getFirstSelectedOption().getAttribute("value"));
    }
    public void checksubmitbutton(){
        assertEquals("rgba(33, 150, 243, 1)",buttonsubmit.getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)",buttonsubmit.getCssValue("color"));
        assertEquals("15px", buttonsubmit.getCssValue("font-size"));
    }
    public void clickformbuttonsubmit(){
        buttonsubmit.click();
    }
    public void checkformisemptyafterclick(){
        assertEquals("",namedescription.getText());
        assertEquals("",agedescription.getText());
        assertEquals("",languagedescription.getText());
        assertEquals("null",genderescription.getText());
        assertEquals("null",optiondescription.getText());
        assertEquals("",commentdescription.getText());
    }
    public void checkbuttoncoloursyesorno(){
       //Chcecking colour green button 'yes'
        assertEquals("rgba(255, 255, 255, 1)",buttonyes.getCssValue("color"));
        assertEquals("rgba(76, 175, 80, 1)",buttonyes.getCssValue("background-color"));

        //Chcecking colour red button 'no'
        assertEquals("rgba(255, 255, 255, 1)",buttonno.getCssValue("color"));
        assertEquals("rgba(244, 67, 54, 1)",buttonno.getCssValue("background-color"));

    }
    String name = "Muthu";
    String text = "Testing task";
    public void fillfeedbackform(){
        fbname.sendKeys(name);
        fbage.sendKeys("22");
        languagecheckboxenglish.click();
        radiobuttonmale.click();
        Select selectlist = new Select(dropdown);
        selectlist.selectByVisibleText("Good");
        comment.sendKeys(text);
    }
    public void checkfilledform(){
        assertEquals(name,namedescription.getText());
        assertEquals("22",agedescription.getText());
        assertEquals("English",languagedescription.getText());
        assertEquals("male",genderescription.getText());
        assertEquals("Good",optiondescription.getText());
        assertEquals(text,commentdescription.getText());
    }
    public void entername(){
        fbname.sendKeys(name);
    }
    public void clickyes(){
        buttonyes.click();
    }
    public void clickno(){
        buttonno.click();
    }
    public void checkmessage(String messagetext){
        assertEquals(message.getText(),messagetext);
    }
    public void checkmessagetextcolour(){

        assertEquals("rgba(255, 255, 255, 1)",message.getCssValue("color"));
        assertEquals("rgba(76, 175, 80, 1)",messagecolour.getCssValue("background-color"));
    }
   public void checkfeedbackformafterentry(){
        assertEquals(name,fbname.getAttribute("value"));
        assertEquals("22",fbage.getAttribute("value"));
        assertTrue(languagecheckboxenglish.isSelected());
        assertTrue(radiobuttonmale.isSelected());
       Select selectlist = new Select(dropdown);
        assertEquals("Good",selectlist.getFirstSelectedOption().getText());
        assertEquals("",comment.getText());

   }
}
