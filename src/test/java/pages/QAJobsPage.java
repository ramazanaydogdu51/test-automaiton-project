package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import utils.CommonLib;
import utils.JsonReader;

public class QAJobsPage extends BasePage {
    private static final Logger log =  LogManager.getLogger(QAJobsPage.class);

    public By seeAllJobs;
    public By filterByDepartment;
    public By jobList;
    public By department;
    public By viewRole;
    public By noJobResult;
    private String qaJobsPageUrl;

    public QAJobsPage(WebDriver driver) {
        super(driver);
        this.seeAllJobs = By.cssSelector(JsonReader.getLocator("QAJobsPage", "seeAllJobs"));
        this.filterByDepartment = By.cssSelector(JsonReader.getLocator("QAJobsPage", "filterByDepartment"));
        this.jobList = By.cssSelector(JsonReader.getLocator("QAJobsPage", "jobList"));
        this.department = By.cssSelector(JsonReader.getLocator("QAJobsPage", "department"));
        this.viewRole = By.xpath(JsonReader.getLocator("QAJobsPage", "viewRole"));
        this.noJobResult = By.cssSelector(JsonReader.getLocator("CommonLoc", "noJobResult"));
        this.qaJobsPageUrl = JsonReader.getUrl("qaJobsPageUrl");
    }
    public void openQAJobsPage() {
        log.info("QA Jobs Page açılıyor...");
        driver.get("https://useinsider.com/careers/quality-assurance/");
    }
    public boolean isQAjobsPageDisplayed() {
        String expectedUrl = qaJobsPageUrl; // Burada doğru sayfa URL'sini belirtiyoruz
        String actualUrl = driver.getCurrentUrl();
        log.info("Checking QA Jobs Page Display");
        log.info("Expected URL: {}", expectedUrl);
        log.info("Actual URL: {}", actualUrl);
        Assert.assertEquals(actualUrl,expectedUrl,"QA Jobs Page Degeri Beklenilen gibi değil");
        CommonLib.captureScreenshot(driver, "QA Jobs Page Ekran Görüntüsü");
        return true;
    }

    public void selectLocation(String locationName) {
        CommonLib.selectDropdownValue(driver, "QAJobsPage", "filterByLocation", locationName);
    }
    public void selectDepartment(String departmentName) {
        CommonLib.selectDropdownValue(driver, "QAJobsPage", "filterByDepartmentDropdown", departmentName);
    }
    public void checkJobListings(SoftAssert softAssert) {
        CommonLib.verifyTextInElements(driver, By.xpath(JsonReader.getLocator("QAJobsPage", "positionTitle")), "Software", softAssert);
        CommonLib.verifyTextInElements(driver, By.xpath(JsonReader.getLocator("QAJobsPage", "positionDepartment")), "Quality Assurance", softAssert);
        CommonLib.verifyTextInElements(driver, By.xpath(JsonReader.getLocator("QAJobsPage", "positionLocation")), "Istanbul", softAssert);
        softAssert.assertAll();
    }

    public void clickRandomViewRoleButton() {
        CommonLib.clickRandomElement(driver, "QAJobsPage", "viewRoleButton");
    }

    public void filterQAJobsAndVerifyJobList(){
        CommonLib.clickElement(driver, seeAllJobs,false,"");
        CommonLib.waitForTextToAppear(driver, filterByDepartment, "Quality Assurance");
        CommonLib.scrollToElementCenter(driver, filterByDepartment);
        selectLocation("Istanbul, Turkiye");
        Assert.assertTrue(CommonLib.isElementNotDisplayed(driver,noJobResult,"No Job Result"),"No positions available");

    }


}
