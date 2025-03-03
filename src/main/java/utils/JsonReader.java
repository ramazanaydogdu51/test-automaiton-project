package utils;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;

public class JsonReader {
    private static JSONObject locatorsJson;
    private static JSONObject configJson;

    // JSON dosyalarını yükleme
    static {
        try {
            JSONParser parser = new JSONParser();

            // locators.json dosyasını oku
            FileReader locatorsReader = new FileReader("src/test/resources/locators.json");
            locatorsJson = (JSONObject) parser.parse(locatorsReader);

            // config.json dosyasını oku
            FileReader configReader = new FileReader("src/test/resources/config.json");
            configJson = (JSONObject) parser.parse(configReader);

        } catch (Exception e) {
            throw new RuntimeException("❌ JSON dosyaları okunamadı!", e);
        }
    }

    // JSON içinden belirli bir sayfa ve locator'ı al
    public static String getLocator(String page, String element) {
        try {
            JSONObject pageObject = (JSONObject) locatorsJson.get(page);
            return (String) pageObject.get(element);
        } catch (Exception e) {
            return null;
        }
    }

    // JSON içinden URL al
    public static String getUrl(String key) {
        return (String) configJson.get(key);
    }
}
