package selenium.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import selenium.enums.LikeUsSelectOptions;
import selenium.enums.Task2Gender;

import java.util.Arrays;
import java.util.LinkedList;

import static junit.framework.TestCase.assertEquals;

public class CheckFeedbackPage {
    @FindBy(how = How.ID, using = "name")
    private WebElement name;
    @FindBy(how = How.ID, using = "age")
    private WebElement age;
    @FindBy(how = How.ID, using = "language")
    private WebElement language;
    @FindBy(how = How.ID, using = "gender")
    private WebElement gender;
    @FindBy(how = How.ID, using = "option")
    private WebElement option;
    @FindBy(how = How.ID, using = "comment")
    private WebElement comment;
    @FindBy(how = How.XPATH, using = "//button[.='Yes']")
    private WebElement yesBtn;
    @FindBy(how = How.XPATH, using = "//button[.='No']")
    private WebElement noBtn;

    private final String[] languageNames = {"English", "French", "Spanish", "Chinese"};

    public void checkName(String name){
        assertEquals(name, this.name.getText());
    }

    public void checkAge(String age){
        assertEquals(age, this.age.getText());
    }

    public void checkLanguage(boolean english, boolean french, boolean spanish, boolean chinese){
        String languages = "";
        boolean[] langFlags = {english, french, spanish, chinese};
        LinkedList<String> langStrings = new LinkedList<>(Arrays.asList(languageNames));
        for (int i = 0; i < langFlags.length; i++) {
            if (langFlags[i]){
                languages += langStrings.poll();
                for (int j = i + 1; j < langFlags.length; j++) {
                    if (langFlags[j]){
                        languages += ",";
                        break;
                    }
                }
            } else {
                langStrings.poll();
            }
        }
        checkLanguage(languages);
    }

    public void checkLanguage(String languages){
        assertEquals(languages, this.language.getText());
    }

    public void checkGender(Task2Gender gender){
        String genderString = "";
        switch (gender){
            case MALE:{
                genderString += "male";
                break;
            }
            case FEMALE:{
                genderString += "female";
                break;
            }
            case NONE:{
                genderString += "null";
                break;
            }
        }
        checkGender(genderString);
    }

    public void checkGender(String gender){
        assertEquals(gender, this.gender.getText());
    }

    public void checkOption(LikeUsSelectOptions option){
        String optionString = "";
        switch (option){
            case NOT_CHOSEN:{
                optionString = "null";
                break;
            }
            case GOOD:{
                optionString = "Good";
                break;
            }
            case OK:{
                optionString = "Ok, i guess";
                break;
            }
            case BAD:{
                optionString = "Bad";
                break;
            }
            case WHY_ME:{
                optionString = "Why me?";
                break;
            }
        }
        checkOption(optionString);
    }

    public void checkOption(String option){
        assertEquals(option, this.option.getText());
    }

    public void checkComment(String comment){
        assertEquals(comment.replace('\n', ' '), this.comment.getText());
    }

    public void checkYesBtnColors(){
        assertEquals("rgba(76, 175, 80, 1)", yesBtn.getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", yesBtn.getCssValue("color"));
    }

    public void checkNoBtnColors(){
        assertEquals("rgba(244, 67, 54, 1)", noBtn.getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", noBtn.getCssValue("color"));
    }

    public void clickYesBtn() {
        yesBtn.click();
    }

    public void clickNoBtn() {
        noBtn.click();
    }
}
