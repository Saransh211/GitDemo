package framework.pageObjects;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import AutomationPractise.SeleniumFramwork0705.utility;
public class OrderConfirmationPage extends utility {
	WebDriver driver;
	WebDriverWait wait;
	public OrderConfirmationPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath="//h1[@class='hero-primary']")
	WebElement OrderConfirmation;
	
	public void VerifyOrderConfirmation() throws InterruptedException
	{
		//waitForElementToBeVisible(OrderConfirmation);
		System.out.println("Executed till here");
		waitForElementToBeVisible(OrderConfirmation);
		System.out.println("Executed till here");
		System.out.println("OrderConfirmation.getText() - "+OrderConfirmation.getText());
        Assert.assertEquals(OrderConfirmation.getText(), "THANKYOU FOR THE ORDER.");
	}
}
