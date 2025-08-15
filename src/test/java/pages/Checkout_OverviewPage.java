package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.ReusableFunctions;

public class Checkout_OverviewPage {

	
	WebDriver driver;
	ReusableFunctions rf;
	
	public Checkout_OverviewPage(WebDriver driver) {
		this.driver = driver;
		rf = new ReusableFunctions(driver);
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(xpath="//span[@class='title']") public WebElement checkoutTitle;
	@FindBy(xpath="//div[@data-test='payment-info-value']") public WebElement paymentInfo;
	@FindBy(xpath="//div[@data-test='shipping-info-value']") public WebElement shippingInfo;
	@FindBy(xpath="//div[@class='summary_subtotal_label']") public WebElement subTotal;
	@FindBy(xpath="//div[@class='summary_tax_label']") public WebElement tax;
	@FindBy(xpath="//div[@class='summary_total_label']") public WebElement Total;
	@FindBy(xpath="//button[@id='finish']") public WebElement finish;
	@FindBy(xpath="//button[@id='cancel']") public WebElement cancel;
	
	public boolean isVisibleCheckoutTitle() {
        return rf.isElementDisplayed(checkoutTitle);
    }

    public boolean isVisiblePaymentInfo() {
        return rf.isElementDisplayed(paymentInfo);
    }

    public boolean isVisibleShippingInfo() {
        return rf.isElementDisplayed(shippingInfo);
    }

    public boolean isVisibleSubTotal() {
        return rf.isElementDisplayed(subTotal);
    }

    public boolean isVisibleTax() {
        return rf.isElementDisplayed(tax);
    }

    public boolean isVisibleTotal() {
        return rf.isElementDisplayed(Total);
    }

    public void clickFinish() {
        rf.clickElement(finish, "Finish Button");
    }

    public void clickCancel() {
        rf.clickElement(cancel, "Cancel Button");
    }
}
