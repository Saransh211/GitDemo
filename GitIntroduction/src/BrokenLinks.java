import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class BrokenLinks {
	private static WebDriver driver = null;

	public static void main(String[] args) throws IOException {
		String url = "";
		HttpURLConnection huc = null;
		int respCode = 200;
		driver = new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/AutomationPractice/");
//Get all the a tag in the links list
		List<WebElement> links = driver.findElements(By.tagName("a"));
		Iterator<WebElement> it = links.iterator();
//loop to check whether the href of each a tag is empty or null
		while (it.hasNext()) {
			url = it.next().getAttribute("href");
			System.out.println(url);
			if (url == null || url.isEmpty()) {
				System.out.println("URL is either not configured for anchor tag or it is empty");
				continue;
			}
			try {// Method in the HttpURLConnection class will send HTTP requests and capture the
					// HTTP response code. Therefore, the output of openConnection() method
					// (URLConnection) is type casted to HttpURLConnection.

				huc = (HttpURLConnection) (new URL(url).openConnection());
//setRequestMethod("HEAD") will return the header info of the request response, if GET is mentioned the whole request will be returned.
				huc.setRequestMethod("HEAD");
//the connect() method, the actual connection to the URL is established and the HTTP request is sent.
				huc.connect();
				respCode = huc.getResponseCode();
				if (respCode >= 400) {
					System.out.println(url + " is a broken link");
				} else {
					System.out.println(url + " is a valid link");
				}

			} catch (MalformedURLException e) {
			}
		}
		driver.quit();
	}
}
