package utils;

import io.qameta.allure.Allure;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.Set;



import static com.sun.activation.registries.LogSupport.log;

public class CommonLib {
    private static final Logger log =  LogManager.getLogger(CommonLib.class);


    // Elementi sayfanın en üstüne hizalar (start)
    public static void scrollToElementStart(WebDriver driver, By element) {
        scrollToElement(driver, element, "start");
    }

    // Elementi sayfanın ortasına hizalar (center)
    public static void scrollToElementCenter(WebDriver driver, By element) {
        scrollToElement(driver, element, "center");
    }

    // Elementi sayfanın en altına hizalar (end)
    public static void scrollToElementEnd(WebDriver driver, By element) {
        scrollToElement(driver, element, "end");
    }

    // Eğer element zaten görünüyorsa, kaydırma yapmaz (nearest)
    public static void scrollToElementNearest(WebDriver driver, By element) {
        scrollToElement(driver, element, "nearest");
    }

    // Ortak kaydırma metodu (bu sayede kod tekrarını engelleriz)
    private static void scrollToElement(WebDriver driver, By element, String position) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement webElement = driver.findElement(element);
        js.executeScript("arguments[0].scrollIntoView({behavior: 'instant', block: '" + position + "'});", webElement);

        // Bekleme ekleyelim ki scroll tamamlanmadan SS alınmasın
        waitForElementToBeVisible(driver, element);
    }

    // Belirtilen elementi görünene kadar bekleyen metot
    public static void waitForElementToBeVisible(WebDriver driver, By element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5)); // 2 saniye bekler
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }


    public static void clickElement2(WebDriver driver, By element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(element));

        WebElement webElement = driver.findElement(element);
        JavascriptExecutor js = (JavascriptExecutor) driver;

        try {
            webElement.click(); // Normal click
        } catch (Exception e) {
            js.executeScript("arguments[0].click();", webElement); // Eğer normal click başarısız olursa JS ile click
        }
    }

    public static void clickElement3(WebDriver driver, By element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            log.info("Tıklanacak element bekleniyor: " + element.toString());
            WebElement webElement = wait.until(ExpectedConditions.elementToBeClickable(element));
            webElement.click();
            log.info("Elemente başarıyla tıklandı: " + element.toString());
        } catch (TimeoutException e) {
            log.error("HATA: Element zaman aşımına uğradı: " + element.toString(), e);
            throw e;
        } catch (NoSuchElementException e) {
            log.error("HATA: Element bulunamadı: " + element.toString(), e);
        }
    }

