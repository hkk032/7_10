package _10Store.testComponents;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import _10Store.data.DataReader;
import _10Store.pageObjects.BuyerInfo;
import _10Store.pageObjects.CartDetails;
import _10Store.pageObjects.ProductInfo;
import _10Store.pageObjects.ProductResult;

public class EndRun extends VerifySetup {
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
		
		CartDetails cd = pi.cart();
		cd.loadCart();
		cd.cartCheckout();
		
		BuyerInfo bi = cd.custDetails();
		bi.fillDetails();
		bi.payment();
	}
	
	@DataProvider
	public Object[][] prodData() throws IOException {
//		User data reader to get required product name and click on it
		DataReader dr = new DataReader();
		List<HashMap<String, String>> map = dr.getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\_10Store\\data\\productDetails.json");
		return new Object[][] {{map.get(0)}};
	}
}
