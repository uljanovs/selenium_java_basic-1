package selenium.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class EnterNumberPage {
    @FindBy(how = How.XPATH, using = "//input[@id'numb']")
    private WebElement enterNumberField;

    public void textInput(String string){
        enterNumberField.clear();
        enterNumberField.sendKeys(string);
    }
    public void numberInput(String number){
        enterNumberField.clear();
        enterNumberField.sendKeys(number);
        numberInput(String.valueOf(number));

    }
    public void checkError(){
        //Please enter a number error
    }

}
