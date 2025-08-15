package stepDefinitions;

import java.util.Map;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import baseClass.LibraryClass;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.LoginPage;
import utils.ExcelReader;

public class LoginSteps {
    
    private WebDriver driver = LibraryClass.driver;
    private Properties prop = LibraryClass.prop;
    private static final Logger logger = LogManager.getLogger(LoginSteps.class); // updated to LoginSteps.class
    
    LoginPage lp;
    
    @Given("Navigate to Swag Labs Login page")
    public void navigate_to_swag_labs_login_page() {
        String websiteURL = prop.getProperty("uri");
        logger.info("Navigating to Swag Labs Login page at URL: {}", websiteURL);
        driver.navigate().to(websiteURL);
        lp = new LoginPage(driver);
        logger.info("Application Launched and LoginPage object initialized");
    }

    @When("Login by entering {string} and {string}")
    public void enter_and(String username, String pass) {
        logger.info("Entering Username: {} and Password: {}", username, pass);
        lp.enterUsername(username);
        lp.enterPassword(pass);
        logger.info("Clicking Login button");
        lp.clickLoginButton();
        logger.info("Login button clicked");
    }
    
    @When("Enter username and password from row {string}")
    public void enter_username_and_password_from_row(String rowNum) {
        int rowIdx = Integer.parseInt(rowNum);
        logger.info("Fetching login data from Excel row: {}", rowIdx);
        Map<String, String> rowData = ExcelReader.getLoginData("Valid", rowIdx);
        
        if (rowData == null) {
            logger.error("No data found in Excel for row: {}", rowIdx);
            throw new RuntimeException("No data found for row: " + rowIdx);
        }
        
        String username = rowData.get("username");
        String password = rowData.get("password");
        logger.info("Excel data - Username: {}, Password: {}", username, password);
        
        lp.enterUsername(username);
        lp.enterPassword(password);
        logger.info("Clicking Login button with Excel data");
        lp.clickLoginButton();
        logger.info("Login attempt completed using Excel data");
    }

    @Then("Validate if redirected to product page")
    public void validate_if_redirected_to_product_page() {
        logger.info("Validating if redirected to Product Page");
        boolean error = lp.isErrorMessageVisible();
        if(error) {
            logger.error("BUG: Error found with message: {}", lp.showErrorMessage());
        } else {
            logger.info("Successfully redirected to Product Page, no errors found");
        }
        Assert.assertFalse(error, "BUG: Error found - " + lp.showErrorMessage());
    }

}
