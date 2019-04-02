package selenium.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class FormPage {

    @FindBy(how = How.ID, using = "name")
    private WebElement nameInput;
    @FindBy(how = How.ID, using = "surname")
    private WebElement surnameInput;
    @FindBy(how = How.ID, using = "job")
    private WebElement jobInput;
    @FindBy(how = How.ID, using = "dob")
    private WebElement dateOfBirthBox;
    @FindBy(how = How.XPATH, using = "//input[@type='checkbox' and @name='language']")
    private List<WebElement> languageCheckboxes;
    @FindBy(how = How.XPATH, using = "//input[@type='radio' and @name='gender']")
    private List<WebElement> genderRadio;
    @FindBy(how = How.ID, using = "status")
    private WebElement statusSelect;
    @FindBy(how = How.XPATH, using = "//button[@id='modal_button' and .='Add']")
    private WebElement addButton;
    @FindBy(how = How.XPATH, using = "//button[@id='modal_button' and .='Edit']")
    private WebElement editButton;
    @FindBy(how = How.XPATH, using = "//button[@id='modal_button' and .='Cancel']")
    private WebElement cancelButton;

    public void enterName(String name) {
        nameInput.clear();
        nameInput.sendKeys(name);
    }

    public void enterSurname(String surname) {
        surnameInput.clear();
        surnameInput.sendKeys(surname);
    }

    public void enterJob(String job) {
        jobInput.clear();
        jobInput.sendKeys(job);
    }

    public void enterDateOfBirth(String date) {
        //TODO check date
        dateOfBirthBox.clear();
        dateOfBirthBox.sendKeys(date);
    }

    public void selectLanguages(boolean english, boolean french, boolean spanish) {
        for (WebElement checkbox: languageCheckboxes) {
            if (checkbox.isSelected()){
                checkbox.click();
            }
        }
        if(english){
            languageCheckboxes.get(0).click();
        }
        if(french){
            languageCheckboxes.get(1).click();
        }
        if(spanish){
            languageCheckboxes.get(2).click();
        }
    }

    public void selectGender(Gender gender) {
        if (gender == Gender.MALE){
            genderRadio.get(0).click();
        } else {
            genderRadio.get(1).click();
        }
    }

    public void selectEmployeeStatus(EmployeeStatus status) {
        Select select = new Select(statusSelect);
        switch (status){
            case EMPLOYEE:{
                select.selectByVisibleText("Employee");
                break;
            }
            case INTERN:{
                select.selectByVisibleText("Intern");
                break;
            }
            case CONTRACTOR:{
                select.selectByVisibleText("Contractor");
                break;
            }
        }
    }

    public void clickAddBtn() {
        addButton.click();
    }

    public void clickEditBtn() {
        editButton.click();
    }

    public void clickCancelBtn() {
        cancelButton.click();
    }
}
