package is.ru.honn.P3_Videos;

/**
 * Hönnun og Smíði Hugbúnaðar - Assignment 1, Part 3:
 * The class TestParseUrl (TestParseUrl.java)
 * This class's only function is to instantiate the ClientRequest
 * and VideoParser classes, get a string from url/web and
 * pass it on to be parsed and printed.
 *
 * @author Kristinn Heiðar Freysteinsson
 * @version 1, 06.09.16
 */
public class TestParseUrl {

    public static void main(String[] args) {
        ClientRequest clientReq = new ClientRequest();
        VideoParser vParser = new VideoParser();
        String content = clientReq.getRequest(
                "https://www.mockaroo.com/e97aedd0/download?count=1&key=e79a3650");
        vParser.parseAndPrint(content);
    }
}
