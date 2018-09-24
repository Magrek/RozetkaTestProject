import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ItemList extends BasePage {

    MainPage mainPage = BasePage.initPage(MainPage.class);
    WebDriver driver = Settings.getDriver();

    @FindBy (xpath = "//div[contains(@class, 'title clearfix')]/a")
    private List<WebElement> itemTitles;

    @FindBy (xpath = "//div[contains (@class, 'price-uah')]")
    private List<WebElement> itemPrices;

    @FindBy (xpath = "//div[contains(@class, 'box-desc')]//span[@class='btn-link-i' or contains(@class, 'submit-light')]")
    private List<WebElement> buyButtons;

    @FindBy (xpath = "//div[not(contains(@class, 'viewed'))]/div[@class='g-rating']//span/span[contains(@class, 'rating')]")
    private List<WebElement> itemRatings;

    @FindBy (xpath = "//div[@name='block_with_goods']//a[@class='pab-h3-link']")
    private List<WebElement> computersAndLaptopsCatalog;

    @FindBy (xpath = "//a[@class='pab-h4-link']")
    private List<WebElement> laptopsCatalog;

    @FindBy (xpath = "//li[@id='filter_presetbudget']")
    private WebElement checkboxLaptopsForSimpleTasks;

    @FindBy (xpath = "//div[@id='cart_payment_info']//button")
    private WebElement cartMakeOrderButton;

    @FindBy (xpath = "//div[@id='sort_view']/a")
    private WebElement sortDropdownMenu;

    @FindBy (xpath = "//div[contains(@class, 'sort-popup')]//li")
    private List<WebElement> sortButtons;

    @FindBy (xpath = "//h1[@itemprop='name']")
    private WebElement laptopsCatalogHeading;

    public String getItemTitle(int itemNumber){
        logger.info("Getting title of item with number " + itemNumber + " in item list");

        return itemTitles.get(itemNumber - 1).getText();
    }

    public String getItemTitle(){
        return getItemTitle(1);
    }

    public String[] getItemTitles(int[] itemsNumbers){
        logger.info("Getting titles of items with numbers " + Arrays.toString(itemsNumbers));

        String[] itemTitles = new String[itemsNumbers.length];
        for (int i = 0; i < itemsNumbers.length; i++){
            itemTitles[i] = getItemTitle(itemsNumbers[i]);
        }
        return itemTitles;
    }

    public int getItemPrice(int itemNumber){
        logger.info("Getting price of item with number " + itemNumber);

        String itemPrice = itemPrices.get(itemNumber - 1).getText();
        itemPrice = itemPrice.replaceAll("[^0-9]", "");

        return Integer.parseInt(itemPrice);
    }

    public int getItemPrice(){
        return getItemPrice(1);
    }

    public int[] getItemPrices(int[] itemsNumbers){
        logger.info("Getting prices of items with numbers " + Arrays.toString(itemsNumbers));

        int[] itemPrices = new int[itemsNumbers.length];
        for (int i = 0; i < itemsNumbers.length; i++){
            itemPrices[i] = getItemPrice(itemsNumbers[i]);
        }
        return itemPrices;
    }

    public void clickBuyButton(int itemNumber){
        logger.info("Clicking buy button with number " + itemNumber);

        WebElement buyButton = buyButtons.get(itemNumber - 1);
        if(!buyButton.getAttribute("class").contains("btn-link-i"))
            buyButton.click();
    }

    public void clickBuyButton(){
        clickBuyButton(1);
    }

    public void buyItem(int itemNumber){
        logger.info("Buying item with number " + itemNumber);

        clickBuyButton(itemNumber);
        waits.longWaitForElementAvailable(cartMakeOrderButton);
        mainPage.closeAdvertisement();
    }

    public void buyItem(){
        buyItem(1);
    }

    public void buyFirstItems(int count){
        logger.info("Buying first " + count + " items");

        String url = driver.getCurrentUrl();
        for (int i = 1; i <= count; i++){
            clickBuyButton(i);
            waits.longWaitForElementAvailable(String.format("(//a[@name='open_cart'])[%s]", String.valueOf(i)));
            if (i != count)
                ActionsClass.openPage(url);
        }
        waits.longWaitForElementAvailable(cartMakeOrderButton);
    }

    public void buyItemsByNumbers(int[] itemsNumbers){
        logger.info("Buying items with numbers " + Arrays.toString(itemsNumbers));

        int amount = itemsNumbers.length;
        String url = driver.getCurrentUrl();
        for (int i = 0; i < amount; i++){
            clickBuyButton(itemsNumbers[i]);
            waits.longWaitForElementAvailable(String.format("(//a[@name='open_cart'])[%s]", String.valueOf(i + 1)));
            if (i != amount - 1)
                ActionsClass.openPage(url);
        }
        waits.longWaitForElementAvailable(cartMakeOrderButton);
    }

    public int getItemRatingInPercents(int itemNumber){
        if(doesItemHaveRating(itemNumber)) {
            String itemRating = itemRatings.get(itemNumber - 1).getAttribute("style");

            itemRating = itemRating.replaceAll("[^0-9]", "");
            int itemRatingInPercents = Integer.parseInt(itemRating);

            if (itemRatingInPercents > 100) {
                return 100;
            } else {
                return itemRatingInPercents;
            }
        }
        return 0;
    }

    public int getItemRatingInPercents(){
        return getItemRatingInPercents(1);
    }

    public double getItemRatingInStars(int itemNumber){
        int itemInPercents = getItemRatingInPercents(itemNumber);
        double itemInStars = itemInPercents / 20.0;

        return itemInStars;
    }

    public double getItemRatingInStars(){
        return getItemRatingInStars(1);
    }

    public void goToLaptopsCatalog(){
        logger.info("Clicking the 'Laptops' link");

        computersAndLaptopsCatalog.get(0).click();
        waits.longWaitForElementAvailable(laptopsCatalogHeading);
        mainPage.closeAdvertisement();
    }

    public void goToLaptopsForSimpleTasksList(){
        logger.info("Choosing the 'Laptops for simple tasks' type");

        laptopsCatalog.get(0).click();
        waits.longWaitForElementAvailable(checkboxLaptopsForSimpleTasks);
        mainPage.closeAdvertisement();
    }

    public void clickSortDropdownMenu(){
        sortDropdownMenu.click();
    }

    public ArrayList<Integer> getIndexOfItemsWithChosenRating(double rating) {
        ArrayList<Integer> indexes = new ArrayList();
        int itemsNumber = itemRatings.size();
        double itemRating;

        for (int i = 0; i < itemsNumber; i++) {
            itemRating = getItemRatingInStars(i + 1);
            if (itemRating == rating && !buyButtons.get(i).getAttribute("class").contains("btn-link-i")) {
                indexes.add(i);
            }
        }
        return indexes;
    }

    public int getItemsWithRatingAmount(double rating){
        logger.info("Getting amount of items with " + rating + " rating");

        return getIndexOfItemsWithChosenRating(rating).size();
    }

    public void buyItemsWithRating(double rating){
        logger.info("Buying items with " + rating + " rating");

        String currentUrl = driver.getCurrentUrl();
        ArrayList<Integer> indexes = getIndexOfItemsWithChosenRating(rating);

        for(int i = 0; i < indexes.size(); i++){
            clickBuyButton(indexes.get(i) + 1);
            waits.shortWaitForElementAvailable(String.format("(//div[@name='buy_catalog']//a[@name='open_cart'])[%s]", String.valueOf(i + 1)));
            if(i != indexes.size() - 1) {
                ActionsClass.openPage(currentUrl);
            }
        }
        waits.longWaitForElementAvailable(cartMakeOrderButton);
    }

    public void sortFromExpensiveToCheap(){
        logger.info("Sorting items from expensive to cheap");

        clickSortDropdownMenu();
        if (!isSortButtonActive(sortButtons.get(0)))
            sortButtons.get(0).click();
        clickSortDropdownMenu();
    }

    public void sortFromCheapToExpensive(){
        logger.info("Sorting items from cheap to expensive");

        clickSortDropdownMenu();
        if (!isSortButtonActive(sortButtons.get(1)))
            sortButtons.get(1).click();
        clickSortDropdownMenu();
    }

    public void sortByPopularity(){
        logger.info("Sorting items by popularity");

        clickSortDropdownMenu();
        if (!isSortButtonActive(sortButtons.get(2)))
            sortButtons.get(2).click();
        clickSortDropdownMenu();
    }

    public void sortByNovelty(){
        logger.info("Sorting items by novelty");

        clickSortDropdownMenu();
        if (!isSortButtonActive(sortButtons.get(3)))
            sortButtons.get(3).click();
        clickSortDropdownMenu();
    }

    public void sortByStocks(){
        logger.info("Sorting items by stocks");

        clickSortDropdownMenu();
        if (!isSortButtonActive(sortButtons.get(4)))
            sortButtons.get(4).click();
        clickSortDropdownMenu();
    }

    public void sortByRank(){
        logger.info("Sort items by ratings");

        clickSortDropdownMenu();
        if (!isSortButtonActive(sortButtons.get(5)))
            sortButtons.get(5).click();
        clickSortDropdownMenu();
    }

    public boolean isSortButtonActive(WebElement webElement){
        return webElement.getAttribute("class").contains("pos-fix");
    }

    public boolean doesItemHaveRating(int itemNumber){
        return !(itemRatings.get(itemNumber - 1).getAttribute("class").contains("none"));
    }
}
