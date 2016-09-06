package is.ru.honn.P3_Videos;

import is.ru.honn.P3_Videos.Entities.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Hönnun og Smíði Hugbúnaðar - Assignment 1, Part 3:
 * The class VideoParser (VideoParser.java)
 *
 * @author Kristinn Heiðar Freysteinsson
 * @version 1, 06.09.16
 */
public class VideoParser {

    /**
     * Takes in a String, parses it and extracts the info we need
     * and then prints it all out.
     *
     * @param s Json string to be parsed and printed
     */
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

    /**
     * Takes in a String, parses it and extracts the info we need.
     * Then creates a Catalog item, fills it with all the info.
     *
     * @param s Json string to be parsed and printed
     * @return a catalog and all of it's items
     */
    public Catalog parseVideo(String s) {
        JSONObject jsonObject = (JSONObject) JSONValue.parse(s);

        String status = jsonObject.get("status").toString();
        int recordCount = Integer.parseInt(jsonObject.get("recordCount").toString());
        String catalog_name = jsonObject.get("catalog_name").toString();
        Catalog cTmp = new Catalog(status, recordCount, catalog_name);

        JSONArray catalog = (JSONArray) jsonObject.get("catalog");
        ArrayList<CatalogItem> cItems = new ArrayList<CatalogItem>();

        Iterator<JSONObject> iterator = catalog.iterator();
        while(iterator.hasNext()) {
            JSONObject jTmp = (JSONObject) iterator.next();
            String title = jTmp.get("title").toString();
            String type = jTmp.get("type").toString();
            String author_name = jTmp.get("author_name").toString();
            CatalogItem item = new CatalogItem(title, type, author_name);
            cItems.add(item);
        }
        cTmp.setCatalog(cItems);

        return cTmp;
    }
}
