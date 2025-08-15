package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.ReusableFunctions;

public class CartPage {

	
	WebDriver driver;
	ReusableFunctions rf;
	
	public CartPage(WebDriver driver) {
		this.driver = driver;
		rf = new ReusableFunctions(driver);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//button[@id='checkout']") public WebElement checkout;
	@FindBy(xpath="//button[@id='continue-shopping']") public WebElement continueShopping;
	@FindBy(xpath="//span[@class='title']") public WebElement yourCart;
	
	
	public void clickCheckout() {
        rf.clickElement(checkout, "Click Checkout");
    }
	
	public void clickContinueShopping() {
        rf.clickElement(continueShopping, "Click Continue Shopping");
    }
	
	public boolean isYourCartTitleDisplayed() {
        return rf.isElementDisplayed(yourCart);
    }
	
}
