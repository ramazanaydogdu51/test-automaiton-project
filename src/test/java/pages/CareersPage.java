package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


import org.testng.Assert;
import utils.CommonLib;
import utils.JsonReader;

public class CareersPage extends BasePage {
    private By locationsBlock;
    private By teamsBlock;
    private By lifeAtInsiderBlock;
    private String careersPageUrl;


    private static final Logger log =  LogManager.getLogger(CareersPage.class);

    public CareersPage(WebDriver driver) {
        super(driver);
        this.careersPageUrl = JsonReader.getUrl("careersPageUrl");
        this.locationsBlock = By.xpath(JsonReader.getLocator("CareersPage", "locationsBlock"));
        this.teamsBlock = By.xpath(JsonReader.getLocator("CareersPage", "teamsBlock"));
        this.lifeAtInsiderBlock = By.xpath(JsonReader.getLocator("CareersPage", "lifeAtInsiderBlock"));

    }


    public void testCareerPageSections() {
        CommonLib.isElementDisplayed(driver, locationsBlock, "Locations Block");
        CommonLib.isElementDisplayed(driver, teamsBlock, "Teams Block");
        CommonLib.isElementDisplayed(driver, lifeAtInsiderBlock, "Life At Insider Block");
    }



    public boolean isCareerPageDisplayed() {
        String expectedUrl = careersPageUrl; // Burada doğru sayfa URL'sini belirtiyoruz
        String actualUrl = driver.getCurrentUrl();
        log.info("Checking CareerPage Display");
        log.info("Expected URL: {}", expectedUrl);
        log.info("Actual URL: {}", actualUrl);
        Assert.assertEquals(actualUrl,expectedUrl,"CareerPage Degeri Beklenilen gibi değil");
        CommonLib.captureScreenshot(driver,"Careers Page Ekran Görüntüsü");
        return true;
    }


}



