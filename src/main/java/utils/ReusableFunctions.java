package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import baseClass.LibraryClass;


public class ReusableFunctions {
	Logger log;

    public ReusableFunctions() {
        this.log = LogManager.getLogger(ReusableFunctions.class);
    }
    
    public ReusableFunctions(WebDriver driver) {
    	LibraryClass.driver = driver;
        this.log = LogManager.getLogger(ReusableFunctions.class);
    }
    
    public void enterText(WebElement element, String value) {
        try {
            element.clear();
            element.sendKeys(value);
            log.info(" enterText(): Entered '" + value + "' in field.");
        } catch (Exception e) {
            log.error(" enterText(): Failed to enter '" + value + "' - " + e.getMessage());
            Assert.fail("Failed to enter text using sendText()");
        }
    }
    
    public void selectDropDownOption(WebElement element, String value) {
        try {
        	Select sel = new Select(element);
        	sel.selectByVisibleText(value);
            log.info(" selectDropDownOption(): Entered '" + value + "' in field.");
        } catch (Exception e) {
            log.error(" selectDropDownOption(): Failed to enter '" + value + "' - " + e.getMessage());
            Assert.fail("Failed to enter text using sendText()");
        }
    }
    
    public void clickElement(WebElement element, String elementName) {
        try {
            element.click();
            log.info(" Clicked on: " + elementName);
        } catch (Exception e) {
            log.error(" Click failed on: " + elementName + " - " + e.getMessage());
            Assert.fail("Click failed on: " + elementName);
        }
    }
    
    public String getElementText(WebElement element, String fieldName) {
        try {
            String text = element.getText().trim();
            log.info(" Text retrieved from " + fieldName + ": " + text);
            return text;
        } catch (Exception e) {
            log.error(" Could not retrieve text from " + fieldName + ": " + e.getMessage());
            return "";
        }
    }
    
    public void verifyTitle(String expectedTitle) {
        try {
            String actualTitle = LibraryClass.getDriver().getTitle();
            Assert.assertEquals(actualTitle, expectedTitle, "❌ Title mismatch");
            log.info("Title matched: " + actualTitle);
        } catch (AssertionError e) {
            log.error("Title mismatch. Expected: " + expectedTitle + ", Actual: " + LibraryClass.getDriver().getTitle());
            throw e;
        }
    }
    
    public boolean isElementDisplayed(WebElement element) {
    	try {
			return element.isDisplayed();
		}
    	catch(NoSuchElementException nse) {
    		return false;
    	}
    	catch (Exception e) {
			return false;
		}
    }
    
}
