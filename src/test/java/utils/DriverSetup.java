package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverSetup {

    private static WebDriver driver;

    // Private constructor (no one can create object)
    private DriverSetup() {}

    // Get WebDriver instance
    public static WebDriver getDriver() {

        if (driver == null) {
            WebDriverManager.chromedriver().setup();   // Auto driver download

            driver = new ChromeDriver();               // Launch browser
            driver.manage().window().maximize();       // Maximize window
        }

        return driver;
    }

    // Quit Driver & Cleanup
    public static void quitDriver() {
        if (driver != null) {
            driver.quit();    // Close all browser windows
            driver = null;    // Remove reference (clean memory)
        }
    }
}