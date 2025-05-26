package AutomationPractise.TestComponents;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import framework.resources.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener{
	ExtentTest test;
	ExtentReports extent = ExtentReporterNG.getREportObject();
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal();
	public void onTestStart(ITestResult result)
	{
		test = extent.createTest(result.getMethod().getMethodName());//to create an entry for a test
		extentTest.set(test);
	}
	
	public void onTestFailure(ITestResult result)
	{
		String filePath = null;
		extentTest.get().fail(result.getThrowable());
		
		try
		{
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());//to give the life to the driver
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		try {
			filePath = getScreenshot(result.getMethod().getMethodName(),driver);
		} catch (IOException e) {
			e.printStackTrace();
		}
		extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
	}
		
	public void onFinish(ITestContext context)
	{
		extent.flush();//to generate the report
	}
}
