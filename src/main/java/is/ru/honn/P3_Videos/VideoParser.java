package is.ru.honn.P3_Videos;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.util.Iterator;

/**
 * Hönnun og Smíði Hugbúnaðar - Assignment 1, Part 3:
 * The class VideoParser (VideoParser.java)
 *
 * @author Kristinn Heiðar Freysteinsson
 * @version 1, 06.09.16
 */
public class VideoParser {

    public void parseAndPrint(String s) {
        JSONObject jsonObject = (JSONObject) JSONValue.parse(s);

        String status = jsonObject.get("status").toString();
        int recordCount = Integer.parseInt(jsonObject.get("recordCount").toString());
        String catalog_name = jsonObject.get("catalog_name").toString();
        System.out.println(status);
        System.out.println(recordCount);
        System.out.println(catalog_name);

        JSONArray catalog = (JSONArray) jsonObject.get("catalog");

        Iterator<JSONObject> iterator = catalog.iterator();
        while(iterator.hasNext()) {
            JSONObject jTmp = (JSONObject) iterator.next();
            String title = jTmp.get("title").toString();
            String type = jTmp.get("type").toString();
            String author_name = jTmp.get("author_name").toString();
            System.out.println(title + ", " + type + ", " + author_name);
        }
    }
}
