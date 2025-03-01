package tests;

import io.qameta.allure.*;
import io.qameta.allure.testng.AllureTestNg;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import pages.CareersPage;
import pages.HomePage;
import pages.QAJobsPage;
import utils.CommonLib;
import utils.DriverManager;

@Listeners({AllureTestNg.class})
public class Test5 {
    private static WebDriver driver;
    private static HomePage homePage;
    private static CareersPage careersPage;
    private static QAJobsPage qaJobsPage;
    private static SoftAssert softAssert;
    private static final Logger log = LogManager.getLogger(Test5.class);

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
    @Description("Ana sayfa açılıyor ve başlık doğrulanıyor")
    @Severity(SeverityLevel.CRITICAL)
    public void testHomePage() {
        log.info("Ana Sayfa açılıyor...");
        homePage.openHomePage();
        Assert.assertTrue(homePage.isHomePageDisplayed(), "Ana sayfa düzgün açılmadı!");
        CommonLib.captureScreenshot(driver, "Ana Sayfa Ekran Görüntüsü");
        CommonLib.clickElement(driver, homePage.acceptCookies);
    }

    @Test(priority = 2)
    @Step("Kariyer Sayfasına Navigasyonu Test Et")
    @Description("Kariyer Sayfasına Yönlendirme ve Başlık Doğrulaması")
    @Severity(SeverityLevel.NORMAL)
    public void testNavigateToCareers() {
        homePage.navigateToCareers();
        Assert.assertTrue(careersPage.getPageTitle().contains("Careers"), "Kariyer sayfası başlığı yanlış!");
        careersPage.testCareerPageSections();
    }

    @Test(priority = 3)
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
        CommonLib.clickElement(driver, qaJobsPage.viewRole);
        CommonLib.switchToNewTab(driver);
        CommonLib.captureScreenshot(driver, "Final Page");
    }

    @AfterClass
    @Step("Tüm testler bittiğinde tarayıcıyı kapat")
    public void tearDownClass() {
        DriverManager.quitDriver();
    }
}
