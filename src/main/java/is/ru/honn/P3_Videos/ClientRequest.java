package is.ru.honn.P3_Videos;

import is.ru.honn.P3_Videos.Exceptions.RequestException;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Hönnun og Smíði Hugbúnaðar - Assignment 1, Part 3:
 * The class ClientRequest (ClientRequest.java)
 * Retrieves data in json form from either a file or url
 *
 * @author Kristinn Heiðar Freysteinsson
 * @version 1, 06.09.16
 */
public class ClientRequest {

    /**
     * Retrieves info in json form from a file and returns
     * a string that then needs to be processed further
     * to retrieve the info.
     *
     * @param fileName the file path and name
     * @return a Json string to be processed
     * @throws RequestException exception thrown with error message
     */
    public String getFileContent(String fileName) throws RequestException {
        JSONParser parser = new JSONParser();
        String tmp = null;
        try {
            Object obj = parser.parse(new FileReader(fileName));
            JSONObject jObj = (JSONObject) obj;
            tmp = jObj.toString();
        } catch (FileNotFoundException e) {
            throw new RequestException("No file found: " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return tmp;
    }

    /**
     * Retrieves info in json form from url/web location
     * and returns a string that then needs to be processed
     * further to retrieve the info.
     *
     * @param url the url/web location to be requested
     * @return a Json string to be processed
     */
    public String getRequest(String url) {
        Client client = ClientBuilder.newBuilder().newClient();
        WebTarget target = client.target(url);
        Invocation.Builder builder = target.request(MediaType.APPLICATION_JSON_TYPE);

        String tmp = builder.get(String.class);
        if(tmp == null) {
            System.out.println("No data was received from: " + url);
        }

        return tmp;
    }
}
