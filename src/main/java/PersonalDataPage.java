import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PersonalDataPage extends BasePage{

    @FindBy (xpath = "//div[@id='personal_information']//h1")
    private WebElement pageHeading;

    public String getHeadingText(){
        logger.info("Getting heading text at personal data page");

        waits.longWaitForElementAvailable(pageHeading);
        String headingText = pageHeading.getText();

        return headingText;
    }
}
