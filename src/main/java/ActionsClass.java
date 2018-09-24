import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;

public class ActionsClass {

    static MainPage mainPage = BasePage.initPage(MainPage.class);
    static Cart cart = BasePage.initPage(Cart.class);
    static ItemList itemList = BasePage.initPage(ItemList.class);
    static WebDriver driver = Settings.getDriver();
    static Waits waits = new Waits();
    static Logger logger = Settings.getLogger();

    public static String getRandomMail(){
        int difference = (int)(Math.random() * 100000) + 20000;
        return "TestMail" + difference + "@gmail.com";
    }

    public static void switchToLastWindow(){
        logger.info("Switching to the last window");

        Set<String> handles = driver.getWindowHandles();
        Iterator<String> itr = handles.iterator();
        String parentWindow = itr.next();
        String newWindow = itr.next();

        driver.switchTo().window(newWindow);
    }

    public static void closeLastWindow(){
        logger.info("Closing the last window");

        Set<String> handles = driver.getWindowHandles();
        Iterator<String> itr = handles.iterator();
        String parentWindow = itr.next();
        String newWindow = itr.next();

        driver.switchTo().window(newWindow).close();
        driver.switchTo().window(parentWindow);
    }

    public static void acceptAlert(){
        logger.info("Accepting the alert");

        driver.switchTo().alert().accept();
    }

    public static void dismissAlert(){
        logger.info("Dismissing the alert");

        driver.switchTo().alert().dismiss();
    }

    public static void openPage(String URL){
        logger.info("Opening the page with " + URL + " URL");

        driver.get(URL);
        mainPage.closeAdvertisement();
    }

    public static void openStartPage(){

        if(!driver.getCurrentUrl().equals("https://rozetka.com.ua/")){
            openPage("https://rozetka.com.ua/");
            waits.longWaitForElementAvailable("//div[@class='logo']");
        }
    }

    public static void openRegistrationPage(){
        logger.info("Opening registration page");

        openStartPage();

        mainPage.clickEnterThePersonalAreaLink();
        mainPage.clickSignupButton();
        mainPage.closeAdvertisement();
    }

    public static void openLaptopsList(){
        logger.info("Opening page with laptops list");

        openStartPage();

        mainPage.clickLaptopsAndComputersLink();
        itemList.goToLaptopsCatalog();
        itemList.goToLaptopsForSimpleTasksList();
    }

    public static void sendKeys(WebElement webElement, String keys){
        webElement.clear();
        webElement.sendKeys(keys);
    }

    public static void openCart(){
        logger.info("Opening cart window");

        if (!cart.isCartWindowShown()) {
            mainPage.clickCartButton();
            waits.longWaitForElementAvailable("//*[contains(@class, 'cart-title')]");
        }
    }

    public static void cleanCart(){
        logger.info("Cleaning cart");

        if(cart.isCartWithItems()) {
            openCart();
            cart.deleteAllItems();
            cart.closeCart();
        }
    }

    public static boolean compareTwoArrays(int[] arr1, int[] arr2){
        logger.info("Comparing two int arrays");

        if (arr1.length == arr1.length){
            Arrays.sort(arr1);
            Arrays.sort(arr2);
            return Arrays.equals(arr1, arr2);
        }
        return false;
    }

    public static boolean compareTwoArrays(String[] arr1, String[] arr2){
        logger.info("Comparing two string arrays");

        if (arr1.length == arr1.length){
            Arrays.sort(arr1);
            Arrays.sort(arr2);
            return Arrays.equals(arr1, arr2);
        }
        return false;
    }
}
