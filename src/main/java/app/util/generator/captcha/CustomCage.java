package app.util.generator.captcha;

import com.github.cage.Cage;
import lombok.extern.slf4j.Slf4j;
import nl.captcha.backgrounds.TransparentBackgroundProducer;
import nl.captcha.noise.CurvedLineNoiseProducer;
import nl.captcha.text.renderer.DefaultWordRenderer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

/**
 * Реализация генератора зачеркнутой черно-белой капчи
 */
@Slf4j
public class CustomCage extends Cage {
    private static final List<Font> fonts = new LinkedList<>();

    static {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Font[] allFonts = ge.getAllFonts();
        for (Font font : allFonts) {
            fonts.add(new Font(font.getFontName(Locale.US), Font.BOLD, 36));
        }
    }

    @Override
    public byte[] draw(String text) {
        List<Color> colors = new LinkedList<>();
        Color myColor = new Color(0, 0, 0);
        colors.add(myColor);

        nl.captcha.Captcha captcha = new nl.captcha.Captcha.Builder(200, 50)
                .addText(() -> text, new DefaultWordRenderer(colors, fonts))
                .addBackground(new TransparentBackgroundProducer())
                .addNoise(new CurvedLineNoiseProducer())
                .gimp()
                .build();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(captcha.getImage(), "png", baos);
            baos.flush();
        } catch (IOException ex) {
            log.info(ex.getMessage());
        }
        return baos.toByteArray();
    }
}