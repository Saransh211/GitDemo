package AutomationPractise.StepDefination;

import java.io.IOException;

import org.testng.Assert;

import AutomationPractise.TestComponents.BaseTest;
import framework.pageObjects.CartPage;
import framework.pageObjects.LandingPage;
import framework.pageObjects.OrderConfirmationPage;
import framework.pageObjects.OrderPage;
import framework.pageObjects.ProductCatalogue;
import framework.pageObjects.ShippingPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinationImpl extends BaseTest{
	
	public LandingPage landingPage;
	public ProductCatalogue onproductCatalogue;
	public CartPage onCartPage;
	public ShippingPage onShippingPage;
	public OrderConfirmationPage onOrderConfirmationPage;
	@Given("I landed on the Ecommerce Page")
	public void I_landed_on_the_Ecommerce_Page() throws IOException
	{
		landingPage = launchApplication();
	}
	
	@Given("Logged in with username {string} and {string}")
	public void logged_in_with_username_and(String username, String password) {
		onproductCatalogue = landingPage.loginApplication(username,password);
	}
	
	@When("I add product {string} and {string} from the cart")
	public void i_add_product_from_the_cart(String product1, String product2) throws InterruptedException {
		onproductCatalogue.selectProducts(product1,product2);  
		onCartPage = landingPage.openCart();
        onShippingPage = onCartPage.verifyCart();
	}

	@When("Checkout and submit the order")
	public void checkout_and_submit_the_order() {
		onOrderConfirmationPage = onShippingPage.AddShippingDetails();
	}

	@Then("{string} message is displayed on ConfirmationPage")
	public void message_is_displayed_on_confirmation_page(String string) throws InterruptedException {
		onOrderConfirmationPage.VerifyOrderConfirmation();
		driver.close();
	}

}
