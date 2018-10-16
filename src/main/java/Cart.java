import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import java.util.List;

public class Cart extends BasePage{

    @FindBy (xpath = "//*[contains(@class, 'cart-title')]")
    private WebElement headingText;

    @FindBy (xpath = "//a[@name='close']")
    private WebElement closeCartButton;

    @FindBy (xpath = "//*[@name='cost']")
    private WebElement totalPrice;

    @FindBy (xpath = "//*[@class='cart-return-link']//a")
    private WebElement continueShoppingButton;

    @FindBy (xpath = "//*[@class='hub-i-count']")
    private WebElement itemsInCartAmount;

    @FindBy (xpath = "//div[contains(@class, 'title')]//a[@name='goods-link']")
    private List<WebElement> itemTitles;

    @FindBy (xpath = "//span[@class='cart-uah']")
    private List<WebElement> itemPrices;

    @FindBy (xpath = "//span[@name='sum']")
    private List<WebElement> sumPrices;

    @FindBy (xpath = "//input[@name='quantity']")
    private List<WebElement> itemAmounts;

    @FindBy (xpath = "//a[@name='plus']")
    private List<WebElement> increaseButtons;

    @FindBy (xpath = "//a[@name='minus']")
    private List<WebElement> decreaseButtons;

    @FindBy (xpath = "//input[@name='quantity']")
    private List<WebElement> amountFields;

    @FindBy (xpath = "//div[@name='activate']")
    private List<WebElement> deleteButtons;

    @FindBy (xpath = "//a[@name='delete']")
    private List<WebElement> deletePopups;

    @FindBy (xpath = "//div[@class='cart-info']")
    private List<WebElement> itemFields;

    @FindBy (xpath = "//*[@id='popup-checkout']")
    private WebElement makeOrderButton;

    @FindBy (xpath = "//*[contains(@class, 'empty-cart-title')]")
    private WebElement emptyCartTitle;

    @FindBy (xpath = "//img[@alt='Отправка данных']")
    private WebElement sendingDataGif;

    @FindBy (xpath = "//div[@id='cart-popup']")
    private WebElement cartPopup;

    @FindBy (xpath = "//div[@class='cart-info']")
    private List<WebElement> purchasedItems;

    public String getHeadingText(){
        logger.info("Getting cart heading text");

        return headingText.getText();
    }

    public String getItemTitle(int itemNumber){
        logger.info("Getting title of item with number " + itemNumber + " in cart");

        return itemTitles.get(itemNumber - 1).getText();
    }

    public String getItemTitle(){
        return getItemTitle(1);
    }

    public String[] getAllItemsTitles(){
        logger.info("Getting titles of all items in cart");

        String[] titles = new String[itemTitles.size()];
        for (int i = 0; i < itemTitles.size(); i++){
            titles[i] = getItemTitle(i + 1);
        }
        return titles;
    }

    public int getItemPrice(int itemNumber){
        logger.info("Getting price of item with number " + itemNumber + " in cart");

        String itemPrice = itemPrices.get(itemNumber - 1).getText();
        itemPrice = itemPrice.replaceAll("[^0-9]", "");
        return Integer.parseInt(itemPrice);
    }

    public int getItemPrice(){
        return getItemPrice(1);
    }

    public int[] getAllItemsPrices(){
        logger.info("Getting prices of all items in cart");

        int[] prices = new int[itemPrices.size()];
        for (int i = 0; i < itemPrices.size(); i++){
            prices[i] = getItemPrice(i + 1);
        }
    return prices;
    }

    public int getTotalItemPrice(int itemNumber){
        logger.info("Getting total item price with number " + itemNumber + " in cart");

        String itemPrice = sumPrices.get(itemNumber - 1).getText();
        itemPrice = itemPrice.replaceAll("[^0-9]", "");
        return Integer.parseInt(itemPrice);
    }

    public int getTotalItemPrice(){
        return getTotalItemPrice(1);
    }

    public int getItemAmount(int itemNumber){
        logger.info("Getting amount of item with number " + itemNumber + " in cart");

        String amount = itemAmounts.get(itemNumber - 1).getAttribute("value");
        return Integer.parseInt(amount);
    }

    public int getItemAmount(){
        return getItemAmount(1);
    }

    public void clickIncreaseButton(int itemNumber){
        increaseButtons.get(itemNumber - 1).click();
    }

    public void clickIncreaseButton(){
        clickIncreaseButton(1);
    }

    public void clickDecreaseButton(int itemNumber){
        decreaseButtons.get(itemNumber - 1).click();
    }

    public void clickDecreaseButton(){
        clickDecreaseButton(1);
    }

    public void increaseByIncreaseButton(int itemNumber, int amountToIncrease){
        logger.info("Increasing amount of item with number " + itemNumber + " to " + amountToIncrease +
                " by clicking increase button");

        int currentAmount = getItemAmount(itemNumber);
        int clicksAmount = amountToIncrease - currentAmount;

        for(int i = 1; i <= clicksAmount; i++){
            clickIncreaseButton(itemNumber);
        }
        waits.longWaitForInvisibilityOfElement(sendingDataGif);
    }

    public void increaseByIncreaseButton(int amountToIncrease){
        increaseByIncreaseButton(1, amountToIncrease);
    }

