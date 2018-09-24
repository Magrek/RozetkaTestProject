import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Settings {

    private static WebDriver driver = new ChromeDriver();
    private static final Logger LOGGER = Logger.getLogger(Settings.class.getSimpleName());

    public static WebDriver getDriver(){
        return driver;
    }

    public static Logger getLogger() {
        return LOGGER;
    }
}
