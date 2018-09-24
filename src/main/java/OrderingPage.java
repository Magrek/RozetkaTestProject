import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;

public class OrderingPage extends BasePage {

    @FindBy (xpath = "//*[@class='check-title']")
    private WebElement pageHeading;

    @FindBy (xpath = "//*[@name='new_user']")
    private WebElement blockWithNewCustomerButton;

    @FindBy (xpath = "//*[@name='new_user']/a")
    private WebElement newCustomerButton;

    @FindBy (xpath = "//*[@name='member_user']")
    private WebElement blockWothRegularCustomerButton;

    @FindBy (xpath = "//*[@name='member_user']/a")
    private WebElement regularCustomerButton;

    @FindBy (xpath = "//input[@id='reciever_name']")
    private WebElement nameAndLastNameField;

    @FindBy (xpath = "//input[@id='suggest_locality']")
    private WebElement cityDropDown;

    @FindBy (xpath = "//input[@id='reciever_phone']")
    private WebElement phoneField;

    @FindBy (xpath = "//input[@id='reciever_email']")
    private WebElement emailFieldForNewUsers;

    @FindBy (xpath = "//input[contains(@name, 'login')]")
    private WebElement emailFieldForRegisteredUsers;

    @FindBy (xpath = "//input[contains(@name, '[temp_pass]')]")
    private WebElement passwordField;

    @FindBy (xpath = "//div[@name='next_field']//button[@name='next_step']")
    private WebElement continueButton;

    @FindBy (xpath = "//a[@name='edit_step']")
    private WebElement editLink;

    @FindBy (xpath = "//a[@data-name='city_chooser']")
    private WebElement changeCityLink;

    @FindBy (xpath = "//label[contains(@name, 'pickup')]")
    private WebElement pickupRadioButton;

    @FindBy (xpath = "//div[@name='select_block_pickups']")
    private WebElement departmentDropdownMenu;

    @FindBy (xpath = "//li[contains(@class, 'pickups-select')]/span")
    private List<WebElement> departments;

    @FindBy (xpath = "//a[@name='map_link']")
    private WebElement lookMapLink;

    @FindBy (xpath = "//span[text()='Самовывоз' or text()='Самовивіз']/parent::div")
    private WebElement pickupFromNovaPoshtaRadioButton;

    @FindBy (xpath = "//input[@data-title='Мист Экспресс'or @data-title='Міст Експрес']/following-sibling::div")
    private WebElement mistExpressCourierRadioButton;

    @FindBy (xpath = "//input[@data-title='Мист Экспресс'or @data-title='Нова Пошта']/following-sibling::div")
    private WebElement novaPoshtaCourierRadioButton;

    @FindBy (xpath = "//input[@id='reciever_street']")
    private WebElement streetField;

    @FindBy (xpath = "//input[@id='reciever_house']")
    private WebElement houseField;

    @FindBy (xpath = "//input[@id='reciever_flat']")
    private WebElement flatField;

    @FindBy (xpath = "//div[contains(text(), 'Готівкою') or contains(text(), 'Наличными')]")
    private WebElement byCashRadioButton;

    @FindBy (xpath = "//div[contains(text(), 'Visa/MasterCard')]")
    private WebElement visaOrMasterCardRadioButton;

    @FindBy (xpath = "//div[contains(text(), 'Приват24')]/parent::div")
    private WebElement privat24RadioButton;

    @FindBy (xpath = "//div[contains(text(), 'Оплата част')]")
    private WebElement byInstallmentsRadioButton;

    @FindBy (xpath = "//div[contains(text(), 'Кредит')]")
    private WebElement creditRadioButton;

    @FindBy (xpath = "//div[contains(text(), 'Безналичными ') or contains(text(), 'Безготівковим ')]")
    private WebElement nonCashRadioButton;

    @FindBy (xpath = "//input[contains(@name, 'first_name')]")
    private WebElement nameField;

    @FindBy (xpath = "//input[contains(@name, 'last_name')]")
    private WebElement lastNameField;

    @FindBy (xpath = "//input[contains(@name, 'second_name')]")
    private WebElement secondNameField;

    @FindBy (xpath = "//label[@id='callback_off_label']")
    private WebElement callbackCheckBox;

    @FindBy (xpath = "//button[@id='make-order']")
    private WebElement orderConfirmationButton;

    @FindBy (xpath = "//a[@class='popup-close']")
    private WebElement popupCloseButton;

    @FindBy (xpath = "//*[contains(@class, 'check-confirm-header ')]")
    private WebElement orderConfirmPageHeading;

