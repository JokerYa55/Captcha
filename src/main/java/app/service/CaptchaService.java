package app.service;

import app.bean.CaptchaItem;
import app.util.generator.captcha.CaptchaGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 *
 * @author vasil
 */
@Service
public class CaptchaService {

    @Autowired
    CacheManager cacheManager;

    public CaptchaItem genCaptcha() {
        CaptchaItem result = new CaptchaItem();
        CaptchaGenerator generator = new CaptchaGenerator(3);
        result.setCode("1000");
        result.setImage(generator.createCaptcha(result.getCode()));
        Cache cache = cacheManager.getCache("captcha");
        cache.put(result.getId(), result);
        return result;
    }

    public CaptchaItem getCaptcha(String id) {
        Cache cache = cacheManager.getCache("captcha");
        return cache.get(id, CaptchaItem.class);
    }
}
