package app.service;

import app.bean.CaptchaItem;
import app.exception.DataNotFoundException;
import app.util.generator.captcha.CaptchaGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

/**
 *
 * @author vasil
 */
@Service
public class CaptchaService {

    @Autowired
    CacheManager cacheManager;

    @Autowired
    GeneratorService genService;

    public CaptchaItem genCaptcha() {
        CaptchaItem result = new CaptchaItem();
        CaptchaGenerator generator = new CaptchaGenerator(3);
        result.setCode(genService.genCode(8));
        result.setImage(generator.createCaptcha(result.getCode()));
        Cache cache = cacheManager.getCache("captcha");
        cache.put(result.getId(), result);
        return result;
    }

    public CaptchaItem getCaptcha(String id) {
        Cache cache = cacheManager.getCache("captcha");
        CaptchaItem result = cache.get(id, CaptchaItem.class);
        if (result == null) {
            throw new DataNotFoundException("Данные не найдены.");
        }
        return result;
    }
}
