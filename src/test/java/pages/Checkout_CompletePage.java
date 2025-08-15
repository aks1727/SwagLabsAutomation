package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.ReusableFunctions;

public class Checkout_CompletePage {

	
	WebDriver driver;
	ReusableFunctions rf;
	
	public  Checkout_CompletePage(WebDriver driver) {
		this.driver = driver;
		rf = new ReusableFunctions(driver);
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath="//span[@class='title']") public WebElement checkoutCompleteTitle;
	@FindBy(xpath="//img[@alt='Pony Express']") public WebElement orderConfirmationLogo;
	@FindBy(xpath="//h2[normalize-space()='Thank you for your order!']") public WebElement thankYouMessage;
	@FindBy(xpath="//div[@class='complete-text']") public WebElement dispatchedText;
	@FindBy(xpath="//button[@id='back-to-products']") public WebElement backHome;
	
	public boolean isVisibleCheckoutCompleteTitle() {
        return rf.isElementDisplayed(checkoutCompleteTitle);
    }

    public boolean isOrderConfirmationLogoDisplayed() {
        return rf.isElementDisplayed(orderConfirmationLogo);
    }

       
    public String getThankYouMessage() {
        return rf.getElementText(thankYouMessage, "Thank You Message");
    }

    
    public boolean isDispatchedMessageDisplayed() {
    	return rf.isElementDisplayed(dispatchedText);
    }
    
    public String getDispatchedText() {
        return rf.getElementText(dispatchedText, "Dispatched Text");
    }

    public void clickBackHome() {
        rf.clickElement(backHome, "Back Home Button");
    }
	
}
