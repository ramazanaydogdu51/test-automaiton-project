package pages;

import io.qameta.allure.Allure;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import utils.CommonLib;
import utils.JsonReader;

import java.io.ByteArrayInputStream;

public class HomePage extends BasePage {
    private By companyMenu;
    private By careersLink;
    private By homePage;
    public By acceptCookies;
    private String homePageUrl;

    private static final Logger log =  LogManager.getLogger(HomePage.class);


    public HomePage(WebDriver driver) {
        super(driver);
        this.homePageUrl = JsonReader.getUrl("baseUrl");
        this.companyMenu = By.xpath(JsonReader.getLocator("HomePage", "companyMenu"));
        this.careersLink = By.xpath(JsonReader.getLocator("HomePage", "careersLink"));
        this.acceptCookies = By.xpath(JsonReader.getLocator("HomePage", "acceptCookies"));
        this.homePage = By.xpath(JsonReader.getLocator("HomePage", "homePage"));
    }

    public void openHomePage() {
        log.info("Ana Sayfa açılıyor...");
        driver.get(homePageUrl);
    }



    public boolean isHomePageDisplayed() {
        String expectedUrl = homePageUrl; // Burada doğru sayfa URL'sini belirtiyoruz
        String actualUrl = driver.getCurrentUrl();
        log.info("Checking HomePage Display");
        log.info("Expected URL: {}", expectedUrl);
        log.info("Actual URL: {}", actualUrl);
        Assert.assertEquals(actualUrl,expectedUrl,"HomePage Degeri Beklenilen gibi değil");
        CommonLib.captureScreenshot(driver, "Ana Sayfa Ekran Görüntüsü");
        return true;
    }

    public boolean isHomePageRouterCorrect() {
        return CommonLib.isElementClassEquals(driver, "HomePage", "homePage", "home-page");
    }






    public void navigateToCareers() {
        CommonLib.clickElement(driver,companyMenu,true,"Company Menu Görüntüsü");
//        CommonLib.captureScreenshot(driver,"Company Menu");
        CommonLib.clickElement(driver,careersLink,false,"Careers");


    }
}
