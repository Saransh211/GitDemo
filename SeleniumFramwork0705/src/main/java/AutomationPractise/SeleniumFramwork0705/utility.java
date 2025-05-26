package AutomationPractise.SeleniumFramwork0705;
import java.time.Duration;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import framework.pageObjects.OrderPage;
public class utility {
	static WebDriver driver;
	public utility(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(css="button[routerlink='/dashboard/myorders']")
	WebElement Orders;
	
	public OrderPage gotoOrdersPage() {
		Orders.click();
		OrderPage onOrderPage = new OrderPage(driver);
		return onOrderPage;
	}
	
    public static void moveIntoViewThenClick(WebElement element) throws InterruptedException
    {
    	//((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    	   wait.until(ExpectedConditions.visibilityOf(element));
    	   wait.until(ExpectedConditions.elementToBeClickable(element));
    	   
    	Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
    	
    	int attempts = 0;
    	while(attempts<2)
    	{
    		try
    		{
    			element.click();
    		}catch(Exception e)
    		{
    			attempts++;
    		}
    	}
    }
    
    public void waitForElementToBeVisible(WebElement element)
    {
    	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
    	wait.until(ExpectedConditions.visibilityOf(element));
    }
    
    public void waitForElementToBeInvisible(WebElement element)
    {
    	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
    	wait.until(ExpectedConditions.invisibilityOf(element));
    }
    
    public static WebElement waitForElementWithFluentWait(WebDriver driver, By locator, int timeoutInSeconds, int pollingInMillis) {
        Wait<WebDriver> wait = new FluentWait<>(driver)
            .withTimeout(Duration.ofSeconds(timeoutInSeconds))
            .pollingEvery(Duration.ofMillis(pollingInMillis))
            .ignoring(NoSuchElementException.class)
            .ignoring(StaleElementReferenceException.class);

        return wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                WebElement element = driver.findElement(locator);
                if (element.isDisplayed()) {
                    return element;
                }
                return null;
            }
        });
    }
    
    public static WebElement waitForElementVisible(WebDriver driver, By locator, int timeoutInSeconds, int pollingInMillis) {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(timeoutInSeconds))        // Max wait time
                .pollingEvery(Duration.ofSeconds(pollingInMillis))        // Polling interval
                .ignoring(NoSuchElementException.class);     // Ignore element not found

        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
}
