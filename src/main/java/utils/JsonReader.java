package utils;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;

public class JsonReader {
    private static JSONObject jsonObject;

    // JSON dosyasını yükleme
    static {
        try {
            JSONParser parser = new JSONParser();
            FileReader reader = new FileReader("src/test/resources/locators.json");  // JSON dosyasını oku
            jsonObject = (JSONObject) parser.parse(reader);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // JSON içinden belirli bir sayfa ve locator'ı al
    public static String getLocator(String page, String element) {
        try {
            JSONObject pageObject = (JSONObject) jsonObject.get(page);
            return (String) pageObject.get(element);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