    @FindBy (xpath = "//div[contains(@id, 'purchase_price')]//div[contains(@class, 'sum')]")
    private List<WebElement> itemPrices;

    @FindBy (xpath = "//div[@id='purchases_block']//div[contains(@class, 'title')]/a")
    private List<WebElement> itemTitles;

    @FindBy (xpath = "//div[@id='purchases_block']//div[contains(@class, 'total-sum')]")
    private WebElement totalPrice;

    @FindBy (xpath = "//div[not(contains(@class, 'inactive'))]/div[contains(@class, 'delivery-info')]")
    private WebElement chosenDeliveryCost;

    @FindBy (xpath = "//span[@id='total_checkout_amount']")
    private WebElement totalPriceWithDelivery;

    public void clickNewCustomerButton(){
        logger.info("Clicking the 'new customer' button");

        if (!blockWithNewCustomerButton.getAttribute("class").contains("active")) {
            newCustomerButton.click();
            waits.longWaitForElementAvailable(nameAndLastNameField);
        }
    }

    public void clickRegularCustomerButton(){
        logger.info("Clicking the 'regular customer' button");

        if (!blockWothRegularCustomerButton.getAttribute("class").contains("active")) {
            newCustomerButton.click();
            waits.longWaitForElementAvailable(passwordField);
        }
    }

    public String getHeadingText(){
        logger.info("Getting heading text from ordering page");

        return pageHeading.getText();
    }

    public void fillNameAndLastNameField(String name, String lastName){
        ActionsClass.sendKeys(nameAndLastNameField, name + " " + lastName);
    }

    public void fillPhoneField(String phone){
        ActionsClass.sendKeys(phoneField, phone);
    }

    public void fillEmailField(String email) {
        ActionsClass.sendKeys(emailFieldForNewUsers, email);
    }

    public void fillContactDetailsFields(String name, String lastName, String phone, String email){
        logger.info("Filling contact details fields");

        fillNameAndLastNameField(name, lastName);
        fillPhoneField(phone);
        fillEmailField(email);
        waits.longWaitForInvisibilityOfElement("//div[@name='next_field']//span[contains(@class, 'disabled')]");
    }

    public void fillContactDetailsFields(String name, String lastName, String phone){
        logger.info("Filling contact details fields");

        fillNameAndLastNameField(name, lastName);
        fillPhoneField(phone);
        waits.longWaitForInvisibilityOfElement("//div[@name='next_field']//span[contains(@class, 'disabled')]");
    }

    public void clickContinueButton(){
        logger.info("Clicking the 'continue' button");

        continueButton.click();
        waits.longWaitForInvisibilityOfElement(continueButton);
    }

    public void choosePickupDelivery(){
        logger.info("Choosing the 'Pickup' delivery type");

        pickupRadioButton.click();
        waits.longWaitForInvisibilityOfElement("//img[@alt='Отправка данных']");
    }

    public void chooseMailDepartment(int departmentNumber){
        logger.info("Choosing mail department");

        String departmentNum = String.format("№%s", String.valueOf(departmentNumber));
        departmentDropdownMenu.click();
        waits.longWaitForElementAvailable("//div[@name='pickups_drop_block']");
        for (int i = 0; i < departments.size(); i++){
            if(departments.get(i).getAttribute("data-title").contains(departmentNum))
                departments.get(i).click();
        }
    }

    public void chooseMistExpressCourierDelivery(){
        logger.info("Choosing the 'Mist Express Courier' delivery type");

        mistExpressCourierRadioButton.click();
        waits.longWaitForInvisibilityOfElement("//img[@alt='Отправка данных']");
    }

    public void chooseNovaPoshtaCourierDelivery(){
        logger.info("Choosing the 'Nova Poshta Courier' delivery type");

        novaPoshtaCourierRadioButton.click();
        waits.longWaitForInvisibilityOfElement("//img[@alt='Отправка данных']");
    }

    public void fillStreetField(String street){
        ActionsClass.sendKeys(streetField, street);
    }

    public void fillHouseField(String house){
        ActionsClass.sendKeys(houseField, house);
    }

    public void fillFlatField(String flat){
        ActionsClass.sendKeys(flatField, flat);
    }

    public void enterAddress(String street, String house, String flat){
        logger.info("Filling address fields");

        fillStreetField(street);
        fillHouseField(house);
        fillFlatField(flat);
    }

    public void chooseByCashPayment(){
        logger.info("Choosing 'By cash' payment type");

        byCashRadioButton.click();
        waits.longWaitForInvisibilityOfElement("//img[@alt='Отправка данных']");
    }

