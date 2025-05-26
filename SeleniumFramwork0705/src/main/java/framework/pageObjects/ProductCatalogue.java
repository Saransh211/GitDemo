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
public class ProductCatalogue extends utility {
	WebDriver driver;
	WebDriverWait wait;
	public ProductCatalogue(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver,this);
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));//explicit wait
	}
	
	@FindBy(css="div#toast-container")
	WebElement loginMessage;
	
	@FindBy(xpath="//div[@class='card']")
	List<WebElement> productCards; 
	
	By AddToCart = By.cssSelector("button.btn.w-10.rounded");
	
	@FindBy(css="div#toast-container")
	WebElement AddedToCartPopup;
	
	@FindBy(css=".ng-animating")
	WebElement CircleLoadElement;
	
	@FindBy(xpath="//div[@role='alert']")
	WebElement Alert;
	
	public void selectProducts(String product1, String product2) throws InterruptedException
	{
		wait.until(ExpectedConditions.visibilityOf(loginMessage));
        //filter out the two products we need and add them to cart
        WebElement zaraCard = driver.findElements(By.xpath("//div[@class='card']")).stream().filter(card->card.findElement(By.cssSelector("b")).getText().equals(product1)).findFirst().orElse(null);
        WebElement phone = driver.findElements(By.xpath("//div[@class='card']")).stream().filter(card->card.findElement(By.cssSelector("b")).getText().equals(product2)).findFirst().orElse(null);
		System.out.println("Zara card fetched - "+zaraCard);
		System.out.println("Phone card fetched - "+phone);
        utility.moveIntoViewThenClick(zaraCard.findElement(AddToCart));
        waitForElementWithFluentWait(driver,By.cssSelector("div#toast-container"),50,10);
        //waitForElementToBeVisible(AddedToCartPopup);//wait for added to cart message pop up
        waitForElementToBeInvisible(AddedToCartPopup);//wait for diapering of cart message pop up
        utility.moveIntoViewThenClick(phone.findElement(AddToCart));
        waitForElementToBeVisible(AddedToCartPopup);//wait for added to cart message pop up
        String actualMsg = AddedToCartPopup.getText();//get the message of the pop up
        System.out.println("Message -> "+actualMsg);
        waitForElementToBeInvisible(CircleLoadElement);//wait for the pop up message to disappear
        try
        {
        	if(Alert.isDisplayed())
            	Alert.click();
        }catch(Exception e) {}
	}	
}
