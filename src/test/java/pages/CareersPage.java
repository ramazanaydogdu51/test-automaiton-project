package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


import org.testng.Assert;
import utils.CommonLib;
import utils.JsonReader;

public class CareersPage extends BasePage {
    private By locationsBlock;
    private By teamsBlock;
    private By lifeAtInsiderBlock;


    public CareersPage(WebDriver driver) {
        super(driver);
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


}



