package selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class ListPage {
    @FindBy(how = How.ID, using = "addPersonBtn")
    private WebElement addPersonBtn;
    @FindBy(how = How.XPATH, using = "//ul[@id='listOfPeople']/div")
    private List<WebElement> listOfPeople;
    @FindBy(how = How.XPATH, using = "//ul[@id='listOfPeople']")
    private WebElement listOfPeopleContainer;

    private final String[] listOfClasses = {"name", "surname", "job", "dob", "language", "gender", "status"};

    public void clickAddPersonBtn(){
        addPersonBtn.click();
    }

    public void clickEditButton(int index) {
        listOfPeople.get(index).findElement(By.className("editbtn")).click();
    }

    public void clickDeleteButton(int index) {
        listOfPeople.get(index).findElement(By.className("closebtn")).click();
    }

    public ArrayList<LinkedHashMap<String, String>> getList(){
        ArrayList<LinkedHashMap<String, String>> list = new ArrayList<>();
        for (int i = 0; i < listOfPeople.size(); i++) {
            list.add(new LinkedHashMap<>());
            for (String classString: listOfClasses) {
                list.get(i).put(classString, listOfPeople.get(i).findElement(By.className(classString)).getText());
            }
        }
        return list;
    }

    public void checkNoChangeAfterAdd(ArrayList<LinkedHashMap<String, String>> previousList){
        checkNoChange(previousList, 0, listOfPeople.size() - 1);
    }

    public void checkNoChangeAfterEdit(ArrayList<LinkedHashMap<String, String>> previousList, int index) {
        checkNoChange(previousList, 0, index);
        checkNoChange(previousList, index + 1, listOfPeople.size());
    }

    public void checkNoChangeAfterDelete(ArrayList<LinkedHashMap<String, String>> previousList, int index) {
        ArrayList<LinkedHashMap<String, String>> list = new ArrayList<>();
        for (LinkedHashMap<String, String> map: previousList) {
            list.add(new LinkedHashMap<>(map));
        }
        list.remove(index);
        checkNoChange(list);
    }

    public void checkNoChange(ArrayList<LinkedHashMap<String, String>> previousList){
        checkNoChange(previousList, 0, listOfPeople.size());
    }

    public void checkNoChange(ArrayList<LinkedHashMap<String, String>> previousList, int from, int to){
        for (int i = from; i < to; i++) {
            for (String classString: listOfClasses) {
                assertEquals(previousList.get(i).get(classString),
                        listOfPeople.get(i).findElement(By.className(classString)).getText());
            }
        }
    }

    public void checkNameOfLastPerson(String name){
        checkName(name, listOfPeople.size() - 1);
    }

    public void checkName(String name, int index){
        assertEquals(name, listOfPeople.get(index).findElement(By.className("name")).getText());
    }

    public void checkSurnameOfLastPerson(String surname){
        checkSurname(surname, listOfPeople.size() - 1);
    }

    public void checkSurname(String surname, int index){
        assertEquals(surname, listOfPeople.get(index).findElement(By.className("surname")).getText());
    }

    public void checkJobOfLastPerson(String job){
        checkJob(job, listOfPeople.size() - 1);
    }

    public void checkJob(String job, int index){
        assertEquals(job, listOfPeople.get(index).findElement(By.className("job")).getText());
    }

    public void checkDateOfBirth(String date, int index) {
        assertEquals(date, listOfPeople.get(index).findElement(By.className("dob")).getText());
    }

    public void checkLanguages(String languages, int index) {
        assertEquals(languages, listOfPeople.get(index).findElement(By.className("language")).getText());
    }

    public void checkLanguages(boolean english, boolean french, boolean spanish, int index) {
        String languages = "";
        if (english){
            languages += "English" + (french || spanish ? ", " : "");
        }
        if (french){
            languages += "French" + (spanish ? ", " : "");
        }
        if (spanish){
            languages += "Spanish";
        }
        if (languages.equals("")){
            languages = "undefined";
        }
        assertEquals(languages, listOfPeople.get(index).findElement(By.className("language")).getText());
    }

    public void checkGender(Gender gender, int index){
        assertEquals((gender == Gender.MALE ? "male" : "female"),
                listOfPeople.get(index).findElement(By.className("gender")).getText());
    }

    public void checkEmployeeStatus(EmployeeStatus status, int index){
        String statusString = "";
        switch (status){
            case EMPLOYEE:{
                statusString = "employee";
                break;
            }
            case INTERN:{
                statusString = "intern";
                break;
            }
            case CONTRACTOR:{
                statusString = "contractor";
                break;
            }
        }
        assertEquals(statusString, listOfPeople.get(index).findElement(By.className("status")).getText());
    }

    public void checkTableNotDisplayed(){
        assertEquals("0px", listOfPeopleContainer.getCssValue("height"));
    }

    public void checkAddPersonBtnIsDisplayed(){
        assertTrue(addPersonBtn.isDisplayed());
    }
}
