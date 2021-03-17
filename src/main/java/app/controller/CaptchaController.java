package app.controller;

import app.service.CaptchaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
    public byte[] getCaptcha(@PathVariable("id") String captchaId) {
        return captchaService.getCaptcha(captchaId).getImage();
    }
}
