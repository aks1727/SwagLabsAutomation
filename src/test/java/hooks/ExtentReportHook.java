package hooks;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import baseClass.LibraryClass;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import testRunner.CucumberExtentReportPlugin;

/**
 * Hooks class contains Cucumber lifecycle hooks used to handle actions
 * after each scenario, such as logging results and capturing screenshots
 * for ExtentReports.
 */
public class ExtentReportHook {

    // Instance of ExtentReports from plugin
    ExtentReports extent = CucumberExtentReportPlugin.getExtentReportInstance();

    // Instance of the current test log
    ExtentTest test;

    /**
     * This method runs after each Cucumber scenario.
     * It logs the scenario result to ExtentReports and captures a screenshot if the scenario fails.
     *
     * @param scenario The executed Cucumber scenario
     */
    @After
    public void checkScenarioStatus(Scenario scenario) {
        WebDriver driver = LibraryClass.getDriver();  // assuming static getDriver() method

        test = extent.createTest(scenario.getName());

        if (scenario.isFailed()) {
            test.log(Status.FAIL, "Scenario failed: " + scenario.getName());
           

            // Take screenshot as base64 and attach to Extent report
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            String base64Screenshot = java.util.Base64.getEncoder().encodeToString(screenshot);
            test.addScreenCaptureFromBase64String(base64Screenshot, "Failure Screenshot");

            // Save screenshot to disk using existing utility
            LibraryClass.captureScreenshot(scenario.getName());
        } else {
            test.log(Status.PASS, "Scenario passed: " + scenario.getName());
        }

        extent.flush();
    }
}
