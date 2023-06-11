package _10Store.pageObjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import _10Store.abstractComponents.*;

public class CartDetails extends abstractComponents {
	
	WebDriver driver;
	public CartDetails(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(className="cart__product-name")
	List<WebElement> prodNames;
	
	@FindBy(className="cart__price")
	List<WebElement> prodPrices;
	
	@FindBy(css="input[class='js-qty__num']")
	List<WebElement> prodQty;
	
	@FindBy(xpath="//div[@class='cart__subtotal']/following-sibling::div[1]")
	WebElement quote;
	
	@FindBy(id="CartDrawerForm")
	WebElement cart;
	
	@FindBy(className="cart__checkout")
	WebElement checkout;
	
	ArrayList<String> price = new ArrayList<String>();
	ArrayList<String> qty = new ArrayList<String>();
	float total=0;
	
	public void loadCart() throws InterruptedException {
		explicitWaitToAppearWebElement(cart);
		threadSleep();
	}
	
	public void allProducts() {	
		for(WebElement link: prodPrices) {
			price.add(link.getText().split(" ")[1].trim());
			System.out.println(price);
		}
	}
	
	public void allQty() {
		for(WebElement link: prodQty) {
			qty.add(link.getAttribute("value"));
			System.out.println(qty);
		}
	}
	
	public void splitProdPrice() {
		String ff;		
		for(int i=0; i<price.size(); i++) {
			String one = price.get(i).split(",")[0];
			String two = price.get(i).split(",")[1];
			ff = one.concat(two);
			float p = Float.parseFloat(ff);
			float q = Float.parseFloat(qty.get(i));	
			total += p*q;
		}
	}
	
	public void finalQuote() {
		String finalquote = quote.getText().split(" ")[1].trim();
		String q1 = finalquote.split(",")[0];
		String q2 = finalquote.split(",")[1];
		String q = q1.concat(q2);
		float finalPrice = Float.parseFloat(q);
		Assert.assertEquals(finalPrice, total);
	}
	
	public void cartCheckout() {
		checkout.click();
	}
	
	public BuyerInfo custDetails() {
		BuyerInfo bi = new BuyerInfo(driver);
		return bi;
	}
	
}