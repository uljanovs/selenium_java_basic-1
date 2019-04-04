package selenium.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static org.junit.Assert.assertEquals;

public class ThankYouPage extends  GenericSamplePage {

    @FindBy(how = How.XPATH, using = "//*[@id=\"fb_thx\"]/div")
    private WebElement thankYouField;

    public void checkThankYouButtonText(){
        assertEquals("Thank you, test, for your feedback!", thankYouField.getText());
    }

   public void checkThankYouButtonColors(){
        assertEquals("rgba(76, 175, 80, 1)", thankYouField.getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", thankYouField.getCssValue("color"));
    }

}
