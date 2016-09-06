package is.ru.honn.P3_Videos;

import is.ru.honn.P3_Videos.Exceptions.RequestException;

/**
 * Hönnun og Smíði Hugbúnaðar - Assignment 1, Part 3:
 * The class TestParseFile (TestParseFile.java)
 * This class's only function is to instantiate the ClientRequest
 * and VideoParser classes, get a string from a file and
 * pass it on to be parsed and printed.
 *
 * @author Kristinn Heiðar Freysteinsson
 * @version 1, 06.09.16
 */
public class TestParseFile {

    public static void main(String[] args) {
        try {
            ClientRequest clientReq = new ClientRequest();
            VideoParser vParser = new VideoParser();
            String content = clientReq.getFileContent("videos.json");
            vParser.parseAndPrint(content);
        } catch (RequestException e) {
            System.out.println(e.getTheMessage());
        }
    }
}
