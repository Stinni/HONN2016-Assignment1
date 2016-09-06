package is.ru.honn.P3_Videos.Entities;

import java.util.ArrayList;

/**
 * Hönnun og Smíði Hugbúnaðar - Assignment 1, Part 3:
 * The class Catalog (Catalog.java)
 * Keeps track of all Catalog items in an ArrayList
 *
 * @author Kristinn Heiðar Freysteinsson
 * @version 1, 06.09.16
 */
public class Catalog {

    private String status;
    private int recordCount;
    private String catalogName;
    private ArrayList<CatalogItem> catalog;

    public Catalog(String status, int recordCount, String catalogName) {
        this.status = status;
        this.recordCount = recordCount;
        this.catalogName = catalogName;
        catalog = null;
    }

    public void setCatalog(ArrayList<CatalogItem> catalog) {
        this.catalog = catalog;
    }

    public ArrayList<CatalogItem> getCatalog() {
        return catalog;
    }

    /**
     * Decided it'd be nice to see what I was doing and if it was working.
     * So I wrote this and I'll leave it here. It's not pretty at all but
     * yaywoo it works! :D
     *
     * @return The Catalog as a string
     */
    @Override
    public String toString() {
        String s = "The Catalog: " + catalogName + "\n";
        s += "There're not items in this catalog...";
        if(catalog != null) {
            s = "Catalog: " + catalogName + "\n\n";
            s += "Title\t\t\t\tType\t\t\tAuthor\n";
            s += "-------------------------------------------------------------\n";
            for(CatalogItem c : catalog) {
                CatalogItem item = (CatalogItem) c;
                s += c.toString();
            }
        }
        return s;
    }
}
