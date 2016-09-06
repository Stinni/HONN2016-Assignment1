package is.ru.honn.P3_Videos.Exceptions;

import com.sun.org.apache.regexp.internal.RE;

/**
 * Hönnun og Smíði Hugbúnaðar - Assignment 1, Part 3:
 * The class RequestException (RequestException.java)
 * Added to be able to throw a RequestException
 *
 * @author Kristinn Heiðar Freysteinsson
 * @version 1, 06.09.16
 */
public class RequestException extends Throwable {

    private String message;

    public RequestException(String msg) {
        message = msg;
    }

    public String getTheMessage() {
        return message;
    }
}
