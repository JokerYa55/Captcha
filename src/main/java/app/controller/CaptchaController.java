package app.controller;

import app.service.CaptchaService;
import app.service.GeneratorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author vasil
 */
@RestController
@RequestMapping(path = "/")
@Slf4j
public class CaptchaController {

    @Autowired
    CaptchaService captchaService;

    @PostMapping(path = "/captcha")
    public String genCaptcha() {
        return captchaService.genCaptcha().getId();
    }

    @GetMapping(path = "/captcha/{id}")
    public ResponseEntity<byte[]> getCaptcha(@PathVariable("id") String captchaId) {
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(captchaService.getCaptcha(captchaId).getImage());
    }
}
