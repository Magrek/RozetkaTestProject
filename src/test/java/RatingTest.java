import org.testng.Assert;
import org.testng.annotations.*;

public class RatingTest extends BaseTestClass{

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

    @Test(groups = "rating")
    public void rating(){
        logger.info("rating method is started");

        int itemsWithRating4 = itemList.getItemsWithRatingAmount(4);
        int itemsWithRating5 = itemList.getItemsWithRatingAmount(5);

        itemList.sortFromCheapToExpensive();
        itemList.buyItemsWithRating(4);
        driver.get(url);
        itemList.buyItemsWithRating(5);

        int purchasedItemsAmount = cart.getPurchasedItemsAmount();

        Assert.assertEquals(purchasedItemsAmount, itemsWithRating4 + itemsWithRating5,
                "Amount of purchased items doesn't match with expected amount");
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
