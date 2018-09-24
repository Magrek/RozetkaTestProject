import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;

public class BaseTestClass {

    static String url;

    WebDriver driver;
    SoftAssert softAssert;
    Logger logger;

    Cart cart;
    ItemList itemList;
    MainPage mainPage;
    OrderingPage orderingPage;
    PersonalDataPage personalDataPage;
    RegistrationPage registrationPage;

    protected void setUpBeforeSuite(){
        logger = Settings.getLogger();
        driver = Settings.getDriver();
        driver.manage().window().maximize();

        logger.info("BeforeSuite method is started");

        cart = BasePage.initPage(Cart.class);
        itemList = BasePage.initPage(ItemList.class);
        mainPage = BasePage.initPage(MainPage.class);
        orderingPage = BasePage.initPage(OrderingPage.class);
        personalDataPage = BasePage.initPage(PersonalDataPage.class);
        registrationPage = BasePage.initPage(RegistrationPage.class);

        softAssert = new SoftAssert();
    }

    protected void setUpBeforeClass(){
        logger.info("BeforeClass method is started");
        ActionsClass.openStartPage();
    }

    protected void tearDownAfterMethod(){
        logger.info("BeforeClass method is started");
    }

    protected void tearDownAfterClass(){
        logger.info("AfterClass method is started");
        ActionsClass.openStartPage();
    }

    protected void tearDownAfterSuite(){
        logger.info("AfterSuite method is started");
        driver.quit();
    }
}
