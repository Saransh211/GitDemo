import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
/*Test Flow
 * 1. Go to https://rahulshettyacademy.com/locatorspractice/
 * 2. Give username and pass click on login
 * 3. Get the invalid username password
 * 4. Click on forget password
 * 5. Give name, email and mobile number
 * 6. click on reset password
 * 7. get the password
 * 8. log with it
 * Note - Use implicit wait
 * verify the login message
 * verify the username greeting
 * logout*/
public class Locators {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver","D:/eclipse/chromedriver/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));//globally applicable to wait for the element to be present
		driver.get("https://rahulshettyacademy.com/locatorspractice/");
		driver.findElement(By.id("inputUsername")).sendKeys("Rahul");
		driver.findElement(By.name("inputPassword")).sendKeys("abdgc");
		driver.findElement(By.xpath("//button[text()='Sign In']")).click();
		//Thread.sleep(2000);
		driver.findElement(By.xpath("//a[text()='Forgot your password?']")).click();
		//Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@placeholder='Name']")).sendKeys("Rahul");
		driver.findElement(By.xpath("//input[@placeholder='Email']")).sendKeys("Rahul@gmail.com");
		driver.findElement(By.xpath("//input[@placeholder='Phone Number']")).sendKeys("9999999999");
		driver.findElement(By.xpath("//button[text()='Reset Login']")).click();
		String txt = driver.findElement(By.cssSelector(".infoMsg")).getText();
		String[] parts = txt.split("'");
		String password = parts[1];
		System.out.print(password);
		driver.findElement(By.xpath("//button[text()='Go to Login']")).click();
		//Thread.sleep(2000);
		driver.findElement(By.id("inputUsername")).sendKeys("Rahul");
		driver.findElement(By.name("inputPassword")).sendKeys(password);
		driver.findElement(By.xpath("//button[text()='Sign In']")).click();
		Thread.sleep(10000);
		driver.close();
		driver.quit();

	}

}
