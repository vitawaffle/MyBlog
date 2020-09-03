package by.vit.myblog.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * This exception used to send forbidden http status code to client.
 *
 * @author Vitaly Lobatsevich (vitaly.lobatsevich@gmail.com)
 */
@ResponseStatus(HttpStatus.FORBIDDEN)
public class ForbiddenException extends RuntimeException {

    /**
     * Constructor.
     */
    public ForbiddenException() {
        super();
    }

    /**
     * Constructor.
     *
     * @param errorMessage - error message.
     */
    public ForbiddenException(final String errorMessage) {
        super(errorMessage);
    }

}
