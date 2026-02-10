package Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;

import java.util.List;

public class BikePage extends BasePage {

    public BikePage(WebDriver driver) {
        super(driver);
    }

    By model = By.id("bikeModel");
    By year = By.id("bikeYear");
    By city = By.id("bikeCity");
    By email = By.id("bikeEmail");
    By phone = By.id("bikePhone");
    By search = By.xpath("//button[text()='Search Bike Plans']");

    By provider = By.xpath("//div[@id='bikeResults']//div[@class='provider']");
    By price = By.xpath("//div[@id='bikeResults']//div[@class='price']");

    //Results title locator
    By resultsHead = By.xpath("//div[@class='results-head']/h3");

    public void searchBikePlans(){
        type(model, "Yamaha");
        new Select(driver.findElement(year)).selectByIndex(1);
        type(city, "Coimbatore");
        type(email, "guna123@gmail.com");
        type(phone, "9876543210");
        click(search);
    }

    //Assertion method
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

    public void printBikePlans(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(provider));

        List<WebElement> providers = driver.findElements(provider);
        List<WebElement> prices = driver.findElements(price);

        System.out.println("\n---- BIKE INSURANCE RESULTS ----");
        for (int i = 0; i < providers.size(); i++){
            System.out.println((i+1) + ") " +
                    providers.get(i).getText() +
                    " -> " + prices.get(i).getText());
        }
    }
}