//    public static void clickElement2(WebDriver driver, By element) {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        try {
//            log.info("Tıklanacak element bekleniyor: {}", element);
//            WebElement webElement = wait.until(ExpectedConditions.elementToBeClickable(element));
//            webElement.click();
//            log.info("✅ Element başarıyla tıklandı: {}", element);
//        } catch (TimeoutException | NoSuchElementException e) {
//            log.error("❌ Element tıklanamadı: {} - Hata: {}", element, e.getMessage());
//            captureScreenshot(driver, "ClickElement_Error");
//            throw e;
//        }
//    }
    public static void clickElement(WebDriver driver, By element, boolean takeScreenshot ,String descriptionOfPic) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            log.info("⏳ Tıklanacak element bekleniyor: {}", element);

            WebElement webElement = wait.until(ExpectedConditions.elementToBeClickable(element));
            webElement.click(); // ✅ Elemente normal click
            log.info("✅ Element başarıyla tıklandı: {}", element);

            // 📸 Eğer test kanıtı isteniyorsa, SS al
            if (takeScreenshot) {
                log.info("📸 Element tıklama sonrası ekran görüntüsü alınıyor...");
                CommonLib.captureScreenshot(driver, "Click_Success_" + descriptionOfPic);
            }
        } catch (TimeoutException | NoSuchElementException e) {
            log.error("❌ Element tıklanamadı: {} - Hata: {}", element, e.getMessage());
            CommonLib.captureScreenshot(driver, "ClickElement_Error");
            throw e;
        }
    }



    public static void selectDropdownValue2(WebDriver driver, String pageName, String dropdownKey, String valueToSelect) {
        // JSON'dan ilgili dropdown locator'ını al
        By dropdown = By.cssSelector(JsonReader.getLocator(pageName, dropdownKey));

        // Dropdown'u aç
        clickElement(driver, dropdown,true,"");

        // Seçeneklerin yüklendiğinden emin ol
        By option = By.xpath("//li[contains(text(),'" + valueToSelect + "')]");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(option));
        log(String.valueOf(option));

        // Şehri veya departmanı seç
        clickElement(driver, option,true,"");

    }
    public static void selectDropdownValue(WebDriver driver, String pageName, String dropdownKey, String valueToSelect) {
        try {
            By dropdown = By.cssSelector(JsonReader.getLocator(pageName, dropdownKey));
            clickElement(driver, dropdown,true,"");

            By option = By.xpath("//li[contains(text(),'" + valueToSelect + "')]");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement optionElement = wait.until(ExpectedConditions.visibilityOfElementLocated(option));

            log.info("🔽 Seçilecek dropdown değeri bulundu: " + valueToSelect);
            optionElement.click();
            log.info("✅ Dropdown değeri başarıyla seçildi: " + valueToSelect);
        } catch (Exception e) {
            log.error("❌ Dropdown değeri seçilemedi: " + valueToSelect, e);
            captureScreenshot(driver, "SelectDropdownValue_Error");
        }
    }



    public static void waitForTextToAppear2(WebDriver driver, By element, String expectedText) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(element, expectedText));
    }


    public static void waitForTextToAppear(WebDriver driver, By element, String expectedText) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            log.info("⏳ Bekleniyor: '{}' metni '{}' elementinde görünsün.", expectedText, element);
            wait.until(ExpectedConditions.textToBePresentInElementLocated(element, expectedText));
            log.info("✅ Metin bulundu: '{}' -> {}", expectedText, element);
        } catch (TimeoutException e) {
            log.error("❌ TimeoutException: Beklenen metin '{}' belirtilen elementte görünmedi: {}", expectedText, element);
            captureScreenshot(driver, "WaitForText_Error");
            throw new TimeoutException("HATA: '" + expectedText + "' metni, element " + element + " içinde yüklenmedi.", e);
        }
    }

    public static void waitForSeconds(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void switchToNewTab2(WebDriver driver) {
        String mainWindow = driver.getWindowHandle(); // Mevcut sekmeyi kaydet
        Set<String> windowHandles = driver.getWindowHandles(); // Açık olan sekmeleri al

        for (String window : windowHandles) {
            if (!window.equals(mainWindow)) {
                driver.switchTo().window(window);
                log("✅ Yeni sekmeye geçildi: " + driver.getTitle());
                return;
            }
        }
        log("❌ Yeni sekme bulunamadı!");
    }

    public static void switchToNewTab3(WebDriver driver) {
        String mainWindow = driver.getWindowHandle();
        Set<String> windowHandles = driver.getWindowHandles();

        log.info("🔄 Açık sekmeler: " + windowHandles);

        for (String window : windowHandles) {
            if (!window.equals(mainWindow)) {
                driver.switchTo().window(window);
                log.info("✅ Yeni sekmeye geçildi: " + driver.getTitle());
                return;
            }
        }

        log.error("❌ Yeni sekme bulunamadı!");
        captureScreenshot(driver, "SwitchToNewTab_Error");
    }

    public static void switchToNewTab(WebDriver driver) {
        String mainWindow = driver.getWindowHandle();
        Set<String> windowHandles = driver.getWindowHandles();
        log.info("🔄 Açık sekmeler: {}", windowHandles);
        for (String window : windowHandles) {
            if (!window.equals(mainWindow)) {
                driver.switchTo().window(window);
                log.info("✅ Yeni sekmeye geçildi: {}", driver.getTitle());
                return;
            }
        }
        log.error("❌ Yeni sekme bulunamadı!");
        captureScreenshot(driver, "SwitchToNewTab_Error");
    }



    public static void verifyTextInElements(WebDriver driver, By locator, String expectedText, SoftAssert softAssert) {
        List<WebElement> elements = driver.findElements(locator);
        for (WebElement element : elements) {
            String text = element.getText();
            log(text);
            softAssert.assertTrue(text.contains(expectedText), "Aradığınız içerik mevcut değil: " + expectedText);
        }
    }

    public static void clickRandomElement2(WebDriver driver, String pageName, String locatorKey) {
        try {
            // Get the locator from JSON
            String locatorValue = JsonReader.getLocator(pageName, locatorKey);
            By locator = By.cssSelector(locatorValue);

            // Find all elements matching the locator
            List<WebElement> elements = driver.findElements(locator);
            log("elements = " + elements);

            if (elements.isEmpty()) {
                log("No elements found for locator: " + locatorValue);
                return;
            }

            // Pick a random element from the list
            Random random = new Random();
            WebElement randomElement = elements.get(random.nextInt(elements.size()));

            // Scroll to the element to ensure it's visible
            // ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", randomElement);
            log("Clicked on a random element from: " + randomElement);

            // Wait for element to be clickable
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(randomElement));
            // Click on the random element
            randomElement.click();
            log("Clicked on a random element from: " + randomElement);

        } catch (Exception e) {
            log("Error clicking on a random element: " + e.getMessage());
        }
    }
    public static void clickRandomElement(WebDriver driver, String pageName, String locatorKey) {
        try {
            String locatorValue = JsonReader.getLocator(pageName, locatorKey);
            By locator = By.cssSelector(locatorValue);

            List<WebElement> elements = driver.findElements(locator);
            log.info("🔍 Bulunan elementler: " + elements.size());

            if (elements.isEmpty()) {
                log.warn("⚠️ Hiç element bulunamadı: " + locatorValue);
                captureScreenshot(driver, "NoElementsFound");
                return;
            }

            Random random = new Random();
            WebElement randomElement = elements.get(random.nextInt(elements.size()));

            log.info("🖱️ Rastgele tıklanacak element: " + randomElement.getText());
            randomElement.click();
            log.info("✅ Rastgele elemente tıklandı: " + randomElement.getText());
        } catch (Exception e) {
            log.error("❌ Rastgele element tıklanırken hata oluştu!", e);
            captureScreenshot(driver, "ClickRandomElement_Error");
        }
    }

    public static boolean isElementDisplayed2(WebDriver driver, By elementLocator, String elementName) {
        try {
            WebElement element = driver.findElement(elementLocator);

            // Sayfanın ortasına kaydır
            scrollToElementCenter(driver, elementLocator);

            // SS al ve log yazdır
            captureScreenshot(driver, elementName + " Element");
            log("[INFO] " + elementName + " elementi görüntülendi.");

            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            // Eğer element bulunamazsa yine SS al ve hata mesajı bas
            log("[WARNING] " + elementName + " elementi bulunamadı.");
            captureScreenshot(driver, elementName + "_Not_Found");
            return false;
        }
    }
    public static boolean isElementDisplayed(WebDriver driver, By elementLocator, String elementName) {
        try {
            log.info("🔍 Kontrol ediliyor: " + elementName + " (Locator: " + elementLocator.toString() + ")");

            WebElement element = driver.findElement(elementLocator);

            // Sayfanın ortasına kaydır
            scrollToElementStart(driver, elementLocator);

            // SS al ve log yazdır
            captureScreenshot(driver, elementName + "_Displayed");
            log.info("✅ ELEMENT GÖRÜNTÜLENDİ: " + elementName + " (Locator: " + elementLocator.toString() + ")");

            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            // Eğer element bulunamazsa yine SS al ve hata mesajı bas
            log.error("❌ HATA: " + elementName + " elementi bulunamadı! (Locator: " + elementLocator.toString() + ")");
            log.error("📌 Sayfa başlığı: " + driver.getTitle());
            captureScreenshot(driver, elementName + "_Not_Found");
            return false;
        } catch (Exception e) {
            // Beklenmeyen bir hata alındığında daha fazla detay ekleyelim
            log.error("⚠️ BEKLENMEYEN HATA: " + elementName + " görüntülenirken hata oluştu!", e);
            log.error("📌 Sayfa başlığı: " + driver.getTitle());
            captureScreenshot(driver, elementName + "_UnexpectedError");
            return false;
        }
    }
    public static boolean isElementNotDisplayed(WebDriver driver, By elementLocator, String elementName) {
        try {
            log.info("🔍 Kontrol ediliyor: " + elementName + " (Locator: " + elementLocator.toString() + ")");

            List<WebElement> elements = driver.findElements(elementLocator);

            if (elements.isEmpty()) {
                log.info("✅ ELEMENT YOK: " + elementName + " sayfada bulunamadı.");
                return true;
            } else {
                log.warn("⚠️ ELEMENT BULUNDU: " + elementName + " beklenmedik şekilde göründü!");
                captureScreenshot(driver, elementName + "_Unexpectedly_Found");
                return false;
            }
        } catch (Exception e) {
            log.error("⚠️ BEKLENMEYEN HATA: " + elementName + " kontrol edilirken hata oluştu!", e);
            captureScreenshot(driver, elementName + "_UnexpectedError");
            return false;
        }
    }


    public static boolean isElementClassEquals(WebDriver driver, String pageName, String locatorKey, String expectedClass) {
        try {
            By elementLocator = By.xpath(JsonReader.getLocator(pageName, locatorKey)); // JSON'dan XPath al
            WebElement element = driver.findElement(elementLocator);
            String classAttribute = element.getAttribute("class").trim(); // Fazladan boşlukları temizle

            log.info("🔍 Elementin class değeri kontrol ediliyor: '{}'", classAttribute);

            if (classAttribute.equals(expectedClass)) {
                log.info("✅ Elementin class değeri tam olarak '{}' eşleşiyor.", expectedClass);
                captureScreenshot(driver, pageName);
                return true;
            } else {
                log.warn("⚠️ Elementin class değeri beklenen '{}' ile eşleşmiyor! Gerçek değer: '{}'", expectedClass, classAttribute);
                captureScreenshot(driver, "ClassCheck_Failed");
                return false;
            }
        } catch (NoSuchElementException e) {
            log.error("❌ Element bulunamadı: {} - {}", locatorKey, e.getMessage());
            captureScreenshot(driver, "ElementNotFound");
            return false;
        }
    }





    public static void addFullPageScreenshotToAllure(WebDriver driver, String screenshotName) {
        try {
            Screenshot screenshot = new AShot()
                    .shootingStrategy(ShootingStrategies.viewportPasting(100)) // Sayfayı aşağı kaydırarak tam ekran SS alır
                    .takeScreenshot(driver);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(screenshot.getImage(), "PNG", baos);
            baos.flush();
            byte[] screenshotBytes = baos.toByteArray();
            baos.close();

            // Allure raporuna ekleme
            Allure.addAttachment(screenshotName, new ByteArrayInputStream(screenshotBytes));

            log("[INFO] Tam sayfa ekran görüntüsü alındı: " + screenshotName);
        } catch (IOException e) {
            log("[ERROR] Tam sayfa ekran görüntüsü alınırken hata oluştu: " + e.getMessage());
        }
    }

    public static void captureScreenshot(WebDriver driver, String screenshotName) {
        Allure.addAttachment(screenshotName, new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
    }
    public static void scrollDown(WebDriver driver, int pixels) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0," + pixels + ");");
    }

    public static boolean isElementPresent(WebDriver driver, By element) {
        try {
            log.info("🔍 Element kontrol ediliyor: {}", element);
            boolean isPresent = driver.findElements(element).size() > 0;
            if (isPresent) {
                log.info("✅ Element bulundu: {}", element);
            } else {
                log.warn("⚠️ Element bulunamadı: {}", element);
                captureScreenshot(driver, "ElementNotFound_Error");
            }
            return isPresent;
        } catch (Exception e) {
            log.error("❌ Element kontrolü sırasında hata oluştu: {} - {}", element, e.getMessage());
            captureScreenshot(driver, "ElementCheck_Error");
            return false;
        }
    }

    public static void assertElementNotPresent(WebDriver driver, By element, String errorMessage) {
        log.info("🔍 Element kontrol ediliyor: " + element.toString());

        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5)); // 5 saniye bekle
            wait.until(ExpectedConditions.presenceOfElementLocated(element));

            log.error("❌ HATA! İstenmeyen element bulundu: " + element.toString());
            captureScreenshot(driver, "Element_Found_" + element.toString());
            Assert.fail(errorMessage);
        } catch (TimeoutException e) {
            log.info("✅ Kontrol başarılı: " + element.toString() + " elementi sayfada bulunamadı.");
        }
    }



}
