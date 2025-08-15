package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.ReusableFunctions;

public class Checkout_YouInformationPage {

	
	WebDriver driver;
	ReusableFunctions rf;
	
	public  Checkout_YouInformationPage(WebDriver driver) {
		this.driver = driver;
		rf = new ReusableFunctions(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//span[@class='title']") public WebElement checkoutYouInformation;
	@FindBy(xpath="//input[@id='first-name']") public WebElement firstName;
	@FindBy(xpath="//input[@id='last-name']") public WebElement lastName;
	@FindBy(xpath="//input[@id='postal-code']") public WebElement  postalCode;
	@FindBy(xpath="//input[@id='continue']") public WebElement btnContinue;
	
	 public boolean getCheckoutInformationTitle() {
	        return rf.isElementDisplayed(checkoutYouInformation);
	    }

	    public void enterFirstName(String fName) {
	        rf.enterText(firstName, fName);
	    }

	    public void enterLastName(String lName) {
	        rf.enterText(lastName, lName);
	    }

	    public void enterPostalCode(String pCode) {
	        rf.enterText(postalCode, pCode);
	    }

	    public void clickContinue() {
	        rf.clickElement(btnContinue, "Continue Button");
	    }
}
