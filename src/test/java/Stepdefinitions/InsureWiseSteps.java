package Stepdefinitions;

import Stepdefinitions.Hooks;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import Pages.*;

public class InsureWiseSteps {

    WebDriver driver = Hooks.driver;

    HomePage home;
    CarPage car;
    BikePage bike;
    TravelPage travel;

    @Given("I open the Insurewise website")
    public void openWebsite() {

        driver.get("https://earnest-macaron-3e274d.netlify.app/");

        home = new HomePage(driver);
        car = new CarPage(driver);
        bike = new BikePage(driver);
        travel = new TravelPage(driver);

        home.acceptCookies();
    }

    @When("I search for car insurance")
    public void carInsurance() {
        home.openCar();
        car.searchCarPlans();
    }

    @Then("I print all car plans")
    public void printCar() {
        car.validateResultsTitle();
        car.printCarPlans();
    }

    @When("I search for bike insurance")
    public void bikeInsurance() {
        home.openBike();
        bike.searchBikePlans();
    }

    @Then("I print all bike plans")
    public void printBike() {
        bike.validateResultsTitle();
        bike.printBikePlans();
    }

    @When("I search for travel insurance")
    public void travelInsurance() {
        home.openTravel();
        travel.searchTravelPlans();
    }

    @Then("I print all travel plans")
    public void printTravel() {
        travel.validateResultsTitle();
        travel.printTravelPlans();
    }
}