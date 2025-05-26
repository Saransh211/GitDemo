package AutomationPractise.SeleniumFramwork0705;

import org.testng.Assert;
import org.testng.annotations.*;
import AutomationPractise.TestComponents.BaseTest;
import AutomationPractise.TestComponents.Retry;
import framework.pageObjects.CartPage;
import framework.pageObjects.LandingPage;
import framework.pageObjects.OrderConfirmationPage;
import framework.pageObjects.ProductCatalogue;
import framework.pageObjects.ShippingPage;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ErrorValidationTest extends BaseTest {
	String product1 = "ZARA COAT 3";
	String product2 = "IPHONE 13 PRO";
    @Test(groups= {"Error"}, retryAnalyzer=Retry.class)
    public void EndToEndTest() throws InterruptedException, IOException {
	        ProductCatalogue onproductCatalogue = landingPage.loginApplication("urfi@gmail.com1","urfi@1234");
	        WebElement LoginErrorPopUp = landingPage.getLoginErrorMessage();
	        Assert.assertEquals(LoginErrorPopUp.getText().trim(), "Incorrect 3email or password.");
	  }
    
    @Test
    public void ProductErrorValidation() throws InterruptedException
    {
    	ProductCatalogue onproductCatalogue = landingPage.loginApplication("urfi@gmail.com","urfi@1234");
    	//ProductCatalogue onproductCatalogue = landingPage.loginApplication("rammy@way.com","Rammy@Way123");
        onproductCatalogue.selectProducts(product1, product2);  
        CartPage onCartPage = landingPage.openCart();
        ShippingPage onShippingPage = onCartPage.verifyCart();
    }
}
