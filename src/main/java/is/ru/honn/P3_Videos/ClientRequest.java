package is.ru.honn.P3_Videos;

import is.ru.honn.P3_Videos.Exceptions.RequestException;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Hönnun og Smíði Hugbúnaðar - Assignment 1, Part 3:
 * The class ClientRequest (ClientRequest.java)
 *
 * @author Kristinn Heiðar Freysteinsson
 * @version 1, 06.09.16
 */
public class ClientRequest {

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

    public String getRequest(String url) {
        Client client = ClientBuilder.newClient();
        return "";
    }
}
