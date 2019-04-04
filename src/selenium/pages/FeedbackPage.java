package selenium.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FeedbackPage extends  GenericSamplePage {

    @FindBy(how = How.TAG_NAME, using = "span")
    private List<WebElement> filledOrNotFields;
    @FindBy(how = How.XPATH, using = "//*[@id=\"fb_thx\"]/div/div[2]/button[1]")
    private WebElement buttonYes;
    @FindBy(how = How.XPATH, using = "//*[@id=\"fb_thx\"]/div/div[2]/button[2]")
    private WebElement buttonNo;

    public void checkFieldsEmpty() {
        for (WebElement fields : filledOrNotFields) {
            assertTrue(fields.getText().equals("") || fields.getText().equals("null"));
        }
    }

    public void checkYesAndNoButtonColor(){
        assertEquals("rgba(76, 175, 80, 1)", buttonYes.getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", buttonYes.getCssValue("color"));
        assertEquals("rgba(244, 67, 54, 1)", buttonNo.getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", buttonNo.getCssValue("color"));
    }

    public void checkFieldsFilledCorrectly(String name, String age, String comment) {

        assertEquals(name, filledOrNotFields.get(0).getText());
        assertEquals(age, filledOrNotFields.get(1).getText());
        assertEquals("English", filledOrNotFields.get(2).getText());
        assertEquals("male", filledOrNotFields.get(3).getText());
        assertEquals("Good", filledOrNotFields.get(4).getText());
        assertEquals(comment, filledOrNotFields.get(5).getText());
    }

    public void clickButtonYes(){
        buttonYes.click();
    }

    public void clickButtonNo(){
        buttonNo.click();
    }
}
