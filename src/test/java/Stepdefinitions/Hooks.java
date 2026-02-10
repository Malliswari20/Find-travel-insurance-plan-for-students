package Stepdefinitions;

import io.cucumber.java.*;
import org.openqa.selenium.WebDriver;
import utils.AllureReportCleaner;
import utils.AllureReportOpener;
import utils.DriverSetup;
import utils.Screenshot;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Hooks {

    public static WebDriver driver;

    /**
     * Run once before the entire test run:
     * Clean old Allure result/report folders to avoid mixing runs.
     */
    @BeforeAll
    public static void beforeAll() {
        AllureReportCleaner.cleanAllureFolders();
        System.out.println("🧹 Allure folders cleaned (target/allure-results & target/allure-report).");
    }

    /**
     * Runs before each scenario:
     * Launches a fresh browser instance using DriverSetup.
     */
    @Before
    public void launchBrowser(Scenario scenario) {
        driver = DriverSetup.getDriver();
        System.out.println("🚀 Browser started for scenario: " + scenario.getName());
    }

    /**
     * Runs after each scenario:
     * - Takes screenshot ONLY if the scenario PASSED
     * - Attaches screenshot bytes to the Cucumber report
     * - Quits the browser
     */
    @After
    public void tearDown(Scenario scenario) {
        try {
            if (!scenario.isFailed()) {
                String screenshotPath = Screenshot.capture(driver, scenario.getName() + "_PASSED");
                byte[] screenshotBytes = readFileBytes(screenshotPath);
                scenario.attach(screenshotBytes, "image/png", "Passed Screenshot");
                System.out.println("📸 Screenshot captured for PASSED scenario: " + screenshotPath);
            } else {
                System.out.println("❌ Scenario failed. (Per your requirement, no failure screenshot captured.)");
            }
        } catch (Exception e) {
            System.out.println("⚠️ Error while attaching screenshot: " + e.getMessage());
        } finally {
            DriverSetup.quitDriver();
            System.out.println("🔚 Browser closed for scenario: " + scenario.getName());
        }
    }

    /**
     * Run once after the entire test run:
     * Generates the Allure report and opens it.
     */
    @AfterAll
    public static void afterAll() {
        try {
            AllureReportOpener.openAllureReport();
            System.out.println("📊 Allure report generated & opening in browser...");
        } catch (Exception e) {
            System.out.println("⚠️ Failed to open Allure report: " + e.getMessage());
        }
    }

    // -------- Utility: Read file bytes safely --------
    private static byte[] readFileBytes(String path) throws IOException {
        return Files.readAllBytes(Path.of(path));
    }
}
