package selenium.pages.Task2;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;


import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FirstPage {

    String fillName = "Name";
    String fillAge = "30";
    String fillAria = "TEST";
    @FindBy(how = How.ID, using = "fb_name")
    private WebElement name;
    @FindBy(how = How.ID, using = "fb_age")
    private WebElement age;

    @FindBy(how = How.XPATH, using = "//*[@type='checkbox']")
    private List<WebElement> checkBoxs;

    @FindBy(how = How.XPATH, using = "//*[@value='male']")
    private WebElement radioButtons0;

    @FindBy(how = How.XPATH, using = "//*[@value='female']")
    private WebElement radioButtons1;

    @FindBy(how = How.XPATH, using = "//*[@id='fb_form']/form/div[4]/label[3]")
    private WebElement radioButtons2;


    @FindBy(how = How.TAG_NAME, using = "option")
    private List<WebElement> dropDowns;

    @FindBy(how = How.TAG_NAME, using = "textarea")
    private WebElement textarea;

    @FindBy(how = How.TAG_NAME, using = "button")
    private WebElement button;


    public void checkNameAgeIsClean() {
        assertEquals("", name.getText());
        assertEquals("", age.getText());
    }

    public void checkCheckBoxIsClean() {
        for (WebElement checkBox : checkBoxs) {
            assertFalse(checkBox.isSelected()); // checkboxes are NOT selected
        }
    }

    public void checkRadioButtonsIsClean() {

        assertFalse(radioButtons0.isSelected());
        assertFalse(radioButtons1.isSelected());
        assertTrue(radioButtons2.isEnabled());
    }

    public void checkDropDownsIsClean() {
        //  Select dropDown = new Select(dropDowns);


        assertEquals("Choose your option", dropDowns.get(0).getText());
        dropDowns.get(0).click();


    }

    public void checkTextareaIsClean() {
        assertEquals("", textarea.getText());
    }

    public void checkButtonColor() {


        assertEquals("rgba(255, 255, 255, 1)", button.getCssValue("color"));
        assertEquals("rgba(33, 150, 243, 1)", button.getCssValue("background-color"));
    }

    public void submitButton() {


        button.click();
    }


    public void fillFields() {

        name.sendKeys(fillName);
        age.sendKeys(fillAge);
        checkBoxs.get(0).click();
        radioButtons0.click();
        dropDowns.get(1).click();
        textarea.sendKeys(fillAria);


    }

    public void enterName() {
        name.sendKeys(fillName);


    }

    public void checkFields() {

        assertEquals(fillName, name.getAttribute("value"));
        assertEquals(fillAge, age.getAttribute("value"));
        assertTrue(checkBoxs.get(0).isSelected());
        assertTrue(radioButtons0.isSelected());
        assertTrue(dropDowns.get(1).isSelected());
        assertEquals(fillAria, textarea.getAttribute("value"));

    }


}








