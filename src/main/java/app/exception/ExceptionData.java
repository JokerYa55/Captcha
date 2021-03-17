package app.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 *
 * @author vasil
 */
@Data
public class ExceptionData {

    @JsonProperty(value = "error_code")
    int errorCode;
    @JsonProperty(value = "error_message")
    String errorMessage;
}
