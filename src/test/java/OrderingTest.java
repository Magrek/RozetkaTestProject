import org.testng.annotations.*;

public class OrderingTest extends BaseTestClass{

    @BeforeSuite(alwaysRun = true)
    public void setUpBeforeSuite(){
        super.setUpBeforeSuite();
    }

    @BeforeClass(alwaysRun = true)
    public void setUpBeforeClass(){
        super.setUpBeforeClass();
        ActionsClass.openLaptopsList();
    }

    @Test(groups = {"ordering"})
    public void itemParametersCheck(){
        logger.info("itemParametersCheck method is started");

        itemList.buyFirstItems(3);

        int[] itemPricesInCart = cart.getAllItemsPrices();
        String[] itemTitleInCart = cart.getAllItemsTitles();
        int totalPriceInCart = cart.getTotalPrice();

        cart.clickMakeOrderButton();
        String heading = orderingPage.getHeadingText();
        softAssert.assertTrue(heading.equals("Оформление заказа"),"Ordering page isn't shown");

        int[] itemPricesOnOrderingPage = orderingPage.getAllItemsPrices();
        String[] itemTitleOnOrderingPage = orderingPage.getAllItemsTitles();
        int totalPriceOnOrderingPage = orderingPage.getTotalPrice();

        softAssert.assertTrue(ActionsClass.compareTwoArrays(itemPricesInCart, itemPricesOnOrderingPage), "Prices don't match");
        softAssert.assertTrue(ActionsClass.compareTwoArrays(itemTitleInCart, itemTitleOnOrderingPage), "Titles don't match");
        softAssert.assertEquals(totalPriceInCart, totalPriceOnOrderingPage, "Total prices don't match");

        softAssert.assertAll();
    }

    @Test(groups = {"ordering"}, dependsOnMethods = {"itemParametersCheck"})
    public void deliveryPriceCheck(){
        logger.info("deliveryPriceCheck method is started");

        int totalPrice = orderingPage.getTotalPrice();

        orderingPage.fillContactDetailsFields("TestName", "TestLastName", "+380123456789", ActionsClass.getRandomMail());
        orderingPage.clickContinueButton();

        orderingPage.chooseMailDepartment(68);
        orderingPage.choosePrivat24Payment();
        orderingPage.chooseByCashPayment();
        orderingPage.fillFullNameFields("TestName", "TestLastName", "TestMiddleName");

        int deliveryCost = orderingPage.getChosenDeliveryCost();
        int totalPriceWithDelivery = orderingPage.getTotalPriceWithDelivery();

        softAssert.assertEquals(totalPrice + deliveryCost, totalPriceWithDelivery, "Price is calculated incorrectly");

        softAssert.assertAll();
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

