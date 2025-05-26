//package AutomationPractise.SeleniumFramwork0705;
//
//import org.testng.Assert;
//import org.testng.annotations.*;
//
//import framework.pageObjects.LandingPage;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.time.Duration;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//
//import io.github.bonigarcia.wdm.WebDriverManager;
//
//public class StandAloneTest {
//    @Test
//    public void EndToEndTest() throws InterruptedException {
//	        WebDriverManager.chromedriver().setup();
//	        WebDriver driver = new ChromeDriver();
//	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));//implicitly wait
//	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));//explicit wait
//	        
//	        driver.manage().window().maximize();
//	        driver.get("https://rahulshettyacademy.com/client");
//	        
//	        //login
//	        LandingPage LPobj = new LandingPage(driver);
//	        driver.findElement(By.cssSelector("input[placeholder='email@example.com']")).sendKeys("urfi@gmail.com");
//	        driver.findElement(By.cssSelector("input[placeholder='enter your passsword']")).sendKeys("urfi@1234");
//	        driver.findElement(By.cssSelector("input[value='Login']")).click();
//	        
//	        //wait for the login message
//	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#toast-container")));
//	        
//	        WebElement zaraCard = null;
//	        WebElement phone = null;
//	        //filter out the two products we need and add them to cart
//	        zaraCard = driver.findElements(By.xpath("//div[@class='card']")).stream().filter(card->card.findElement(By.cssSelector("b")).getText().equals("ZARA COAT 3")).findFirst().orElse(null);
//	        phone = driver.findElements(By.xpath("//div[@class='card']")).stream().filter(card->card.findElement(By.cssSelector("b")).getText().equals("IPHONE 13 PRO")).findFirst().orElse(null);
//    		System.out.println("Zara card fetched - "+zaraCard);
//    		System.out.println("Phone card fetched - "+phone);
//	        utility.moveIntoViewThenClick(driver,zaraCard.findElement(By.cssSelector("button.btn.w-10.rounded")));
//	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#toast-container")));//wait for added to cart message pop up
//	        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#toast-container")));//wait for diapering of cart message pop up
//	        utility.moveIntoViewThenClick(driver,phone.findElement(By.cssSelector("button.btn.w-10.rounded")));
//	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#toast-container")));//wait for added to cart message pop up
//	        String actualMsg = driver.findElement(By.cssSelector("div#toast-container")).getText();//get the message of the pop up
//	        System.out.println("Message -> "+actualMsg);
//	        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));//wait for the pop up message to disappear
//	        driver.findElement(By.xpath("//div[@role='alert']")).click();
//	        utility.moveIntoViewThenClick(driver,driver.findElement(By.cssSelector("button[routerlink='/dashboard/cart']")));
//	        WebElement myCart = driver.findElement(By.xpath("//div[@class='heading cf']/h1"));
//	        wait.until(ExpectedConditions.visibilityOf(myCart));
//	        
//	        //verify whether ZARA COAT 3 is present in the cart or not?
//	        List<WebElement> cartProductsList = driver.findElements(By.xpath("//div[@class='cart']/ul"));
//	        Boolean match = driver.findElements(By.xpath("//div[@class='cart']/ul")).stream().anyMatch(product->product.findElement(By.cssSelector("h3")).getText().equals("ZARA COAT 3"));
//	        Assert.assertTrue(match);
//	        
//	        //clicking on the checkout button
//	        utility.moveIntoViewThenClick(driver,driver.findElement(By.xpath("//button[text()='Checkout']")));
//	        
//	        //wait for shipping information form to display
//	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='payment__shipping']")));
//	        
//	        //update shipping info
//	        driver.findElement(By.xpath("//input[@placeholder='Select Country']")).sendKeys("India");
//	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
//	        List<WebElement> countryOptions = driver.findElements(By.xpath("//input[@placeholder='Select Country']/following-sibling::section/button/span"));
//	        WebElement countryOption = driver.findElements(By.xpath("//input[@placeholder='Select Country']/following-sibling::section/button/span")).stream().filter(option->option.getText().equals("India")).findFirst().orElse(null);
//	        countryOption.click();
//	        
//	        //click on place order
//	        driver.findElement(By.xpath("//a[text()='Place Order ']")).click();
//	        String OrderConfirmationActualMsg = driver.findElement(By.xpath("//h1[@class='hero-primary']")).getText();
//	        Assert.assertEquals(OrderConfirmationActualMsg, "THANKYOU FOR THE ORDER.");
//	        
//	        }
//}
