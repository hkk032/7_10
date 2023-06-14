package _10Store.testComponents;

import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class exceldata {
	DataFormatter formatter = new DataFormatter();
	@Test(dataProvider = "dp")
	public void f(String country, String mail, String fname, String lname, String ad1, String ad2, String state, String city, String pin, String phone) {
		System.out.println(country+mail+fname+lname+ad1+ad2+state+city+pin+phone);
	}
	
	
	
	@DataProvider
	public Object[][] dp() throws IOException {
		FileInputStream fi = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\java\\_10Store\\data\\UserAddress.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fi);
		XSSFSheet addressSheet = workbook.getSheetAt(0);
//		get row count
		int rowCount = addressSheet.getPhysicalNumberOfRows();
		
//		get column count
		XSSFRow row = addressSheet.getRow(0);
		int colCount = row.getLastCellNum();
		System.out.println(colCount);
//		declaring array size as per row and column count
//		rowCount-1 bcz first row have header values not data
		Object[][] data = new Object[rowCount-1][colCount];
		for(int i=0; i<rowCount-1; i++) {
//			i+1 bcz at 0th index header values are present
			row=addressSheet.getRow(i+1);
			for(int j=0; j<colCount; j++) {
//				extracting & capturing cell data
				XSSFCell cell = row.getCell(j);
				data[i][j] = formatter.formatCellValue(cell);
//				data[i][j]=row.getCell(j);
			}
		}
		
		return data;
	}
}
