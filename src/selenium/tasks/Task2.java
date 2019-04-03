package selenium.tasks;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import selenium.pages.MessageCheck;
import selenium.pages.Task2Page_SubmitFeedback;
import selenium.pages.Task2Page_yesno;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class Task2 {
    WebDriver driver;
    static Task2Page_SubmitFeedback subFB;
    static Task2Page_yesno task2Page_yesno;
    static MessageCheck msgCheck;

    @Before
    public void openPage() {
        String libWithDriversLocation = System.getProperty("user.dir") + "\\lib\\";
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://kristinek.github.io/site/tasks/provide_feedback");
        subFB = PageFactory.initElements(driver, Task2Page_SubmitFeedback.class);
        task2Page_yesno = PageFactory.initElements(driver, Task2Page_yesno.class);
        msgCheck = PageFactory.initElements(driver, MessageCheck.class);

    }

    @After
    public void closeBrowser() {
        driver.quit();
    }

    @Test
    public void initialFeedbackPage() throws Exception {
//         TODO:
//         check that all field are empty and no tick are clicked
//         "Don't know" is selected in "Genre"
//         "Choose your option" in "How do you like us?"
//         check that the button send is blue with white letters


        //
        //                  Sorry, I did these already before learning about
        //                "pages" and that we could have saved the time by them.
        //                 "Pages" are quite complex, especially when we have to check data we entered in another page
        //                I tried many ways to do it, but unfortunately I failed.
        //              It would be great if these things would be taught a bit more
        //
        //              Tried to do Task3Bonus, but after learning that the web page that we had to work with changes, I stopped.
        //
        //              And I put Thread.sleep() because I want to see the actual visual results



        WebElement inputName = driver.findElement(By.id("fb_name"));
        String text = inputName.getAttribute("value"); if(text.isEmpty()) {
            System.out.println("input name box is empty"); }
        WebElement inputAge = driver.findElement(By.id("fb_age"));
        String text1 = inputAge.getAttribute("value"); if(text1.isEmpty()) {
            System.out.println("input age box is empty"); }


        WebElement englishCheck = driver.findElement(By.xpath("//div[@id='lang_check']//input[1]"));
        assertFalse(englishCheck.isSelected());
        WebElement frenchCheck = driver.findElement(By.xpath("//div[@id='lang_check']//input[2]"));
        assertFalse(frenchCheck.isSelected());
        WebElement spanishCheck = driver.findElement(By.xpath("//div[@id='lang_check']//input[3]"));
        assertFalse(spanishCheck.isSelected());
        WebElement chineseCheck = driver.findElement(By.xpath("//div[@id='lang_check']//input[4]"));
        assertFalse(chineseCheck.isSelected());

        WebElement radioMale = driver.findElement(By.xpath("//input[@type='radio'][1]"));
        assertFalse(radioMale.isSelected());
        WebElement radioFemale = driver.findElement(By.xpath("//input[@type='radio'][2]"));
        assertFalse(radioFemale.isSelected());
        WebElement radioNoGender = driver.findElement(By.xpath("//input[@type='radio'][3]"));
        assertTrue(radioNoGender.isSelected());

        Select dropdown = new Select(driver.findElement(By.id("like_us")));
        assertEquals("Choose your option", dropdown.getFirstSelectedOption().getText());
        dropdown.selectByVisibleText("Good");

        WebElement inputComment = driver.findElement(By.name("comment"));
        String text2 = inputComment.getAttribute("value"); if(text2.isEmpty()) {
            System.out.println("input age box is empty"); }

        assertEquals("rgba(33, 150, 243, 1)",
                driver.findElement(By.cssSelector(".w3-blue")).getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)",
                driver.findElement(By.cssSelector(".w3-blue")).getCssValue("color"));

        Thread.sleep(3000);

    }

    @Test
    public void emptyFeedbackPage() throws Exception {
//         TODO:
//         click "Send" without entering any data
//         check fields are empty or null
//         check button colors
//         (green with white letter and red with white letters)

        driver.findElement(By.cssSelector(".w3-btn-block")).click();

        assertEquals("rgba(76, 175, 80, 1)",
                driver.findElement(By.cssSelector(".w3-green")).getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)",
                driver.findElement(By.cssSelector(".w3-green")).getCssValue("color"));

        assertEquals("rgba(244, 67, 54, 1)",
                driver.findElement(By.cssSelector(".w3-red")).getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)",
                driver.findElement(By.cssSelector(".w3-red")).getCssValue("color"));

        Thread.sleep(2000);


    }

    @Test
    public void notEmptyFeedbackPage() throws Exception {
//         TODO:
//         fill the whole form, click "Send"
//         check fields are filled correctly
//         check button colors
//         (green with white letter and red with white letters)

        WebElement textName = driver.findElement(By.name("name"));
        textName.sendKeys("Jānis");
        WebElement textAge = driver.findElement(By.name("age"));
        textAge.sendKeys("24");

        WebElement englishCheck = driver.findElement(By.xpath("//div[@id='lang_check']//input[1]"));
        englishCheck.click();
        WebElement chineseCheck = driver.findElement(By.xpath("//div[@id='lang_check']//input[4]"));
        chineseCheck.click();

        WebElement radioMale = driver.findElement(By.xpath("//input[@type='radio'][1]"));
        radioMale.click();


        Select dropdown = new Select(driver.findElement(By.id("like_us")));
        dropdown.selectByVisibleText("Good");

        WebElement textCom = driver.findElement(By.name("comment"));
        textCom.sendKeys("I hope to get an internship and work at Accenture after this bootcamp! :) ");

        driver.findElement(By.cssSelector(".w3-btn-block")).click();



        Thread.sleep(5000);


    }

    @Test
    public void notEmptyFeedbackPage2() throws Exception {
//         TODO:
//         fill the whole form, click "Send"
//         check fields are filled correctly
//         check button colors
//         (green with white letter and red with white letters)



        //  Tried to do this task in a different way(with pages) as well.
        //  But I don't know how to check the data I have provided in previous page
        //

        subFB.theWholeForm("Jānis","24", "Hello");
        task2Page_yesno.checkColorOfButtons();

        Thread.sleep(2000);





    }

        @Test
    public void yesOnWithNameFeedbackPage() throws Exception {
//         TODO:
//         enter only name
//         click "Send"
//         click "Yes"
//         check message text: "Thank you, NAME, for your feedback!"
//         color of text is white with green on the background

        subFB.enterNameOnly("Jānis");
        task2Page_yesno.clickYes();
        Thread.sleep(2000);
        msgCheck.checkSuccessfulMessage();


        Thread.sleep(2000);
    }

    @Test
    public void yesOnWithoutNameFeedbackPage() throws Exception {
//         TODO:
//         click "Send" (without entering anything
//         click "Yes"
//         check message text: "Thank you for your feedback!"
//         color of text is white with green on the background

        subFB.enterWithoutWritingAnything();
        task2Page_yesno.clickYes();
        msgCheck.checkMessage("Thank you for your feedback!");
        msgCheck.checkColor();

        Thread.sleep(2000);




    }

    @Test
    public void noOnFeedbackPage() throws Exception {
//         TODO:
//         fill the whole form
//         click "Send"
//         click "No"
//         check fields are filled correctly                               ????/kā var pārbaudīt informāciju, kura tika ievadīta citā lapā?
        subFB.theWholeForm("Jānis","24", "Hello");
        task2Page_yesno.clickNo();

        Thread.sleep(3000);



    }
}
