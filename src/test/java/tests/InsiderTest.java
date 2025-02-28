package tests;

import io.qameta.allure.testng.AllureTestNg;
import io.qameta.allure.*;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import pages.*;
import utils.CommonLib;
import utils.DriverManager;
import java.io.ByteArrayInputStream;


@Listeners({AllureTestNg.class})  // TestNG Listener ekledik
public class InsiderTest {
    private WebDriver driver;
    private HomePage homePage;
    private CareersPage careersPage;
    private QAJobsPage qaJobsPage;
    private SoftAssert softAssert ;

    @BeforeMethod
    @Step("Tarayıcıyı başlat ve sayfaları hazırla")
    public void setUp() {
        driver = DriverManager.getDriver();
        homePage = new HomePage(driver);
        careersPage = new CareersPage(driver);
        qaJobsPage = new QAJobsPage(driver);
        softAssert = new SoftAssert();
    }

    @Test
    @Description("Insider Kariyer Sayfasının Açıldığını Doğrula")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Navigasyon ve İş İlanları")
    @Step("Insider kariyer sayfasının açıldığını kontrol et")
    public <WebElement> void testInsiderCareers() {


        //1 step
        homePage.openHomePage();
        Assert.assertTrue(homePage.isHomePageDisplayed());
        CommonLib.clickElement(driver,homePage.acceptCookies);
        Allure.addAttachment("Ana Sayfa Ekran Görüntüsü", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));


        //2-  Select the “Company” menu in the navigation bar, select “Careers”
        homePage.navigateToCareers();
        Allure.addAttachment("Carrers Ekran Görüntüsü", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));

        // and check Career page,
         Assert.assertTrue(careersPage.getPageTitle().contains("Careers")); // title bakarak contains olarak dogrulama yapar

        // its Locations, Teams, and Life at Insider blocks are open or not
        Assert.assertTrue(careersPage.isLocationsBlockDisplayed(), "Locations bloğu görünmüyor!");
        Assert.assertTrue(careersPage.isTeamsBlockDisplayed(), "Teams bloğu görünmüyor!");
        Assert.assertTrue(careersPage.isLifeAtInsiderBlockDisplayed(), "Life at Insider bloğu görünmüyor!");

        //3. Go to https://useinsider.com/careers/quality-assurance/,
        qaJobsPage.openQAJobsPage();
        CommonLib.captureScreenshot(driver,"QA Jobs Page");
        // click “See all QA jobs”, filter

       CommonLib.clickElement(driver,qaJobsPage.qaJobsLink);

        CommonLib.waitForTextToAppear(driver,qaJobsPage.filterByDepartment,"Quality Assurance");

        CommonLib.scrollToElementCenter(driver,qaJobsPage.filterByDepartment);
        qaJobsPage.selectLocation("Istanbul, Turkiye");

            //check the presence of the jobs list
        CommonLib.waitForSeconds(3);

        qaJobsPage.checkJobListings(softAssert);

                            //5. Click the “View Role” button and check that this action redirects us to the Lever Application form page
        //btn btn-navy rounded pt-2 pr-5 pb-2 pl-5


          CommonLib.scrollToElementCenter(driver,qaJobsPage.viewRole);
       // CommonLib.clickElement(driver,qaJobsPage.viewRole);
        qaJobsPage.clickRandomViewRoleButton();
        System.out.println("driver.getTitle() = " + driver.getTitle());

        CommonLib.switchToNewTab(driver);
        System.out.println("driver.getTitle() = " + driver.getTitle());
        CommonLib.captureScreenshot(driver,"Last PAge pic");

    }


    @Step("Tarayıcıyı kapat")
    @AfterMethod
    public void tearDown(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus()) {
            Allure.addAttachment("Test Hatası", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        }
       // DriverManager.quitDriver();
    }
}
