package selenium.pages.Task2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SecondPage extends FirstPage {

    @FindBy(how = How.TAG_NAME, using = "span")
    private List<WebElement> list;

    @FindBy(how = How.TAG_NAME, using = "button")
    private List<WebElement> button2;
    @FindBy(how = How.ID, using = "message")
    private WebElement message;
    @FindBy(how = How.CLASS_NAME, using = "w3-panel")
    private WebElement messageColor;

    public void checkEptyFields() {
        for (WebElement lists : list) {
            assertTrue(lists.getText().equals("") || lists.getText().equals("null"));


        }

    }

    public void checkColorButtons2() {
        assertEquals("rgba(76, 175, 80, 1)", button2.get(0).getCssValue("background-color"));
        assertEquals("rgba(244, 67, 54, 1)", button2.get(1).getCssValue("background-color"));


        assertEquals("rgba(255, 255, 255, 1)", button2.get(0).getCssValue("color"));
        assertEquals("rgba(255, 255, 255, 1)", button2.get(1).getCssValue("color"));


    }

    public void correctFields() {

        //         check fields are filled correctly

        assertEquals(fillName, list.get(0).getText());
        assertEquals(fillAge, list.get(1).getText());
        assertEquals("English", list.get(2).getText());
        assertEquals("male", list.get(3).getText());
        assertEquals("Good", list.get(4).getText());
        assertEquals(fillAria, list.get(5).getText());


    }

    public void yesButton() {

        button2.get(0).click();

    }

    public void noButton() {

        button2.get(1).click();

    }

    public void checkMessage() {

        assertEquals("Thank you, " + fillName + ", for your feedback!", message.getText());

    }

    public void checkMessageNoName() {

        assertEquals("Thank you for your feedback!", message.getText());

    }

    public void checkMessageColor() {

        assertEquals("rgba(76, 175, 80, 1)", messageColor.getCssValue("background-color"));


        assertEquals("rgba(255, 255, 255, 1)", messageColor.getCssValue("color"));


    }
}
