import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Screenshot {

	public static void main(String[] args) throws IOException {
			System.setProperty("webdriver.chrome.driver", "D:/eclipse/chromedriver/chromedriver.exe");
			WebDriver driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.get("https://rahulshettyacademy.com/angularpractice/");
			WebElement nameEditBox =driver.findElement(By.cssSelector("[name='name']"));
			nameEditBox.sendKeys("ABC");
			File file = nameEditBox.getScreenshotAs(OutputType.FILE);
			
			//FileUtils - is class with will convert file object into physical file
			//We need Apache Commons IOjar file to be added in the project
			//file is the source and new file is the destination
			FileUtils.copyFile(file, new File("Name Text File.png"));
	}

}
