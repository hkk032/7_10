package _10Store.pageObjects;

import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import _10Store.abstractComponents.*;

public class ProductResult extends abstractComponents {

	WebDriver driver;
	public ProductResult(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="#boost-pfs-filter-tree-pf-pt-product-type li:nth-child(2) .boost-pfs-check-box")
	WebElement highTopCheck;
	
	@FindBys(
	@FindBy(className="grid-product__title"))
	List<WebElement> products;
	
	@FindBy(css="a[data-product-id='7303752646837']")
	WebElement finalProduct;
	
	Iterator itr = products.iterator();
	String prodName;
	List<WebElement> c;
	
	public void selectProduct(String pName) {		
		while(itr.hasNext()) {
			WebElement name;
			try {
				name = (WebElement) itr.next();	
				prodName = name.getText();
				System.out.println(prodName);
				if(prodName.equalsIgnoreCase(pName)) {
					finalProduct.click();
					break;
				}
			}
			catch(StaleElementReferenceException ex) {
				products = driver.findElements(By.className("grid-product__title"));
				itr = products.iterator();
				name = (WebElement) itr.next();
				prodName = name.getText();
				System.out.println(prodName);
				if(prodName.equalsIgnoreCase(pName)) {
					finalProduct.click();
					break;
				}
			}
		}
	}
	
	public ProductInfo prodDetails() {
		ProductInfo pi = new ProductInfo(driver);
		return pi;
	}
	
}