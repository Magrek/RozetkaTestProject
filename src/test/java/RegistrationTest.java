import org.testng.Assert;
import org.testng.annotations.*;

public class RegistrationTest extends BaseTestClass{

    @BeforeSuite (alwaysRun = true)
    public void setUpBeforeSuite(){
        super.setUpBeforeSuite();
    }

    @BeforeClass (alwaysRun = true)
    public void setUpBeforeClass(){
        super.setUpBeforeClass();
        ActionsClass.openRegistrationPage();
        url = driver.getCurrentUrl();
    }

    @Test (groups = {"pageLoad"})
    public void headingText(){
        logger.info("headingText method is started");
        Assert.assertTrue(registrationPage.getHeadingText().equals("Регистрация"),
                "Registration page is nod displayed");
    }

    @Test (groups = {"invalidDataTests"})
    public void registrationEmptyFields(){
        logger.info("registrationEmptyFields method is started");

        registrationPage.registrationWithEmptyFields();
        Assert.assertTrue(registrationPage.isAllFieldsError(), "Field aren't red");
    }

    @Test (groups = {"invalidDataTests"})
    public void registrationInvalidName(){
        logger.info("registrationInvalidName method is started");
        registrationPage.registrationWithEmptyNameField();
        Assert.assertTrue(registrationPage.isNameFieldError(), "The 'Имя' field isn't red");
    }

    @Test (groups = {"invalidDataTests"})
    public void registrationInvalidEmailOrPhone(){
        logger.info("registrationInvalidEmailOrPhone is started");
        registrationPage.registrationWithEmptyEmailOrPhoneField();
        Assert.assertTrue(registrationPage.isEmailOrPhoneFieldError(), "The 'Эл. почта или телефон' field isn't red");
    }

    @Test (groups = {"invalidDataTests"})
    public void registrationInvalidPassword(){
        logger.info("registrationInvalidPassword");
        registrationPage.registrationWithEmptyPasswordField();
        Assert.assertTrue(registrationPage.isPasswordFieldError(), "The 'Придумайте пароль' field isn't red");
    }

    @Test(groups = {"clickElementsTests"})
    public void clickGoogleButton(){
        logger.info("clickGoogleButton method is started");
        registrationPage.clickGoogleButton();
        String heading = registrationPage.getGoogleHeadingText();
        ActionsClass.closeLastWindow();
        Assert.assertEquals(heading, "Вход", "Google website isn't shown");
    }

    @Test(groups = {"clickElementsTests"})
    public void clickFacebookButton(){
        logger.info("clickFacebookButton method is started");
        registrationPage.clickFacebookButton();
        String heading = registrationPage.getFacebookHeadingText();
        ActionsClass.closeLastWindow();
        Assert.assertEquals(heading, "Facebook", "Facebook website isn't shown");
    }

    @Test(groups = {"clickElementsTests"})
    public void termsOfUseLink(){
        logger.info("termsOfUseLink method is started");
        registrationPage.clickTermsOfUseLink();
        String heading = registrationPage.getTermsOfUseHeadingText();
        registrationPage.closeTermsOfUsePopup();
        Assert.assertTrue(heading.equals("Пользовательское соглашение") || heading.equals("Угода користувача"),
                "Terms of use window isn't shown");
    }

    @Test (groups = {"validDataTests"}, dependsOnGroups = {"pageLoad", "invalidDataTests", "clickElementsTests"})
    public void registrationValidData(){
        logger.info("registrationValidData");
        registrationPage.registrationWithValidData();
        Assert.assertTrue(personalDataPage.getHeadingText().equals("Личные данные"), "Registration was unsuccessful");
    }

    @AfterMethod (alwaysRun = true)
    public void tearDownAfterMethod(){
        ActionsClass.openPage(url);
    }

    @AfterClass (alwaysRun = true)
    public void tearDownAfterClass(){
        mainPage.logOut();
    }

    @AfterSuite (alwaysRun = true)
    public void tearDownAfterSuite(){
        super.tearDownAfterSuite();
    }
}
