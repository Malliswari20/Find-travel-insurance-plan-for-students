package Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;

import java.util.List;

public class TravelPage extends BasePage {

    public TravelPage(WebDriver driver) { super(driver); }

    By destination = By.id("travelDestination");
    By start = By.id("travelStart");
    By end = By.id("travelEnd");
    By count = By.id("travellerCount");
    By age1 = By.id("ageOne");
    By age2 = By.id("ageTwo");
    By search = By.xpath("//button[text()='Search Travel Plans']");

    By provider = By.xpath("//div[@id='travelResults']//div[@class='provider']");
    By price = By.xpath("//div[@id='travelResults']//div[@class='price']");
    By resultsHead = By.xpath("//div[@class='results-head']/h3");
    public void searchTravelPlans(){
        select(destination, "Germany");
        type(start, "05-02-2026");
        type(end, "10-02-2026");
        type(count, "2");
        type(age1, "21");
        type(age2, "22");
        click(search);
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


    public void printTravelPlans(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(provider));

        List<WebElement> providers = driver.findElements(provider);
        List<WebElement> prices = driver.findElements(price);

        System.out.println("\n---- TRAVEL INSURANCE RESULTS ----");
        for (int i = 0; i < providers.size(); i++){
            System.out.println((i+1) + ") " +
                    providers.get(i).getText() +
                    " -> " + prices.get(i).getText());
        }
    }
}