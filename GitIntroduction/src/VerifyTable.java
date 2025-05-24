import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.util.List;
import java.util.stream.Collectors;

public class VerifyTable {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver","D:/eclipse/chromedriver/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/seleniumPractise/#/offers");
		
		WebElement searchField= driver.findElement(By.xpath("//input[@id='search-field']"));
		searchField.sendKeys("Rice");
				
		//Get all the fruit Name tr elements
		List<WebElement>  l = driver.findElements(By.xpath("//table[@class='table table-bordered']/descendant::tbody/tr/td[1]"));
		
		//filter the l stream with elements which contains Rice and count
		long itemCount = l.stream().filter(s->s.getText().contains("Rice")).count();
		System.out.print("l.size() - "+l.size()+" itemCount - "+itemCount);
		
		//compare the l List size and the itemCount
		Assert.assertEquals(l.size(), itemCount);
	}

}
