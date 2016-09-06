package is.ru.honn.P3_Videos.Entities;

/**
 * Hönnun og Smíði Hugbúnaðar - Assignment 1, Part 3:
 * The class CatalogItem (CatalogItem.java)
 * Used to keep track of each item in the incoming
 * Catalog info
 *
 * @author Kristinn Heiðar Freysteinsson
 * @version 1, 06.09.16
 */
public class CatalogItem {

    private String title;
    private String type;
    private String author_name;

    public CatalogItem(String title, String type, String author_name) {
        this.title = title;
        this.type = type;
        this.author_name = author_name;
    }

    /**
     * Decided it'd be nice to see what I was doing and if it was working.
     * So I wrote this and I'll leave it here. It's not pretty at all but
     * yaywoo it works! :D
     *
     * @return The CatalogItem as a string
     */
    @Override
    public String toString() {
        return title + "\t\t\t\t" + type + "\t\t" + author_name + "\n";
    }
}
