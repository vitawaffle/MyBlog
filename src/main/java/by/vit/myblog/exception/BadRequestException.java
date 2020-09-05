package by.vit.myblog.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * This exception used to send bad request http status code to client.
 *
 * @author Vitaly Lobatsevich (vitaly.lobatsevich@gmail.com)
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {

    /** Constructor.*/
    public BadRequestException() {
        super();
    }

    /**
     * Constructor.
     *
     * @param errorMessage - error message.
     */
    public BadRequestException(final String errorMessage) {
        super(errorMessage);
    }

}
