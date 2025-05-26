package framework.pageObjects;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import AutomationPractise.SeleniumFramwork0705.utility;
public class CartPage extends utility {
	
//	@FindBy(xpath="")
//	WebElement myCartHeading;
	
	@FindBy(xpath="//div[@class='cart']/ul")
	List<WebElement> cartProductList;
	
	@FindBy(xpath="//button[text()='Checkout']")
	WebElement Checkout;
	
	WebDriver driver;
	public CartPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	public ShippingPage verifyCart() throws InterruptedException
	{
        //waitForElementToBeVisible(driver.findElement(By.xpath("//div[@class='heading cf']/h1")));//wait for the cart heading to be loaded
        Thread.sleep(2000);
        //System.out.println("Executed till line 34");
        //verify whether ZARA COAT 3 is present in the cart or not?
        Boolean match = cartProductList.stream().anyMatch(product->product.findElement(By.cssSelector("h3")).getText().equals("ZARA COAT 3"));
        Assert.assertTrue(match);
        //System.out.println("Executed till line 38");
        //clicking on the checkout button
        utility.moveIntoViewThenClick(Checkout);
        ShippingPage onShippingPage = new ShippingPage(driver);
        return onShippingPage;
	}
}
