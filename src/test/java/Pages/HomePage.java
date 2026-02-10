package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) { super(driver); }

    By cookie = By.id("btnCookieAccept");
    By car = By.xpath("//nav/a[text()='Car']");
    By bike = By.xpath("//nav/a[text()='Bike']");
    By travel = By.xpath("//nav/a[@href='#travel']");

    public void acceptCookies(){
        try { click(cookie); } catch (Exception ignored){}
    }

    public void openCar(){ click(car); }

    public void openBike(){ click(bike); }

    public void openTravel(){ click(travel); }
}