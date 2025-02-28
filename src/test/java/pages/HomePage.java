package pages;

import io.qameta.allure.Allure;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import utils.CommonLib;
import utils.JsonReader;

import java.io.ByteArrayInputStream;

public class HomePage extends BasePage {
    private By companyMenu;
    private By careersLink;
    public By acceptCookies;

    public HomePage(WebDriver driver) {
        super(driver);
        this.companyMenu = By.xpath(JsonReader.getLocator("HomePage", "companyMenu"));
        this.careersLink = By.xpath(JsonReader.getLocator("HomePage", "careersLink"));
        this.acceptCookies = By.xpath(JsonReader.getLocator("HomePage", "acceptCookies"));
    }

    public void openHomePage() {
        driver.get("https://useinsider.com/");
    }

    public boolean isHomePageDisplayed() {
        return getPageTitle().contains("Insider");
    }

    public void navigateToCareers() {
        driver.findElement(companyMenu).click();
        CommonLib.captureScreenshot(driver,"Company Menu SS");
        driver.findElement(careersLink).click();

    }
}
