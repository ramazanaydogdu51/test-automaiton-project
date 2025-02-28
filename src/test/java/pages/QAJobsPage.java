package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;
import utils.CommonLib;
import utils.JsonReader;

public class QAJobsPage extends BasePage {

    public By qaJobsLink;
    public By filterByDepartment;
    public By jobList;
    public By department;
    public By viewRole;

    public QAJobsPage(WebDriver driver) {
        super(driver);
        this.qaJobsLink = By.xpath(JsonReader.getLocator("QAJobsPage", "qaJobsLink"));
        this.filterByDepartment = By.cssSelector(JsonReader.getLocator("QAJobsPage", "filterByDepartment"));
        this.jobList = By.cssSelector(JsonReader.getLocator("QAJobsPage", "jobList"));
        this.department = By.cssSelector(JsonReader.getLocator("QAJobsPage", "department"));
        this.viewRole = By.xpath(JsonReader.getLocator("QAJobsPage", "viewRole"));

    }
    public void openQAJobsPage() {
        driver.get("https://useinsider.com/careers/quality-assurance/");
    }
//    public void selectLocation(String locationName) {
//        By dropdown = By.xpath("//span[@id='select2-filter-by-location-container']"); // Dropdown locator'ı
//        CommonLib.selectDropdownValue(driver, dropdown, locationName);
//    }
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



}
