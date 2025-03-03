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


    public boolean isLocationsBlockDisplayed() {
        CommonLib.scrollToElementCenter(driver, locationsBlock);
        CommonLib.captureScreenshot(driver, "Locations Block");
        return driver.findElement(locationsBlock).isDisplayed();
    }

    public boolean isTeamsBlockDisplayed() {
        CommonLib.scrollToElementCenter(driver, teamsBlock);
        CommonLib.captureScreenshot(driver, "Teams Block");
        return driver.findElement(teamsBlock).isDisplayed();
    }

    public boolean isLifeAtInsiderBlockDisplayed() {
        CommonLib.scrollToElementCenter(driver, lifeAtInsiderBlock);
        CommonLib.captureScreenshot(driver, "Life at Insider Block");
        return driver.findElement(lifeAtInsiderBlock).isDisplayed();
    }

    public void testCareerPageSections() {
        CommonLib.isElementDisplayed(driver, locationsBlock, "Locations Block");
        CommonLib.isElementDisplayed(driver, teamsBlock, "Teams Block");
        CommonLib.isElementDisplayed(driver, lifeAtInsiderBlock, "Life At Insider Block");
    }

    public boolean isCareerPageRouterCorrect() {
        return CommonLib.isElementClassEquals(driver, "CareersPage", "careerPageRouter", "career-page");
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



