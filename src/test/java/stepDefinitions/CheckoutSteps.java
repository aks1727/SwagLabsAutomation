package stepDefinitions;

import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import baseClass.LibraryClass;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.Checkout_CompletePage;
import pages.Checkout_OverviewPage;
import pages.Checkout_YouInformationPage;

public class CheckoutSteps {
    
    private WebDriver driver = LibraryClass.driver;
    private Properties prop = LibraryClass.prop;
    private static final Logger logger = LogManager.getLogger(CheckoutSteps.class); // updated to CheckoutSteps.class
    
    Checkout_OverviewPage overview;
    Checkout_CompletePage complete;
    Checkout_YouInformationPage information;
    
    @When("Enter {string}, {string} and {string} in YourInformation Page and click on Continue")
    public void enter_and_in_your_information_page_and_click_on_continue(String firstName, String lastName, String postalCode) {
        String stepOneUrl = prop.getProperty("checkoutStepOneUri");
        logger.info("Navigating to Checkout Step One page: {}", stepOneUrl);
        driver.navigate().to(stepOneUrl);
        
        information = new Checkout_YouInformationPage(driver);
        logger.info("Entering user information - FirstName: {}, LastName: {}, PostalCode: {}", firstName, lastName, postalCode);
        information.enterFirstName(firstName);
        information.enterLastName(lastName);
        information.enterPostalCode(postalCode);
        
        logger.info("Clicking Continue button on YourInformation Page");
        information.clickContinue();
        logger.info("Continue button clicked");
    }

    @Then("Verify Details on Overview page and click on Finish")
    public void verify_details_on_overview_page_and_click_on_finish() {
        String stepTwoUrl = prop.getProperty("checkoutStepTwoUri");
        logger.info("Navigating to Checkout Overview page: {}", stepTwoUrl);
        driver.navigate().to(stepTwoUrl);
        
        overview = new Checkout_OverviewPage(driver);
        
        logger.info("Verifying Overview page details...");
        Assert.assertTrue(overview.isVisibleCheckoutTitle(), "Checkout title not visible");
        Assert.assertTrue(overview.isVisiblePaymentInfo(), "Payment info not visible");
        Assert.assertTrue(overview.isVisibleShippingInfo(), "Shipping info not visible");
        Assert.assertTrue(overview.isVisibleSubTotal(), "Subtotal not visible");
        Assert.assertTrue(overview.isVisibleTax(), "Tax not visible");
        Assert.assertTrue(overview.isVisibleTotal(), "Total not visible");
        logger.info("All Overview page details verified successfully");
    }

    @Then("Verify the confimation message")
    public void verify_the_confimation_message() {
        String completeUrl = prop.getProperty("checkoutCompleteUri");
        logger.info("Navigating to Checkout Complete page: {}", completeUrl);
        driver.navigate().to(completeUrl);
        
        complete = new Checkout_CompletePage(driver);
        logger.info("Verifying Checkout Complete page elements...");
        
        Assert.assertTrue(complete.isVisibleCheckoutCompleteTitle(), "Checkout complete title not visible");
        Assert.assertTrue(complete.isOrderConfirmationLogoDisplayed(), "Order confirmation logo not visible");
        String thankYouMsg = complete.getThankYouMessage();
        logger.info("Thank You message on page: {}", thankYouMsg);
        Assert.assertEquals(thankYouMsg, "Thank you for your order!", "Thank you message mismatch");
        Assert.assertTrue(complete.isDispatchedMessageDisplayed(), "Dispatched message not visible");
        
        logger.info("Checkout Complete page verified successfully");
    }
}
