package app.util.generator.captcha;

import com.github.cage.Cage;
import com.github.cage.GCage;
import com.github.cage.YCage;
import lombok.Data;

/**
 * Генератор капчи
 */
@Data
public class CaptchaGenerator {

    private Cage cage;

    public CaptchaGenerator(int i) {
        switch (i) {
            case 2:
                cage = new GCage();
                break;
            case 3:
                cage = new CustomCage();
                break;
            case 4:
                cage = new DarkCustomCage();
                break;
            default:
                cage = new YCage();
                break;
        }
    }

    /**
     * Создание капчи
     * @param captchaText - текст, который будет помещен в капчу
     * @return - изображение с капчей, представленное массивом байтов
     */
    public byte[] createCaptcha(String captchaText) {
        return cage.draw(captchaText);
    }
}
