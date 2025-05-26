package framework.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import AutomationPractise.SeleniumFramwork0705.utility;
public class LandingPage extends utility {
	WebDriver driver;
	public LandingPage(WebDriver driver)
	{
		super(driver);
		//initialization of the driver
		this.driver = driver;
		PageFactory.initElements(driver,this);//to initialize all the elements
	}
	
	//WebElement email = driver.findElement(By.cssSelector("input[placeholder='email@example.com']")).sendKeys("urfi@gmail.com");
    //driver.findElement(By.cssSelector("input[placeholder='enter your passsword']")).sendKeys("urfi@1234");
    //driver.findElement(By.cssSelector("input[value='Login']")).click();
	
	//Page factory
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement userPassword;
	
	@FindBy(css="input[value='Login']")
	WebElement loginButton;
	
	@FindBy(css="button[routerlink='/dashboard/cart']")
	WebElement cartLink;
	
	@FindBy(css="div[class*='toast-message']")
	WebElement LoginErrorPopUp;
	
	public ProductCatalogue loginApplication(String email, String pass)
	{
		userEmail.sendKeys(email);
		userPassword.sendKeys(pass);
		loginButton.click();
		ProductCatalogue onproductCatalogue = new ProductCatalogue(driver);
		return onproductCatalogue;
	}
	
	public WebElement getLoginErrorMessage()
	{
		waitForElementToBeVisible(LoginErrorPopUp);
		return LoginErrorPopUp;
	}
	
	public void openApplication()
	{
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	public CartPage openCart() throws InterruptedException
	{
		utility.moveIntoViewThenClick(driver.findElement(By.cssSelector("button[routerlink='/dashboard/cart']")));
		//driver.findElement(By.cssSelector("button[routerlink='/dashboard/cart']")).click();
		CartPage onCartPage = new CartPage(driver);
		return onCartPage;
	}
}
