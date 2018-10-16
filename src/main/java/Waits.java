import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Waits {

    WebDriverWait shortWait = getWebDriverWait(2);
    WebDriverWait longWait = getWebDriverWait(10);

    public WebDriverWait getWebDriverWait(int secondsForWaiting){
        return new WebDriverWait(Settings.getDriver(), secondsForWaiting);
    }

    public void longWaitForElementAvailable(String xpathLocator){
        try {
            longWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpathLocator)));
            longWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathLocator)));
            longWait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpathLocator)));
        } catch (TimeoutException e){}
    }

    public void longWaitForElementAvailable(WebElement webElement){
        try {
            longWait.until(ExpectedConditions.visibilityOf(webElement));
        } catch (TimeoutException e){}
    }

    public void shortWaitForElementAvailable(String xpathLocator){
        try {
            shortWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpathLocator)));
            shortWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathLocator)));
        } catch (TimeoutException e){}
    }

    public void shortWaitForElementAvailable(WebElement webElement){
        try {
            shortWait.until(ExpectedConditions.visibilityOf(webElement));
        } catch (TimeoutException e){}
    }

    public void longWaitForInvisibilityOfElement(String xpathLocator){
        try {
            longWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(xpathLocator)));
        } catch (TimeoutException e) {}
    }

    public void longWaitForInvisibilityOfElement(WebElement webElement){
        try {
            longWait.until(ExpectedConditions.invisibilityOf(webElement));
        } catch (TimeoutException e) {}
    }

    public void shortWaitForInvisibilityOfElement(String xpathLocator){
        try {
            shortWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(xpathLocator)));
        } catch (TimeoutException e) {}
    }

    public void shortWaitForInvisibilityOfElement(WebElement webElement){
        try {
            shortWait.until(ExpectedConditions.invisibilityOf(webElement));
        } catch (TimeoutException e) {}
    }

    public void longWaitForElementAttribute(WebElement webElement, String attribute, String value){
        try {
            longWait.until(ExpectedConditions.attributeContains(webElement, attribute, value));
        } catch (TimeoutException e) {}
    }

    public void sleep(int milisToSleep){
        try {
            Thread.sleep(milisToSleep);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
