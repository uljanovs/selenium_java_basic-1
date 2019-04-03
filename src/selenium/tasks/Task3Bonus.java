package selenium.tasks;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import selenium.enums.EmployeeStatus;
import selenium.pages.FormPage;
import selenium.enums.Task3Gender;
import selenium.pages.ListPage;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.fail;

public class Task3Bonus {
    private WebDriver driver;
//	ListPage listPage = PageFactory.initElements(driver, ListPage.class);
//     should contain what you see when you just open the page (the table with names/jobs)
    private ListPage listPage;
//	FormPage formPage = PageFactory.initElements(driver, FormPage.class);
//     should be what you see if you click "Add" or "Edit" (2 input field and a button (Add/Edit) and (Cancel)
    private FormPage formPage;

//    Bonus:
//    try storing people via an Object/separate class

    @Before
    public void openPage() {
        String libWithDriversLocation = System.getProperty("user.dir") + "\\lib\\";
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://kristinek.github.io/site/tasks/list_of_people");
        listPage = PageFactory.initElements(driver, ListPage.class);
        formPage = PageFactory.initElements(driver, FormPage.class);
    }

    @After
    public void closeBrowser() {
        driver.quit();
    }

    @Test
    public void addPerson() {
        /*
         * implement adding new person using page object
         *
         * in order: store the list of people and jobs currently on page
         * add a person via "Add person button"
         * check the list again, that non of the people where changes, but an additional one with correct name/job was added
         */
        ArrayList<LinkedHashMap<String, String>> listBeforeChange = listPage.getList();

        listPage.clickAddPersonBtn();

        formPage.enterName("name");
        formPage.enterSurname("surname");
        formPage.enterJob("job");
        try {
            formPage.enterDateOfBirth("11/01/1990");
        } catch (ParseException e) {
            fail("incorrect date format");
        }
        formPage.selectLanguages(true, false, true);
        formPage.selectGender(Task3Gender.MALE);
        formPage.selectEmployeeStatus(EmployeeStatus.CONTRACTOR);
        formPage.clickAddBtn();

        listPage.checkNoChangeAfterAdd(listBeforeChange);
        listPage.checkNameOfLastPerson("name");
        listPage.checkSurnameOfLastPerson("surname");
        listPage.checkJobOfLastPerson("job");
    }

    @Test
    public void editPerson() {
        /* implement editing a person using page object
         *
         * in order: store the list of people and jobs currently on page
         * edit one of existing persons via the edit link
         * check the list again and that 2 people stayed the same and the one used was changed
         */
        ArrayList<LinkedHashMap<String, String>> listBeforeChange = listPage.getList();

        listPage.clickEditButton(1);

        formPage.enterName("New name");
        formPage.selectLanguages(false, false, false);
        try {
            formPage.enterDateOfBirth("11/01/1990");
        } catch (ParseException e) {
            fail("incorrect date format");
        }
        formPage.selectGender(Task3Gender.MALE);
        formPage.selectEmployeeStatus(EmployeeStatus.CONTRACTOR);
        formPage.clickEditBtn();

        listPage.checkNoChangeAfterEdit(listBeforeChange, 1);
        listPage.checkName("New name", 1);
        listPage.checkSurname(listBeforeChange.get(1).get("surname"), 1);
        listPage.checkJob(listBeforeChange.get(1).get("job"), 1);
        listPage.checkDateOfBirth("11/01/1990", 1);
        listPage.checkLanguages(false, false, false, 1);
        listPage.checkGender(Task3Gender.MALE, 1);
        listPage.checkEmployeeStatus(EmployeeStatus.CONTRACTOR, 1);
    }

    @Test
    public void editPersonCancel() {
        /*
         * implement editing a person using page object
         *
         * in order: store the list of people and jobs currently on page
         * edit one of existing persons via the edit link then click "Cancel" on edit form instead of "Edit"
         * check the list again and that no changes where made
         */
        ArrayList<LinkedHashMap<String, String>> listBeforeChange = listPage.getList();

        listPage.clickEditButton(1);

        formPage.enterName("New name");
        formPage.selectLanguages(false, false, false);
        try {
            formPage.enterDateOfBirth("11/01/1990");
        } catch (ParseException e) {
            fail("incorrect date format");
        }
        formPage.selectGender(Task3Gender.MALE);
        formPage.selectEmployeeStatus(EmployeeStatus.CONTRACTOR);
        formPage.clickCancelBtn();

        listPage.checkNoChange(listBeforeChange);
    }


    @Test
    public void deletePerson() {
        /*
         * implement deleting a person using page object
         *
         * in order: store the list of people and jobs currently on page
         * delete 1 person see that there are now 2 people in the table with correct data
         */
        ArrayList<LinkedHashMap<String, String>> listBeforeChange = listPage.getList();

        listPage.clickDeleteButton(1);
        listPage.checkNoChangeAfterDelete(listBeforeChange, 1);
    }


    @Test
    public void deletePersonAll() {
        /*
         * implement deleting a person using page object
         *
         * in order: store the list of people and jobs currently on page
         * delete all people and check that there is no no table on page, but the button Add is still present and working
         */
        boolean noElements = false;
        do{
            listPage.clickDeleteButton(0);
            if (listPage.getList().size() == 0){
                noElements = true;
            }
        } while (!noElements);
        listPage.checkTableNotDisplayed();
        listPage.checkAddPersonBtnIsDisplayed();
        listPage.clickAddPersonBtn();
        assertEquals("https://kristinek.github.io/site/tasks/enter_a_new_person.html", driver.getCurrentUrl());
    }
}
