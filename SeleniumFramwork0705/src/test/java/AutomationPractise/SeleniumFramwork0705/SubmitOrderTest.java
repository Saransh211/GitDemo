package AutomationPractise.SeleniumFramwork0705;

import org.testng.Assert;
import org.testng.annotations.*;

import AutomationPractise.TestComponents.BaseTest;
import framework.pageObjects.CartPage;
import framework.pageObjects.LandingPage;
import framework.pageObjects.OrderConfirmationPage;
import framework.pageObjects.OrderPage;
import framework.pageObjects.ProductCatalogue;
import framework.pageObjects.ShippingPage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.poi.ss.usermodel.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import testdata.data.dataFromExcel;

public class SubmitOrderTest extends BaseTest {
	String product1 = "ZARA COAT 3";
	String product2 = "IPHONE 13 PRO";
	static Set<String> tags = new HashSet<>();
    @Test(dataProvider="getData",groups= {"Purchase"})
    public void submitOrder(HashMap<String, String> input) throws InterruptedException, IOException {
	        ProductCatalogue onproductCatalogue = landingPage.loginApplication(input.get("email"),input.get("pass"));
	        onproductCatalogue.selectProducts(input.get("product1"),input.get("product2"));  
	        CartPage onCartPage = landingPage.openCart();
	        ShippingPage onShippingPage = onCartPage.verifyCart();
	        OrderConfirmationPage onOrderConfirmationPage = onShippingPage.AddShippingDetails();
	        onOrderConfirmationPage.VerifyOrderConfirmation();
	  }
    
    @Test(dependsOnMethods = {"submitOrder"})
    public void verifyOrderHistory() 
    {
    	ProductCatalogue onproductCatalogue = landingPage.loginApplication("urfi@gmail.com","urfi@1234");
    	OrderPage onOrderPage = util.gotoOrdersPage();
    	System.out.println("Executed tilllin 45");
    	Boolean orderPresent = onOrderPage.getOrder(product1);
    	System.out.println("orderPresent - "+orderPresent);
    	Assert.assertTrue(orderPresent,product1+" is not present on the Order Page.");
    }
    
    @Test
//    public void getDataPrinted() throws IOException
//    {
//    	dataFromExcel dfe = new dataFromExcel();
//    	System.out.println("Locaiton - "+dfe.getData());
//    }
//    
    @DataProvider
    public Object[][] getData() throws IOException
    {
    	HashMap<String, String> map = new HashMap<String, String>();
//    	map.put("email","urfi@gmail.com");
//    	map.put("pass","urfi@1234");
//    	map.put("product1","ZARA COAT 3");
//    	map.put("product2","IPHONE 13 PRO");
    	List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//testdata//data//PurchaseOrder.json");
    	return new Object[][] {{data.get(0)},{data.get(1)}};//using object as it can handle all type of datatype
    }
    
//    @Test(dataProvider="driveTest")
//    public void printData(String TestCase, String Data1, String Data2, String Data3)
//    {
//    	System.out.println(TestCase+" "+Data1+" "+Data2+" "+Data3);
//    }
    
	static DataFormatter formatter = new DataFormatter();
   // @DataProvider(name="driveTest")
    public static Set<String> getTagsToRun() throws IOException
    {
    	//get access to the excel sheet
    	FileInputStream fis = new FileInputStream("D:\\eclipse\\eclipse-workspace\\ExcelDataSheet\\src\\test\\java\\TestData\\data.xlsx");
    	XSSFWorkbook workbook = new XSSFWorkbook(fis);//create an object to get store the excel sheet    	
    	int numberOfSheets = workbook.getNumberOfSheets();//get the total number of sheets
    	System.out.println("numberOfSheets - "+numberOfSheets);
    	for(int i=0;i<numberOfSheets;i++)//run the loop till the number of sheets in the excel file
    	{
    		if(workbook.getSheetAt(i).getSheetName().equalsIgnoreCase("testData"))//check each sheet name in the excel file
    		{
    			XSSFSheet sheet = workbook.getSheetAt(i);//get the sheet
    			
    			int rowCount = sheet.getPhysicalNumberOfRows();//get total number of rows
    			System.out.println("rowCount - "+rowCount);
    			XSSFRow firstRow = sheet.getRow(0);//get the first row
    			
    			int colCount = firstRow.getLastCellNum();//number of total columns
    			
    			Iterator<Cell> cl = firstRow.cellIterator();//to get the cell of the firstRow
    			int runColIndex = -1;
    	        for (int j= 0; j < colCount; j++)//run loop till the 
    	        {
    	            String header = formatter.formatCellValue(firstRow.getCell(j));//get the firstRow each cell
    	            if (header.equalsIgnoreCase("Run")) {
    	            	runColIndex = j;//save the index of the Run heading column
    	                break;
    	            }
    	        }
    			System.out.println("Run col index - "+runColIndex);
    			if (runColIndex == -1) {
    	            workbook.close();
    	            throw new RuntimeException("Column 'Run' not found in header row.");
    	        }
    			 // Use List to store matching rows
    	        List<Object[]> filteredData = new ArrayList<>();

    	        for (int k = 1; k < rowCount; k++) //k=1 as first row is heading
    	        {
    	            XSSFRow row = sheet.getRow(k);//get each row
    	            if (row == null) continue;

    	            String runValue = formatter.formatCellValue(row.getCell(runColIndex));//getting run col for each row
    	            if (runValue.equalsIgnoreCase("Y")) {
    	            	String testCase = formatter.formatCellValue(row.getCell(0));
    	            	tags.add("@" + testCase);//update test case name as tags
    	                Object[] rowData = new Object[colCount - 1]; // Exclude "Run" column
    	                int colIndex = 0;
    	                for (int j = 0; j < colCount; j++) {
    	                    if (j == runColIndex) continue; // Skip "Run" column
    	                    rowData[colIndex++] = formatter.formatCellValue(row.getCell(j));
    	                }
    	                filteredData.add(rowData);
    	            }
    	        }
    	        workbook.close();

    	        // Convert List to Object[][]
    	        Object[][] data = new Object[filteredData.size()][];
    	        for (int j = 0; j < filteredData.size(); j++) {
    	            data[j] = filteredData.get(j);
    	        }
    	        return tags;
    		}
    	}
		return null;
    }
}
