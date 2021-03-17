package app.service;

import java.util.Random;
import org.springframework.stereotype.Service;

/**
 *
 * @author vasil
 */
@Service
public class GeneratorService {

    public String genCode(int length) {
        Random random = new Random();
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        return random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}
