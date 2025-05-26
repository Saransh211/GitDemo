package AutomationPractise.TestComponents;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import AutomationPractise.SeleniumFramwork0705.utility;
import framework.pageObjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	public WebDriver driver;
	public LandingPage landingPage;
	public utility util;
//	public WebDriver initializeDriver() throws IOException
//	{
//		Properties prop = new Properties();
//		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"//src//main//java//framework//resources//GlobalData.properties");
//		prop.load(fis);
//		String browser = prop.getProperty("browser");
//        if(browser.equalsIgnoreCase("chrome"))
//        {
//        	WebDriverManager.chromedriver().setup();
//            driver = new ChromeDriver();
//            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));//implicitly wait
//            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));//explicit wait
//            driver.manage().window().maximize();
//        }
//        return driver;
//	}
	
	public WebDriver initializeDriver() throws IOException
	{
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"//src//main//java//framework//resources//GlobalData.properties");
		prop.load(fis);

		String browser = System.getProperty("browser")!=null ? System.getProperty("browser") : prop.getProperty("browser");

        if(browser.equalsIgnoreCase("chrome"))
        {
        	WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));//implicitly wait
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));//explicit wait
            driver.manage().window().maximize();
        }
        else if(browser.equalsIgnoreCase("firefox"))
        {
        	
        }
        return driver;
	}
	
	@BeforeMethod(alwaysRun=true)
	public LandingPage launchApplication() throws IOException
	{
		driver = initializeDriver();
		landingPage = new LandingPage(driver);
		util = new utility(driver);
        landingPage.openApplication();
        return landingPage;
	}
	
	@AfterMethod(alwaysRun=true)
	public void tearDown()
	{
		driver.close();
		if (driver != null) {
		        driver.quit();
		    };
	}
	
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
        // Read the JSON file into a String
        String jsonContent = FileUtils.readFileToString(
            new File(System.getProperty("user.dir") + filePath),
            "UTF-8"
        );

        // Convert JSON String to List of HashMaps
        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String, String>> data = mapper.readValue(
            jsonContent,
            new TypeReference<List<HashMap<String, String>>>() {}
        );

        return data;
    }
	
	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException
    {
    	TakesScreenshot ts = (TakesScreenshot)driver;
    	File source = ts.getScreenshotAs(OutputType.FILE);
    	File file = new File(System.getProperty("user.dir")+"//reports//"+testCaseName+".png");
    	FileUtils.copyFile(source,file);
    	return System.getProperty("user.dir")+"//reports//"+testCaseName+".png";
    }
}
