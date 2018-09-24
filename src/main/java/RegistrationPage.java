import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegistrationPage extends BasePage{

    @FindBy (xpath = "//*[@class='signup-title']")
    private WebElement pageHeading;

    @FindBy (xpath = "//input[@name='title']")
    private WebElement nameField;

    @FindBy (xpath = "//input[@name='login']")
    private WebElement emailOrPhoneField;

    @FindBy (xpath = "//input[@name='password']")
    private WebElement passwordField;

    @FindBy (xpath = "//div[@class='signup-submit']//button")
    private WebElement registrationButton;

    @FindBy (xpath = "//a[@id='user_agreement_link']")
    private WebElement termsOfUseLink;

    @FindBy (xpath = "//div[@type='facebook']//a")
    private WebElement facebookButton;

    @FindBy (xpath = "//*[@id='homelink']")
    private WebElement facebookHeading;

    @FindBy (xpath = "//div[@type='google']//a")
    private WebElement googleButton;

    @FindBy (xpath = "//*[@id='headingText']")
    private WebElement googleHeading;

    @FindBy (xpath = "//div[@class='popup-agreement-content']/h2")
    private WebElement termsOfUseHeading;

    @FindBy (xpath = "//a[@class='popup-close']")
    private WebElement termsOfUseCloseButton;

    public String getHeadingText(){
        logger.info("Getting heading test at registration page");

        return pageHeading.getText();
    }

    public void typeName(String name){
        ActionsClass.sendKeys(nameField, name);
    }

    public void typeEmailOrPhone(String emailOrPhone){
        ActionsClass.sendKeys(emailOrPhoneField, emailOrPhone);
    }

    public void typePassword(String password){
        ActionsClass.sendKeys(passwordField, password);
    }

    public void clickRegistrationButton(){
        registrationButton.click();
    }

    public void clickTermsOfUseLink(){
        logger.info("Clicking the 'Terms of use' link");

        waits.longWaitForElementAvailable(termsOfUseLink);
        termsOfUseLink.click();
        waits.longWaitForElementAvailable("//div[@class='popup-agreement-content']");
    }

    public void closeTermsOfUsePopup(){
        logger.info("Closing the 'Terms of use' popup");

        termsOfUseCloseButton.click();
        waits.longWaitForInvisibilityOfElement(termsOfUseHeading);
    }

    public String getTermsOfUseHeadingText(){
        logger.info("Getting 'Terms of use' heading text");

        return termsOfUseHeading.getText();
    }

    public void clickFacebookButton(){
        logger.info("Clicking the 'Facebook' button");

        facebookButton.click();
        waits.longWaitForElementAvailable(facebookHeading);
        ActionsClass.switchToLastWindow();
        waits.longWaitForElementAvailable(facebookHeading);
    }

    public String getFacebookHeadingText(){
        logger.info("Getting heading text at facebook page");

        return facebookHeading.getText();
    }

    public void clickGoogleButton(){
        logger.info("Clicking the 'Google' button");

        googleButton.click();
        waits.longWaitForElementAvailable(googleHeading);
        ActionsClass.switchToLastWindow();
        waits.longWaitForElementAvailable(googleHeading);
    }

    public String getGoogleHeadingText(){
        logger.info("Getting heading text at google page");

        return googleHeading.getText();
    }

    public void register(String name, String emailOrPhone, String password){
        logger.info("Registration with '" + name + "' name, '" + emailOrPhone + "' emailOrPhone, '" + password + "' password");

        typeName(name);
        typeEmailOrPhone(emailOrPhone);
        typePassword(password);
        clickRegistrationButton();
    }

    public boolean isNameFieldError() {
        logger.info("Check if name field is red");

        waits.longWaitForElementAttribute(nameField, "class", "invalid");
        return nameField.getAttribute("class").contains("invalid");
    }

    public boolean isEmailOrPhoneFieldError() {
        logger.info("Check if emailOrPhone field is red");

        waits.longWaitForElementAttribute(emailOrPhoneField, "class", "invalid");
        return emailOrPhoneField.getAttribute("class").contains("invalid");
    }

    public boolean isPasswordFieldError() {
        logger.info("Check if password field is red");

        waits.longWaitForElementAttribute(passwordField, "class", "invalid");
        return passwordField.getAttribute("class").contains("invalid");
    }

    public boolean isAllFieldsError(){
        logger.info("Checking if all registration fields are red");

        return isNameFieldError() && isEmailOrPhoneFieldError() && isPasswordFieldError();
    }

    public void registrationWithEmptyNameField(){
        logger.info("Registration with empty name field");

        register("", ActionsClass.getRandomMail(), "TestPassword289");
    }

    public void registrationWithEmptyEmailOrPhoneField(){
        logger.info("Registration with empty emailOrPhone field");

        register("Test", "", "TestPassword289");
    }

    public void registrationWithEmptyPasswordField(){
        logger.info("Registration with empty password field");

        register("Test", ActionsClass.getRandomMail(), "");
    }

    public void registrationWithValidData(){
        logger.info("Registration with valid data");

        register("TestName", ActionsClass.getRandomMail(), "TestPassword289");
        waits.longWaitForElementAvailable("//div[@id='personal_information']");
    }

    public void registrationWithEmptyFields(){
        logger.info("Registration with empty fields");

        register("", "", "");
    }
}
