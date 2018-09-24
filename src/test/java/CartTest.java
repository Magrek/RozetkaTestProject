import org.testng.Assert;
import org.testng.annotations.*;

public class CartTest extends BaseTestClass{

    @BeforeSuite (alwaysRun = true)
    public void setUpBeforeSuite(){
        super.setUpBeforeSuite();
    }

    @BeforeClass (alwaysRun = true)
    public void setUpBeforeClass(){
        super.setUpBeforeClass();
        ActionsClass.openLaptopsList();
        url = driver.getCurrentUrl();
    }

    @Test(groups = {"ChangingItemsAmount"})
    public void headingText(){
        logger.info("headingText method is started");
        itemList.buyItem();
        String headingText = cart.getHeadingText();

        Assert.assertTrue(headingText.equals("Корзина") || headingText.equals("Вы добавили товар в корзину"),
                "Cart window isn't shown");
    }

    @Test(groups = {"ChangingItemsAmount"})
    public void increasingAndDecreasing(){
        logger.info("increasingAndDecreasing method is started");
        itemList.buyItem();
        //---------------------INCREASING---------------------

        int laptopPrice = cart.getItemPrice();

        cart.increaseByIncreaseButton(4);

        int laptopsPrice = cart.getTotalItemPrice();

        Assert.assertEquals(laptopPrice * 4, laptopsPrice,
                "The wrong price of the items is shown after increasing item amount");

        //---------------------DECREASING--------------------

        cart.decreaseByDecreaseButton(2);

        laptopsPrice = cart.getTotalItemPrice();

        Assert.assertEquals(laptopPrice * 2, laptopsPrice,
                "The wrong price of the items is shown after decreasing item amount");
    }

    @Test(groups = {"ChangingItemsAmount"})
    public void changingItemsAmountByCounter(){
        logger.info("changingItemsAmountByCounter is started");
        itemList.buyItem();
        //---------------------CHANGING THE STUFF AMOUNT TO 5--------------------------------

        int itemPrice = cart.getItemPrice();
        cart.setItemAmount(5);
        int totalPrice = cart.getTotalItemPrice();
        Assert.assertEquals(itemPrice * 5, totalPrice, "The wrong price of items is shown after changing item amount");

        //---------------------FILLING THE COUNTER WITH LETTERS---------------------------------

        cart.fillAmountField("test");
        Assert.assertTrue(cart.isAmountFieldRed(), "The error isn't shown after filling the counter with letters ");


        //---------------------CHANGING THE STUFF AMOUNT TO 0--------------------------------

        cart.setItemAmount(0);
        Assert.assertTrue(cart.isCartEmpty(), "Item was not deleted from cart after changing item amount to 0");
    }

    @Test(groups = {"AddingAndDeleting"})
    public void addingItemsAndCalculatingTotalPrice(){
        logger.info("testAddingItemsAndCalculatingTotalPrice method is started");
        int[] itemNumbers = {1, 3, 5};
        int[] itemPricesInCatalog = itemList.getItemPrices(itemNumbers);
        String[] itemTitleInCatalog = itemList.getItemTitles(itemNumbers);

        itemList.buyItemsByNumbers(itemNumbers);

        int[] itemPricesInCart = cart.getAllItemsPrices();
        String[] itemTitleInCart = cart.getAllItemsTitles();
        int actualTotalPrice = cart.sumTotalItemsPrices();
        int shownTotalPrice = cart.getTotalPrice();

        softAssert.assertTrue(ActionsClass.compareTwoArrays(itemPricesInCatalog, itemPricesInCart), "Titles don't match");
        softAssert.assertTrue(ActionsClass.compareTwoArrays(itemTitleInCatalog, itemTitleInCart), "Prices don't match");
        softAssert.assertEquals(actualTotalPrice, shownTotalPrice, "Prices were incorrectly summed up");

        softAssert.assertAll();
    }

    @Test(groups = {"AddingAndDeleting"})
    public void deletingStuff(){
        logger.info("deletingStuff method is started");
        itemList.buyFirstItems(3);
        cart.deleteAllItems();
        Assert.assertTrue(cart.isCartEmpty(), "Item was not deleted from cart");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDownAfterMethod(){
        super.tearDownAfterMethod();
        ActionsClass.cleanCart();
        ActionsClass.openPage(url);
    }

    @AfterClass (alwaysRun = true)
    public void tearDownAfterClass(){
        super.tearDownAfterClass();
        ActionsClass.cleanCart();
    }

    @AfterSuite (alwaysRun = true)
    public void tearDownAfterSuite(){
        super.tearDownAfterSuite();
    }
}
