package tests;

import io.qameta.allure.*;
import io.qameta.allure.testng.AllureTestNg;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import pages.CareersPage;
import pages.HomePage;
import pages.QAJobsPage;
import utils.CommonLib;
import utils.DriverManager;

@Listeners({AllureTestNg.class})
public class Ramazan_Aydogdu_Test {
    private static WebDriver driver;
    private static HomePage homePage;
    private static CareersPage careersPage;
    private static QAJobsPage qaJobsPage;
    private static SoftAssert softAssert;
    private static final Logger log = LogManager.getLogger(Ramazan_Aydogdu_Test.class);

    @BeforeClass
    @Step("Test süresince aynı WebDriver'ı kullan")
    public void setUpClass() {
        log.info("Test başlamadan önce WebDriver başlatılıyor...");
        driver = DriverManager.getDriver();
        homePage = new HomePage(driver);
        careersPage = new CareersPage(driver);
        qaJobsPage = new QAJobsPage(driver);
        softAssert = new SoftAssert();
    }

    @Test(priority = 1)
    @Step("Ana sayfanın açıldığını doğrula")
    @Description("Ana sayfa açılıyor ve açıldıgı doğrulanıyor")
    @Severity(SeverityLevel.CRITICAL)
    public void testHomePage() {
        homePage.openHomePage();
        homePage.isHomePageDisplayed();
        CommonLib.clickElement(driver, homePage.acceptCookies,false,""); // duzeltilecek
    }

    @Test(priority = 2)
    @Step("Kariyer Sayfasına Navigasyonu Test Et")
    @Description("Kariyer Sayfasına Yönlendirme ve Başlık Doğrulaması")
    @Severity(SeverityLevel.NORMAL)
    public void testCareerPage() {
        homePage.navigateToCareers();
        careersPage.isCareerPageDisplayed();
        careersPage.testCareerPageSections();
    }

    @Test(priority = 3)
    @Step("QA İşleri Sayfasını Aç ve Filtreleme Yap")
    @Description("QA İşleri Sayfasını Aç ve Filtrele")
    @Severity(SeverityLevel.CRITICAL)
    public void testQAJobsPage() {
        qaJobsPage.openQAJobsPage();
        qaJobsPage. isQAjobsPageDisplayed();
        qaJobsPage.filterQAJobsAndVerifyJobList();
//        CommonLib.clickElement(driver, qaJobsPage.seeAllJobs,false,"");
//        CommonLib.waitForTextToAppear(driver, qaJobsPage.filterByDepartment, "Quality Assurance");
//        CommonLib.scrollToElementCenter(driver, qaJobsPage.filterByDepartment);
//        qaJobsPage.selectLocation("Istanbul, Turkiye");
    }

    @Test(priority = 4)
    @Step("QA İşleri Sayfasında Açık Pozisyonları Kontrol Et")
    @Description("QA İşleri Sayfasında Açık Pozisyonları Kontrol Et")
    @Severity(SeverityLevel.NORMAL)
    public void testJobListings() {
        CommonLib.scrollDown(driver, 500);
        CommonLib.waitForTextToAppear(driver, qaJobsPage.viewRole, "View Role");
        qaJobsPage.checkJobListings(softAssert);

        CommonLib.captureScreenshot(driver, "QA JOBS SS");
    }

    @Test(priority = 5)
    @Step("View Role Butonunu Tıkla ve Yönlendirmeyi Kontrol Et")
    @Description("View Role Butonunu Tıkla ve Yönlendirmeyi Kontrol Et")
    @Severity(SeverityLevel.CRITICAL)
    public void testViewRoleButton() {
        CommonLib.clickElement(driver, qaJobsPage.viewRole,true,"After Click View Role");
        CommonLib.switchToNewTab(driver);
        CommonLib.captureScreenshot(driver, "Final Page");
    }

    @AfterClass
    @Step("Tüm testler bittiğinde tarayıcıyı kapat")
    public void tearDownClass() {
        DriverManager.quitDriver();
        softAssert.assertAll();
    }
}
