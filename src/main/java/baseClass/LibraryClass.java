package baseClass;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LibraryClass {

    public static WebDriver driver;

    public static Logger log = LogManager.getLogger(LibraryClass.class);

	 public static Properties prop;

    static {
    	try {
            prop = new Properties();
            FileInputStream ip = new FileInputStream("src/test/resources/conf/config.properties");
            prop.load(ip);
        } catch (IOException e) {
            System.out.println("Failed to load config.properties: " + e.getMessage());
        }
    }

    public static WebDriver getDriver() {
    	return driver;
    }
    
    public static String getPropValue(String key) {
    	return prop.getProperty(key);
    }
    
    public static WebDriver launchRemotelyOnSeleniumGrid() throws MalformedURLException {
    	DesiredCapabilities cap = new DesiredCapabilities();
    	
    	cap.setBrowserName(prop.getProperty("remoteBrowser"));
    	String platform = prop.getProperty("remotePlatform");
    	if(platform.equalsIgnoreCase("windows")) cap.setPlatform(Platform.WIN11);
    	else if(platform.equalsIgnoreCase("mac")) cap.setPlatform(Platform.MAC);
    	else cap.setPlatform(Platform.LINUX);
    	
    	driver = new RemoteWebDriver(
    			new URL(prop.getProperty("remoteURL")),
    			cap
    		);
    	return driver;
    	

    }
    
    public static WebDriver launchRemotelyOnSauceLabs() throws MalformedURLException {
        String sauceUserName = System.getenv("SAUCE_USERNAME");
        String sauceAccessKey = System.getenv("SAUCE_ACCESS_KEY");

        if (sauceUserName == null || sauceAccessKey == null) {
            throw new RuntimeException("Sauce Labs credentials are not set in environment variables.");
        }

        // Sauce Labs specific options
        MutableCapabilities sauceOptions = new MutableCapabilities();
        sauceOptions.setCapability("build", "SwagLabs Automation Build");
        sauceOptions.setCapability("name", "Validate Login Test");

        // Browser capabilities (W3C format)
        MutableCapabilities caps = new MutableCapabilities();
        caps.setCapability("browserName", prop.getProperty("remoteBrowser")); // e.g. chrome
        caps.setCapability("browserVersion", "latest");
        caps.setCapability("platformName", "Windows 11");
        caps.setCapability("sauce:options", sauceOptions);

        String sauceURL = "https://" + sauceUserName + ":" + sauceAccessKey +"@ondemand.eu-central-1.saucelabs.com/wd/hub";
//        System.out.println(sauceURL);
        driver = new RemoteWebDriver(new URL(sauceURL), caps);
        return driver;
    }




    public static WebDriver launchLocally() {
    	String browserName = prop.getProperty("localbrowser");

        if (browserName.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
            driver.manage().window().maximize();
        } else {
            System.out.println("Browser not supported: " + browserName);
        }

        return driver;
    }
    
    
    public static WebDriver launchForDockerEnv() {
    	String browserName = prop.getProperty("localbrowser");

        if (browserName.equalsIgnoreCase("chrome")) {
        	ChromeOptions options = new ChromeOptions();
        	options.addArguments("--headless");
        	options.addArguments("--no-sandbox");
        	options.addArguments("--disable-dev-shm-usage");

            driver = new ChromeDriver(options);
            driver.manage().window().maximize();
        } else {
            System.out.println("Browser not supported: " + browserName);
        }

        return driver;
    }
    
    public WebDriver launchBrowser() throws MalformedURLException {
    	String env = prop.getProperty("env");
    	if(env.equalsIgnoreCase("remote")) {
    		return launchRemotelyOnSeleniumGrid();
    	}
    	else if(env.equalsIgnoreCase("saucelabs")) {
    		return launchRemotelyOnSauceLabs();
    	}
    	else if(env.equalsIgnoreCase("docker")){
    		return launchForDockerEnv();
    	}
    	else {
    		return launchLocally();
    	}
        
    }
    
    public static String captureScreenshot(String testName) {
        String path = "";
        try {
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            path = System.getProperty("user.dir") + "/target/screenshots/" + testName + "_" + timestamp + ".png";
            FileUtils.copyFile(src, new File(path));
            log.info("Screenshot saved: " + path);
        } catch (IOException e) {
            log.error("Screenshot failed: " + e.getMessage(), e);
        }
        return path;
    }


  
    
    public static void waitForElementVisible(WebElement element, int timeout) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
            wait.until(ExpectedConditions.visibilityOf(element));
            log.info("Element visible: " + element);
        } catch (Exception e) {
            log.error("Wait failed: Element not visible - " + e.getMessage(), e);
        }
    }


    public void waitForElementClickable(WebElement element, int timeout) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
            wait.until(ExpectedConditions.elementToBeClickable(element));
            log.info("Element clickable: " + element);
        } catch (Exception e) {
            log.error("Wait failed: Element not clickable - " + e.getMessage(), e);
        }
    }

    public boolean isElementDisplayed(WebElement element) {
        try {
            boolean visible = element.isDisplayed();
            log.info("Element displayed: " + visible);
            return visible;
        } catch (Exception e) {
            log.error("Element not displayed: " + e.getMessage(), e);
            return false;
        }
    }
    
    

    public void closeBrowser() {
        try {
            if (driver != null) {
            	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
                driver.quit();
                log.info("Browser closed.");
            }
        } catch (Exception e) {
            log.error("Failed to close browser: " + e.getMessage(), e);
        }
    }

	public static void waitForElementToBeEqual(WebElement element, int timeout, int count) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeout));
		wait.until(driver -> Integer.parseInt(element.getText()) == count);
				
	}

    
}
