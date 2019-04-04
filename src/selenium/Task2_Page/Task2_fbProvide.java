package selenium.Task2_Page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

public class Task2_fbProvide {

    @FindBy(how = How.ID, using = "fb_name")// By.id("fb_name")
    private WebElement nameInput;           // WebElement nameInput = driver.findElement(By.id("name"));
    @FindBy(how=How.ID,using="fb_age")      // By.id("fb_age")
    private WebElement ageInput;

    //Language
    @FindBy(xpath = "//input[@type='checkbox']")
    private List<WebElement> chkBxlang;

    //Genre
    @FindBy(xpath = "//input[@type='radio']")
    private List<WebElement> genre;

    //Dropdown
    @FindBy(tagName = "option")
    private List<WebElement> dropdownLikeUs;

    //Comment text area
    @FindBy(xpath = "//textarea[@name='comment']")
    private WebElement commentary;

    //Button "Send"
    @FindBy(xpath = "//button[@type='submit']")
    private WebElement btnSend;


    public void checkFBPFormIsClean() {
        assertTrue(nameInput.getAttribute("value").equals(""));
        assertTrue(ageInput.getAttribute("value").equals(""));
        //Check boxes
        for(int i = 0; i < 4; i++) { assertFalse(chkBxlang.get(i).isSelected()); }
        //Genre
        assertFalse(genre.get(0).isSelected());
        assertFalse(genre.get(1).isSelected());
        assertTrue(genre.get(2).isSelected());
        //Dropdown = default option
        assertTrue(dropdownLikeUs.get(0).getText().equals("Choose your option"));
        //Button check colors
        assertTrue(btnSend.getCssValue("background-color").equals("rgba(33, 150, 243, 1)"));
        assertTrue(btnSend.getCssValue("color").equals("rgba(255, 255, 255, 1)"));
    }

    public void clickFBPSendForm(){
        btnSend.click();
    }

    public void fillFBPForm(String name, String age, String lang, String gen, String like, String comment){
        nameInput.clear();
        nameInput.sendKeys(name);
        ageInput.clear();
        ageInput.sendKeys(age);
        //Language
        switch(lang) {
            case "English":
                chkBxlang.get(0).click();
                break;
            case "French":
                chkBxlang.get(1).click();
                break;
            case "Spanish":
                chkBxlang.get(2).click();
                break;
            case "Chinese":
                chkBxlang.get(3).click();
                break;
        }
        //Genre
        switch(gen) {
            case "male":
                genre.get(0).click();
                break;
            case "female":
                genre.get(1).click();
                break;
            case "default":
                genre.get(2).click();
                break;
         }
        //LikeUs
        switch(like) {
            case "Choose your option":
                dropdownLikeUs.get(0).click();
                break;
            case "Good":
                dropdownLikeUs.get(1).click();
                break;
            case "Ok, i guess":
                dropdownLikeUs.get(2).click();
                break;
            case "Bad; Why me?":
                dropdownLikeUs.get(3).click();
                break;
        }
        commentary.clear();
        commentary.sendKeys(comment);
    }
    public void fbProvide_CheckFilledCorrect(String name, String age, String lang, String gen, String like, String comment){
        assertTrue(nameInput.getAttribute("value").equals(name));
        assertTrue(ageInput.getAttribute("value").equals(age));
        switch(lang) {
            case "English":
                assertTrue(chkBxlang.get(0).getAttribute("value").equals(lang));
                break;
            case "French":
                assertTrue(chkBxlang.get(1).getAttribute("value").equals(lang));
                break;
            case "Spanish":
                assertTrue(chkBxlang.get(2).getAttribute("value").equals(lang));
                break;
            case "Chinese":
                assertTrue(chkBxlang.get(3).getAttribute("value").equals(lang));
                break;
        }
        //Genre
        switch(gen) {
            case "male":
                assertTrue(genre.get(0).getAttribute("value").equals(gen));
                break;
            case "female":
                assertTrue(genre.get(1).getAttribute("value").equals(gen));
                break;
            case "default":
                assertTrue(genre.get(2).getAttribute("value").equals(gen));
                break;
        }
        //LikeUs
        switch(like) {
            case "Choose your option":
                assertTrue(dropdownLikeUs.get(0).getAttribute("value").equals(like));
                break;
            case "Good":
                assertTrue(dropdownLikeUs.get(1).getAttribute("value").equals(like));
                break;
            case "Ok, i guess":
                assertTrue(dropdownLikeUs.get(2).getAttribute("value").equals(like));
                break;
            case "Bad; Why me?":
                assertTrue(dropdownLikeUs.get(3).getAttribute("value").equals(like));
                break;
        }
        assertTrue(commentary.getAttribute("value").equals(comment));
//        assertEquals(fillName, name.getAttribute("value"));
//        assertEquals(fillAge, age.getAttribute("value"));
//        assertTrue(checkBoxs.get(0).isSelected());
//        assertTrue(radioButtons0.isSelected());

//        assertTrue(dropDowns.get(1).isSelected());
//        assertEquals(fillAria, textarea.getAttribute("value"));

    }

}
