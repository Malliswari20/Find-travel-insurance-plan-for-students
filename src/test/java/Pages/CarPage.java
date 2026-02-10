package Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.List;

import org.testng.Assert;
import utils.Screenshot;

public class CarPage extends BasePage {

    public CarPage(WebDriver driver) {
        super(driver);
    }

    By number = By.id("carNumber");
    By brand = By.id("carBrand");
    By year  = By.id("carYear");
    By city  = By.id("carCity");
    By email = By.id("carEmail");
    By phone = By.id("carPhone");
    By search = By.xpath("//button[text()='Search Car Plans']");

    // Your error message locator
    By carError = By.xpath("//div[@id='carError']");

    By provider = By.xpath("//div[@id='carResults']//div[@class='provider']");
    By price    = By.xpath("//div[@id='carResults']//div[@class='price']");
    By resultsHead = By.xpath("//div[@class='results-head']/h3");
    public void searchCarPlans() {

        type(number, "TN01AB1234");
        new Select(driver.findElement(brand)).selectByIndex(1);
        new Select(driver.findElement(year)).selectByIndex(1);

        type(city, "Coimbatore");
        type(email, "guna123@gmail.com");

        //Step 1: Enter invalid phone number and click search
        clearAndType(phone, "1276543210");
        click(search);

        //Step 2: If error message displayed -> take screenshot, then correct phone and proceed
        if (isCarErrorDisplayed()) {

            // Capture screenshot when error comes
            String path = Screenshot.capture(driver, "Invalid_Mobile_Error");
            System.out.println("📸 Screenshot captured: " + path);

            // Optional: print error text in console
            System.out.println("Error Message: " + driver.findElement(carError).getText());

            //Step 3: Enter correct phone and click search again
            clearAndType(phone, "9876543210");
            click(search);
        }
    }
    public void validateResultsTitle() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(resultsHead));

        String actualTitle = driver.findElement(resultsHead).getText().trim();
        String expectedTitle = "Recommended Plans";

        Assert.assertEquals(
                actualTitle,
                expectedTitle,
                "Results title mismatch!"
        );

        System.out.println("Assertion Passed: Results title is '" + actualTitle + "'");
    }

    public void printCarPlans() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(provider));

        List<WebElement> providers = driver.findElements(provider);
        List<WebElement> prices = driver.findElements(price);

        System.out.println("\n---- CAR INSURANCE RESULTS ----");
        for (int i = 0; i < providers.size(); i++) {
            System.out.println((i + 1) + ") " + providers.get(i).getText() + " -> " + prices.get(i).getText());
        }
    }

    //Helper: check error appears without failing the test
    private boolean isCarErrorDisplayed() {
        try {
            // Wait small duration for error - if not visible, return false
            wait.until(ExpectedConditions.visibilityOfElementLocated(carError));
            return driver.findElement(carError).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    //Helper: clear and type (important to avoid concatenation)
    private void clearAndType(By locator, String value) {
        WebElement el = driver.findElement(locator);
        el.clear();
        el.sendKeys(value);
    }
}