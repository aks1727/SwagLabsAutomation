package testRunner;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class CucumberExtentReportPlugin {

    // Singleton instance of ExtentReports
    private static ExtentReports extent;

    /**
     * Provides a singleton instance of ExtentReports configured with ExtentSparkReporter.
     * If the instance is already created, it returns the existing one.
     * Otherwise, it initializes a new report with default configuration and system info.
     *
     * @return ExtentReports instance for reporting test execution
     */
    public static ExtentReports getExtentReportInstance() {
        if (extent == null) {
            // Create a new ExtentSparkReporter with the target report file path
            ExtentSparkReporter reporter = new ExtentSparkReporter("target/ExtentReport.html");

            // Set the name and title of the report
            reporter.config().setReportName("Swag Labs Automation Report");
            reporter.config().setDocumentTitle("Test Results");

            // Create and attach the reporter to ExtentReports
            extent = new ExtentReports();
            extent.attachReporter(reporter);

            // Set system info to be displayed in the report
            extent.setSystemInfo("Tester", "Om");
        }
        return extent;
    }
}
