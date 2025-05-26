package testdata.data;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

public class dataFromExcel {
	
	@Test
	public static void getData() throws IOException
	{
		//Get access to the excel file
		FileInputStream fis = new FileInputStream("D:\\eclipse\\eclipse-workspace\\ExcelDataSheet\\src\\test\\java\\TestData\\data.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		
		//get number of sheets in the excel file
		int sheets = workbook.getNumberOfSheets();
		//iterate to the number of sheets to get the name of the sheet
		for(int i=0;i<sheets;i++)
		{
			//compare the name of each of the sheet 
			if(workbook.getSheetAt(i).getSheetName().equalsIgnoreCase("testData"))
			{
				//get the desired sheet
				XSSFSheet sheet = workbook.getSheetAt(i);
				
				//create an iterator to iterate through the rows
				Iterator<Row> rows = sheet.iterator();
				
				//get the first row
				Row firstRow = rows.next();
				
				//create an iterator to iterate through the cells
				Iterator<Cell> cell = firstRow.cellIterator();
				
				int k = 0;
				int col = 0;
				//run the while loop till the next cell is present
				while(cell.hasNext())
				{
					Cell value = cell.next();//assigning the next cell to the value
					if(value.getStringCellValue().equalsIgnoreCase("TestCase"))//get the cell value and check whether it is Data2
					{
						col = k;//if the cell value is data2 take the column number
						break;
					}
					k++;//increment k
				}
				System.out.println("Data2 Location - "+col);
				//return col;
				while(rows.hasNext())//run the while loop till next row is present
				{
					//System.out.println("1");
					Row value = rows.next();//assigning next row to value
					if(value.getCell(k).getStringCellValue().equalsIgnoreCase("Purchase"))//in the row get the column number k then get cell value
					{
						//System.out.println("2");
						Iterator<Cell> cv = value.cellIterator();//if the cell value is Purchase get other cells of the row
						while(cv.hasNext())
						{
							//System.out.println("3");
							Cell c = cv.next();//get the cell and put it in c
							if(c.getCellType()==CellType.STRING)//check what type of value is stored in the cell
							{
								System.out.println(cv.next().getStringCellValue());
							}
							else if(c.getCellType()==CellType.NUMERIC)
							{
								System.out.println(NumberToTextConverter.toText(cv.next().getNumericCellValue()));
							}
						}
					}
				}
				//System.out.println("4");
			}
		}
		//return col;
	}


	public static void getDataByDataProvider()
	{
		
	}
}
