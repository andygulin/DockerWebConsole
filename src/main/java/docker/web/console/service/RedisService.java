package docker.web.console.service;

import java.util.Set;

public interface RedisService {

    void set(final String key, final String value);

    void setEx(final String key, final String value, final long expire);

    String get(final String key);

    long del(final String key);

    boolean exist(String key);

    Set<String> keys(final String pattern);
}
