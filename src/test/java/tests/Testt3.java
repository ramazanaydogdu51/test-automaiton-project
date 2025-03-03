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
//import org.testng.annotations.AfterMethod;
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.Listeners;
//import org.testng.annotations.Test;
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
//public class Testt3 {
//    private WebDriver driver;
//    private HomePage homePage;
//    private CareersPage careersPage;
//    private QAJobsPage qaJobsPage;
//    private SoftAssert softAssert;
//    private static final Logger log = LogManager.getLogger(Testt3.class);
//
//    @BeforeMethod
//    @Step("Yeni WebDriver başlat ve sayfaları hazırla")
//    public void setUp() {
//        log.info("Test başlamadan önce WebDriver başlatılıyor...");
//        driver = DriverManager.getDriver(); // Her test için bağımsız WebDriver al
//        homePage = new HomePage(driver);
//        careersPage = new CareersPage(driver);
//        qaJobsPage = new QAJobsPage(driver);
//        softAssert = new SoftAssert();
//        log.info("Test setup tamamlandı.");
//
//    }
//
//    @Test
//    @Step("Ana sayfa ve kariyer sayfasına yönlendirme testi")
//    @Description("Ana sayfa açılıyor ve kariyer sayfasına yönlendirme doğrulanıyor")
//    @Severity(SeverityLevel.CRITICAL)
//    public void testHomeAndCareersFlow() {
//        log.info("🟢 TEST BAŞLADI: Ana sayfa ve kariyer sayfası akışı test ediliyor.");
//        log.info("Ana Sayfa açılıyor: https://useinsider.com/");
//        homePage.openHomePage();
//        log.info("Sayfa başlığı alındı: " + driver.getTitle());
//        Assert.assertTrue(driver.getTitle().contains("Insider"), "Ana sayfa başlığı beklenen değeri içermiyor!"); //home page ile yap
//        CommonLib.captureScreenshot(driver, "Ana Sayfa Ekran Görüntüsü");
//        log.info("✅ Ana sayfa başlık doğrulandı.");
//        log.info("🖱️ Çerez kabul butonuna tıklanıyor.");
//        CommonLib.clickElement(driver, homePage.acceptCookies,true);
//
//        log.info("🔄 Kariyer sayfasına yönlendiriliyor...");
//        homePage.navigateToCareers();
//        log.info("📌 Kariyer sayfası başlığı alındı: " + careersPage.getPageTitle());
//        Assert.assertTrue(careersPage.getPageTitle().contains("Careers"), "Kariyer sayfası başlığı yanlış!");
//        log.info("✅ Kariyer sayfası başlık doğrulandı.");
//        log.info("🔍 Kariyer sayfasındaki bloklar kontrol ediliyor...");
//        careersPage.testCareerPageSections();
//        log.info("✅ Kariyer sayfasındaki tüm bloklar başarıyla görüntülendi.");
//        log.info("🟢 TEST TAMAMLANDI: Ana sayfa ve kariyer sayfası akışı başarıyla test edildi.");
//
//    }
//
//
//
//    @AfterMethod
//    @Step("Tarayıcıyı kapat")
//    public void tearDown(ITestResult result) {
//        if (ITestResult.FAILURE == result.getStatus()) {
//            log.error("HATA: Test başarısız oldu - " + result.getName());
//            log.error("Hata Detayı: " + result.getThrowable().getMessage());
//            Allure.addAttachment("Test Hatası", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
//        } else {
//            log.info("Test başarılı: " + result.getName());
//        }
//        DriverManager.quitDriver();
//    }
//}
