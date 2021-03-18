package app.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 *
 * @author vasil
 */
@Data
@AllArgsConstructor
public class GenCaptchaResult {

    @JsonProperty("captcha_id")
    String captchaId;
}
