package framework.pageObjects;
import org.openqa.selenium.By;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AutomationPractise.SeleniumFramwork0705.utility;
public class OrderPage extends utility {
	WebDriver driver;
	public OrderPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	public Boolean getOrder(String product1)
	{
		waitForElementToBeVisible(driver.findElement(By.tagName("tbody")));
		List<WebElement> OrdersList = driver.findElements(By.xpath("//tbody/tr/td[2]"));
		System.out.println("product1 - "+product1);
		System.out.println("OrdersList.getFirst().findElement(By.xpath('/td[2]')).getText()" + OrdersList.get(0).getText());
		Boolean OrderPresent = OrdersList.stream().anyMatch(order->order.getText().equalsIgnoreCase(product1));
		//Boolean match = cartProductList.stream().anyMatch(product->product.findElement(By.cssSelector("h3")).getText().equals("ZARA COAT 3"));
		System.out.println(product1+" OrderPresent - "+OrderPresent);
		return OrderPresent;
	}
}
