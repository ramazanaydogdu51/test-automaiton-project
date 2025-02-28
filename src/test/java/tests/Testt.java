package tests;

import io.qameta.allure.testng.AllureTestNg;
import io.qameta.allure.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import pages.*;
import utils.CommonLib;
import utils.DriverManager;
import java.io.ByteArrayInputStream;

@Listeners({AllureTestNg.class})
public class Testt {
    private WebDriver driver;
    private HomePage homePage;
    private CareersPage careersPage;
    private QAJobsPage qaJobsPage;
    private SoftAssert softAssert;
    private static final Logger log = LogManager.getLogger(Testt.class);

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
    @Step("Ana Sayfanın Açıldığını ve Görüntülendiğini Doğrula")
    @Description("Ana Sayfa Açılıyor ve Doğrulama Yapılıyor")
    @Severity(SeverityLevel.CRITICAL)
    public void testHomePage() {
        log.info("Ana Sayfa açılıyor...");
        homePage.openHomePage();
        log.info("Sayfa başlığı kontrol ediliyor...");
        Assert.assertTrue(driver.getTitle().contains("Insider"), "Ana sayfa düzgün açılmadı!");
        CommonLib.captureScreenshot(driver,"Ana Sayfa Ekran Görüntüsü");
        CommonLib.clickElement(driver, homePage.acceptCookies);
    }

    @Test(dependsOnMethods = "testHomePage")
    @Step("Kariyer Sayfasına Navigasyonu Test Et")
    @Description("Kariyer Sayfasına Yönlendirme ve Başlık Doğrulaması")
    @Severity(SeverityLevel.NORMAL)
    public void testNavigateToCareers() {
        homePage.navigateToCareers();
        Assert.assertTrue(careersPage.getPageTitle().contains("Careers"), "Kariyer sayfası başlığı yanlış!");
        careersPage.testCareerPageSections();
    }

//    @Test(dependsOnMethods = "testNavigateToCareers")
//    @Description("Kariyer Sayfasındaki Blokların Görüntülendiğini Doğrula")
//    @Severity(SeverityLevel.NORMAL)
//    public void testCareerPageSections() {
//
//    }

    @Test(dependsOnMethods = "testNavigateToCareers")
    @Step("QA İşleri Sayfasını Aç ve Filtreleme Yap")
    @Description("QA İşleri Sayfasını Aç ve Filtrele")
    @Severity(SeverityLevel.CRITICAL)
    public void testQAJobsPage() {
        qaJobsPage.openQAJobsPage();
        CommonLib.captureScreenshot(driver, "QA Jobs Page");
        CommonLib.clickElement(driver, qaJobsPage.qaJobsLink);
        CommonLib.waitForTextToAppear(driver, qaJobsPage.filterByDepartment, "Quality Assurance");
        CommonLib.scrollToElementCenter(driver, qaJobsPage.filterByDepartment);
        qaJobsPage.selectLocation("Istanbul, Turkiye");
    }

    @Test(dependsOnMethods = "testQAJobsPage")
    @Step("QA İşleri Sayfasında Açık Pozisyonları Kontrol Et")
    @Description("QA İşleri Sayfasında Açık Pozisyonları Kontrol Et")
    @Severity(SeverityLevel.NORMAL)
    public void testJobListings() {
      //  CommonLib.waitForSeconds(3);
        CommonLib.scrollDown(driver, 500);
        CommonLib.waitForTextToAppear(driver, qaJobsPage.viewRole, "View Role");
        qaJobsPage.checkJobListings(softAssert);
        CommonLib.captureScreenshot(driver,"QA JOBS SS");


    }

    @Test(dependsOnMethods = "testJobListings")
    @Step("View Role Butonunu Tıkla ve Yönlendirmeyi Kontrol Et")
    @Description("View Role Butonunu Tıkla ve Yönlendirmeyi Kontrol Et")
    @Severity(SeverityLevel.CRITICAL)
    public void testViewRoleButton() {
        //CommonLib.scrollToElementCenter(driver, qaJobsPage.viewRole);
        CommonLib.clickElement(driver,qaJobsPage.viewRole);
        System.out.println("driver.getTitle() = " + driver.getTitle());
        CommonLib.switchToNewTab(driver);
        System.out.println("driver.getTitle() = " + driver.getTitle());
        CommonLib.captureScreenshot(driver, "Final Page");
    }

    @AfterMethod
    @Step("Tarayıcıyı kapat")
    public void tearDown(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus()) {
            Allure.addAttachment("Test Hatası", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        }
        // DriverManager.quitDriver();
    }
}
