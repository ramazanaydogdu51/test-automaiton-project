package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverManager {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static final Logger log =  LogManager.getLogger(DriverManager.class);

    public static WebDriver getDriver() {
        if (driver.get() == null) {
            log.info("Yeni WebDriver başlatılıyor...");
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");


            WebDriver newDriver = new ChromeDriver(options);
            newDriver.manage().window().maximize();
            driver.set(newDriver);
            log.info("WebDriver başarıyla başlatıldı.");

        }
        return driver.get();
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            log.info("WebDriver kapatılıyor...");
            driver.get().quit();
            driver.remove();
            log.info("WebDriver başarıyla kapatıldı.");

        }
    }
}
