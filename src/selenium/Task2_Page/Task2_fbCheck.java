package selenium.Task2_Page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

import static junit.framework.TestCase.assertTrue;


public class Task2_fbCheck extends Task2_fbProvide {

    @FindBy(how = How.TAG_NAME, using = "span")
    private List<WebElement> spanElement;

    @FindBy(xpath = "//button[@class='w3-btn w3-green w3-xlarge']")
    private WebElement btnYes;
    @FindBy(xpath = "//button[@class='w3-btn w3-red w3-xlarge']")
    private WebElement btnNo;


    public void checkCFBEmpty() {
        for(WebElement spans : spanElement){
            assertTrue(spans.getText().equals("") || spans.getText().equals("null"));
        }
    }
    public void checkCFBButtonYesNoColors() {
        assertTrue(btnYes.getCssValue("background-color").equals("rgba(76, 175, 80, 1)"));
        assertTrue(btnYes.getCssValue("color").equals("rgba(255, 255, 255, 1)"));
        assertTrue(btnNo.getCssValue("background-color").equals("rgba(244, 67, 54, 1)"));
        assertTrue(btnNo.getCssValue("color").equals("rgba(255, 255, 255, 1)"));
    }
    public void checkCFBFieldsFilledOk(String name, String age, String lang, String gen, String like, String comment){
        assertTrue(spanElement.get(0).getText().equals(name));
        assertTrue(spanElement.get(1).getText().equals(age));
        assertTrue(spanElement.get(2).getText().equals(lang) || spanElement.get(2).getText().equals(""));
        assertTrue(spanElement.get(3).getText().equals(gen) || spanElement.get(3).getText().equals("null"));
        assertTrue(spanElement.get(4).getText().equals(like) || spanElement.get(4).getText().equals("null"));
        assertTrue(spanElement.get(5).getText().equals(comment) || spanElement.get(5).getText().equals(""));
    }
    public void clickCFBYes(){
        btnYes.click();
    }
    public void clickCFBNo(){
        btnNo.click();
    }

}
