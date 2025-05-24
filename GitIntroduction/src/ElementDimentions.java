import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class ElementDimentions {
		public static void main(String[] args) throws IOException {
				System.setProperty("webdriver.chrome.driver", "D:/eclipse/chromedriver/chromedriver.exe");
				WebDriver driver = new ChromeDriver();
				driver.manage().window().maximize();
				driver.get("https://rahulshettyacademy.com/angularpractice/");
				WebElement nameEditBox = driver.findElement(By.cssSelector("[name='name']"));
				System.out.println("Height - "+nameEditBox.getRect().getDimension().getHeight());
				System.out.print("Width - "+nameEditBox.getRect().getDimension().getWidth());
	}

}
