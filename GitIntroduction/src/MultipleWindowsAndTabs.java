import java.awt.Window;
import java.util.Iterator;
import java.util.Set;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
public class MultipleWindowsAndTabs {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "D:/eclipse/chromedriver/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/angularpractice/");
		driver.switchTo().newWindow(WindowType.TAB);
		Set<String> handles = driver.getWindowHandles();
		Iterator<String> it = handles.iterator();
		String parentWindowId = it.next();
		String childWindowId = it.next();
		driver.switchTo().window(childWindowId);
		driver.get("https://rahulshettyacademy.com");
		driver.switchTo().window(parentWindowId);
		driver.quit();
		
		WebDriver driverr = new ChromeDriver();
		driverr.get("https://rahulshettyacademy.com/angularpractice/");
		driverr.switchTo().newWindow(WindowType.WINDOW);
		Thread.sleep(2000);
		Set<String> handless = driverr.getWindowHandles();
		Iterator<String> itt = handles.iterator();
		String parentWindowIdd = itt.next();
		String childWindowIdd = itt.next();
		driverr.switchTo().window(childWindowIdd);
		driverr.get("https://rahulshettyacademy.com");
		driverr.switchTo().window(parentWindowIdd);
	}

}
