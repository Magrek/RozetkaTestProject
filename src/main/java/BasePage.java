import org.apache.log4j.Logger;
import org.openqa.selenium.support.PageFactory;

public class BasePage extends Settings{

    Waits waits = new Waits();
    Logger logger = Settings.getLogger();

    public static <T extends BasePage> T initPage(Class<T> pageClass) {
        return PageFactory.initElements(Settings.getDriver(), pageClass);
    }
}