    public void decreaseByDecreaseButton(int itemNumber, int amountToDecrease){
        logger.info("Decreasing amount of item with number " + itemNumber + " to " + amountToDecrease +
                " by clicking decrease button");

        int currentAmount = getItemAmount(itemNumber);
        int clicksAmount = currentAmount - amountToDecrease;

        for(int i = 1; i <= clicksAmount; i++){
            clickDecreaseButton(itemNumber);
        }
        waits.longWaitForInvisibilityOfElement(sendingDataGif);
    }

    public void decreaseByDecreaseButton(int amountToDecrease){
        decreaseByDecreaseButton(1, amountToDecrease);
    }

    public void fillAmountField(int itemNumber, String amount){
        logger.info("Setting the amount of item with number " + itemNumber + " equal " + amount);

        WebElement counter = amountFields.get(itemNumber - 1);
        counter.clear();
        counter.sendKeys(amount);
        counter.sendKeys(Keys.ENTER);
    }

    public void fillAmountField(String amount){
        fillAmountField(1, amount);
    }

    public boolean isAmountFieldRed(int itemNumber){
        logger.info("Check if amount field of item with number " + itemNumber + " is red");

        return !amountFields.get(itemNumber - 1).getAttribute("style").contains("background-color: rgb(255, 255, 255)");
    }

    public boolean isAmountFieldRed(){
        return isAmountFieldRed(1);
    }

    public void setItemAmount(int itemNumber, int amount){
        if(amount == 0){
            fillAmountField(itemNumber, String.valueOf(amount));
            waits.longWaitForInvisibilityOfElement(sendingDataGif);
        } else {
            fillAmountField(itemNumber, String.valueOf(amount));
            waits.longWaitForInvisibilityOfElement(sendingDataGif);
        }
    }

    public void setItemAmount(int amount){
        setItemAmount(1, amount);
    }

    public void deleteItem(int itemNumber){
        logger.info("Deleting item with number " + itemNumber + " from cart");

        waits.longWaitForElementAvailable(deleteButtons.get(itemNumber - 1));
        deleteButtons.get(itemNumber - 1).click();
        waits.shortWaitForInvisibilityOfElement(sendingDataGif);
        deletePopups.get(itemNumber - 1).click();
        waits.shortWaitForInvisibilityOfElement(sendingDataGif);

    }

    public void deleteItem(){
        deleteItem(1);
    }

    public void deleteAllItems(){
        logger.info("Deleting all items from cart");

        int amount = itemFields.size();
        for(int i = amount; i > 0; i--){
            deleteItem(i);
        }
        waits.longWaitForElementAvailable(emptyCartTitle);
    }

    public void deleteItemByCounter(int itemNumber){
        logger.info("Deleting item with number " + itemNumber + "by counter from cart");

        setItemAmount(itemNumber, 0);
        waits.longWaitForInvisibilityOfElement(sendingDataGif);
    }

    public void deleteItemByCounter(){
        deleteItemByCounter(1);
    }

    public void deleteAllItemsByCounter(){
        logger.info("Deleting all items from cart by counter");

        int amount = itemFields.size();
        for(int i = 1; i <= amount; i++) {
            deleteItemByCounter();
        }
        waits.longWaitForElementAvailable(emptyCartTitle);
    }

    public int getTotalPrice(){
        logger.info("Getting total price in cart");

        String totalAmount = totalPrice.getText();
        totalAmount = totalAmount.replaceAll("[^0-9]", "");
        return Integer.parseInt(totalAmount);
    }

    public void clickContinueShoppingButton(){
        logger.info("Clicking continue shopping button");

        continueShoppingButton.click();
        waits.longWaitForInvisibilityOfElement(cartPopup);
    }

    public boolean isCartEmpty(){
        logger.info("Checking if cart is empty");

        String expectedCartHeading = "Корзина пуста";
        String actualCartHeading = getHeadingText();

        return expectedCartHeading.equals(actualCartHeading);
    }

    public boolean isCartWithItems(){
        boolean isCartEmpty = false;
        try {
            isCartEmpty = itemsInCartAmount.isDisplayed();
        } catch (NullPointerException e) {
        } catch (WebDriverException e) {
        }
        return isCartEmpty;
    }

    public int sumTotalItemsPrices(){
        logger.info("Summation of total items prices in cart");

        int amount = sumPrices.size();
        int sum = 0;
        for (int i = 1; i <= amount; i++) {
            sum += getTotalItemPrice(i);
        }
        return sum;
    }

    public void closeCart(){
        logger.info("Closing cart window");

        closeCartButton.click();
        waits.longWaitForInvisibilityOfElement(cartPopup);
    }

    public boolean isCartWindowShown(){
        boolean isCartWindowShown = false;
        try {
            isCartWindowShown = headingText.isDisplayed();
        } catch (NoSuchElementException e){
        } catch (WebDriverException e){
        }
        return isCartWindowShown;
    }

    public int getPurchasedItemsAmount(){
        logger.info("Getting amount of purchased items");
        ActionsClass.openCart();

        return purchasedItems.size();
    }

    public void clickMakeOrderButton(){
        logger.info("Clicking make order button");

        makeOrderButton.click();
        waits.longWaitForElementAvailable("//*[@class='check-title']");
        waits.longWaitForElementAvailable("//div[@id='purchases_block']//div[contains(@class, 'title')]/a");
    }
}
