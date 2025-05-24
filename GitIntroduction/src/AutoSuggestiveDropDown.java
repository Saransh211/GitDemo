import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.WebElement;

public class AutoSuggestiveDropDown {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver","D:/eclipse/chromedriver/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2000));
		driver.get("https://rahulshettyacademy.com/dropdownsPractise/");
		driver.findElement(By.cssSelector("input#autosuggest")).sendKeys("Ind");
		List<WebElement> l = driver.findElements(By.cssSelector("ul#ui-id-1 li"));
		List<WebElement> li = l.stream().filter(s->s.getText().equals("India")).limit(1).collect(Collectors.toList());
		li.getFirst().click();
	}

}
