package selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class Task2Page_SubmitFeedback  {

    @FindBy(how = How.ID, using = "fb_name") // By.id("name")
    private WebElement inputName; // WebElement nameInput = driver.findElement(By.id("name"));

    @FindBy(how = How.ID, using = "fb_age")
    private WebElement inputAge;

    @FindBy(how = How.XPATH, using = "//div[@id='lang_check']//input[1]")
    private WebElement englishCheck;

    @FindBy(how = How.XPATH, using = "//div[@id='lang_check']//input[2]")
    private WebElement frenchCheck;

    @FindBy(how = How.XPATH, using = "//div[@id='lang_check']//input[3]")
    private WebElement spanishCheck;

    @FindBy(how = How.XPATH, using = "//div[@id='lang_check']//input[4]")
    private WebElement chineseCheck;

    @FindBy(how = How.XPATH, using = "//input[@type='radio'][1]")
    private WebElement radioMale;

    @FindBy(how = How.XPATH, using = "//input[@type='radio'][2]")
    private WebElement radioFemale;

    @FindBy(how = How.XPATH, using = "//input[@type='radio'][3]")
    private WebElement radioNoGender;

    @FindBy(how = How.ID, using = "like_us")
    private WebElement dropdown1;

    @FindBy(how = How.NAME, using = "comment")
    private WebElement inputComment;

    @FindBy (how = How.CSS, using = ".w3-btn-block")
    private WebElement sendButton;






    //iespējamās darbības


    public void enterName(String name) {
        inputName.clear();
        inputName.sendKeys(name);
    }

     //kaut kas ne tā

    public void enterAge(String age) {
        inputAge.clear();
        inputAge.sendKeys(age);
    }


    public void clickSubmit() {
        sendButton.click();
    }


    public void frenchC () { frenchCheck.click();}
    public void englishC () { englishCheck.click();}
    public void spanishC () { spanishCheck.click();}
    public void chineseC () { chineseCheck.click();}

    public void rMale () {radioMale.click();}
    public void rFemale () {radioFemale.click();}
    public void rDisabled () {radioNoGender.click();}

    public void enterComment (String comments) {

        inputComment.clear();
        inputComment.sendKeys("Hello");
    }

    public void selectOptionByValue (String option) throws Exception {
        Select dropdown = new Select(dropdown1);
        assertEquals(option, dropdown.getFirstSelectedOption().getText());
        dropdown.selectByVisibleText("Good");

    }

    //  izvērstās darbības

    public void enterWithoutWritingAnything() {


        sendButton.click();
    }

    //darbības testiem, ko iemest Task2 ar paplašinājumiem no citām lapām

    public void enterNameOnly (String name) {
        enterName(name);
        sendButton.click();
    }



        public void theWholeForm (String name, String age, String comments){
            enterName(name);
            enterAge(age);
            chineseCheck.click();
            radioMale.click();
            dropdown1.click();
            enterComment(comments);
            sendButton.click();
        }




    }

