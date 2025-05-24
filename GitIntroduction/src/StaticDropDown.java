import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
public class StaticDropDown {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver","D:/eclipse/chromedriver/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/dropdownsPractise/");
		WebElement staticDropDown = driver.findElement(By.id("ctl00_mainContent_DropDownListCurrency"));
		Select dropdown = new Select(staticDropDown);
		dropdown.selectByIndex(3);
		System.out.print(dropdown.getFirstSelectedOption().getText());
		dropdown.selectByVisibleText("AED");
		System.out.print(dropdown.getFirstSelectedOption().getText());
		dropdown.selectByValue("INR");
		System.out.print(dropdown.getFirstSelectedOption().getText());
		List<WebElement> l = dropdown.getAllSelectedOptions();
		System.out.println();
		System.out.println(l.size());
		System.out.println(l.getFirst().getText());
	}
}
