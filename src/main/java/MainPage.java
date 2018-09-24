import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class MainPage extends BasePage{

    Actions actions = new Actions(Settings.getDriver());

    @FindBy (xpath = "//div[@class='logo']")
    private WebElement logo;

    @FindBy (xpath = "//input[contains(@class, 'rz-header')]")
    private WebElement searchField;

    @FindBy (xpath = "//a[@name='signin']")
    private WebElement enterThePersonalAreaLink;

    @FindBy (xpath = "//div[@id='comparison']")
    private WebElement comparisonButton;

    @FindBy (xpath = "//div[@id='wishlist']")
    private WebElement wishlistButton;

    @FindBy (xpath = "//li[@id='cart_popup_header']")
    private WebElement cartButton;

    @FindBy (xpath = "//ul[@name='header-top-menu']//a[contains(text(), 'Запитання') or contains(text(), 'Вопросы')]")
    private WebElement asksAndAnswersLink;

    @FindBy (xpath = "//ul[@name='header-top-menu']//a[contains(text(), 'Кредит')]")
    private WebElement creditLink;

    @FindBy (xpath = "//ul[@name='header-top-menu']//a[contains(text(), 'Доставка')]")
    private WebElement shippingAndPaymentLink;

    @FindBy (xpath = "//ul[@name='header-top-menu']//a[contains(text(), 'Гарант')]")
    private WebElement guaranteeLink;

    @FindBy (xpath = "//ul[@name='header-top-menu']//a[contains(text(), 'Контакт')]")
    private WebElement contactsLink;

    @FindBy (xpath = "//a[@id='status_orders']")
    private WebElement trackTheOrderLink;

    @FindBy (xpath = "//ul[@name='header-top-menu']//a[contains(text(), 'Прода')]")
    private WebElement sellOnRozetkaLink;

    @FindBy (xpath = "//input[@name='section_id']/following-sibling::span//button")
    private WebElement findButton;

    @FindBy (xpath = "//div[@id='language-switcher']//a[text()='ua']")
    private WebElement uaLangButton;

    @FindBy (xpath = "//div[@id='language-switcher']//a[text()='ru']")
    private WebElement ruLangButton;

    @FindBy (xpath = "//*[@name='login']")
    private WebElement emailOrPhoneField;

    @FindBy (xpath = "//div[@name='simple_auth']//*[@name='password']")
    private WebElement passwordField;

    @FindBy (xpath = "//button[@name='auth_submit']")
    private WebElement loginButton;

    @FindBy (xpath = "//div[contains(@class, 'signup')]/a")
    private WebElement signupButton;

    @FindBy (xpath = "//a[contains(text(), 'Ноутбуки')]")
    private WebElement laptopsAndComputersLink;

    @FindBy (xpath = "//span[@class='exponea-close']")
    private WebElement advertisementCloseButton;

    @FindBy (xpath = "//div[@id='cart_payment_info']//button")
    private WebElement cartMakeOrderButton;

    @FindBy (xpath = "//a[@name='profile']")
    private WebElement userLink;

    @FindBy (xpath = "//div[contains(@class, 'popup-auth')]")
    private WebElement authorizationPopup;

    @FindBy (xpath = "//a[@name='signout']")
    private WebElement signOutButton;

    public void closeAdvertisement(){
        waits.shortWaitForElementAvailable(advertisementCloseButton);
        try {
            advertisementCloseButton.click();
        } catch (WebDriverException e) {
        } catch (NullPointerException e) {
        }
    }

    public void clickFindButton(){
        findButton.click();
    }

    public void switchLangToRussian(){
        logger.info("Switching language to Russian");

        try {
            ruLangButton.click();
        } catch (NullPointerException e) {
            logger.info("The Russian language is already chosen");
        } catch (WebDriverException e) {
            logger.info("The Russian language is already chosen");
        }
        waits.longWaitForElementAvailable(uaLangButton);
    }

    public void switchLangToUkrainian(){
        logger.info("Switching language to Ukrainian");

        try {
            uaLangButton.click();
        } catch (NullPointerException e) {
            logger.info("The Ukrainian language is already chosen");
        } catch (WebDriverException e) {
            logger.info("The Ukrainian language is already chosen");
        }
        waits.longWaitForElementAvailable(ruLangButton);
    }

    public void clickEnterThePersonalAreaLink(){
        logger.info("Opening window for authorization");

        enterThePersonalAreaLink.click();
        waits.longWaitForElementAvailable(authorizationPopup);
    }

    public void typeEmailOrPhone(String emailOrPhone){
        ActionsClass.sendKeys(emailOrPhoneField, emailOrPhone);
    }

    public void typePassword(String password){
        ActionsClass.sendKeys(passwordField, password);
    }

    public void clickLoginButton(){
        loginButton.click();
    }

    public void authorizate(String emailOrPhone, String password){
        logger.info("Authorization");

        if(!enterThePersonalAreaLink.isDisplayed())
            clickEnterThePersonalAreaLink();
        typeEmailOrPhone(emailOrPhone);
        typePassword(password);
        clickLoginButton();
        waits.longWaitForElementAvailable(userLink);
    }

    public String getUserName(){
        return userLink.getText();
    }

    public void fillSearchField(String text){
        ActionsClass.sendKeys(searchField, text);
    }

    public void clickCartButton(){
        cartButton.click();
        waits.longWaitForElementAvailable(cartMakeOrderButton);
    }

    public void clickSignupButton(){
        logger.info("Clicking signUp button");

        signupButton.click();
        waits.longWaitForElementAvailable("//div[@name='signup']");
    }

    public void clickLaptopsAndComputersLink(){
        logger.info("Clicking the 'Laptops and computers' link");

        laptopsAndComputersLink.click();
        waits.longWaitForElementAvailable("//h1[@class='pab-h1']");
        closeAdvertisement();
    }

    public void logOut(){
        logger.info("Logging out");

        actions.moveToElement(userLink).build().perform();
        waits.longWaitForElementAvailable(signOutButton);
        signOutButton.click();
        waits.longWaitForElementAvailable(enterThePersonalAreaLink);
    }


}
