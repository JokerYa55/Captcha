package app.bean;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author vasil
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CaptchaItem {

    String id = UUID.randomUUID().toString();
    String code;
    byte[] image;
}
