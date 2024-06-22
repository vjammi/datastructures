package io.dev.v2.dev.caching.caffeine;

import org.springframework.cache.interceptor.KeyGenerator;
import java.lang.reflect.Method;

public class SurnameKeyGenerator implements KeyGenerator {

    public Object generate(Object target, Method method, Object... params) {
        return params[1];
    }
}