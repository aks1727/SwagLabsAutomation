package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.ReusableFunctions;

public class LoginPage {
	
	WebDriver driver;
	ReusableFunctions rf;
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		rf = new ReusableFunctions(driver);
		PageFactory.initElements(driver,this);
	}
	
	

	@FindBy(xpath="//div[@class='login_logo']") public WebElement swagLabs;
	@FindBy(xpath="//input[@id='user-name']") public WebElement username;
	@FindBy(xpath="//input[@id='password']") public WebElement password;
	@FindBy(xpath="//input[@id='login-button']") public WebElement login_button;
	@FindBy(xpath="//h3[@data-test='error']") public WebElement errorBox;
	public String getSwagLabsText() {
		return rf.getElementText(swagLabs, "Swag Lab");
	}
	
	public void enterUsername(String user) {
		rf.enterText(username, user);
	}
	
	public void enterPassword(String pass) {
		rf.enterText(password, pass);
	}
	
	public void clickLoginButton() {
		rf.clickElement(login_button,"Login Button");
	}
	
	public void login(String user, String pass) {
		enterUsername(user);
		enterPassword(pass);
		clickLoginButton();
	}
	
	public boolean isErrorMessageVisible() {
		return rf.isElementDisplayed(errorBox);
	}
	
	public String showErrorMessage() {
		if(isErrorMessageVisible()) {
			return errorBox.getText();
		}
		else {
			return "found the element";
		}
	}
}
