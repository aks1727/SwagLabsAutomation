package stepDefinitions;

import org.testng.Assert;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import baseClass.LibraryClass;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.ProductPage;
import utils.ExcelReader;

public class ProductSteps {

    private WebDriver driver = LibraryClass.driver;
    ProductPage productPage;
    private static final Logger logger = LogManager.getLogger(ProductSteps.class);
    List<Map<String, String>> products;
    Properties prop = LibraryClass.prop;
    int ExcelproductCount = 0;

    @When("Click on Add to cart of the products from rows {string}")
    public void click_on_the_products_of_rows(String rows) throws InterruptedException {

        productPage = new ProductPage(driver);

        List<Integer> rowNumbers = Arrays.stream(rows.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        products = ExcelReader.getProductDetails("Products", rowNumbers);

        logger.info("Rows selected from Excel: {}", rowNumbers);
        logger.info("Total products found in Excel: {}", products.size());

        for (Map<String, String> product : products) {
            String productName = product.get("ProductName");
            productPage.addProductToCart(productName);
            ExcelproductCount++;
            logger.info("Product added to cart: {}", productName);
        }
    }
    

	@When("Click on Remove the products from rows {string}")
	public void click_on_remove_the_products_from_rows(String rows) throws InterruptedException {
		driver.navigate().to(prop.getProperty("cartUri"));
		List<Integer> rowNumbers = Arrays.stream(rows.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
		products = ExcelReader.getProductDetails("Products", rowNumbers);
		logger.info("Rows selected from Excel: {}", rowNumbers);
        logger.info("Total products found in Excel: {}", products.size());
        
        for (Map<String, String> product : products) {
            String productName = product.get("ProductName");
            productPage.removeProductFromCart(productName);

            ExcelproductCount--;
            logger.info("Product removed from cart: {}", productName);
        }
        
	}


    @Then("Validate the product count of cart")
    public void validate_the_product_count_of_cart() {
        int cartProductCount = productPage.getProductCount();
        logger.info("Expected product count from Excel: {}", ExcelproductCount);
        logger.info("Actual product count in cart: {}", cartProductCount);

        if (cartProductCount == ExcelproductCount) {
            logger.info("✅ Product count in cart matches the expected count.");
        } else {
            logger.error("❌ Product count mismatch! Expected: {}, Found: {}", ExcelproductCount, cartProductCount);
        }

        Assert.assertEquals(ExcelproductCount, cartProductCount, 
                "BUG: Some products not added to cart or count mismatch");
    }
}
