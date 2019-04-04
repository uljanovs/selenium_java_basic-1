package selenium.pages;

import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static org.junit.Assert.assertEquals;

public class GenericPage {
    @FindBy(how = How.TAG_NAME, using = "h1")
    private WebElement header;

    public String getHeaderText() {
        return header.getText();
    }

    public void checkHeader(String headerText) {
        assertEquals(headerText, getHeaderText());
    }
}
