package framework.pageObjects;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import AutomationPractise.SeleniumFramwork0705.utility;
public class ShippingPage extends utility {
	WebDriver driver;
	WebDriverWait wait ;
	public ShippingPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath="//input[@placeholder='Select Country']")
	WebElement SelectCountry;
	
	@FindBy(xpath="//input[@placeholder='Select Country']/following-sibling::section/button/span")
	List<WebElement> countryOptions;
	
	@FindBy(xpath="//a[text()='Place Order ']")
	WebElement PlaceOrderButton;
	
	public OrderConfirmationPage AddShippingDetails()
	{
		//wait for shipping information form to display
		waitForElementToBeVisible(driver.findElement(By.xpath("//div[@class='payment__shipping']")));

        //update shipping info
		SelectCountry.sendKeys("India");
		waitForElementToBeVisible(driver.findElement(By.cssSelector(".ta-results")));
        WebElement countryOption = driver.findElements(By.xpath("//input[@placeholder='Select Country']/following-sibling::section/button/span")).stream().filter(option->option.getText().equals("India")).findFirst().orElse(null);
        countryOption.click();
        
        //click on place order
        PlaceOrderButton.click();
        OrderConfirmationPage onOrderConfirmationPage = new OrderConfirmationPage(driver);
        return onOrderConfirmationPage;
	}
}
