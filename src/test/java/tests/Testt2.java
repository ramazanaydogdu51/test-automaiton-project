//package tests;
//
//import io.qameta.allure.*;
//import io.qameta.allure.testng.AllureTestNg;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.openqa.selenium.OutputType;
//import org.openqa.selenium.TakesScreenshot;
//import org.openqa.selenium.WebDriver;
//import org.testng.Assert;
//import org.testng.ITestResult;
//import org.testng.annotations.*;
//import org.testng.asserts.SoftAssert;
//import pages.CareersPage;
//import pages.HomePage;
//import pages.QAJobsPage;
//import utils.CommonLib;
//import utils.DriverManager;
//
//import java.io.ByteArrayInputStream;
//
//@Listeners({AllureTestNg.class})
//public class Testt2 {
//    private WebDriver driver;
//    private HomePage homePage;
//    private CareersPage careersPage;
//    private QAJobsPage qaJobsPage;
//    private SoftAssert softAssert;
//    private static final Logger log = LogManager.getLogger(Testt2.class);
//
//    @BeforeMethod
//    @Step("Yeni WebDriver başlat ve sayfaları hazırla")
//    public void setUp() {
//        driver = DriverManager.getDriver(); // Her test için bağımsız WebDriver al
//        homePage = new HomePage(driver);
//        careersPage = new CareersPage(driver);
//        qaJobsPage = new QAJobsPage(driver);
//        softAssert = new SoftAssert();
//    }
//
//    @Test
//    @Step("Ana sayfa ve kariyer sayfasına yönlendirme testi")
//    @Description("Ana sayfa açılıyor ve kariyer sayfasına yönlendirme doğrulanıyor")
//    @Severity(SeverityLevel.CRITICAL)
//    public void testHomeAndCareersFlow() {
//        log.info("Ana Sayfa açılıyor...");
//        homePage.openHomePage();
//        Assert.assertTrue(driver.getTitle().contains("Insider"), "Ana sayfa düzgün açılmadı!");
//        CommonLib.captureScreenshot(driver, "Ana Sayfa Ekran Görüntüsü");
//        CommonLib.clickElement(driver, homePage.acceptCookies,true);
//
//        log.info("Kariyer sayfasına yönlendiriliyor...");
//        homePage.navigateToCareers();
//        Assert.assertTrue(careersPage.getPageTitle().contains("Careers"), "Kariyer sayfası başlığı yanlış!");
//        careersPage.testCareerPageSections();
//    }
//
//    @Test
//    @Step("QA İşleri sayfası testi")
//    @Description("QA İşleri sayfası açılıyor, filtreleme yapılıyor ve açık pozisyonlar kontrol ediliyor")
//    @Severity(SeverityLevel.CRITICAL)
//    public void testQAJobsFlow() {
//        log.info("QA İşleri sayfası açılıyor...");
//        qaJobsPage.openQAJobsPage();
//        CommonLib.clickElement(driver, homePage.acceptCookies,true);
//        CommonLib.captureScreenshot(driver, "QA Jobs Page");
//        CommonLib.clickElement(driver, qaJobsPage.qaJobsLink,true);
//        CommonLib.waitForTextToAppear(driver, qaJobsPage.filterByDepartment, "Quality Assurance");
//        CommonLib.scrollToElementCenter(driver, qaJobsPage.filterByDepartment);
//        qaJobsPage.selectLocation("Istanbul, Turkiye");
//
//        log.info("QA İşleri sayfasında açık pozisyonlar kontrol ediliyor...");
//        CommonLib.scrollDown(driver, 500);
//        CommonLib.waitForTextToAppear(driver, qaJobsPage.viewRole, "View Role");
//        qaJobsPage.checkJobListings(softAssert);
//        CommonLib.captureScreenshot(driver, "QA JOBS SS");
//
//        log.info("View Role butonuna tıklanıyor ve yeni sayfaya geçiş yapılıyor...");
//        CommonLib.clickElement(driver, qaJobsPage.viewRole,true);
//        CommonLib.switchToNewTab(driver);
//        CommonLib.captureScreenshot(driver, "Final Page");
//
//        softAssert.assertAll();
//    }
//
//    @AfterMethod
//    @Step("Tarayıcıyı kapat")
//    public void tearDown(ITestResult result) {
//        if (ITestResult.FAILURE == result.getStatus()) {
//            Allure.addAttachment("Test Hatası", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
//        }
//        DriverManager.quitDriver(); // Her testin sonunda tarayıcı kapatılacak
//    }
//}