    public void chooseVisaOrMasterCardPayment(){
        logger.info("Choosing 'Visa Or Master Card' payment type");

        visaOrMasterCardRadioButton.click();
        waits.longWaitForInvisibilityOfElement("//img[@alt='Отправка данных']");
    }

    public void choosePrivat24Payment(){
        logger.info("Choosing 'Privat24' payment type");

        privat24RadioButton.click();
        waits.longWaitForInvisibilityOfElement("//img[@alt='Отправка данных']");
    }

    public void chooseByInstallmentsPayment(){
        logger.info("Choosing 'By Installments' payment type");

        byInstallmentsRadioButton.click();
        waits.longWaitForInvisibilityOfElement("//img[@alt='Отправка данных']");
    }

    public void chooseCreditPayment(){
        logger.info("Choosing 'Credit' payment type");

        creditRadioButton.click();
        waits.longWaitForInvisibilityOfElement("//img[@alt='Отправка данных']");
    }

    public void chooseNonCashPayment(){
        logger.info("Choosing 'Non Cash' payment type");

        nonCashRadioButton.click();
        waits.longWaitForInvisibilityOfElement("//img[@alt='Отправка данных']");
    }

    public void fillNameField(String name){
        ActionsClass.sendKeys(nameField, name);
    }

    public void fillLastNameField(String lastName){
        ActionsClass.sendKeys(lastNameField, lastName);
    }

    public void fillMiddleNameField(String middleName){
        try {
            ActionsClass.sendKeys(secondNameField, middleName);
        } catch (InvalidElementStateException e) {
        }
    }

    public void fillFullNameFields(String name, String lastName, String middleName){
        logger.info("Filling name fields");

        fillNameField(name);
        fillLastNameField(lastName);
        fillMiddleNameField(middleName);
    }

    public void clickCallbackRequestCheckbox(){
        logger.info("Selecting 'Call back request' checkbox");

        callbackCheckBox.click();
        waits.shortWaitForElementAvailable(popupCloseButton);
        try {
            popupCloseButton.click();
        } catch (NullPointerException e) {
        } catch (WebDriverException e) {
        }
    }

    public void clickOrderConfirmationButton(){
        logger.info("Clicking 'Order confirmation' button");

        waits.longWaitForElementAvailable(orderConfirmationButton);
        if(!orderConfirmationButton.getAttribute("class").contains("disabled")) {
            orderConfirmationButton.click();
            waits.longWaitForInvisibilityOfElement("//img[@alt='Отправка данных']");
            waits.longWaitForElementAvailable(orderConfirmPageHeading);
        }
    }

    public String getConfirmPageHeading(){
        logger.info("Getting heading of confirm page");

        return orderConfirmPageHeading.getText();
    }

    public int getItemPrice(int itemNumber){
        logger.info("Getting price of item with number " + itemNumber + " at ordering page");

        String itemPrice = itemPrices.get(itemNumber - 1).getText();
        itemPrice = itemPrice.replaceAll("[^0-9]", "");

        return Integer.parseInt(itemPrice);
    }

    public int getItemPrice(){
        return getItemPrice(1);
    }

    public int[] getAllItemsPrices(){
        logger.info("Getting all prices of items at ordering page");

        int[] prices = new int[itemPrices.size()];
        for (int i = 0; i < itemPrices.size(); i++){
            prices[i] = getItemPrice(i + 1);
        }
    return prices;
    }

    public String getItemTitle(int itemNumber){
        logger.info("Getting title of item with number " + itemNumber + " at ordering page");

        return itemTitles.get(itemNumber - 1).getText();
    }

    public String getItemTitle(){
        return getItemTitle(1);
    }

    public String[] getAllItemsTitles(){
        logger.info("Getting all titles of items at ordering page");

        String[] titles = new String[itemTitles.size()];
        for (int i = 0; i < itemTitles.size(); i++){
            titles[i] = getItemTitle(i + 1);
        }
        return titles;
    }

    public int getTotalPrice(){
        logger.info("Getting total price at ordering page");

        String totalAmount = totalPrice.getText();
        totalAmount = totalAmount.replaceAll("[^0-9]", "");
        return Integer.parseInt(totalAmount);
    }

    public int getChosenDeliveryCost(){
        logger.info("Getting cost of chosen delivery type");

        String cost = chosenDeliveryCost.getText();
        cost = cost.replaceAll("[^0-9]", "");
        return Integer.parseInt(cost);
    }

    public int getTotalPriceWithDelivery(){
        logger.info("Get total price of order with delivery cost");

        String totalAmount = totalPriceWithDelivery.getText();
        totalAmount = totalAmount.replaceAll("[^0-9]", "");
        return Integer.parseInt(totalAmount);
    }
}
