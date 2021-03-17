package app.exception;

import lombok.Getter;

/**
 *
 * @author vasil
 */
@Getter
public class DataNotFoundException extends RuntimeException {

    private final ExceptionData data = new ExceptionData();

    public DataNotFoundException(String message) {
        super(message);
        this.data.setErrorMessage(message);
        this.data.setErrorCode(404);
    }

}
