package by.vit.myblog.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * This exception used to send unauthorized http status code to client.
 *
 * @author Vitaly Lobatsevich (vitaly.lobatsevich@gmail.com)
 */
@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UnauthorizedException extends RuntimeException {

    /**
     * Constructor.
     */
    public UnauthorizedException() {
        super();
    }

    /**
     * Constructor.
     * @param errorMessage - error message.
     */
    public UnauthorizedException(final String errorMessage) {
        super(errorMessage);
    }

}
