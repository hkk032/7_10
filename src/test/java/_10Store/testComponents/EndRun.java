package _10Store.testComponents;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import _10Store.data.DataReader;
import _10Store.pageObjects.BuyerInfo;
import _10Store.pageObjects.CartDetails;
import _10Store.pageObjects.ProductInfo;
import _10Store.pageObjects.ProductResult;

public class EndRun extends VerifySetup {
	CartDetails cd;
	DataFormatter formatter = new DataFormatter();
	
	@Test(groups={"Product"})
	public void startTest() {
		home.navigate();
	}
	
	@Test(dependsOnMethods= {"startTest"}, dataProvider= "prodData", groups={"Product"})
	public void buyProduct(HashMap<String, String> name) throws InterruptedException, IOException {
		ProductResult pr = home.product();
		pr.selectProduct(name.get("name"));
		
		ProductInfo pi = pr.prodDetails();
		pi.captureSS();
		pi.toCart();
		
		cd = pi.cart();
		cd.loadCart();
		cd.cartCheckout();
	}
	
	@Test(dependsOnMethods= {"buyProduct"}, dataProvider= "aDetails", groups={"Product"})
	public void userDetails(String country, String mail, String fname, String lname, String ad1, String ad2, String state, String city, String pin, String phone) throws InterruptedException, IOException {		
		BuyerInfo bi = cd.custDetails();
		bi.fillDetails(country, mail, fname, lname, ad1, ad2, state, city, pin, phone);
		bi.payment();
	}
	
	@DataProvider
	public Object[][] prodData() throws IOException {
//		User data reader to get required product name and click on it
		DataReader dr = new DataReader();
		List<HashMap<String, String>> map = dr.getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\_10Store\\data\\productDetails.json");
		return new Object[][] {{map.get(0)}};
	}
	
	@DataProvider
	public Object[][] aDetails() throws IOException {
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
