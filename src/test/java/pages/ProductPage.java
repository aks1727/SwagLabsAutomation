package pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.ReusableFunctions;

public class ProductPage {

	WebDriver driver;
	ReusableFunctions rf;
	
	public ProductPage(WebDriver driver) {
		this.driver = driver;
		rf = new ReusableFunctions(driver);
		PageFactory.initElements(driver, this);
	}
	
	

	@FindBy(xpath="//button[@id='add-to-cart-sauce-labs-backpack']") public WebElement addToCartBackPack;
	@FindBy(xpath="//button[@id='add-to-cart-sauce-labs-bike-light']") public WebElement addToCartBikeLight;
	@FindBy(xpath="//button[@id='add-to-cart-sauce-labs-bolt-t-shirt']") public WebElement addToCartBoltTshirt;
	@FindBy(xpath="//button[@id='add-to-cart-sauce-labs-fleece-jacket']") public WebElement addToCartFleeceJacket;
	@FindBy(xpath="//button[@id='add-to-cart-sauce-labs-onesie']") public WebElement addToCartOnesie;
	@FindBy(xpath="//button[@id='add-to-cart-test.allthethings()-t-shirt-(red)']") public WebElement addToCartRedTshirt;
	@FindBy(xpath="//img[@alt='Sauce Labs Backpack']") public WebElement BackpackImg;
	@FindBy(xpath="//img[@alt='Sauce Labs Bike Light']") public WebElement BikeLightImg;
	@FindBy(xpath="//img[@alt='Sauce Labs Fleece Jacket']") public WebElement FleeceJacketImg;
	@FindBy(xpath="//img[@alt='Sauce Labs Bolt T-Shirt']") public WebElement TShirtImage;
	@FindBy(xpath="//img[@alt='Sauce Labs Onesie']") public WebElement OnesieImg;
	@FindBy(xpath="//img[@alt='Test.allTheThings() T-Shirt (Red)']") public WebElement redTshirtImg;
	@FindBy(xpath="//a[@class='shopping_cart_link']") public WebElement cartIcon;
	@FindBy(xpath="//span[@class='title']") public WebElement products;
	@FindBy(xpath="//button[@id='remove-sauce-labs-backpack']") public WebElement removeBackPack;
	@FindBy(xpath="//button[@id='remove-sauce-labs-bike-light']") public WebElement removeBikeLight;
	@FindBy(xpath="//button[@id='remove-sauce-labs-fleece-jacket']") public WebElement removeFleeceJacket;
	@FindBy(xpath="//button[@id='remove-sauce-labs-bolt-t-shirt']") public WebElement removeBoltTshirt;
	@FindBy(xpath="//button[@id='remove-test.allthethings()-t-shirt-(red)']") public WebElement removeRedTshirt;
	@FindBy(xpath="//button[@id='remove-sauce-labs-onesie']") public WebElement removeOnesie;
	@FindBy(xpath="//select[@class='product_sort_container']") public WebElement sortDropDown;
	@FindBy(xpath="//span[@class='shopping_cart_badge']") public WebElement productCount;
	
	public void addProductToCart(String productName) {
	    if (productName.equalsIgnoreCase("Back Pack")) {
	        rf.clickElement(addToCartBackPack, "Add to Cart - Back Pack");
	    } else if (productName.equalsIgnoreCase("Bike Light")) {
	        rf.clickElement(addToCartBikeLight, "Add to Cart - Bike Light");
	    } else if (productName.equalsIgnoreCase("Bolt T-Shirt")) {
	        rf.clickElement(addToCartBoltTshirt, "Add to Cart - Bolt T-Shirt");
	    } else if (productName.equalsIgnoreCase("Fleece Jacket")) {
	        rf.clickElement(addToCartFleeceJacket, "Add to Cart - Fleece Jacket");
	    } else if (productName.equalsIgnoreCase("Onesie")) {
	        rf.clickElement(addToCartOnesie, "Add to Cart - Onesie");
	    } else if (productName.equalsIgnoreCase("Red T-Shirt")) {
	        rf.clickElement(addToCartRedTshirt, "Add to Cart - Red T-Shirt");
	    } else {
	        throw new IllegalArgumentException("Unknown product: " + productName);
	    }
	}

	public void removeProductFromCart(String productName) {
	    if (productName.equalsIgnoreCase("Back Pack")) {
	        rf.clickElement(removeBackPack, "Remove from Cart - Back Pack");
	    } else if (productName.equalsIgnoreCase("Bike Light")) {
	        rf.clickElement(removeBikeLight, "Remove from Cart - Bike Light");
	    } else if (productName.equalsIgnoreCase("Bolt T-Shirt")) {
	        rf.clickElement(removeBoltTshirt, "Remove from Cart - Bolt T-Shirt");
	    } else if (productName.equalsIgnoreCase("Fleece Jacket")) {
	        rf.clickElement(removeFleeceJacket, "Remove from Cart - Fleece Jacket");
	    } else if (productName.equalsIgnoreCase("Onesie")) {
	        rf.clickElement(removeOnesie, "Remove from Cart - Onesie");
	    } else if (productName.equalsIgnoreCase("Red T-Shirt")) {
	        rf.clickElement(removeRedTshirt, "Remove from Cart - Red T-Shirt");
	    } else {
	        throw new IllegalArgumentException("Unknown product: " + productName);
	    }
	}

    public boolean isProductTitleDisplayed() {
        return rf.isElementDisplayed(products);
    }

    public boolean isBackpackImageDisplayed() {
        return rf.isElementDisplayed(BackpackImg);
    }

    public boolean isBikeLightImageDisplayed() {
        return rf.isElementDisplayed(BikeLightImg);
    }

    public boolean isFleeceJacketImageDisplayed() {
        return rf.isElementDisplayed(FleeceJacketImg);
    }

    public boolean isTshirtImageDisplayed() {
        return rf.isElementDisplayed(TShirtImage);
    }

    public boolean isOnesieImageDisplayed() {
        return rf.isElementDisplayed(OnesieImg);
    }

    public boolean isRedTshirtImageDisplayed() {
        return rf.isElementDisplayed(redTshirtImg);
    }

    public void sortProductsByValue(String value) {
    	rf.selectDropDownOption(sortDropDown,value);
    }
    
    
    public int getProductCount() {
    	if(rf.isElementDisplayed(productCount)) {
    		return Integer.parseInt(productCount.getText());
    	}
    	else {
    		return -1;
    	}
    }
    
    public void waitForCartCountToBe(int expectedCount) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(driver -> getProductCount() == expectedCount);
    }

}
